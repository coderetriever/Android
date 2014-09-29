package com.example.threadingthree;

import com.example.threadingthree.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
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
	
	DoCountingTask task;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout); 
        
        txtView1 = (TextView) findViewById(R.id.textView1);
    }    
   
    public void startCounter(View view) {  
    	task = (DoCountingTask) new DoCountingTask().execute();        
    }
    
    public void stopCounter(View view) {  
    	task.cancel(true);
    }    
    
    private class DoCountingTask extends AsyncTask<Void, Integer, Void> {
        protected Void doInBackground(Void... params) {         
            for (int i = 0; i < 1000; i++) {
                //---report its progress---
                publishProgress(i);
                try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {					
					Log.d("Threading", e.getLocalizedMessage());
				}
                if (isCancelled()) break;
            }
            return null;
        }

        protected void onProgressUpdate(Integer... progress) {            
        	txtView1.setText(progress[0].toString());
        	Log.d("Threading", "updating...");
        }
    }    
}
