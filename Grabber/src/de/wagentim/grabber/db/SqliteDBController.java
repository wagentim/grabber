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

	public boolean createTable(String tableName)
	{
		String state = "CREATE TABLE " + tableName + " (prod_id INTEGER PRIMARY KEY, prod_content TEXT NOT NULL);";
		
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
	
	public boolean insertNewProduct(String table, int id, String content)
	{
		sb.delete(0, sb.length());
		
		sb.append("INSERT INTO " + table + " (").append("'prod_id', 'prod_content') ")
			.append("VALUES ('").append(id).append("', '").append(content).append("');");
		
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
	
	public Map<Integer, ProductDB> getAllProducts(String table)
	{
		sb.delete(0, sb.length());
		sb.append("SELECT * FROM ").append(table);
		
		handler.openDB(ISQLConstants.SQLITE_JDBC, ISQLConstants.SQLITE_CONNECTION);
		
		try
		{
			List<ProductDB> products = handler.executeQuery(sb.toString(), new ProductExtractor());
			
			if( !products.isEmpty() )
			{
				Map<Integer, ProductDB> result = new HashMap<Integer, ProductDB>();
				
				Iterator<ProductDB> it = products.iterator();
				
				while(it.hasNext())
				{
					ProductDB r = it.next();
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
	
	public boolean updateProduct(int id, ProductDB newValue)
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
