package com.example.intentreturnresults;

import com.example.intentreturnresults.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Second_Activity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_layout);
	}

	public void onClick(View view) {
		Intent data = new Intent();
		// ---get the EditText view---
		EditText txt_username = (EditText) findViewById(R.id.txt_username);
		// ---set the data to pass back---
		data.setData(Uri.parse(txt_username.getText().toString()));
		setResult(RESULT_OK, data);
		// ---closes the activity---
		finish();
	}
}