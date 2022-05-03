package codesnippet.async.completable_future;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class App
{
	public static void
		main( String[] args ) throws IOException
	{
		try(CloseableHttpClient client = HttpClientBuilder.create().build())
		{
			// blocking IO
			CloseableHttpResponse response =
				client.execute(new HttpGet("http://www.google.com"));

			System.out.println(
				"status code: " + response.getStatusLine().getStatusCode());
			System.out.println(
				"body: " + EntityUtils.toString(response.getEntity()));
		}

		System.out.println("hello world");
	}
}
