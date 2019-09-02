package de.wagentim.sites;

import de.wagentim.worker.IWorker;

public interface ISite
{
	public void setWorker(IWorker worker);
	public IWorker getWorker();
}
