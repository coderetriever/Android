package com.example.smsusingintent;

import com.example.smsusingintent.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.os.Build;

public class SMSUsingIntent extends Activity {

	String SENT = "SMS_SENT";
	String DELIVERED = "SMS_DELIVERED";
	
	PendingIntent sentPI, deliveredPI;
	
	BroadcastReceiver smsSentReceiver, smsDeliveredReceiver;
    IntentFilter intentFilter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
	}
    
	public void onClick(View v) 
	{
		Intent i = new Intent(android.content.Intent.ACTION_VIEW);
		i.putExtra("address", "5556; 5558; 5560");
		i.putExtra("sms_body", "Hello my friends");
		i.setType("vnd.android-dir/mms-sms");
		startActivity(i);
	}
}
