package de.wagentim.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.wagentim.grabber.db.ProductDB;

public class Converter
{
	public ProductDB fromJson(String jsonProduct)
	{
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(jsonProduct, ProductDB.class);
	}
	
	public String toJson(ProductDB product)
	{
		Gson gson = new GsonBuilder().create();
		return gson.toJson(product);
	}
}
