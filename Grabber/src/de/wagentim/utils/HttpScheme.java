package de.wagentim.utils;

public class HttpScheme
{
	public static final String SCHEME_HTTP = "http";
	public static final String SCHEME_HTTPS = "https";
	
	private String scheme = null;
	
	public HttpScheme()
	{
		this(SCHEME_HTTP);
	}
	
	public HttpScheme(String scheme)
	{
		this.scheme = scheme;
	}
	
	public String getScheme()
	{
		return scheme;
	}
	
	public void updateScheme(final String scheme)
	{
		this.scheme = scheme;
	}
	
	public String toString()
	{
		return getScheme();
	}
}
