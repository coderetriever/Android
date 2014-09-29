package com.example.passingdatafromactivitytoaservice;

import java.net.MalformedURLException;
import java.net.URL;

import com.example.passingdatafromactivitytoaservice.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class ServiceActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
    }
    
    public void startService(View view)
    {
    	Intent intent = new Intent(getBaseContext(), MyService.class);
    	
    	try
    	{
    		URL[] urls = new URL[]
    				{
    					new URL("http://www.amazon.com/somefiles.gif"),
    					new URL("http://www.wrox.com/somefiles.pdf"),
    					new URL("http://www.google.com/somefiles.pdf")
    				};
    		
    		intent.putExtra("URLs", urls);
    	}catch(MalformedURLException e)
    	{
    		e.printStackTrace();
    	}
    	
    	startService(intent);
    }
    
    public void stopService(View view)
    {
    	stopService(new Intent(getBaseContext(), MyService.class));
    }
}
