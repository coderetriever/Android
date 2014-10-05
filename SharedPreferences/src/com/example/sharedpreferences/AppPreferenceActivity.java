package com.example.sharedpreferences;

import com.example.sharedpreferences.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.os.Build;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class AppPreferenceActivity extends PreferenceActivity 
{
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		//To change the default name of the preferences file
		/*PreferenceManager prefMgr = getPreferenceManager();
		prefMgr.setSharedPreferencesName("appPreferences");*/		
		
		// ---load the preferences from an XML file---
		addPreferencesFromResource(R.xml.myapppreferences);
	}
}