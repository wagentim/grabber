package de.wagentim.grabber.core;

public interface ITask
{
	public String getTaskLink();
	public void setTaskLink(String taskLink);
	public IParser getParser();
	public void setParser(IParser parser);
	public void run();
}
