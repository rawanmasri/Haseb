package com.asaltech.haseb.activities;

import java.util.Locale;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
@SuppressWarnings("deprecation")
public class HomePage extends TabActivity {
	// TabSpec Names
	Bundle userData;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        TabHost tabHost = getTabHost();
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
        	 tabHost.addTab(homespec); 
             tabHost.addTab(settingspec);
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
        	 tabHost.addTab(settingspec); 
        	 tabHost.addTab(homespec);
        }        
    }
}
