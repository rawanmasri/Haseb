package com.asaltech.haseb.activities;
import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.asaltech.haseb.utils.JSONParser;
import com.asaltech.haseb.utils.SharedPref;
import com.example.bean.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DisplaySignupActivity extends ActionBarActivity {
	private ProgressDialog pDialog;
	//private JSONParser jsonParser;
	private EditText firstName;
	private EditText lastName;
	private EditText phone;
	private EditText eMail;
	private EditText password;
	private EditText confirmPassword;
	private String signupUrl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_signup);
	  /*  if (SharedPref.getInstance(getApplicationContext()).isLoggedIn()) {
			Intent intent = new Intent(this, HomePage.class);
			startActivity(intent);
			this.finish();
		}*/
		signupUrl = "http://192.168.43.160/7aseb/public/users/signup";
		//jsonParser = new JSONParser();
		firstName = (EditText) findViewById(R.id.editText1);
		lastName = (EditText) findViewById(R.id.editText2);
		phone = (EditText) findViewById(R.id.editText3);
		eMail = (EditText) findViewById(R.id.editText4);
		password = (EditText) findViewById(R.id.editText5);
		confirmPassword = (EditText) findViewById(R.id.editText6);
		final Button sginupButton = (Button) findViewById(R.id.button1);
		sginupButton.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            	 new createAccountUser().execute();
             }
         });

	}
     //Checking the validity of Email 
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
	class createAccountUser extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(DisplaySignupActivity.this);
			pDialog.setMessage("Creating Account...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... args) {
			Boolean isSuccess;
			String firstname = firstName.getText().toString();
			String lastname = lastName.getText().toString();
			String phonee = phone.getText().toString();
			String email = eMail.getText().toString();
			String pass = password.getText().toString();
			String confirmpassword = confirmPassword.getText().toString();
			// Email and password valid .
			if (isEmailValid(email) && pass.equals(confirmpassword)) {
				try {
					User user = new User(firstname, lastname, email, pass, " ", phonee);	
					Gson g = new Gson();
					String data = g.toJson(user);
					String json =  JSONParser.getInstance().makeHttpRequest(signupUrl, "POST", data, null);
					Type listType = new TypeToken<User>() {}.getType();
				     user = g.fromJson(json, listType);	
					//user = g.fromJson(json, User.class);
					// check for success sign up
					isSuccess = user.isSuccess();
					if (isSuccess) {
						// successfully sign up 
						SharedPref.getInstance(getApplicationContext()).setLoginInformation(email, pass);
				        SharedPref.getInstance(getApplicationContext()) .setUser(json);
						Intent i = new Intent(getApplicationContext(),HomePage.class);
						startActivity(i);
						finish();
						// closing this screen
					} else {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(getApplicationContext(), "Signup failed!", Toast.LENGTH_LONG).show();
							}
						});	
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(getApplicationContext(), "Signup failed!", Toast.LENGTH_LONG).show();
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
