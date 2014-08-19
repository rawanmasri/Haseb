package com.asaltech.haseb.activities;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.asaltech.haseb.activities.adapter.SearchAdapter;
import com.asaltech.haseb.utils.JSONParser;
import com.asaltech.haseb.utils.SharedPref;
import com.example.bean.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AddFriendActivity extends Activity{	
	private Button searchBtn; 
	private EditText searchInput;
	private ListView searchList;
	private SimpleAdapter searchAdapter;
	private String url_add_member;
	private User user;
	private List<User> userMember;
	//private JSONParser jsonParser;
	private String [] resultList = {};
	
	private SearchAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_friend);	
		init();	
	}
	
	private void init() {
		 userMember=new ArrayList<User>();
		 userMember.add(null);
		// jsonParser = new JSONParser();
		user = (new Gson()).fromJson(
				SharedPref.getInstance(getApplicationContext()).getUser(),
				User.class);
		searchBtn = (Button) findViewById(R.id.searchBtn);
		url_add_member = "http://192.168.43.160/7aseb/public/users";
		searchInput = (EditText) findViewById(R.id.searchInput);
		searchList = (ListView) findViewById(R.id.searchList);
		adapter = new SearchAdapter(this, android.R.layout.list_content, userMember );
		searchList.setAdapter(adapter);
		
		searchBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
					new addMember().execute();
					
		
			}
		});
		
		searchList.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					Intent returnIntent = new Intent();
					returnIntent.putExtra("result",userMember.get( position));
					setResult(RESULT_OK,returnIntent);
					finish();					
				}
				 });	
	}
	
/*	public void Test(){
		
		User test=new User("Rawan", "Masri", "RawanEmail", "RawanPass", " h",
				 "RawanPhone");
		 userMember.add(test);
		 User test2=new User("Reem", "sal3os", "ReemEmail", "ReemPass", " hh",
				 "ReemPhone");
		userMember.add(test2);

		if (!userMember.isEmpty()) {
			String te=userMember.get(0).getFirstName();
			adapter = new SearchAdapter(getApplicationContext(), android.R.layout.list_content, userMember);
				searchList.setAdapter(adapter);}
		
	}*/
	class addMember extends AsyncTask<String, String, String> {
		
		@Override
		protected String  doInBackground(String... params) {
			Gson gsonObject = new Gson();
			userMember.clear();
			String memberNames =  JSONParser.getInstance().makeHttpRequest(url_add_member, "GET",
					searchInput.getText().toString(), user.getRemember_token());
			
			
		 userMember.addAll((Collection<? extends User>) gsonObject.fromJson(memberNames, new TypeToken<List<User>>() {}.getType()));
		 
		/* User test=new User("Rawan", "Masri", "RawanEmail", "RawanPass", " h",
					 "RawanPhone");
			 userMember.add(test);
			 User test2=new User("Reem", "sal3os", "ReemEmail", "ReemPass", " hh",
					 "ReemPhone");*/
			//userMember.add(test2);
			//y=userMember.size();
			
		 String returnStr = null;
			if (!userMember.isEmpty()) {
				returnStr ="";
			}
		
			userMember.add(null);
			return returnStr;
		}
		
		@Override
		protected void onPostExecute(String result) {
			if(result == null) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(getApplicationContext(),
								"No result !", Toast.LENGTH_LONG)
								.show();
					}
				});
			} else {
				adapter.notifyDataSetChanged();
			}
			
		}	
	
	}
	
}
