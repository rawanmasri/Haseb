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
import com.example.bean.Auth;
import com.example.bean.User;
import com.google.gson.Gson;

public class DisplayLoginActivity extends ActionBarActivity {
	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	EditText eMail;
	EditText password;

	private static final String TAG_SUCCESS = "error";
	private static final String TAG_MESSAGE = "message";
	private static final String TAG_ID = "ID";
	private String url_login_account;
	public String extra_message;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_login);

		if (SharedPref.getInstance(getApplicationContext()).isLoggedIn()) {
			Intent intent = new Intent(this, HomePage.class);
			startActivity(intent);
			this.finish();
		}

		url_login_account = "http://192.168.43.105/7aseb/login";
		extra_message = getString(R.string.extra);
		eMail = (EditText) findViewById(R.id.editText1);
		password = (EditText) findViewById(R.id.editText2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_login, menu);
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

	public void loginAccount(View view) {
		new loginAccountUser().execute();

	}

	class loginAccountUser extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			pDialog = new ProgressDialog(DisplayLoginActivity.this);
			pDialog.setMessage("Start Login ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			Boolean isSuccess;
			String email = eMail.getText().toString();
			String pass = password.getText().toString();
			// Valid Email
			if (isEmailValid(email)) {
				try {
					Auth auth = new Auth(email, pass);
					Gson g = new Gson();
					String data = g.toJson(auth);
					// List<NameValuePair> params = new
					// ArrayList<NameValuePair>();
					// params.add(new BasicNameValuePair("email",email));
					// params.add(new BasicNameValuePair("pass", pass));
					String json = jsonParser.makeHttpRequest(url_login_account,
							"POST", data, null);

					User user = g.fromJson(json, User.class);
					
					Log.d("Create Response", json.toString());
					isSuccess = user.isSuccess();
					if (isSuccess) { // successfully created product
						Log.d("Login Successful!", json.toString());

						SharedPref.getInstance(getApplicationContext())
								.setLoginInformation(email, pass);
						SharedPref.getInstance(getApplicationContext())
								.setUser(json);

						Intent i = new Intent(getApplicationContext(),
								HomePage.class);
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
