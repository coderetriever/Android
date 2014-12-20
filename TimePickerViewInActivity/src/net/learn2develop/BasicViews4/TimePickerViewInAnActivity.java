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

public class TimePickerViewInAnActivity extends Activity {
	TimePicker timePicker;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		timePicker = (TimePicker) findViewById(R.id.timePicker);
		timePicker.setIs24HourView(true);       
	}

	public void onClick(View view) {
		Toast.makeText(getBaseContext(),
				"Time selected:" + timePicker.getCurrentHour() +
				":" + timePicker.getCurrentMinute(),
				Toast.LENGTH_SHORT).show();
	}

}