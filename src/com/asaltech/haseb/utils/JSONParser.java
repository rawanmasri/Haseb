package com.asaltech.haseb.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {
	

	private CookieStore cookieStore ;
	
	private static JSONParser instance;
	
	// constructor
	private JSONParser() {
		cookieStore = new BasicCookieStore();
	}
	
	public static JSONParser getInstance() {
		if (instance == null) {
			instance = new JSONParser();
		}
		return instance;
	}

	public String makeHttpRequest(String url, String method, String params,String API) {

		JSONObject jObj = null;
		String json = "";

		try {

			// check for request method
			if (method == "POST") {
				// request method is POST
				DefaultHttpClient httpClient = new DefaultHttpClient();

				HttpPost httpPost = new HttpPost(url);
				StringEntity entity = new StringEntity(params);
				entity.setContentType(new BasicHeader("Content-Type","application/json"));
				httpPost.setEntity(entity);

				//httpPost.setHeader("Key", API);
				
			    // Create local HTTP context
			    HttpContext localContext = new BasicHttpContext();
			    // Bind custom cookie store to the local context
			    localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
			    httpClient.setCookieStore(cookieStore);
			    
				HttpResponse httpResponse = httpClient.execute(httpPost , localContext);
				
				cookieStore = httpClient.getCookieStore();
				
				json = readResponse(httpResponse.getEntity());

			} else if (method == "GET") {
				// request method is GET
				DefaultHttpClient httpClient = new DefaultHttpClient();
				// String paramString = params;//URLEncodedUtils.format(params,
				// "utf-8");
				url += "/" + params;
				HttpGet httpGet = new HttpGet(url);
				 HttpContext localContext = new BasicHttpContext();
				    // Bind custom cookie store to the local context
				    localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
				    httpClient.setCookieStore(cookieStore);

				HttpResponse httpResponse = httpClient.execute(httpGet, localContext);
				cookieStore = httpClient.getCookieStore();

				json = readResponse(httpResponse.getEntity());
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return json;

	}

	private String readResponse(HttpEntity httpEntity) throws IllegalStateException, IOException {
		
		InputStream is = null;
		String json = "";
		
		is = httpEntity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is,
				"iso-8859-1"), 8);
		
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}
		
		
		is.close();
		json = sb.toString();
		
		return json;
	}

}
