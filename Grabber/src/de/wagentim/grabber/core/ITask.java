package de.wagentim.grabber.core;

public interface ITask
{
	void run();
	void loadExistedData();
	String getDBTableName();
	String getStartLink();
}
