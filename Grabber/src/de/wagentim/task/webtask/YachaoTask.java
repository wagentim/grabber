package de.wagentim.task.webtask;

import de.wagentim.worker.AbstractTask;
import de.wagentim.worker.WebPageDefaultDownloader;

public class YachaoTask extends AbstractTask
{
	private static final String START_LINK = "https://www.yachao.de/";
	private WebPageDefaultDownloader downloader = null;
	
	public YachaoTask()
	{
		downloader = new WebPageDefaultDownloader();
	}

	@Override
	public void run()
	{
		// Step 1. Get the main Homepage
		
		downloader.setCurrentLink(START_LINK);
		downloader.run();
	}

}
