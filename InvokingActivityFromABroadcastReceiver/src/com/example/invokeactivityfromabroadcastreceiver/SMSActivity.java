package com.example.invokeactivityfromabroadcastreceiver;

import com.example.invokeactivityfromabroadcastreceiver.R;

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
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class SMSActivity extends Activity {

	String SENT = "SMS_SENT";
	String DELIVERED = "SMS_DELIVERED";
		
	BroadcastReceiver smsSentReceiver, smsDeliveredReceiver;
    IntentFilter intentFilter;

    private BroadcastReceiver intentReceiver = new BroadcastReceiver()
    {
		@Override
		public void onReceive(Context context, Intent intent) {
    		TextView SMSes = (TextView) findViewById(R.id.textView1);
    		SMSes.setText(intent.getExtras().getString("sms"));    	    				
		}
    };
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		
		intentFilter = new IntentFilter();
		intentFilter.addAction("SMS_RECEIVED_ACTION");
		
        //---register the receiver---
        registerReceiver(intentReceiver, intentFilter);				
	}
    
	@Override
	public void onResume() {
		super.onResume();
	}
	
	@Override
	public void onPause() 
	{
		super.onPause();
		
        //---unregister the receiver---
        unregisterReceiver(intentReceiver);
	}		
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		unregisterReceiver(intentReceiver);
	}
}
