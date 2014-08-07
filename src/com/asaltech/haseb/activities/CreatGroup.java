package com.asaltech.haseb.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.asaltech.haseb.activities.DisplaySignupActivity.createAccountUser;
import com.asaltech.haseb.utils.JSONParser;
import com.asaltech.haseb.utils.SharedPref;
import com.example.bean.Group;
import com.example.bean.User;
import com.google.gson.Gson;

import android.R.integer;
import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class CreatGroup extends ActionBarActivity {
	
	private ProgressDialog pDialog;
	
	private JSONParser jsonParser = new JSONParser(); 
	private List<Map<String, String>> adaptList=new ArrayList<Map<String,String>>() ;
	private SimpleAdapter simpleAdpt ;
	private ListView lView;
    private EditText groupName;
    
    
    
    
    private  EditText groupCurrency;
    
    private EditText groupMember;
    private  String friendToData;
    private ArrayList<Integer> friendlist ; 
    private String url_create_group;
    private String url_add_member;
    private  User user;
   
    
    

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creat_group);
	    user = (new Gson()).fromJson(SharedPref.getInstance(getApplicationContext()).getUser(), User.class);
	    lView = (ListView) findViewById(R.id.listView);
        adaptList = new ArrayList<Map<String,String>>();
        simpleAdpt = new SimpleAdapter(this, adaptList, android.R.layout.simple_list_item_1, new String[] {"setting"}, new int[] {android.R.id.text1});
        lView.setAdapter(simpleAdpt); 
	    friendlist  = new ArrayList<Integer>();
		url_create_group= "http://192.168.43.105/7aseb/createGroup";
		url_add_member= "http://192.168.43.105/7aseb/addmember";
		groupName= (EditText) findViewById(R.id.editText1);
		groupCurrency = (EditText) findViewById(R.id.editText2);
		groupMember   = (EditText)findViewById(R.id.editText3);
		 adaptList.add(createPlanet("setting", "hhh"));
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.creat_group, menu);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	 public void addMember(View view) {
		 friendToData=groupMember.getText().toString();
		 
		 
		 new addMember().execute();		
		 
	    	
	    }
	 class addMember extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			   Gson gsonObject =new Gson();
			 String memberID = jsonParser.makeHttpRequest(url_add_member,
	                    "GET", friendToData, user.getApiKey());
			 memberID = gsonObject.fromJson(memberID, String.class);
			if(!memberID.equals("failed")){
				
				friendlist.add(Integer.parseInt(memberID));
				
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						 adaptList.add(createPlanet("setting", "hhh"));
					}
				});
				
				
			}
			else{
				
				
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(getApplicationContext(), "Faild in adding member!", Toast.LENGTH_LONG).show();
					}
				});
				
			}
			
			 
			 
			 
			
			return null;
		}
		 
		 
	 }
	 
	 
	 
	 
	
	 public void createGroup(View view) {
		 new createGroup().execute();		 
	    	
	    }
	 class createGroup extends AsyncTask<String, String, String> {
		 
		 
		 
		 
		 @Override
		    
		    protected void onPreExecute() {
		        super.onPreExecute();
		        pDialog = new ProgressDialog(CreatGroup.this);
		        pDialog.setMessage("Creating Group...");
		        pDialog.setIndeterminate(false);
		        pDialog.setCancelable(false);
		        pDialog.show();
	            
		    
		    }

			@Override
			 protected String doInBackground(String... args) {
				 Boolean isSuccess;
				 String message;
				 int id;
				 String groupname = groupName.getText().toString();
		         String groupcurrency = groupCurrency.getText().toString();
		         int [] billsID =new int[40] ;
	            try {
	          
	            	
	            	
	            Group group = new Group(0,groupname,groupcurrency,user.getId(),billsID,friendlist);
	           
	      
	           Log.d("request!", "starting");
	           Gson gsonObject =new Gson();
	           String data = gsonObject.toJson(group);
	           String json = jsonParser.makeHttpRequest(url_create_group,
	                    "POST", data, user.getApiKey());
	           
	           group = gsonObject.fromJson(json,Group.class);
	           
	 
	            // check log cat fro response
	            Log.d("Create Response", json.toString());
	 
	            // check for success tag
	            
	                 isSuccess = group.isSuccess();
	 
	                if (isSuccess) {
	                    // successfully created Group
	                	Log.d("Signup  Successful!", json.toString());
	                    Intent i = new Intent(getApplicationContext(), HomePage.class);
	                    startActivity(i);
	                    finish();
	                    
	                    // closing this screen
	                   
	                } else {
	                	
	                	runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(getApplicationContext(), " Failed in creatig group !", Toast.LENGTH_LONG).show();
							}
						});
	                	
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            
	           
	            return null;
	           
	        }
			 protected void onPostExecute(String file_url) {
		            // dismiss the dialog once done
		            pDialog.dismiss();
		        }
			
		 
	 }
	 
	 private void initList(String member) {
		    // We populate the planets
		  
		        adaptList.add(createPlanet("setting", member));
		        
		  }
	 private HashMap<String, String> createPlanet(String key, String name) {
		    HashMap<String, String> setting = new HashMap<String, String>();
		    setting.put(key, name);
		 
		    return setting;
		}



	
}
