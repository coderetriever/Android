package net.learn2develop.BasicViews5;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class BasicViews5Activity extends ListActivity  {
	
    String[] presidents = {
    		"Dwight D. Eisenhower",
    		"John F. Kennedy",
    		"Lyndon B. Johnson",
    		"Richard Nixon",
    		"Gerald Ford",
    		"Jimmy Carter",
    		"Ronald Reagan",
    		"George H. W. Bush",
    		"Bill Clinton",
    		"George W. Bush",
    		"Barack Obama"
    };
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        
        //no need to call setContentView(R.layout.main)

        setListAdapter(new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_checked, presidents));
    }
    
    public void onListItemClick(
    ListView parent, View v, int position, long id)
    {
        Toast.makeText(this,
            "You have selected " + presidents[position],
            Toast.LENGTH_SHORT).show();
    }    
}