package com.example.simplesms;

import com.example.simplesms.R;

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

public class EmailActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
    }
    
	public void onClick(View v) {
		sendSMS("5556", "Hello my friends!");
	}
	
	private void sendSMS(String phoneNumber, String message)
	{
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phoneNumber, null, message, null, null);
		Toast.makeText(this, "Attempted to send an SMS", Toast.LENGTH_LONG).show();
	}
}
