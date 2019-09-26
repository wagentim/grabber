package de.wagentim.task.webtask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import de.wagentim.grabber.core.AbstractTask;
import de.wagentim.utils.Utils;

public class YachaoTask extends AbstractTask
{
	private static final String START_LINK = "https://www.yachao.de/";
	private Document doc;
	private String file = Utils.getAbsolutePath("test.html");
	
	public YachaoTask()
	{
	}

	@Override
	public void run()
	{
		// Step 1. Get the main Homepage and Category links
		
		try
		{
//			doc = Jsoup.connect(START_LINK).get();
//			
//			FileWriter fw = new FileWriter(new File(file));
//			fw.write(doc.html());
//			fw.flush();
//			
//			doc = Jsoup.parse(new File(file), "utf-8");
//			Elements elements = doc.select(".subitem dd a");
//			
//			Iterator<Element> it = elements.iterator();
//			List<String> cates = new ArrayList<String>();
//			
//			while(it.hasNext())
//			{
//				Element e = it.next();
//				String fullLink = START_LINK + e.attr("href");
//				System.out.println(fullLink);
//				cates.add(fullLink);
//			}
			
		// Step 2. Get Detail Product
			
//			String link = "https://www.yachao.de/category.php?id=212";
//			doc = Jsoup.connect(link).get();
//			
//			FileWriter fw = new FileWriter(new File(file));
//			fw.write(doc.html());
//			fw.flush();
			
			doc = Jsoup.parse(new File(file), "utf-8");
			Elements elements = doc.select(".goodsbox");
			
			Iterator<Element> it = elements.iterator();
			List<String> cates = new ArrayList<String>();
			
			while(it.hasNext())
			{
				Element e = it.next();
				
				Elements links = e.select("a");
				
				Iterator<Element> itr = links.iterator();
				
				while(itr.hasNext())
				{
					Element ele = itr.next();
					
					if(ele.hasAttr("title"))
					{
						System.out.println(ele.attr("title"));
						System.out.println(START_LINK + ele.attr("href"));
					}
				}
				
			}
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		YachaoTask task = new YachaoTask();
		task.run();
	}

	@Override
	protected String getDBTableName()
	{
		return "yachao";
	}

}
