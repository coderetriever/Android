package com.example.threadingtwo;

import com.example.threadingtwo.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
   
	static Handler UIupdater = new Handler() {
		@Override
		public void handleMessage(Message msg) {              
			byte[] buffer = (byte[]) msg.obj;
			
			//---convert the entire byte array to string---
			String strReceived = new String(buffer);

			//---display the text received on the TextView---              
			txtView1.setText(strReceived);
			Log.d("Threading", "running");
		}
	};
    
    public void startCounter(View view) {  
    	new Thread(new Runnable() {
    		@Override
    		public void run() {    			
    			for (int i=0; i<=1000; i++) {    				
    				//---update the main activity UI using a Handler
                    ThreadingActivity.UIupdater.obtainMessage(
                        0,  String.valueOf(i).getBytes() ).sendToTarget();
    				
    				//---insert a delay    				
    				try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						Log.d("Threading", e.getLocalizedMessage());
					}
    			}
    		}    		
    	}).start();
    }
    
}
