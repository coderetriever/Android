package com.example.invokeactivityfromaservice;

import com.example.simpleservice.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.app.Activity;
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

public class ServiceActivity extends Activity {
	IntentFilter intentFilter;
	
	private BroadcastReceiver intentReceiver = new BroadcastReceiver()
	{
		@Override
		public void onReceive(Context context, Intent intent)
		{
			Toast.makeText(getBaseContext(), "File downloaded!", Toast.LENGTH_LONG).show();
		}
	};
	
	@Override
	public void onResume()
	{
		super.onResume();
	
		intentFilter = new IntentFilter();
		intentFilter.addAction("FILE_DOWNLOAD_ACTION");
		
		registerReceiver(intentReceiver, intentFilter);
	}
    
	@Override
	public void onPause()
	{
		unregisterReceiver(intentReceiver);
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
    }
    
    public void startService(View view)
    {
    	startService(new Intent(getBaseContext(), MyIntentService.class));
    }
    
    public void stopService(View view)
    {
    	stopService(new Intent(getBaseContext(), MyIntentService.class));
    }
}
