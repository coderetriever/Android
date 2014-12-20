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

public class TimePickerInADialog extends Activity {
	TimePicker timePicker;
	DatePicker datePicker;

	int hour, minute;
	int yr, month, day;

	static final int TIME_DIALOG_ID = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		timePicker = (TimePicker) findViewById(R.id.timePicker);
		timePicker.setIs24HourView(true);

		showDialog(TIME_DIALOG_ID);
	}

	@Override
	protected Dialog onCreateDialog(int id)
	{
		switch (id) {
		case TIME_DIALOG_ID:
			return new TimePickerDialog(
					this, mTimeSetListener, hour, minute, false);
		}
		return null;
	}

	private TimePickerDialog.OnTimeSetListener mTimeSetListener =
			new TimePickerDialog.OnTimeSetListener()
	{
		public void onTimeSet(
				TimePicker view, int hourOfDay, int minuteOfHour)
		{
			hour = hourOfDay;
			minute = minuteOfHour;

			SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm aa");			
			Date date = new Date(0,0,0, hour, minute);
			String strDate = timeFormat.format(date);

			Toast.makeText(getBaseContext(),
					"You have selected " + strDate,
					Toast.LENGTH_SHORT).show();			
		}
	};

	public void onClick(View view) {
		Toast.makeText(getBaseContext(),
				"Time selected:" + timePicker.getCurrentHour() +
				":" + timePicker.getCurrentMinute(),
				Toast.LENGTH_SHORT).show();
	}
}