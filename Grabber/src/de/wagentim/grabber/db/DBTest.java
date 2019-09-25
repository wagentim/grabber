package de.wagentim.grabber.db;

public class DBTest
{
	public static void main(String[] args)
	{
		SqliteDBController c = new SqliteDBController();
		
//		boolean i = c.createTable("test");
		
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
		System.out.println(c.getAllProducts("test").keySet().size());
		
		for(Product s : c.getAllProducts("test").values())
		{
			System.out.println(s.getProduct());
		}
	}
}
