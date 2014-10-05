package com.example.downloadmultipleimages;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import com.example.downloadmultipleimages.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.util.Log;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class DownloadMultipleImages extends Activity 
{
	ImageView img; 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		img = (ImageView) findViewById(R.id.img);
		new DownloadImageTask()
				.execute(
						"http://www.mayoff.com/5-01cablecarDCP01934.jpg",
						"http://www.hartiesinfo.net/greybox/Cable_Car_Hartbeespoort.jpg",
						"http://mcmanuslab.ucsf.edu/sites/default/files/imagepicker/m/mmcmanus/CaliforniaSanFranciscoPaintedLadiesHz.jpg",
						"http://www.fantom-xp.com/wallpapers/63/San_Francisco_-_Sunset.jpg",
						"http://travel.roro44.com/europe/france/Paris_France.jpg",
						"http://wwp.greenwichmeantime.com/time-zone/usa/nevada/las-vegas/hotel/the-strip/paris-las-vegas/paris-las-vegas-hotel.jpg",
						"http://designheaven.files.wordpress.com/2010/04/eiffel_tower_paris_france.jpg");
	}

	private InputStream openHTTPConnection(String urlString) throws IOException {
		InputStream in = null;
		int response = -1;

		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();

		if (!(conn instanceof HttpURLConnection)) {

			throw new IOException("Not an HTTP Connection");
		}

		try {

			HttpURLConnection httpConn = (HttpURLConnection) conn;
			httpConn.setAllowUserInteraction(false);
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setRequestMethod("GET");
			httpConn.connect();
			response = httpConn.getResponseCode();

			if (response == HttpURLConnection.HTTP_OK) {
				in = httpConn.getInputStream();
			}
		} catch (Exception e) {
			Log.d("Networking", e.getLocalizedMessage());
			throw new IOException("Error Connecting");
		}

		return in;
	}

	private Bitmap DownloadImage(String URL) {
		Bitmap bitmap = null;
		InputStream in = null;
		try {
			in = openHTTPConnection(URL);
			bitmap = BitmapFactory.decodeStream(in);
			in.close();
		} catch (IOException e1) {
			Log.d("NetworkingActivity", e1.getLocalizedMessage());
		}
		return bitmap;
	}

	private class DownloadImageTask extends AsyncTask<String, Bitmap, Long> {
		// ---takes in a list of image URLs in String type---
		protected Long doInBackground(String... urls) {
			long imagesCount = 0;
			for (int i = 0; i < urls.length; i++) {
				// ---download the image---
				Bitmap imageDownloaded = DownloadImage(urls[i]);
				if (imageDownloaded != null) {
					// ---increment the image count---
					imagesCount++;
					try {
						// ---insert a delay of 3 seconds---
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// ---return the image downloaded---
					publishProgress(imageDownloaded);
				}
			}
			// ---return the total images downloaded count---
			return imagesCount;
		}

		// ---display the image downloaded---
		protected void onProgressUpdate(Bitmap... bitmap) {
			img.setImageBitmap(bitmap[0]);
		}

		// ---when all the images have been downloaded---
		protected void onPostExecute(Long imagesDownloaded) {
			Toast.makeText(getBaseContext(),
					"Total " + imagesDownloaded + " images downloaded",
					Toast.LENGTH_LONG).show();
		}
	}

}
