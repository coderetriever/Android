package com.example.intentservice;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyIntentService extends IntentService 
{

	public MyIntentService(String name) {
		super("MyIntentServiceName");
		// TODO Auto-generated constructor stub
	}
	
	protected void onHandleIntent(Intent intent)
	{
		try
		{
			int result = downloadFile(new URL("http://www.amazon.com/somefile.pdf"));
		}catch(MalformedURLException e)
		{
			e.printStackTrace();
		}
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
}    

