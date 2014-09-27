package com.example.simpleservice;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service 
{
	@Override
	public IBinder onBind(Intent arg0)
	{
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		//We want this service to continue running until it is explicitly stopped, so return sticky.
		//Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
		
		try
		{
			int result = downloadFile(new URL("http://www.amazon.com/somefile.pdf"));
			Toast.makeText(getBaseContext(), "Downloaded " + result + " bytes", Toast.LENGTH_LONG).show();
			
		}catch(MalformedURLException e)
		{
			e.printStackTrace();
		}
		return START_STICKY;
	}
	
	private int downloadFile(URL url)
	{
		try
		{
			Thread.sleep(5000);
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		
		return 100;
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
	}
}    

