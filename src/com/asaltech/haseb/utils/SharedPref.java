package com.asaltech.haseb.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPref {

	private static SharedPref pref;
	private static Context context;
	private SharedPreferences prefs;
	
	private SharedPref(Context context) {
		this.context = context;
	}
	
	public static SharedPref getInstance(Context context) {
		if(pref == null) {
			pref = new SharedPref(context);
		}
		return pref;
	}
	
	
	public void setUser(String user) {
		prefs = context.getSharedPreferences(
			      "com.example.app", Context.MODE_PRIVATE);
		
		Editor editor = prefs.edit();
		editor.putString("user", user);
		editor.commit();
		
	}
	
	public String getUser() {
		prefs = context.getSharedPreferences(
			      "com.example.app", Context.MODE_PRIVATE);
		String user = prefs.getString("user", "{}");
		return user;
	}
	
	public boolean isLoggedIn() {
		prefs = context.getSharedPreferences(
			      "com.example.app", Context.MODE_PRIVATE);
		String username = prefs.getString("username", "");
		return username.equals("") ? false : true;
	}
	
	public void setLoginInformation(String username , String password) {
		prefs = context.getSharedPreferences(
			      "com.example.app", Context.MODE_PRIVATE);
		
		Editor editor = prefs.edit();
		editor.putString("username", username);
		editor.putString("password", password);
		editor.commit();
	}
	
	public void logout() {
		prefs = context.getSharedPreferences(
			      "com.example.app", Context.MODE_PRIVATE);
		
		Editor editor = prefs.edit();
		editor.putString("username", "");
		editor.putString("password", "");
		editor.clear();
		editor.commit();
		
	}
	
	
	
	
	
}
