package com.asaltech.haseb.activities;

import java.util.Locale;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

import com.asaltech.haseb.utils.SharedPref;
import com.example.bean.User;

public class HomePage extends TabActivity {
	// TabSpec Names
	Bundle userData;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        
        
        TabHost tabHost = getTabHost();
        
        // Home tab
       
         
        // Tab for Setting
        
         
        // Tab for notification
        TabSpec notificationspec = tabHost.newTabSpec("Notification");
        notificationspec.setIndicator("Notification", getResources().getDrawable(R.drawable.notificationxml));
        Intent notificationIntent = new Intent(this, NotificationActivity.class);
        notificationspec.setContent(notificationIntent);
         
        if(Locale.getDefault().getLanguage().equals("en")) {
        	 TabSpec homespec = tabHost.newTabSpec("Home");
             // setting Title and Icon for the Tab
             homespec.setIndicator("Home", getResources().getDrawable(R.drawable.homexml));
             Intent homeIntent = new Intent(this, HomeActivity.class);
             homespec.setContent(homeIntent);
             TabSpec settingspec = tabHost.newTabSpec("Setting");       
             settingspec.setIndicator("Setting", getResources().getDrawable(R.drawable.settingxml));
             Intent settingIntent = new Intent(this, MyAccountActivity.class);
             settingspec.setContent(settingIntent);
        	 tabHost.addTab(homespec); // Adding photos tab
             tabHost.addTab(settingspec); // Adding songs tab
        } else {
        	 TabSpec homespec = tabHost.newTabSpec("«·—∆Ì”Ì…");
             // setting Title and Icon for the Tab
             homespec.setIndicator("«·—∆Ì”Ì…", getResources().getDrawable(R.drawable.homexml));
             Intent homeIntent = new Intent(this, HomeActivity.class);
             homespec.setContent(homeIntent);
             TabSpec settingspec = tabHost.newTabSpec("«·«⁄œ«œ« ");       
             settingspec.setIndicator("«·«⁄œ«œ« ", getResources().getDrawable(R.drawable.settingxml));
             Intent settingIntent = new Intent(this, MyAccountActivity.class);
             settingspec.setContent(settingIntent);
        	 tabHost.addTab(settingspec); // Adding songs tab
        	 tabHost.addTab(homespec); // Adding photos tab
        } 
        	
        // Adding all TabSpec to TabHost
       
     //   tabHost.addTab(notificationspec); // Adding videos tab
         
       
    }
	
	
	
	
	
}
