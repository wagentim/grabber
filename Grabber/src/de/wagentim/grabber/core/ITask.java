package de.wagentim.grabber.core;

public interface ITask
{
	String getTaskLink();
	void setTaskLink(String taskLink);
	void run();
	int getSteps();
}
