package com.asaltech.haseb.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.asaltech.haseb.utils.JSONParser;
import com.asaltech.haseb.utils.SharedPref;
import com.example.bean.Auth;
import com.example.bean.User;
import com.google.gson.Gson;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
public class MyAccountActivity extends Activity {
	private List<Map<String, String>> settingList;
	private SimpleAdapter simpleAdpt ;
	private ListView lview;
    private ProgressDialog pDialog;
    private String logoutUrl;
  //  private JSONParser jsonParser;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        settingList = new ArrayList<Map<String,String>>();
        initList();
        logoutUrl = "http://192.168.43.160/7aseb/public/users";
		lview = (ListView) findViewById(R.id.list11);
		//jsonParser= new JSONParser();
	    simpleAdpt = new SimpleAdapter(this, settingList, android.R.layout.simple_list_item_1, new String[] {"setting"}, new int[] {android.R.id.text1});
	    lview.setAdapter(simpleAdpt);
	    lview.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Intent intent;
					 switch( position )
				    {				       
				       case 1:  intent = new Intent(getApplicationContext(),CreatGroupActivity.class);
				     	startActivity(intent);     		           
				    	       break;
				       case 3: new logoutUserAccount().execute();
				    	   break;
				    }					
				}
				 });      
	}
	
	  //AsynncTask to logout user account 
    class logoutUserAccount extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog( MyAccountActivity.this);
			pDialog.setMessage("Start Login ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... args) {
				try {			
					Gson g = new Gson();
					String json =  JSONParser.getInstance().makeHttpRequest(logoutUrl,"GET", " ", null);					
					String isSuccess = g.fromJson(json, String.class);					
					if (isSuccess.equals("success")) { 
						SharedPref.getInstance(getApplicationContext()).logout();			
						Intent i = new Intent(getApplicationContext(),MainActivity.class);		
						startActivity(i);
						finish();
					}
					else {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(getApplicationContext(), "Logout failed!", Toast.LENGTH_LONG).show();
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
	private void initList() {
		  if(Locale.getDefault().getLanguage().equals("en")) {
		        settingList.add(createPlanet("setting", "Edit Account"));
		        settingList.add(createPlanet("setting", "Create Group"));
		        settingList.add(createPlanet("setting", "Delete Group"));
		        settingList.add(createPlanet("setting", "Logout"));
		  }
		  else{
			  settingList.add(createPlanet("setting", " ⁄œÌ· «·Õ”«»"));
		        settingList.add(createPlanet("setting", "«‰‘«¡ „Ã„Ê⁄…"));
		        settingList.add(createPlanet("setting", "Õ–› „Ã„Ê⁄…"));
		        settingList.add(createPlanet("setting", " ”ÃÌ· «·Œ—ÊÃ "));		  
		  }		        			 
			}
	private HashMap<String, String> createPlanet(String key, String name) {
			    HashMap<String, String> setting = new HashMap<String, String>();
			    setting.put(key, name);			 
			    return setting;
			}	
}