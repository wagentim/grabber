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

public class YachaoOnlineTask extends AbstractTask
{
	private final Logger logger = LoggerFactory.getLogger(YachaoOnlineTask.class);

	public YachaoOnlineTask(final IDBController controller)
	{
		super(controller);
	}

	public void run()
	{
		loadExistedData();
		int count = 0;

		// Step 1. Get the main Homepage and Category links

		try
		{
			doc = Jsoup.connect(getStartLink()).get();
			Elements elements = doc.select(".shadow_border a");

			Iterator<Element> it = elements.iterator();
			List<String> cates = new ArrayList<String>();

			while (it.hasNext())
			{
				Element e = it.next();
				String fullLink = getLinkPrefix() + e.attr("href");
				cates.add(fullLink);
			}
			
			// Step 2. Get Detail Product
//
			for (String link : cates)
			{
				doc = Jsoup.connect(link).get();
				elements = doc.select(".goodsItem");

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

							link = getLinkPrefix() + ele.attr("href");
							int id = getProductID(link);

							Product product = oldProducts.get(id);
							
							String name = ele.attr("title");
							
							if(name == null || name.isEmpty())
							{
								name = ele.text();
								System.out.println("No Name");
							}

							if (product == null)
							{
								product = new Product();
								product.setDbName(getDBTableName());
								product.setId(id);
								logger.info("Add Product: " + id + " -> " + name);
							}
							
							product.setChanged(false);

							PriceHistory ph = new PriceHistory();
							ph.setId(id);
							ph.setTime(System.currentTimeMillis());

							product.setLink(link);
							product.setName(name);
							ph.setLink(link);

							Element shopPrice = e.select(".shop_s").first();
							String s = shopPrice.text();
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
		return "yachaoonline";
	}

	public String getStartLink()
	{
		return "https://www.yachaoonline.de/index.php";
	}
	
	public String getLinkPrefix()
	{
		return "https://www.yachaoonline.de/";
	}

}
