package de.wagentim.grabber.db;

import java.util.List;

import de.wagentim.common.IConstants;

public class Product
{
	private int id = 0;
	private String name = IConstants.EMPTY_STRING;
	private int amount = 0;
	private String link = IConstants.EMPTY_STRING;
	private String siteName = IConstants.EMPTY_STRING;
	private String currentPrice = IConstants.EMPTY_STRING;
	private String marketPrice = IConstants.EMPTY_STRING;
	private String imageLink = IConstants.EMPTY_STRING;
	private List<PriceHistory> history = null;
	private boolean changed = false;
	private String dbName = IConstants.EMPTY_STRING;
	
	public String getDbName()
	{
		return dbName;
	}
	public void setDbName(String dbName)
	{
		this.dbName = dbName;
	}
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
		if(!name.equals(getName()))
		{
			System.out.println("Name changed: " + getName() + " | " + name);
			changed = true;
		}
		
		this.name = name;
	}
	public int getAmount()
	{
		return amount;
	}
	public void setAmount(int amount)
	{
		if(amount != getAmount())
		{
			System.out.println("amount changed: " + getAmount() + " | " + amount);
			changed = true;
		}
		
		this.amount = amount;
	}
	public String getLink()
	{
		return link;
	}
	
	public void setLink(String link)
	{
		if(!link.equals(getLink()))
		{
			System.out.println("link changed: " + getLink() + " | " + link);
			changed = true;
		}
		
		this.link = link;
	}
	
	public String getSiteShort()
	{
		return siteName;
	}
	public void setSiteShort(String siteShort)
	{
		if(!siteShort.equals(getSiteShort()))
		{
			System.out.println("siteShort changed: " + getSiteShort() + " | " + siteShort);
			changed = true;
		}
		
		this.siteName = siteShort;
	}
	
	public String getCurrentPrice()
	{
		return currentPrice;
	}
	
	public void setCurrentPrice(String currentPrice)
	{
		if(!currentPrice.equals(getCurrentPrice()))
		{
			System.out.println("currentPrice changed: " + getCurrentPrice() + " | " + currentPrice);
			changed = true;
		}
		
		this.currentPrice = currentPrice;
	}
	
	public String getMarketPrice()
	{
		return marketPrice;
	}
	
	public void setMarketPrice(String marketPrice)
	{
		if(!marketPrice.equals(getMarketPrice()))
		{
			System.out.println("marketPrice changed: " + getMarketPrice() + " | " + marketPrice);
			changed = true;
		}
		
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
	
	public String imageLink()
	{
		return imageLink;
	}
	
	public void setImageLink(String imageLink)
	{
		if(!imageLink.equals(imageLink()))
		{
			System.out.println("imageLink changed: " + imageLink() + " | " + imageLink);
			changed = true;
		}
		
		this.imageLink = imageLink;
	}
	
	public boolean isChanged()
	{
		return changed;
	}
	
	public void setChanged(boolean changed)
	{
		this.changed = changed;
	}
	
}