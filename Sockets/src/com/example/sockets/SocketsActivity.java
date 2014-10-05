package com.example.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.example.sockets.R;

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
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class SocketsActivity extends Activity 
{
    static final String NICKNAME = "Nickname";
	//---socket---
	InetAddress serverAddress;
	Socket socket;
	
	//---all the Views---    
	static TextView txtMessagesReceived;
	EditText txtMessage;

	//---thread for communicating on the socket---
	CommunicationThread communicationThread;

	//---used for updating the UI on the main activity---
	static Handler UIupdater = new Handler() {
		@Override
		public void handleMessage(Message msg) {              
			int numOfBytesReceived = msg.arg1;
			byte[] buffer = (byte[]) msg.obj;
			
			//---convert the entire byte array to string---
			String strReceived = new String(buffer);

			//---extract only the actual string received---
			strReceived = strReceived.substring(
					0, numOfBytesReceived);

			//---display the text received on the TextView---              
			txtMessagesReceived.setText(
					txtMessagesReceived.getText().toString() + 
					strReceived);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);

		//---get the views---
		txtMessage = (EditText) findViewById(R.id.txtMessage);
		txtMessagesReceived = (TextView)
				findViewById(R.id.txtMessagesReceived);
	}
	
    @Override
    public void onResume() {
        super.onResume();
        new CreateCommThreadTask().execute();
    }
    
    private class CreateCommThreadTask extends AsyncTask
    <Void, Integer, Void> {
		@Override
        protected Void doInBackground(Void... params) {            
            try {
                //---create a socket---
            	Log.d("Sockets", "Started");
                serverAddress = 
                    InetAddress.getByName("192.168.1.71");
                socket = new Socket(serverAddress, 9889);
                Log.d("Sockets", "Connected");
                communicationThread = new CommunicationThread(socket);
                communicationThread.start();                
    			//---sign in for the user; sends the nick name---
    			sendToServer(NICKNAME);
            } catch (UnknownHostException e) {
                Log.d("Sockets", e.getLocalizedMessage());
            } catch (IOException e) {
            	Log.d("Sockets", e.getLocalizedMessage());
            }
            return null;
        }
    }

	public void onClickSend(View view) {
		//---send the message to the server---
		Toast.makeText(this, "Sending " + txtMessage.getText().toString(), Toast.LENGTH_LONG).show();
		sendToServer(txtMessage.getText().toString());
	}
    
	private void sendToServer(String message) {		
        new WriteToServerTask().execute(message);		
	}    

    private class WriteToServerTask extends AsyncTask
    <String, Void, Void> {
        protected Void doInBackground(String... data) {
            communicationThread.write(data[0]);
            return null;
        }
    }
	
    @Override
    public void onPause() {
        super.onPause();
        new CloseSocketTask().execute();
    }

    private class CloseSocketTask extends AsyncTask
    <Void, Void, Void> {        
        @Override
        protected Void doInBackground(Void... params) {
            try {
                socket.close();
            } catch (IOException e) {
            	Log.d("Sockets", e.getLocalizedMessage());                
            }
            return null;
        }
    }    
}