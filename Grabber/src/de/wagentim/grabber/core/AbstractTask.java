package de.wagentim.grabber.core;

import de.wagentim.common.IConstants;

public abstract class AbstractTask implements ITask
{
	protected String taskLink = IConstants.EMPTY_STRING;
	
	@Override
	public String getTaskLink()
	{
		return taskLink;
	}
	
	@Override
	public void setTaskLink(String taskLink)
	{
		this.taskLink = taskLink;
	}
	
	protected abstract String getDBTableName();
	
}
