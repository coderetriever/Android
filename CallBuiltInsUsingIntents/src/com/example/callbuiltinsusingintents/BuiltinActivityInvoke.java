package com.example.callbuiltinsusingintents;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class BuiltinActivityInvoke extends Activity {

	int request_Code = 1;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
	}

	public void onClickWebBrowser(View view) {
		/*
		Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://www.amazon.com"));
		*/
		/*
		Intent i = new Intent("android.intent.action.VIEW", Uri.parse("http://www.amazon.com"));
		*/
		
        Intent i = new Intent("android.intent.action.VIEW");
        i.setData(Uri.parse("http://www.amazon.com"));		
		startActivity(i);
	}

	public void onClickMakeCalls(View view) {        
		Intent i = new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:+2899885056"));
		startActivity(i);
        
		/*
		Intent i = new Intent(android.content.Intent.ACTION_CALL, Uri.parse("tel:+651234567"));
		startActivity(i);
		*/		
	}

	public void onClickShowMap(View view) {
		Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:37.827500,-122.481670"));
		startActivity(i);

	}
	
	public void onClickLaunchMyBrowser(View view) {
		
		Intent i = new Intent("com.example.callbuiltinsusingintents.mybrowseractivity");
        i.setData(Uri.parse("http://www.amazon.com"));
        startActivity(i);
        		
      
        //Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://www.amazon.com"));
        //i.addCategory("net.learn2develop.Apps");
        //---this category does not match any in the intent-filter---
        /*i.addCategory("com.example.callbuiltinsusingintents.OtherApps");
        i.addCategory("com.example.callbuiltinsusingintents.SomeOtherApps");
        startActivity(Intent.createChooser(i, "Open URL using..."));*/
	}
	
}