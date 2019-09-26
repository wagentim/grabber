package de.wagentim.test;

import de.wagentim.grabber.core.DefaultTask;
import de.wagentim.grabber.core.DefaultWorker;

public class Test
{
	public static void main(String[] args)
	{
		DefaultWorker dw = new DefaultWorker("Thread 1");
		
		Thread t = new Thread(dw);
		t.start();
		try
		{
			t.sleep(1000);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dw.addTask(new DefaultTask());
		
		try
		{
			t.sleep(1000);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dw.stop();
	}
}
