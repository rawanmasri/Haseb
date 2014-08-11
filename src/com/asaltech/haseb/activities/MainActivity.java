package com.asaltech.haseb.activities;

import com.asaltech.haseb.activities.R;
import com.asaltech.haseb.utils.SharedPref;
import com.example.bean.User;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
       
        if(SharedPref.getInstance(getApplicationContext()).isLoggedIn()) {
    		Intent intent = new Intent(this, HomePage.class);
        	startActivity(intent);
        	this.finish();
    	}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    public void signUp(View view) {
    	
    	Intent intent = new Intent(getApplicationContext(), DisplaySignupActivity.class);
    	startActivity(intent);
    	
    }
    
    public void login(View view) {
    	
    	Intent intent = new Intent(getApplicationContext(), DisplayLoginActivity.class);
    	startActivity(intent);
    	/*User user =new User();
    	user.setFirst_name("Ahmad");
    	Intent intent = new Intent(this, HomePage.class);
    	
    	intent.putExtra("UserObject", user);
    	startActivity(intent);*/
    }  
    
    
}
