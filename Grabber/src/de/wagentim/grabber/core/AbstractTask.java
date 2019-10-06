package de.wagentim.grabber.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import de.wagentim.grabber.db.Product;
import de.wagentim.utils.Utils;

public abstract class AbstractTask implements ITask
{
	
	protected String file = Utils.getAbsolutePath("test.html");
	protected Map<Integer, Product> oldProducts = Collections.emptyMap();
	protected int prodCounter = 0;
	
	protected abstract String getDBTableName();

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
		loadData();
		
		Set<Integer> ids = oldProducts.keySet();
		
		System.out.println(ids.size());
		
		for(int id : ids)
		{
			System.out.println(id);
		}
	}
	
	protected abstract void loadData();
	
}
