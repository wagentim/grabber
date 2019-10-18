package de.wagentim.task.webtask;

import java.util.ArrayList;
import java.util.List;

import de.wagentim.grabber.core.AbstractTask;
import de.wagentim.grabber.core.ITask;
import de.wagentim.grabber.db.IDBController;
import de.wagentim.grabber.db.SqliteDBController;

public class Starter
{
	private final IDBController controller;
	private final List<AbstractTask> tasks;
	
	public Starter()
	{
		controller = new SqliteDBController();
		
		tasks = new ArrayList<AbstractTask>();
		tasks.add(new YachaoTask(controller));
		tasks.add(new YachaoOnlineTask(controller));
	}
	
	public void start()
	{
		controller.startDBUpdate();
		
		for(ITask task : tasks)
		{
			task.run();
		}
	}
	
	public static void main(String[] args)
	{
		new Starter().start();
	}
}
