package de.wagentim.worker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.wagentim.common.IConstants;

/**
 * Standard HTTP Download. Without any special handling. Only support Get Method.
 * 
 * @author UIH9FE
 *
 */
public class WebPageDefaultDownloader implements Runnable
{
	private final CloseableHttpClient client;
	private final HttpClientContext context = HttpClientContext.create();
	private static final Logger logger = LoggerFactory.getLogger(WebPageDefaultDownloader.class);
	
	private CloseableHttpResponse response = null;
	
	public WebPageDefaultDownloader()
	{
		client = HttpClients.createDefault();
	}

	@Override
	public void run()
	{
		URI uri = getURI();
		
		logger.info("URI Info: {}", uri.toString());
		
		HttpGet httpget = new HttpGet(uri);
		try
		{
			response = client.execute(httpget, context);
		}
		catch (ClientProtocolException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
		{
			try
			{
				HttpEntity entity = response.getEntity();
				
				if( entity == null )
				{
					logger.error("Response Entity is NULL");
					return;
				}
				
				ContentType contentType = ContentType.getOrDefault(entity);
				Charset charset = contentType.getCharset();
				
				InputStream is = entity.getContent();
				
				try
				{
					BufferedReader br = new BufferedReader(new InputStreamReader(is, charset));
					
					String line = IConstants.EMPTY_STRING;
					
					while( (line = br.readLine()) != null )
					{
						System.out.println(line);
					}
				}
				finally
				{
					is.close();
				}
				
			}
			catch (UnsupportedOperationException | IOException e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					response.close();
					
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		else
		{
			logger.error("Get Reponse Code: {}", response.getStatusLine().getStatusCode());
		}
	}

	private URI getURI()
	{
		try
		{
			return new URIBuilder()
					.build();
		}
		catch (URISyntaxException e)
		{
			e.printStackTrace();
			
			return null;
		}
	}
	
	public static void main(String[] args)
	{
		WebPageDefaultDownloader dd = new WebPageDefaultDownloader();
		
		dd.run();
	}
}
