package de.wagentim.task.webtask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.wagentim.common.IConstants;
import de.wagentim.grabber.db.PriceHistory;
import de.wagentim.grabber.db.Product;
import de.wagentim.grabber.db.SqliteDBController;
import de.wagentim.utils.PriceHistoryComparator;
import de.wagentim.utils.Utils;

public class YachaoOnlineTask
{
	private Document doc;
	private String file = Utils.getAbsolutePath("test.html");
	private StringBuilder sb = new StringBuilder();
	private final PriceHistoryComparator comparator = new PriceHistoryComparator();
	private Map<Integer, Product> oldProducts = Collections.emptyMap();
	private List<Product> updatedProds = new ArrayList<Product>();
	private final SqliteDBController controller;
	private final static Logger logger = LoggerFactory.getLogger(YachaoOnlineTask.class);

	public YachaoOnlineTask()
	{
		controller = new SqliteDBController();
	}

	public void loadData()
	{
		oldProducts = controller.getAllProducts(getDBTableName());
	}
	
	public void printDBID()
	{
		loadData();
		
		Set<Integer> ids = oldProducts.keySet();
		
		System.out.println(ids.size());
		
		for(int id : ids)
		{
			System.out.println(id);
		}
	}

	public void run()
	{
		loadData();
		int count = 0;

		// Step 1. Get the main Homepage and Category links

		try
		{
			doc = Jsoup.connect(getStartLink()).get();
//
//			FileWriter fw = new FileWriter(new File(file));
//			fw.write(doc.html());
//			fw.flush();

//			doc = Jsoup.parse(new File(file), "utf-8");
			Elements elements = doc.select(".shadow_border a");

			Iterator<Element> it = elements.iterator();
			List<String> cates = new ArrayList<String>();

			while (it.hasNext())
			{
				Element e = it.next();
				String fullLink = getLinkPrefix() + e.attr("href");
//				System.out.println(fullLink);
				cates.add(fullLink);
			}
			
			// Step 2. Get Detail Product
//
			for (String link : cates)
			{
				doc = Jsoup.connect(link).get();
//
//				FileWriter fw = new FileWriter(new File(file));
//				fw.write(doc.html());
//				fw.flush();

//				doc = Jsoup.parse(new File(file), "utf-8");
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

							if (product == null)
							{
								product = new Product();
								product.setDbName(getDBTableName());
								product.setId(id);
								System.out.println("Add Product: " + id);
							}

							PriceHistory ph = new PriceHistory();
							ph.setId(id);
							ph.setTime(System.currentTimeMillis());

							product.setLink(link);
							product.setName(ele.attr("title"));
							ph.setLink(link);

//						System.out.println(product.getName());
//						System.out.println(product.getLink());

//							Element marketPrice = e.select(".market").first();
//							String s = marketPrice.text();
//							product.setMarketPrice(String.valueOf(handlePrice(s)));
//						System.out.println(product.getMarketPrice());

							Element shopPrice = e.select(".shop_s").first();
							String s = shopPrice.text();
							product.setCurrentPrice(String.valueOf(handlePrice(s)));
							ph.setPrice(product.getCurrentPrice());
//						System.out.println(product.getCurrentPrice());

							product.setSiteShort(getDBTableName());
							ph.setSite(getDBTableName());

							addPriceHistroy(product, ph);

							if (product.isChanged())
							{
								System.out.println("Update Product: " + product.getId());
								product.setChanged(false);
								updatedProds.add(product);
							}
							else
							{
								System.out.print(".");
								count++;
								
								if(count == 20)
								{
									count = 0;
									System.out.println();
								}
							}
						}
					}
				}

			}
			updateChangedProducts(updatedProds);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		YachaoOnlineTask task = new YachaoOnlineTask();
		task.run();
		
//		task.printDBID();
	}

	protected String getDBTableName()
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

	public String getSiteShort()
	{
		return getDBTableName();
	}

	private String handlePrice(String input)
	{
		Pattern p = Pattern.compile("(.*)(\\d\\d.\\d+)");
		Matcher matcher = p.matcher(input);

		while (matcher.find())
		{
			return matcher.group(2);
		}

		p = Pattern.compile("(.*)(\\d.\\d+)");
		matcher = p.matcher(input);

		while (matcher.find())
		{
			return matcher.group(2);
		}
		
		return IConstants.EMPTY_STRING;
	}

	private void addPriceHistroy(Product product, PriceHistory newPrice)
	{
		List<PriceHistory> priceHistory = product.getHistory();

		if (null == priceHistory)
		{
			priceHistory = new ArrayList<PriceHistory>();
			product.setHistory(priceHistory);
		}

		if (priceHistory.isEmpty())
		{
			priceHistory.add(newPrice);
			product.setChanged(true);
			return;
		}

		if (newPrice == null)
		{
			return;
		}

		Collections.sort(priceHistory, comparator);

		PriceHistory latest = priceHistory.get(0);

		long latestTime = latest.getTime();
		long currTime = newPrice.getTime();

		double latestPrice = Double.valueOf(latest.getPrice());
		double currtPrice = Double.valueOf(newPrice.getPrice());

		if (currTime > latestTime && latestPrice != currtPrice)
		{
			priceHistory.add(newPrice);
			product.setChanged(true);
		}

	}

	private int getProductID(String input)
	{
		if (null == input || input.isEmpty())
		{
			return -1;
		}

		Pattern p = Pattern.compile("(.*)(id=)(\\d*)");
		Matcher matcher = p.matcher(input);

		while (matcher.find())
		{
			return Integer.valueOf(matcher.group(3));
		}

		return -1;
	}

	public void updateChangedProducts(List<Product> prodList)
	{
		for(Product p : prodList)
		{
			int id = p.getId();
			
			Product oldP = oldProducts.get(id);
			
			if(oldP != null)
			{
				controller.updateProduct(p.getDbName(), id, p);
			}
			else
			{
				controller.insertNewProduct(p.getDbName(), p.getId(), p);
			}
		}
	}
}
