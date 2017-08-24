package com.qyf404.httpclient;

import java.io.IOException;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

public class TestHttps {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, 10 * 1000);
		HttpConnectionParams.setSoTimeout(params, 10 * 1000);
		HttpProtocolParams.setUserAgent(params, getRandomUserAgent());
		
		HttpClient client = new DefaultHttpClient(params);
		
//		HttpPost request = new HttpPost("https://www.baidu.com");
//		HttpGet request = new HttpGet("https://localhost:8443/appserver");
		HttpGet request = new HttpGet("https://www.baidu.com?username=abc");
//		HttpGet request = new HttpGet("http://cuc.asiainfo.com?username=abc");
		
		
		
		
		HttpResponse response = client.execute(request);
		
		if(response.getStatusLine().getStatusCode() == 200){
			String s = EntityUtils.toString(response.getEntity());
			System.out.println(s);
			
			
		}else{
			System.out.println(response.getStatusLine().getStatusCode());
		}
		
	}
	
	public static String getRandomUserAgent(){
		String userAgent = null;
		switch (RandomUtils.nextInt(4)) {
		case 0:
			userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36";
			break;
		case 1:
			userAgent = "Mozilla/5.0 (X11; U; Linux x86_64; en-US; rv:1.9.2.13) Gecko/20101206 Ubuntu/10.10 (maverick) Firefox/3.6.13";
			break;
		case 2:
			userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:22.0) Gecko/20100101 Firefox/22.0";
			break;
		default:
			userAgent = "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)";
			break;
		}
		
		return userAgent;
	}
}
