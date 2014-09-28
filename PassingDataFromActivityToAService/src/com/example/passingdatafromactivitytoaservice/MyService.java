package com.example.passingdatafromactivitytoaservice;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// We want this service to continue running until it is explicitly
		// stopped, so return sticky.

		Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();

		Object[] objURLs = (Object[]) intent.getExtras().get("URLs");
		URL[] urls = new URL[objURLs.length];
		
		for(int i = 0; i < urls.length; i++)
		{
			urls[i] = (URL) objURLs[i];
		}

		new DoBackGroundTask().execute(urls);
		
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
