package de.wagentim.grabber.db;

public interface ISQLConstants
{
	public static final String SQLITE_JDBC = "org.sqlite.JDBC";
	public static final String SQLITE_FILE_PATH = "C:\\tmp\\product.sqlite3";
	public static final String SQLITE_CONNECTION = "jdbc:sqlite:" + SQLITE_FILE_PATH;
	public static final String PRODUCT_ID = "prod_id";
	public static final String TABLE_YACHAO = "table_yachao";
	public static final String PRODUCT_CONTENT = "prod_content";
}
