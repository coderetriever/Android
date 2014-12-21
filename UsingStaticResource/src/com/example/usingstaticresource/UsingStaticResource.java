package com.example.usingstaticresource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.example.usingstaticresource.R;

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
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class UsingStaticResource extends Activity 
{
	EditText textBox;
	static final int READ_BLOCK_SIZE = 100;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		
		textBox = (EditText) findViewById(R.id.txtText1);
		
		InputStream is = this.getResources().openRawResource(R.raw.textfile);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String str = null;
		try 
		{		
			while ((str = br.readLine()) != null) 
			{
				Toast.makeText(getBaseContext(), str, Toast.LENGTH_SHORT).show();
			}
			
			is.close();
			br.close();
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

	}
} 
