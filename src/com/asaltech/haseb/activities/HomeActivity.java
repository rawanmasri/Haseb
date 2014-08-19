package com.asaltech.haseb.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.asaltech.haseb.utils.JSONParser;
import com.asaltech.haseb.utils.SharedPref;
import com.example.bean.Group;
import com.example.bean.User;
import com.google.gson.Gson;

public class HomeActivity extends Activity  {
	List<Map<String, String>> settingList = new ArrayList<Map<String,String>>();
	SimpleAdapter simpleAdpt ;
	 ListView  lview;
	 private ProgressDialog pDialog;
	 private  User user;
	 public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); 
        user = (new Gson()).fromJson(SharedPref.getInstance(getApplicationContext()).getUser(), User.class);
        lview = (ListView) findViewById(R.id.list11);
	    simpleAdpt = new SimpleAdapter(this, settingList, android.R.layout.simple_list_item_1, new String[] {"setting"}, new int[] {android.R.id.text1});
	    lview.setAdapter(simpleAdpt);
	    for(int index=0;index<user.getGroupObject().size();index++){	
	    	settingList.add(createPlanet("setting", user.getGroupObject().get(index).getName()));	
	    }
	    
     }
	 private HashMap<String, String> createPlanet(String key, String name) {
	    HashMap<String, String> setting = new HashMap<String, String>();
	    setting.put(key, name);
	    return setting;
	 }
	 
	
	
	

	
}
