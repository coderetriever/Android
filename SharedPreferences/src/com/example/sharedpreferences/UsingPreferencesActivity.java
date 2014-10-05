package com.example.sharedpreferences;

import com.example.sharedpreferences.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class UsingPreferencesActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);		
	}

	public void onClickLoad(View view) {
		Intent i = new Intent("com.example.sharedpreferences.AppPreferenceActivity");
		startActivity(i);
		}
	
	public void onClickDisplay(View view) {
		
		//Specify the name of the xml file in the format: <PackageName>_preferences
		//The MODE_PRIVATE constant indicates that the preference fi le can only be opened by the application that created it
		SharedPreferences appPrefs = getSharedPreferences("com.example.sharedpreferences_myapppreferences", MODE_PRIVATE); 
		
        /*SharedPreferences appPrefs = getSharedPreferences("appPreferences", MODE_PRIVATE);*/
		//Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
		DisplayText(appPrefs.getString("editTextPref", "Default"));
	}
	
	private void DisplayText(String str) {
		Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
	}
	
	public void onClickModify(View view) {
		SharedPreferences appPrefs = getSharedPreferences("com.example.sharedpreferences_myapppreferences", MODE_PRIVATE); 

		/*SharedPreferences appPrefs = getSharedPreferences("appPreferences", MODE_PRIVATE);*/
		
		SharedPreferences.Editor prefsEditor = appPrefs.edit();
		prefsEditor.putString("editTextPref", ((EditText) findViewById(R.id.txtString)).getText().toString());
		prefsEditor.commit();
	}
	
	
}