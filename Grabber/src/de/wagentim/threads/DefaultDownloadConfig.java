package de.wagentim.threads;

public class DefaultDownloadConfig
{
	
//	private HttpClient httpClient;
//	private volatile Long startPoint;
//	private Long endPoint;
//	private RandomAccessFile targetFile;
//	private DownloadFile file;
//	
//	public DefaultDownloadConfig(final DownloadFile file, final RandomAccessFile targetFile)
//	{
//		this.file = file;
//		this.targetFile = targetFile;
//	}
//
//	@Override
//	public String getURI() {
//		
//		return file.getDonwloadURL();
//	}
//
//	@Override
//	public Header[] getHeaders() {
//		
//		return Converter.convertMyHeader(file.getHeaders());
//	}
//
//	@Override
//	public String getRequestMethod() {
//		
//		return file.getMethod();
//	}
//
//	@Override
//	public HttpClient getHttpClient() {
//		return httpClient;
//	}
//
//	@Override
//	public void updateOffset(int size) {
//		
//		startPoint += size;
//	}
//
//	@Override
//	public RandomAccessFile getTargetFile() {
//		
//		return targetFile;
//	}
//
//	@Override
//	public long getStartPoint() {
//		
//		return startPoint;
//	}
//
//	public Long getEndPoint() {
//		return endPoint;
//	}
//	
//	public void setStartPoint(Long start)
//	{
//		this.startPoint = start;
//		
//	}
//	
//	public void setEndPoint(Long end)
//	{
//		this.endPoint = end;
//	}
//	
//	public void setHttpClient(HttpClient client)
//	{
//		this.httpClient = client;
//	}
//	
//
//	public void flushInfo()
//	{
//		if( startPoint < endPoint)
//		{
//			StringBuffer sb = new StringBuffer(""+startPoint);
//			sb.append("-");
//			sb.append(endPoint);
//			
//			file.persistInfo(sb.toString());
//		}
//	}
//
//	@Override
//	public Long getFileSize() {
//		return file.getFileSize();
//	}

}
