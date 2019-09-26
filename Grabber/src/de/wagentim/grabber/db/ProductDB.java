package de.wagentim.grabber.db;

public class ProductDB
{
	private int id;
	private String product;
	
	public ProductDB(int id, String product)
	{
		super();
		this.id = id;
		this.product = product;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getProduct()
	{
		return product;
	}
	public void setProduct(String product)
	{
		this.product = product;
	}
	
}
