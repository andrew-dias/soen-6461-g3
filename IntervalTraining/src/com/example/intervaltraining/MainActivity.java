package com.example.intervaltraining;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	// settings
	private int intervalDistance;
	private int intervalTime;
	private int timeDecrement;
	private String intervalBeep;
	private String songID;
	
	// statistics
	private int lapCounter = 0;

	// screen view objects
	private TextView timerTextView;
	private TextView lapTextView;

	private CountDownTimer timer;
	
	// format milliseconds into display string
	private String getTimeString(long time) {
		String str = String.format("%1$TS:%1$TL", time);
		str = str.substring(0, str.length()-2);
		return str;		
	}

	// get app settings
	private void setValues() {
		// load default settings from XML if user has not set them yet
		PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
		
		// get stored settings
		SharedPreferences sharedPref = PreferenceManager
				.getDefaultSharedPreferences(this);
		intervalTime = Integer.parseInt(sharedPref.getString(
				"pref_key_interval_time", "0")) * 1000;
		intervalDistance = Integer.parseInt(sharedPref.getString(
				"pref_key_interval_distance", "0"));
		timeDecrement = Integer.parseInt(sharedPref.getString(
				"key_pref_time_decrement", "0"));
		intervalBeep = sharedPref.getString("key_pref_interval_beep", "");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setValues();

		timerTextView = (TextView) this.findViewById(R.id.timerTextView);
		lapTextView = (TextView) this.findViewById(R.id.lapTextView);

		timerTextView.setText(getTimeString(intervalTime));
		
		timer = new CountDownTimer(intervalTime, 100) {

			public void onTick(long millisUntilFinished) {
				timerTextView.setText(getTimeString(millisUntilFinished));
			}

			public void onFinish() {
				lapTextView.setText("Lap: " + ++lapCounter);
				this.start();
			}
		};
	}
	
	public void onStartToggleButtonClicked(View view) {
		// Is the toggle on?
		boolean on = ((ToggleButton) view).isChecked();

		if (on) {
			timer.start();
		} else {
			timer.cancel();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;

		// Handle item selection
		switch (item.getItemId()) {
		case android.R.id.home:
			intent = new Intent(this, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		case R.id.setting_menu_item:
			intent = new Intent(this, Setting.class);
			startActivity(intent);
			return true;
		case R.id.settings_menu_item:
			intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
			return true;
		case R.id.view_log_menu_item:
			intent = new Intent(this, ViewLogActivity.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
