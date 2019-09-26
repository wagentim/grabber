package de.wagentim.grabber.db;

import java.util.Collections;
import java.util.List;

import de.wagentim.common.IConstants;

public class Product
{
	private int id = 0;
	private String name = IConstants.EMPTY_STRING;
	private int amount = 0;
	private String link = IConstants.EMPTY_STRING;
	private String siteShort = IConstants.EMPTY_STRING;
	private String siteLong = IConstants.EMPTY_STRING;
	private String currentPrice = IConstants.EMPTY_STRING;
	private String marketPrice = IConstants.EMPTY_STRING;
	private List<PriceHistory> history = Collections.emptyList();
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getAmount()
	{
		return amount;
	}
	public void setAmount(int amount)
	{
		this.amount = amount;
	}
	public String getLink()
	{
		return link;
	}
	public void setLink(String link)
	{
		this.link = link;
	}
	public String getSiteShort()
	{
		return siteShort;
	}
	public void setSiteShort(String siteShort)
	{
		this.siteShort = siteShort;
	}
	public String getSiteLong()
	{
		return siteLong;
	}
	public void setSiteLong(String siteLong)
	{
		this.siteLong = siteLong;
	}
	public String getCurrentPrice()
	{
		return currentPrice;
	}
	public void setCurrentPrice(String currentPrice)
	{
		this.currentPrice = currentPrice;
	}
	public String getMarketPrice()
	{
		return marketPrice;
	}
	public void setMarketPrice(String marketPrice)
	{
		this.marketPrice = marketPrice;
	}
	public List<PriceHistory> getHistory()
	{
		return history;
	}
	public void setHistory(List<PriceHistory> history)
	{
		this.history = history;
	}
	
	
	
}
