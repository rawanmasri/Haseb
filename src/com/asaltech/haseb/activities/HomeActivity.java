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
import com.example.bean.User;
import com.google.gson.Gson;

public class HomeActivity extends Activity  {
	List<Map<String, String>> settingList = new ArrayList<Map<String,String>>();
	SimpleAdapter simpleAdpt ;
	 ListView  lview;
	 private ProgressDialog pDialog;
	 private JSONParser jsonParser = new JSONParser(); 
	 private String url_show_group;
	 private  User user;
	 private String userID;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        user = (new Gson()).fromJson(SharedPref.getInstance(getApplicationContext()).getUser(), User.class);
		userID=String.valueOf(user.getId());
        lview = (ListView) findViewById(R.id.list11);
	    simpleAdpt = new SimpleAdapter(this, settingList, android.R.layout.simple_list_item_1, new String[] {"setting"}, new int[] {android.R.id.text1});
	    url_show_group= "http://192.168.43.105/7aseb/showGroup";
	    lview.setAdapter(simpleAdpt);
	 //   new showGroup().execute();
	    
       
    }
	
	 class showGroup extends AsyncTask<String, String, String> {
		 
		 @Override
		    
		    protected void onPreExecute() {
		        super.onPreExecute();
		        pDialog = new ProgressDialog(HomeActivity.this);
		        pDialog.setMessage("Waiting...");
		        pDialog.setIndeterminate(false);
		        pDialog.setCancelable(false);
		        pDialog.show();
	            
		    
		    }

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			 Gson gsonObject =new Gson();
			 String memberID = jsonParser.makeHttpRequest(url_show_group,
	                    "GET",userID , user.getApiKey());
			
			
			return null;
		}}
	
	private HashMap<String, String> createPlanet(String key, String name) {
	    HashMap<String, String> setting = new HashMap<String, String>();
	    setting.put(key, name);
	 
	    return setting;
	}
	 
	
	
	

	
}
