package de.wagentim.threads;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * A Wrapper for the Java Thread Pool Implementation
 * 
 * @author UIH9FE
 *
 */
public class ThreadPool
{
	private static final ThreadPool tp = new ThreadPool();
	private final ThreadPoolExecutor executor;
	
	public static ThreadPool INSTANCE()
	{
		return tp;
	}
	
	private ThreadPool()
	{
		executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
	}
	
	public boolean runThread(Runnable runnable)
	{
		boolean result = false;
		
		executor.submit(runnable, result);
		
		return result;
	}
}
