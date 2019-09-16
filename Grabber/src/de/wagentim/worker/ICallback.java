package de.wagentim.worker;

public interface ICallback
{
	void taskFinished();
	Object getResult();
}
