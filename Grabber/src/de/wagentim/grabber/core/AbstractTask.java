package de.wagentim.grabber.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import de.wagentim.common.IConstants;
import de.wagentim.grabber.db.IDBController;
import de.wagentim.grabber.db.PriceHistory;
import de.wagentim.grabber.db.Product;
import de.wagentim.utils.PriceHistoryComparator;
import de.wagentim.utils.Utils;

public abstract class AbstractTask implements ITask
{
	protected final String file = Utils.getAbsolutePath("test.html");
	protected final PriceHistoryComparator comparator;
	protected final IDBController controller;
	protected final Map<Integer, Product> updatedProds;

	protected Document doc;
	protected int prodCounter = 0;
	protected Map<Integer, Product> oldProducts;
	
	public void run()
	{
		
	}
	
	public void loadExistedData()
	{
		oldProducts = controller.getAllProducts(getDBTableName());
	}
	
	public AbstractTask(final IDBController controller)
	{
		this.controller = controller;
		comparator = new PriceHistoryComparator();
		updatedProds = new HashMap<Integer, Product>();
	}
	
	protected void writeToFile(final String file, final Document doc)
	{
		FileWriter fw;
		try
		{
			fw = new FileWriter(new File(file));
			fw.write(doc.html());
			fw.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	protected Document readFromFile(final String file)
	{
		try
		{
			return Jsoup.parse(new File(file), "utf-8");
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public void printDBID()
	{
		loadExistedData();
		
		Set<Integer> ids = oldProducts.keySet();
		
		System.out.println(ids.size());
		
		for(int id : ids)
		{
			System.out.println(id);
		}
	}
	
	protected String handlePrice(String input)
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
	
	protected int getProductID(String input)
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
	
	protected void addPriceHistroy(Product product, PriceHistory newPrice)
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
	
	protected void updateChangedProducts(Map<Integer, Product> prodList)
	{
		
		for(Product p : prodList.values())
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
			
			try
			{
				Thread.sleep(500);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	protected void printUpdatedProducts()
	{
		for(Product p : updatedProds.values())
		{
			String name = p.getName();
			
			if(null == name || name.isEmpty())
			{
				System.out.println(p.getLink() + " ||| ");
			}
			
			System.out.println(p.getId() + " ||| " + p.getName());
		}
	}
	
	protected void addUpdatedProduct(Product prod)
	{
		int id = prod.getId();
		
		if(!updatedProds.containsKey(id))
		{
			updatedProds.put(id, prod);
		}
	}
}
