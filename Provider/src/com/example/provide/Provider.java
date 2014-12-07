package com.example.provide;

import java.net.MalformedURLException;
import java.net.URL;

import com.example.provide.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.app.ListActivity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.os.Build;
import android.provider.ContactsContract;

public class Provider extends ListActivity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        
        //Uri allContacts = Uri.parse("content://contacts/people");
        Uri allContacts = ContactsContract.Contacts.CONTENT_URI;

        String[] projection = new String[]
                {ContactsContract.Contacts._ID,
                 ContactsContract.Contacts.DISPLAY_NAME,
                 ContactsContract.Contacts.HAS_PHONE_NUMBER};
        
        Cursor c; 
        if (android.os.Build.VERSION.SDK_INT <11) {
        	//---before Honeycomb---
            c = managedQuery(allContacts, projection, 
                    null, null, null);
            
            /*
            c = getContentResolver().query(allContacts, null, null, null, null);
            //---allows the activity to manage the Cursor’s 
            // lifecyle based on the activity’s lifecycle---
            startManagingCursor(c); 
            */
        } else {
        	//---Honeycomb and later---
            CursorLoader cursorLoader = new CursorLoader(
            		this, 
            		allContacts, 
            		projection, 
                    null, null, null);
            c = cursorLoader.loadInBackground();        	
        }
        
        String[] columns = new String[] {
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Contacts._ID};
        
        int[] views = new int[] {R.id.contactName, R.id.contactID};

        /*
         * The SimpleCursorAdapter object maps a cursor to Textviews (or Image views) defined in our XML file.
         * It maps the data (as represented by columns) to views (as represented by views)
         */
        SimpleCursorAdapter adapter;
               
        if (android.os.Build.VERSION.SDK_INT <11) {
        	//---before Honeycomb---
        	adapter = new SimpleCursorAdapter(
        			this, R.layout.main_layout, c, columns, views);
        } else {
        	//---Honeycomb and later---
        	/*
        	 * The flag registers the adapter to be informed when there is a change in the content provider.
        	 */
        	adapter = new SimpleCursorAdapter(
        			this, R.layout.main_layout, c, columns, views,
        			CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);        	
        }        
        this.setListAdapter(adapter);
        
        PrintContacts(c);
    }
    
    private void PrintContacts(Cursor c)
    {
        if (c.moveToFirst()) {
            do{
            String contactID = c.getString(c.getColumnIndex(
                   ContactsContract.Contacts._ID));
            String contactDisplayName = 
                  c.getString(c.getColumnIndex(
                      ContactsContract.Contacts.DISPLAY_NAME));
                Log.v("Content Providers", contactID + ", " +
                    contactDisplayName);
                
            int hasPhone = c.getInt(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
            
            if(hasPhone == 1)
            {
            	Cursor phoneCursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactID, null, null);
            	
            	while(phoneCursor.moveToNext())
            	{
            		Log.v("Content Providers", phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
            	}
            	
            	phoneCursor.close();
            }
            
            } while (c.moveToNext());
        }
    }

}