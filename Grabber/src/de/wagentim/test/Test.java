package de.wagentim.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test
{
	public static void main(String[] args)
	{
		String input = "https://www.yachao.de/category.php?id=212";
		
		Pattern p = Pattern.compile("(.*)(id=)(\\d*)");
		Matcher matcher = p.matcher(input);
		
		while(matcher.find())
		{
			System.out.println(matcher.group(3));
		}
		
//		System.out.println("NULL");
		
//		DefaultWorker dw = new DefaultWorker("Thread 1");
//		
//		Thread t = new Thread(dw);
//		t.start();
//		try
//		{
//			t.sleep(1000);
//		}
//		catch (InterruptedException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		dw.addTask(new DefaultTask());
//		
//		try
//		{
//			t.sleep(1000);
//		}
//		catch (InterruptedException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		dw.stop();
	}
}
