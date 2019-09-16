package de.wagentim.grabber.db;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import de.wagentim.common.db.SqliteDBHandler;

public class SqliteDBController
{
	private final SqliteDBHandler handler;
	private final StringBuffer sb;
	
	public SqliteDBController()
	{
		handler = new SqliteDBHandler();
		sb = new StringBuffer();
	}

	public boolean createProductTable()
	{
		String state = "CREATE TABLE product (prod_id INTEGER PRIMARY KEY AUTOINCREMENT, prod_content TEXT NOT NULL);";
		
		handler.openDB(ISQLConstants.SQLITE_JDBC, ISQLConstants.SQLITE_CONNECTION);
		
		try
		{
			return handler.executeUpdate(state) == 0 ? true : false;
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean insertNewProduct(String content)
	{
		sb.delete(0, sb.length());
		
		sb.append("INSERT INTO product (").append("'prod_content') ")
			.append("VALUES ('").append(content).append("');");
		
		System.out.println(sb.toString());
		
		handler.openDB(ISQLConstants.SQLITE_JDBC, ISQLConstants.SQLITE_CONNECTION);
		
		try
		{
			return handler.executeUpdate(sb.toString()) == 0 ? true : false;
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Map<Integer, Product> getAllProducts()
	{
		sb.delete(0, sb.length());
		sb.append("SELECT * FROM product");
		
		handler.openDB(ISQLConstants.SQLITE_JDBC, ISQLConstants.SQLITE_CONNECTION);
		
		try
		{
			List<Product> products = handler.executeQuery(sb.toString(), new ProductExtractor());
			
			if( !products.isEmpty() )
			{
				Map<Integer, Product> result = new HashMap<Integer, Product>();
				
				Iterator<Product> it = products.iterator();
				
				while(it.hasNext())
				{
					Product r = it.next();
					result.put(r.getId(), r);
				}
				
				return result;
			}
			
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Collections.emptyMap();
	}
	
	public boolean updateProduct(int id, Product newValue)
	{
		sb.delete(0, sb.length());
		
		sb.append("UPDATE product SET prod_content = '").append(newValue.getProduct()).append("' WHERE prod_id = ").append(newValue.getId()).append(";");
		
		System.out.println(sb.toString());
		
		handler.openDB(ISQLConstants.SQLITE_JDBC, ISQLConstants.SQLITE_CONNECTION);
		
		try
		{
			return handler.executeUpdate(sb.toString()) == 0 ? true : false;
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
}
