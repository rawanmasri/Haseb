package com.asaltech.haseb.activities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.asaltech.haseb.utils.JSONParser;
import com.asaltech.haseb.utils.SharedPref;
import com.example.bean.User;
import com.google.gson.Gson;

public class DisplaySignupActivity extends ActionBarActivity {

	private ProgressDialog pDialog;
	private JSONParser jsonParser = new JSONParser();

	// JSONParser jsonParser = new JSONParser();
	private EditText firstName;
	private EditText lastName;
	private EditText phone;
	private EditText eMail;
	EditText password;
	EditText confirmPassword;
	// private static String url_create_account =
	// "http://192.168.43.160/7aseb/signup";
	private String url_create_account;
	public String extra_message;

	// Edit Text

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_signup);

	/*	if (SharedPref.getInstance(getApplicationContext()).isLoggedIn()) {
			Intent intent = new Intent(this, HomePage.class);
			startActivity(intent);
			this.finish();
		}*/

		url_create_account = "http://192.168.43.105/7aseb/signup";
		extra_message = getString(R.string.extra);

		firstName = (EditText) findViewById(R.id.editText1);
		lastName = (EditText) findViewById(R.id.editText2);
		phone = (EditText) findViewById(R.id.editText3);
		eMail = (EditText) findViewById(R.id.editText4);
		password = (EditText) findViewById(R.id.editText5);
		confirmPassword = (EditText) findViewById(R.id.editText6);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_signup, menu);
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

	public static boolean isEmailValid(String email) {
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

	public void createAccount(View view) {
		new createAccountUser().execute();

	}

	class createAccountUser extends AsyncTask<String, String, String> {
		/**
		 * Before starting background thread Show Progress Dialog
		 * */

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
			String message;
			int id;
			String firstname = firstName.getText().toString();
			String lastname = lastName.getText().toString();
			String phonee = phone.getText().toString();
			String email = eMail.getText().toString();
			String pass = password.getText().toString();
			// String pic=""
			String confirmpassword = confirmPassword.getText().toString();
			int[] groupsID = new int[40];

			// Email and password valid .
			if (isEmailValid(email) && pass.equals(confirmpassword)) {
				try {
					// bulid class

					User user = new User(firstname, lastname, email, pass, " ",
							0, phonee);

					Log.d("request!", "starting");
					Gson g = new Gson();
					String data = g.toJson(user);
					String json = jsonParser.makeHttpRequest(
							url_create_account, "POST", data, null);

					user = g.fromJson(json, User.class);

					// check log cat fro response
					Log.d("Create Response", json.toString());

					// check for success tag

					isSuccess = user.isSuccess();

					if (isSuccess) {
						// successfully created product
						// id=user.getId();
						Log.d("Signup  Successful!", json.toString());
						
						SharedPref.getInstance(getApplicationContext())
					    	.setLoginInformation(email, pass);
				         SharedPref.getInstance(getApplicationContext())
						    .setUser(json);
						
						Intent i = new Intent(getApplicationContext(),
								HomePage.class);
						
						
						
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
