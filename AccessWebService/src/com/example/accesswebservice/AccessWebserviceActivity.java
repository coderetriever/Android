package com.example.accesswebservice;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.example.accesswebservice.R;

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

public class AccessWebserviceActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);

		new AccessWebServiceTask().execute("apple");
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

	private String WordDefinition(String word) {
		InputStream in = null;
		String strDefinition = "";
		try {
			in = openHTTPConnection("http://services.aonaware.com/DictService/DictService.asmx/Define?word="
					+ word);
			Document doc = null;
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			try {
				db = dbf.newDocumentBuilder();
				doc = db.parse(in);
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			doc.getDocumentElement().normalize();

			// ---retrieve all the <Definition> elements---
			NodeList definitionElements = doc
					.getElementsByTagName("Definition");

			// ---iterate through each <Definition> elements---
			for (int i = 0; i < definitionElements.getLength(); i++) {
				Node itemNode = definitionElements.item(i);
				if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
					// ---convert the Definition node into an Element---
					Element definitionElement = (Element) itemNode;

					// ---get all the <WordDefinition> elements under
					// the <Definition> element---
					NodeList wordDefinitionElements = (definitionElement)
							.getElementsByTagName("WordDefinition");

					strDefinition = "";
					// ---iterate through each <WordDefinition> elements---
					for (int j = 0; j < wordDefinitionElements.getLength(); j++) {
						// ---convert a <WordDefinition> node into an Element---
						Element wordDefinitionElement = (Element) wordDefinitionElements
								.item(j);

						// ---get all the child nodes under the
						// <WordDefinition> element---
						NodeList textNodes = ((Node) wordDefinitionElement)
								.getChildNodes();

						strDefinition += ((Node) textNodes.item(0))
								.getNodeValue() + ". \n";
					}

				}
			}
		} catch (IOException e1) {
			Log.d("NetworkingActivity", e1.getLocalizedMessage());
		}
		// ---return the definitions of the word---
		return strDefinition;
	}

	private class AccessWebServiceTask extends AsyncTask<String, Void, String> {
		protected String doInBackground(String... urls) {
			return WordDefinition(urls[0]);
		}
		
		protected void onPostExecute(String result) {
			Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG).show();
		}
	}

}
