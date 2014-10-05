package com.example.simplehttpconnection;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import com.example.simplehttpconnection.R;

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

public class HTTPConnectionActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		Toast.makeText(this, "Started", Toast.LENGTH_LONG).show();
		new DownloadImageTask().execute("http://www.it-ebooks.info/images/ebooks/2/beginning_ios_game_development.jpg");
	}
    
   private InputStream openHTTPConnection(String urlString) throws IOException
   {
	   InputStream in = null;
	   int response = -1;
	   
	   URL url = new URL(urlString);
	   URLConnection conn = url.openConnection();
	   
	   if(!(conn instanceof HttpURLConnection))
	   {
		   
		   throw new IOException("Not an HTTP Connection");
	   }
   
	   try
	   {
		   
		   HttpURLConnection httpConn = (HttpURLConnection) conn;
		   httpConn.setAllowUserInteraction(false);
		   httpConn.setInstanceFollowRedirects(true);
		   httpConn.setRequestMethod("GET");
		   httpConn.connect();
		   response = httpConn.getResponseCode();
		   
		   if(response == HttpURLConnection.HTTP_OK)
		   {
			   in = httpConn.getInputStream();
		   }		   
	   }catch(Exception e)
	   {
		   Log.d("Networking", e.getLocalizedMessage());
		   throw new IOException("Error Connecting");
	   }
	   
	   return in;
   }
   
   private Bitmap DownloadImage(String URL)
   {        
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
   private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
   	protected Bitmap doInBackground(String... urls) {
   		return DownloadImage(urls[0]);
   	}
   	
   	protected void onPostExecute(Bitmap result) {
   		ImageView img = (ImageView) findViewById(R.id.img);
   		img.setImageBitmap(result);
   	}
   }   
}
