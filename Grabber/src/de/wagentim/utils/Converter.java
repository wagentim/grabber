package de.wagentim.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.wagentim.grabber.db.PriceHistory;
import de.wagentim.grabber.db.Product;

public class Converter
{
	public Product fromJson(String jsonProduct)
	{
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(jsonProduct, Product.class);
	}
	
	public String toJson(Product product)
	{
		Gson gson = new GsonBuilder().create();
		return gson.toJson(product);
	}
	
	public static void main(String[] args)
	{
		Product product = new Product();
		
		product.setAmount(4);
		product.setChanged(false);
		product.setCurrentPrice("23.12");
		product.setDbName("Hello");
		product.setId(2324);
		product.setImageLink("https://www.www.www");
		product.setLink("https://ttt.ttt.ttt");
		product.setMarketPrice("14.22");
		product.setName("Piggy");
		product.setSiteShort("Yachao");
		
		PriceHistory his = new PriceHistory();
		his.setId(product.getId());
		his.setLink(product.getLink());
		his.setPrice(product.getCurrentPrice());
		his.setSite(product.getSiteShort());
		his.setTime(System.currentTimeMillis());
		
		List<PriceHistory> pList = new ArrayList<PriceHistory>();
		pList.add(his);
		
		product.setHistory(pList);
		
		Converter c = new Converter();
		
		System.out.println(c.toJson(product));
	}
}
