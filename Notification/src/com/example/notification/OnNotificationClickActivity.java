package com.example.notification;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;

public class OnNotificationClickActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
            
        //---look up the notification manager service---
        NotificationManager nm = (NotificationManager) 
            getSystemService(NOTIFICATION_SERVICE);

        //---cancel the notification that we started--- 
        nm.cancel(getIntent().getExtras().getInt("notificationID"));
    }
}
