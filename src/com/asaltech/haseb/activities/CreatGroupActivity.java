package com.asaltech.haseb.activities;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.asaltech.haseb.utils.JSONParser;
import com.asaltech.haseb.utils.SharedPref;
import com.example.bean.Group;
import com.example.bean.User;
import com.google.gson.Gson;

import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
public class CreatGroupActivity extends ActionBarActivity {
	private ProgressDialog pDialog;
	//private JSONParser jsonParser;
	private List<Map<String, String>> adaptList;
	private SimpleAdapter simpleAdpt;
	private ListView lView;
	private EditText groupName;
	private EditText groupCurrency;	
	private ArrayList<User> friendlist;
	private String url_create_group;
	private User user;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creat_group);
		final Button addFriendButton = (Button) findViewById(R.id.button1);
        final Button createGroupButton = (Button) findViewById(R.id.button2);
        addFriendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(getApplicationContext(), AddFriendActivity.class);
        		startActivityForResult(intent, 0);
            }
        });
        createGroupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	new createGroup().execute();
            }
        });
		//jsonParser = new JSONParser();
		adaptList = new ArrayList<Map<String, String>>();
		user = (new Gson()).fromJson(SharedPref.getInstance(getApplicationContext()).getUser(),
				User.class);
		lView = (ListView) findViewById(R.id.listView);
		adaptList = new ArrayList<Map<String, String>>();
		simpleAdpt = new SimpleAdapter(this, adaptList,android.R.layout.simple_list_item_1,
				new String[] { "setting" }, new int[] { android.R.id.text1 });	
		lView.setAdapter(simpleAdpt);
		friendlist = new ArrayList<User>();
		url_create_group = "http://192.168.43.160/7aseb/public/groups/create";
		groupName = (EditText) findViewById(R.id.editText1);
		groupCurrency = (EditText) findViewById(R.id.editText2);

	}	
	class createGroup extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(CreatGroupActivity.this);
			pDialog.setMessage("Creating Group...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... args) {
			Boolean isSuccess;
			String groupname = groupName.getText().toString();
			String groupcurrency = groupCurrency.getText().toString();		
			try {
				Group group = new Group(groupname, groupcurrency,user.getId(), friendlist);								
				Gson gsonObject = new Gson();
				String data = gsonObject.toJson(group);
				String json =  JSONParser.getInstance().makeHttpRequest(url_create_group,"POST", data, user.getRemember_token());					
				group = gsonObject.fromJson(json, Group.class);
				isSuccess = group.isSuccess();
				if (isSuccess) {
					// successfully created Group
					//user.setGroups(group);
					data = gsonObject.toJson(user);
					SharedPref.getInstance(getApplicationContext()).setUser(data);
					Intent i = new Intent(getApplicationContext(),HomePage.class);		
					startActivity(i);
					finish();
					// closing this screen
				} else {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(getApplicationContext()," Failed in creatig group !",Toast.LENGTH_LONG).show();																		
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

		adaptList.add(createPlanet("setting", member));
	}

	private HashMap<String, String> createPlanet(String key, String name) {
		HashMap<String, String> setting = new HashMap<String, String>();
		setting.put(key, name);
		return setting;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	    if (requestCode == 0) {
	        if(resultCode == RESULT_OK){
	            User userMember=(User) data.getSerializableExtra("result");
	            friendlist.add(userMember);
	            adaptList.add(createPlanet("setting", userMember.getFirstName()+" "+userMember.getLastName()));
				simpleAdpt.notifyDataSetChanged();            
	            
	        } else if (resultCode == RESULT_CANCELED) {
	            //Write your code if there's no result
	        }
	    }
	}	
}
