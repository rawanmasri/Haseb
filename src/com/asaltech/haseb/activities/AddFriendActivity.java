package com.asaltech.haseb.activities;

import java.util.ArrayList;
import java.util.List;

import com.asaltech.haseb.activities.adapter.SearchAdapter;
import com.asaltech.haseb.utils.JSONParser;
import com.asaltech.haseb.utils.SharedPref;
import com.example.bean.User;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.Intent;
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
	List<User> userMember;
	private JSONParser jsonParser = new JSONParser();
	
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
		user = (new Gson()).fromJson(
				SharedPref.getInstance(getApplicationContext()).getUser(),
				User.class);
		searchBtn = (Button) findViewById(R.id.searchBtn);
		url_add_member = "http://192.168.43.105/7aseb/addmember";
		searchInput = (EditText) findViewById(R.id.searchInput);
		searchList = (ListView) findViewById(R.id.searchList);
		adapter = new SearchAdapter(this, android.R.layout.list_content, resultList);
		searchList.setAdapter(adapter);
		
		searchBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				new addMember().execute();
				//resultList = new String[2];
				//resultList[0] = "hay";
				//resultList[1] = "I wana smoke";
				
			//	adapter = new SearchAdapter(getApplicationContext(), android.R.layout.list_content, resultList);
				// searchList.setAdapter(adapter);
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
	class addMember extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			Gson gsonObject = new Gson();
			//String memberNames = jsonParser.makeHttpRequest(url_add_member, "GET",
				//	searchInput.getText().toString(), user.getApiKey());
			
			
			//  userMember = gsonObject.fromJson(memberNames, ArrayList.class);
			User test=new User("Rawan", "Masri", "RawanEmail", "RawanPass", " h",
					0, "RawanPhone");
			userMember.add(test);
			User test2=new User("Reem", "sal3os", "ReemEmail", "ReemPass", " hh",
					0, "ReemPhone");
			userMember.add(test2);

			if (!userMember.isEmpty()) {
				final String[] separatedNames =new String[userMember.size()];
				 for(int index=0;index<separatedNames.length;index++){
					 separatedNames[index]=userMember.get(index).getFirst_name()+" "+userMember.get(index).getLast_name() ;
					 
				 }
				
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						adapter = new SearchAdapter(getApplicationContext(), android.R.layout.list_content, separatedNames);
						searchList.setAdapter(adapter);
						 
					
					
					}
				});
				
			}
			else {

				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(getApplicationContext(),
								"No result !", Toast.LENGTH_LONG)
								.show();
					}
				});

			}
			return null;
		}}
	
}
