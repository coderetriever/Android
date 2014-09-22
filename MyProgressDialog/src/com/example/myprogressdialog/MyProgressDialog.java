package com.example.myprogressdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MyProgressDialog extends Activity {
	CharSequence[] items = { "Google", "Apple", "Microsoft" };
	boolean[] itemsChecked = new boolean[items.length];

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
	}

	public void onClick(View v) {
		showDialog(0);
	}

	public void onClick2(View v) {
		// ---show the dialog---
		final ProgressDialog dialog = ProgressDialog.show(this,
				"Doing something", "Please wait...", true);
		new Thread(new Runnable() {
			public void run() {
				try {
					// ---simulate doing something lengthy---
					Thread.sleep(5000);
					// ---dismiss the dialog---
					dialog.dismiss();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}