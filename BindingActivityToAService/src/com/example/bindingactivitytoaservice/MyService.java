package com.example.bindingactivitytoaservice;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

	URL[] urls;
	
    //Create an inner class that extends the Binder class
    public class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }

	//Create an instance of MyBinder that extends Binder
    private final IBinder binder = new MyBinder();
    
    //Return the instance of MyBinder
	@Override
	public IBinder onBind(Intent arg0) {
		return binder;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// We want this service to continue running until it is explicitly
		// stopped, so return sticky.

		try {
			new DoBackGroundTask().execute(new URL(
					"http://www.amazon.com/somefiles.pdf"), new URL(
					"http://www.wrox.com/somefiles.pdf"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
		return START_STICKY;
	}

	private int downloadFile(URL url) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return 100;
	}

	private class DoBackGroundTask extends AsyncTask<URL, Integer, Long> {
		@Override
		protected Long doInBackground(URL... urls) {
			int count = urls.length;

			long totalBytesDownloaded = 0;

			for (int i = 0; i < count; i++) {
				totalBytesDownloaded += downloadFile(urls[i]);
				publishProgress((int) (((i + 1) / (float) count) * 100));
			}
			return totalBytesDownloaded;
		}
		
		protected void onProgressUpdate(Integer... progress)
		{
			Log.d("Downloading files", String.valueOf(progress[0]) + "% downloaded");
			Toast.makeText(getBaseContext(), String.valueOf(progress[0]) + "% downloaded", Toast.LENGTH_LONG).show();			
		}
		
		protected void onPostExecute(Long result)
		{
			Toast.makeText(getBaseContext(), "Downloaded " + result + " bytes", Toast.LENGTH_LONG).show();
			stopSelf();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
	}
}
