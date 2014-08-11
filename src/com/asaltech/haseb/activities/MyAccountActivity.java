package com.asaltech.haseb.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
public class MyAccountActivity extends Activity {
	private List<Map<String, String>> settingList ;
	private SimpleAdapter simpleAdpt ;
	private ListView lview; 
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        initList();
        settingList = new ArrayList<Map<String,String>>();
		lview = (ListView) findViewById(R.id.list11);
	    simpleAdpt = new SimpleAdapter(this, settingList, android.R.layout.simple_list_item_1, new String[] {"setting"}, new int[] {android.R.id.text1});
	    lview.setAdapter(simpleAdpt);
	    lview.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) {											
					Intent intent;
					 switch( position )
				    {				       
				       case 1:  intent = new Intent(getApplicationContext(),CreatGroupActivity.class);
				     	startActivity(intent);     		           
				    	       break;				       
				    }					
				}				 
				 });         
	}
	private void initList() {
		  if(Locale.getDefault().getLanguage().equals("en")) {
		       settingList.add(createPlanet("setting", "Edit Account"));
		       settingList.add(createPlanet("setting", "Create Group"));
		       settingList.add(createPlanet("setting", "Delete Group"));
		       settingList.add(createPlanet("setting", "Logout"));
		  }else{
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
