package de.wagentim.sites;

import org.apache.http.HttpHost;

import de.wagentim.common.IConstants;
import de.wagentim.threads.IDownloadConfig;
import de.wagentim.utils.HttpScheme;

public class DouBan implements IDownloadConfig
{
	private final HttpHost httpHost;
	private final HttpScheme httpScheme;
	private final String path;
	
	public DouBan()
	{
		this.httpHost = new HttpHost("movie.douban.com", 80);
		this.httpScheme = new HttpScheme(HttpScheme.SCHEME_HTTPS);
		this.path = IConstants.EMPTY_STRING;
	}
	
	@Override
	public HttpHost getHost()
	{
		return httpHost;
	}

	@Override
	public HttpScheme getScheme()
	{
		return httpScheme;
	}

	@Override
	public String getPath()
	{
		return path;
	}

}
