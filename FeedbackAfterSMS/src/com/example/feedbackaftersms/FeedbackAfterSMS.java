package com.example.feedbackaftersms;

import com.example.feedbackaftersms.R;

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

public class FeedbackAfterSMS extends Activity {

	String SENT = "SMS_SENT";
	String DELIVERED = "SMS_DELIVERED";
	
	PendingIntent sentPI, deliveredPI;
	
	BroadcastReceiver smsSentReceiver, smsDeliveredReceiver;
    IntentFilter intentFilter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);

		sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);

		deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);
	}
    
	@Override
	public void onResume() {
		super.onResume();

        //---register the receiver---
        //registerReceiver(intentReceiver, intentFilter);
		
		//---create the BroadcastReceiver when the SMS is sent---
		smsSentReceiver = new BroadcastReceiver(){
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode())
				{
				case Activity.RESULT_OK:
					Toast.makeText(getBaseContext(), "SMS sent",
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					Toast.makeText(getBaseContext(), "Generic failure",
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					Toast.makeText(getBaseContext(), "No service",
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					Toast.makeText(getBaseContext(), "Null PDU",
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Toast.makeText(getBaseContext(), "Radio off",
							Toast.LENGTH_SHORT).show();
					break;
				}
			}
		};

		//---create the BroadcastReceiver when the SMS is delivered---
		smsDeliveredReceiver = new BroadcastReceiver(){
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode())
				{
				case Activity.RESULT_OK:
					Toast.makeText(getBaseContext(), "SMS delivered",
							Toast.LENGTH_SHORT).show();
					break;
				case Activity.RESULT_CANCELED:
					Toast.makeText(getBaseContext(), "SMS not delivered",
							Toast.LENGTH_SHORT).show();
					break;
				}
			}
		}; 

		//---register the two BroadcastReceivers---
		registerReceiver(smsDeliveredReceiver, new IntentFilter(DELIVERED));      
		registerReceiver(smsSentReceiver, new IntentFilter(SENT));
	}
	
	@Override
	public void onPause() {
		super.onPause();
		
        //---unregister the receiver---
        //unregisterReceiver(intentReceiver);

		//---unregister the two BroadcastReceivers---
		unregisterReceiver(smsSentReceiver);
		unregisterReceiver(smsDeliveredReceiver);    	
	}	
	
	public void onClick(View v) {
		sendSMS("5556", "Hello my friends!");
	}
	
	private void sendSMS(String phoneNumber, String message)
	{
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
	}
}
