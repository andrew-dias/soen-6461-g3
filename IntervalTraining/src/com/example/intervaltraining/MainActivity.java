package com.example.intervaltraining;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements
		android.view.View.OnClickListener {
	EditText txt;
	Button setting;
	Button viewlog;
	Chronometer chrono;
	ToggleButton toggle;
	long time = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setting = (Button) this.findViewById(R.id.setting);
		viewlog = (Button) this.findViewById(R.id.viewlog);
		chrono = (Chronometer) this.findViewById(R.id.chrono);
		toggle = (ToggleButton) this.findViewById(R.id.toggleButton);
		setting.setOnClickListener(this);
		viewlog.setOnClickListener(this);
		toggle.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (arg0.getId() == R.id.toggleButton) {
			boolean on = ((ToggleButton) arg0).isChecked();
			if (on) {
				chrono.setBase(SystemClock.elapsedRealtime());
				chrono.start();
			} else {
				chrono.setBase(SystemClock.elapsedRealtime());
				chrono.stop();

			}
		}
		if (arg0.getId() == R.id.setting) {
			Intent myIntent = new Intent(this, SettingsActivity.class);
			startActivity(myIntent);
		}
		if (arg0.getId() == R.id.viewlog) {
			Intent myIntent = new Intent(this, ViewLogActivity.class);
			startActivity(myIntent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
