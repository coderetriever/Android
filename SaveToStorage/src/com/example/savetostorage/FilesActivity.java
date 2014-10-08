package com.example.savetostorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.example.savetostorage.R;

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

public class FilesActivity extends Activity 
{
	EditText textBox;
	static final int READ_BLOCK_SIZE = 100;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		
		textBox = (EditText) findViewById(R.id.txtText1);

	}
 
	public void onClickSave(View view) {
		String str = textBox.getText().toString();
		try
		{
			FileOutputStream fOut = openFileOutput("textfile.txt", MODE_WORLD_READABLE);                        
			OutputStreamWriter osw = new OutputStreamWriter(fOut);

			//---write the string to the file---
			osw.write(str);
			osw.flush(); 
			osw.close();

			//---display file saved message---
			Toast.makeText(getBaseContext(), "File saved successfully!", Toast.LENGTH_SHORT).show();

			//---clears the EditText---
			textBox.setText("");
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}	
	
	public void onClickLoad(View view) {
		Toast.makeText(getBaseContext(),
				"Attempting!",
				Toast.LENGTH_SHORT).show();
		try
		{
            FileInputStream fIn = new FileInputStream("/data/data/com.example.savetostorage/files/textfile.txt");
            InputStreamReader isr = new InputStreamReader(fIn);
            
			char[] inputBuffer = new char[READ_BLOCK_SIZE];
			String s = "";

			int charRead;
			while ((charRead = isr.read(inputBuffer))>0)
			{
				//---convert the chars to a String---
				String readString =
						String.copyValueOf(inputBuffer, 0,
								charRead);
				s += readString;

				inputBuffer = new char[READ_BLOCK_SIZE];
			}
			//---set the EditText to the text that has been 
			// read---
			textBox.setText(s);

			Toast.makeText(getBaseContext(),
					"File loaded successfully!",
					Toast.LENGTH_SHORT).show();
		}
		catch (IOException ioe) {
			Log.d("Blah", ioe.getMessage());
			ioe.printStackTrace();
		}
	}	
}