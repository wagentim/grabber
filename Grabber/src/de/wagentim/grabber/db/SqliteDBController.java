package de.wagentim.grabber.db;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import de.wagentim.common.db.SqliteDBHandler;
import de.wagentim.utils.Converter;

public class SqliteDBController implements IDBController
{
	private final SqliteDBHandler handler;
	private final StringBuffer sb;
	private final Converter converter;
	
	public SqliteDBController()
	{
		handler = new SqliteDBHandler();
		sb = new StringBuffer();
		converter = new Converter();
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
	
	public boolean insertNewProduct(String table, int id, Product product)
	{
		sb.delete(0, sb.length());
		
		String pContent = converter.toJson(product);
		
		sb.append("INSERT INTO " + table + " (").append("'prod_id', 'prod_content') ")
			.append("VALUES ('").append(id).append("', '").append(pContent).append("');");
		
//		System.out.println(sb.toString());
		
//		handler.openDB(ISQLConstants.SQLITE_JDBC, ISQLConstants.SQLITE_CONNECTION);
		
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
	
	public Map<Integer, Product> getAllProducts(String table)
	{
		sb.delete(0, sb.length());
		sb.append("SELECT * FROM ").append(table);
		
//		handler.openDB(ISQLConstants.SQLITE_JDBC, ISQLConstants.SQLITE_CONNECTION);
		
		try
		{
			List<ProductDB> products = handler.executeQuery(sb.toString(), new ProductExtractor());
			
			if( !products.isEmpty() )
			{
				Map<Integer, Product> result = new HashMap<Integer, Product>();
				
				Iterator<ProductDB> it = products.iterator();
				
				while(it.hasNext())
				{
					ProductDB r = it.next();
					Product p = converter.fromJson(r.getProduct());
					result.put(r.getId(), p);
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
	
	public boolean updateProduct(String table, int id, Product newValue)
	{
		sb.delete(0, sb.length());
		
		sb.append("UPDATE ").append(table).append(" SET prod_content = '").append(converter.toJson(newValue)).append("' WHERE prod_id = ").append(newValue.getId()).append(";");
		
//		System.out.println(sb.toString());
		
//		handler.openDB(ISQLConstants.SQLITE_JDBC, ISQLConstants.SQLITE_CONNECTION);
		
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
	
	public void startDBUpdate()
	{
		handler.openDB(ISQLConstants.SQLITE_JDBC, ISQLConstants.SQLITE_CONNECTION);
	}
	
	public void closeDBUpdate()
	{
		handler.destroyed();
	}

}
