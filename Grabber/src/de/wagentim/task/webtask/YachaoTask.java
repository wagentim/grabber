package de.wagentim.task.webtask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.wagentim.grabber.core.AbstractTask;
import de.wagentim.grabber.db.IDBController;
import de.wagentim.grabber.db.PriceHistory;
import de.wagentim.grabber.db.Product;

public class YachaoTask extends AbstractTask
{
	private final Logger logger = LoggerFactory.getLogger(YachaoTask.class);

	public YachaoTask(final IDBController controller)
	{
		super(controller);
	}

	public void run()
	{
		logger.info("Start to grabbing site: " + getDBTableName());
		
		loadExistedData();
		int count = 0;

		// Step 1. Get the main Homepage and Category links

		try
		{
			doc = Jsoup.connect(getStartLink()).get();
			Elements elements = doc.select(".subitem dd a");	

			Iterator<Element> it = elements.iterator();
			List<String> cates = new ArrayList<String>();

			while (it.hasNext())
			{
				Element e = it.next();
				String fullLink = getStartLink() + e.attr("href");	// get category list
				cates.add(fullLink);
			}

			// Step 2. Get Detail Product

			for (String link : cates)
			{
				doc = Jsoup.connect(link).get();
				elements = doc.select(".goodsbox");

				it = elements.iterator();
				while (it.hasNext())
				{
					Element e = it.next();
					Elements links = e.select("a");
					Iterator<Element> itr = links.iterator();

					while (itr.hasNext())
					{
						Element ele = itr.next();

						if (ele.hasAttr("title"))
						{
							link = getStartLink() + ele.attr("href");
							int id = getProductID(link);

							Product product = oldProducts.get(id);	// check if this product is already existed

							if (product == null)
							{
								product = new Product();
								product.setDbName(getDBTableName());
								product.setId(id);
								logger.info("\nAdd Product: " + id + " -> " + ele.attr("title"));
								count = 0;
							}
							
							product.setChanged(false);
							
							PriceHistory ph = new PriceHistory();
							ph.setId(id);
							ph.setTime(System.currentTimeMillis());

							product.setLink(link);
							product.setName(ele.attr("title"));
							ph.setLink(link);

							Element marketPrice = e.select(".market").first();
							String s = marketPrice.text();
							product.setMarketPrice(String.valueOf(handlePrice(s)));

							Element shopPrice = e.select(".fpink").first();
							s = shopPrice.text();
							product.setCurrentPrice(String.valueOf(handlePrice(s)));
							ph.setPrice(product.getCurrentPrice());

							product.setSiteShort(getDBTableName());
							ph.setSite(getDBTableName());

							addPriceHistroy(product, ph);

							if (product.isChanged())
							{
								product.setChanged(false);
								addUpdatedProduct(product);
							}
							else
							{
								System.out.print(".");
								count++;
								
								if(count == 50)
								{
									count = 0;
									System.out.println();
								}
							}
						}
					}
				}
			}
			printUpdatedProducts();
			updateChangedProducts(updatedProds);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public String getDBTableName()
	{
		return "yachao";
	}

	public String getStartLink()
	{
		return "https://www.yachao.de/";
	}
}
