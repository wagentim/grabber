package de.wagentim.grabber.db;

import java.util.Map;

public interface IDBController
{
	Map<Integer, Product> getAllProducts(String dbTableName);

	boolean insertNewProduct(String dbName, int id, Product p);

	boolean updateProduct(String dbName, int id, Product p);
	
	void startDBUpdate();
	
	void closeDBUpdate();
}
