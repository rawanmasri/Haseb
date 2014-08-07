package com.asaltech.haseb.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {

	// constructor
	public JSONParser() {

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
				// entity.setContentType(new BasicHeader("Content-Type",
				// "application/json"));
				httpPost.setEntity(entity);

				 httpPost.setHeader("Key", API);

				HttpResponse httpResponse = httpClient.execute(httpPost);

				json = readResponse(httpResponse.getEntity());

			} else if (method == "GET") {
				// request method is GET
				DefaultHttpClient httpClient = new DefaultHttpClient();
				// String paramString = params;//URLEncodedUtils.format(params,
				// "utf-8");
				url += "/" + params;
				HttpGet httpGet = new HttpGet(url);

				HttpResponse httpResponse = httpClient.execute(httpGet);

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
