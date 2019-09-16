package de.wagentim.grabber.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.wagentim.common.db.IResultSetExtractor;

public class ProductExtractor implements IResultSetExtractor<List<Product>>
{

	@Override
	public List<Product> extractData(ResultSet rs)
	{
		if( null != rs )
		{
			List<Product> result = new ArrayList<Product>();
			
			try
			{
				while(rs.next())
				{
					Product product = new Product(rs.getInt(ISQLConstants.PRODUCT_ID), rs.getString(ISQLConstants.PRODUCT_CONTENT));
					result.add(product);
				}
				
				return result;
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return Collections.emptyList();
	}

}
