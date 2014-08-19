package com.asaltech.haseb.activities;
import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.asaltech.haseb.activities.R;
import com.asaltech.haseb.utils.JSONParser;
import com.asaltech.haseb.utils.SharedPref;
import com.example.bean.Auth;
import com.example.bean.Group;
import com.example.bean.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends ActionBarActivity {
	private ProgressDialog pDialog;
	//private JSONParser jsonParser;
	private EditText eMail;
	private EditText password;
	private String loginUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                
        
        //Checking login 
     /*   if(SharedPref.getInstance(getApplicationContext()).isLoggedIn()) {
    		Intent intent = new Intent(this, HomePage.class);
        	startActivity(intent);
        	this.finish();
    	}*/
       // jsonParser= new JSONParser();
        loginUrl = "http://192.168.43.160/7aseb/public/users/login";
		eMail = (EditText) findViewById(R.id.editText1);
		password = (EditText) findViewById(R.id.editText2);
	   final Button loginButton = (Button) findViewById(R.id.button1);
	   final Button sginupButton = (Button) findViewById(R.id.button2);
	   //if you want to open your own account 
	   loginButton.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
        	   new openUserAccount().execute();
           }
       });

	   // if you want to create a new account 
		 sginupButton.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DisplaySignupActivity.class);
            	startActivity(intent);
             }
         });

    }
    //Check if the Email valid 
    private static boolean isEmailValid(String email) {
		boolean isValid = false;
		String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		CharSequence inputStr = email;
		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);
		if (matcher.matches()) {
			isValid = true;
		}
		return isValid;
	}
    //AsynncTask to check the database to open user account 
    class openUserAccount extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog( MainActivity.this);
			pDialog.setMessage("Start Login ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			Boolean isSuccess;
			String email = eMail.getText().toString();
			String pass = password.getText().toString();
			// Valid Email
			if (isEmailValid(email)) {
				try {
					Auth auth = new Auth(email, pass);
					Gson g = new Gson();
					String data = g.toJson(auth);
					String json =  JSONParser.getInstance().makeHttpRequest(loginUrl,"POST", data, null);	
					
					Type listType = new TypeToken<User>() {}.getType();
					User user = g.fromJson(json, listType);	
					
					//int in = user.getGroupName().size();
					//User ahmad=new User("e","r","t","y","u","h");
					//Group group=new Group("g","h",1);
					//ahmad.setGroups(group);
				    
					isSuccess = user.isSuccess();
					// successfully open the account
					if (isSuccess) { 
						SharedPref.getInstance(getApplicationContext()).setLoginInformation(email, pass);			
						SharedPref.getInstance(getApplicationContext()).setUser(json);
						Intent i = new Intent(getApplicationContext(),HomePage.class);		
						startActivity(i);
						finish();

					} else {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(getApplicationContext(), "Login failed!", Toast.LENGTH_LONG).show();
							}
						});
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// Not valid email
			else {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(getApplicationContext(), "Invalid Email !", Toast.LENGTH_LONG).show();
					}
				});
			}
			return null;
		}
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
		}

	}
}
