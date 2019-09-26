package de.wagentim.grabber.core;

import de.wagentim.common.IConstants;

public abstract class AbstractTask implements ITask
{
	protected String taskLink = IConstants.EMPTY_STRING;
	protected IParser parser = new NullParser();
	
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
	
	@Override
	public IParser getParser()
	{
		return parser;
	}
	
	@Override
	public void setParser(IParser parser)
	{
		this.parser = parser;
	}
	
	protected abstract String getDBTableName();
	
}
