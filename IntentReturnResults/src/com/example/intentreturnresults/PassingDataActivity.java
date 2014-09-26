package com.example.intentreturnresults;

import com.example.intentreturnresults.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class PassingDataActivity extends Activity {
	int request_Code = 1;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
	}

	public void onClick(View view) {
		// startActivity(new Intent("net.learn2develop.SecondActivity"));
		// or
		// startActivity(new Intent(this, SecondActivity.class));
		startActivityForResult(new Intent(
				"com.example.intentreturnresults.SecondActivity"), request_Code);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == request_Code) {
			if (resultCode == RESULT_OK) {
				Toast.makeText(this, data.getData().toString(),
						Toast.LENGTH_SHORT).show();
			}
		}
	}
}