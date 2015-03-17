package com.android.adapter.basicadapter2;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ListActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.menu_entries, R.layout.list_element);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView lv, View clickedView, int position, long id){
        super.onListItemClick(lv, clickedView, position, id);

        TextView tv = (TextView) clickedView;

        Toast.makeText(getBaseContext(), tv.getText(), Toast.LENGTH_SHORT).show();
    }
}
