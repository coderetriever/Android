package net.learn2develop.BasicViews4;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class DatePickerViewInAnActivity extends Activity {
	DatePicker datePicker;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		datePicker = (DatePicker) findViewById(R.id.datePicker);		     
	}

	public void onClick(View view) {
		Toast.makeText(getBaseContext(),
				"Date selected:" + (datePicker.getMonth() + 1) +
				"/" + datePicker.getDayOfMonth() +
				"/" + datePicker.getYear() + "\n",
				Toast.LENGTH_SHORT).show();
	}

}