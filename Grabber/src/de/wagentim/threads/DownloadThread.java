package de.wagentim.threads;

/**
 * Download Data
 * <p>
 * Download Data from the remote server to the local predefined file
 * </p>
 * 
 * @author wagentim
 *
 */
public class DownloadThread
{
	
//	private IDownloadConfig config = null;
//	private static final Logger logger = LoggerFactory.getLogger(DownloadThread.class);
//	
//	private volatile boolean cancel = false;
//	
//	private int bufferSize;
//	
//	
//	public DownloadThread( final IDownloadConfig config)
//	{
//		this(config, 2048);
//	}
//	
//	public DownloadThread( final IDownloadConfig config, final int bufferSize )
//	{
//		this.config = config;
//		this.bufferSize = bufferSize;
//	}
//	
//	@Override
//	public void run()
//	{
//		if( null == config )
//		{
//			logger.error("No defined config object");
//			return;
//		}
//
//		HttpClient client = config.getHttpClient();
//		
//		if( null == client )
//		{
//			logger.error("HttpClient is null");
//			return;
//		}
//		
//		RandomAccessFile target = config.getTargetFile();
//		
//		try {
//			target.seek(config.getStartPoint());
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//
//		// send request for the file
//		HttpUriRequest request = RequestBuilder.create( config.getRequestMethod() )
//										.setUri( config.getURI() )
//										.setHeader( config.getHeaders() )
//										.build();
//		
//		HttpResponse resp = null;
//		
//		try {
//			resp = client.execute(request);
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		if( null == resp || (resp.getStatusLine().getStatusCode() != HttpStatus.SC_OK && resp.getStatusLine().getStatusCode() != HttpStatus.SC_PARTIAL_CONTENT))
//		{
//			log.log("Response is null or Server return an unsuccessful code", Log.LEVEL_CRITICAL_ERROR);
//			return;
//		}
//		
//		if( resp.getStatusLine().getStatusCode() == HttpStatus.SC_PARTIAL_CONTENT )
//		{
//			
//			InputStream ins = null;
//			try {
//				
//				ins = resp.getEntity().getContent();
//				
//				byte[] buffer = new byte[bufferSize];
//				
//				int size;
//				
//				while( !cancel && (size = ins.read(buffer)) > 0 )
//				{
//					target.write(buffer);
//					config.updateOffset(size);
//					log.log("Donwload: " + config.getStartPoint() + "/" + config.getFileSize(), Log.LEVEL_INFO);
//				}
//				
//				config.flushInfo();
//				
//			} catch (IllegalStateException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}finally
//			{
//				if( null != ins )
//				{
//					try {
//						ins.close();
//						target.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//					
//					ins = null;
//					target = null;
//				}
//			}
//			
//		}else
//		{		
//			InputStream ins = null;
//			try {
//				
//				ins = resp.getEntity().getContent();
//				
//				byte[] buffer = new byte[bufferSize];
//				
//				int size;
//				
//				while( !cancel && (size = ins.read(buffer)) > 0 )
//				{
//					target.write(buffer);
//					config.updateOffset(size);
//					log.log("Donwload: " + config.getStartPoint() + "/" + config.getFileSize(), Log.LEVEL_INFO);
//				}
//				
//				config.flushInfo();
//				
//			} catch (IllegalStateException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}finally
//			{
//				if( null != ins )
//				{
//					try {
//						ins.close();
//						target.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//					
//					ins = null;
//					target = null;
//				}
//			}
//		}
//	}
//	
//	public void stop()
//	{
//		cancel = true;
//	}
	
}
