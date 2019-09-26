package de.wagentim.grabber.core;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.wagentim.common.IConstants;

public abstract class AbstractWorker implements IWorker, Runnable
{
	protected Queue<ITask> taskList = new ConcurrentLinkedQueue<ITask>();
	private volatile boolean stop = false;
	private String workerName = IConstants.EMPTY_STRING;
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	private volatile Object locker = new Object();
	
	public AbstractWorker(String name)
	{
		this.workerName = name;
		logger = LoggerFactory.getLogger(name);
	}

	public void run()
	{
		synchronized (locker)
		{
			while(!stop)
			{
				if( taskList.isEmpty() )
				{
					try
					{
						logger.info("Go to wait...");
						locker.wait();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				else
				{
					logger.info("Wake Up and Work");
					ITask task = taskList.poll();
					task.run();
				}
			}
		}
	}
	
	public void stop()
	{
		this.stop = true;
		
		synchronized (locker)
		{
			locker.notify();
		}
	}
	
	public boolean addTask(final ITask task)
	{
		if( task == null )
		{
			return false;
		}
		
		taskList.add(task);
		synchronized (locker)
		{
			locker.notify();
		}
		return true;
	}
	
	public String getWorkerName()
	{
		return workerName;
	}
}