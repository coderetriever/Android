package com.example.threading;

import com.example.threading.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class ThreadingActivity extends Activity {
	static TextView txtView1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout); 
        
        txtView1 = (TextView) findViewById(R.id.textView1);
    }    
    
    public void startCounter(View view) 
    {  
    	//Do not hangup the UI
    	new Thread(new Runnable() {
    		@Override
    		public void run()
    		{
    			for(int i = 0; i <= 1000; i++)
    			{
    				final int valueOfI = i;
    				
    				//Place the job of updating the UI in the message queue
    				txtView1.post(new Runnable() {
    					public void run()
    					{
    						txtView1.setText(String.valueOf(valueOfI));
    					}
    				});
    				
    				try
    				{
    					Thread.sleep(1000);
    				}catch(InterruptedException e)
    				{
    					Log.d("Threading ", e.getLocalizedMessage());
    				}
    			}
    		}
    	}
    	).start();        
    }       
}
