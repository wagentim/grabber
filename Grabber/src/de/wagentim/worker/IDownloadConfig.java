package de.wagentim.worker;

import java.io.RandomAccessFile;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;

import de.wagentim.utils.HttpScheme;


/**
 * Configurations for <code>DownloadThread</code>
 * 
 * @author bihu8398
 *
 */
public interface IDownloadConfig 
{
	
	HttpHost getHost();
	HttpScheme getScheme();
	String getPath();

//	String getURI();
//
//	Header[] getHeaders();
//
//	String getRequestMethod();
//
//	HttpClient getHttpClient();
//
//	void updateOffset(int size);
//
//	RandomAccessFile getTargetFile();
//
//	long getStartPoint();
//	
//	void flushInfo();
//
//	Long getFileSize();
	
}
