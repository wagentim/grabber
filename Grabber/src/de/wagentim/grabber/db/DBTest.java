package de.wagentim.grabber.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBTest
{
	public static void main(String[] args)
	{
		DBTest test = new DBTest();
		
		SqliteDBController c = new SqliteDBController();
		
		c.startDBUpdate();
		Map<Integer, Product> oldProducts = c.getAllProducts("yachao");
		c.closeDBUpdate();	
		List<Product> newList = new ArrayList<Product>();
		
		newList.add(test.getProduct());
		
		test.updateChangedProducts(newList, c);
		
		
//		boolean i = c.createTable("yachaoonline");
//		
//		System.out.println(i);
		
//		i = c.createItemTable();
//		
//		System.out.println(i);
		
//		c.insertNewProduct("test", 123, "Hello World");
//		c.insertNewItem(IDManager.INSTANCE().getRandomInteger(), IDManager.INSTANCE().getRandomInteger(), "Hello", "World");
		
//		Iterator<Record> iterator = c.getAllRecords().values().iterator();
//		
//		while(iterator.hasNext())
//		{
//			System.out.println(iterator.next().toString());
//		}
//		
//		Iterator<Item> it = c.getAllItems().values().iterator();
//		
//		while(it.hasNext())
//		{
//			System.out.println(it.next().toString());
//		}
		
//		c.updateRecordName(654, "wo");
//		System.out.println(c.getAllProducts("test").keySet().size());
//		
//		for(ProductDB s : c.getAllProducts("test").values())
//		{
//			System.out.println(s.getProduct());
//		}
	}
	
	public Product getProduct() 
	{
		Product product = new Product();
		
		product.setAmount(4);
		product.setChanged(false);
		product.setCurrentPrice("23.12");
		product.setDbName("yachao");
		product.setId(000001);
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
		
		return product;
	}
	
	public void updateChangedProducts(List<Product> prodList, SqliteDBController controller)
	{
		controller.startDBUpdate();
		
		for(Product p : prodList)
		{
//			int id = p.getId();
			
//			Product oldP = oldProducts.get(id);
			
//			if(oldP != null)
//			{
				controller.updateProduct(p.getDbName(), p.getId(), p);
//			}
//			else
//			{
//				controller.insertNewProduct(p.getDbName(), p.getId(), p);
//			}
		}
		
		controller.closeDBUpdate();
	}
}
