package de.wagentim.grabber.db;

import de.wagentim.common.IConstants;

public class PriceHistory
{
	private long time = 0L;
	private String price = IConstants.EMPTY_STRING;
	private int id = 0;
	private String site = IConstants.EMPTY_STRING;
	private String link = IConstants.EMPTY_STRING;
	
	public long getTime()
	{
		return time;
	}
	public void setTime(long time)
	{
		this.time = time;
	}
	public String getPrice()
	{
		return price;
	}
	public void setPrice(String price)
	{
		this.price = price;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getSite()
	{
		return site;
	}
	public void setSite(String site)
	{
		this.site = site;
	}
	public String getLink()
	{
		return link;
	}
	public void setLink(String link)
	{
		this.link = link;
	}
	
	
}
