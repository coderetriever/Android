package com.example.bindingactivitytoaservice;

import java.net.MalformedURLException;
import java.net.URL;

import com.example.bindingactivitytoaservice.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class ServiceActivity extends Activity {

	//Acts as a reference to the service
    MyService serviceBinder;
    Intent i;

    //Instance of ServiceConnection is used to monitor the state of the service
    private ServiceConnection connection = new ServiceConnection() 
    {
    	
    	//Called when the activity is connected to the service
        public void onServiceConnected(ComponentName className, IBinder service) {
            //---called when the connection is made---
            serviceBinder = ((MyService.MyBinder)service).getService();
            try {
                URL[] urls = new URL[] {
                    new URL("http://www.amazon.com/somefiles.pdf"),
                    new URL("http://www.wrox.com/somefiles.pdf"),
                    new URL("http://www.google.com/somefiles.pdf"),
                    new URL("http://www.learn2develop.net/somefiles.pdf")};
                    //---assign the URLs to the service through the serviceBinder object---
                    serviceBinder.urls = urls;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }   
                startService(i);
        }
        
        //Called when the service is disconnected from the activity
        public void onServiceDisconnected(ComponentName className) {
            //---called when the service disconnects---
            serviceBinder = null;
        }
    };
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
    }
    
    public void startService(View view)
    {
    	i = new Intent(ServiceActivity.this, MyService.class);
    	
    	//Before starting the service, bind the activity to the service
    	//<intent object>, <ServiceConnection object>, <flag to indicate how the service should be bound>    	
    	bindService(i, connection, Context.BIND_AUTO_CREATE); 
    }
    
    public void stopService(View view)
    {
    	stopService(new Intent(getBaseContext(), MyService.class));
    }
}
