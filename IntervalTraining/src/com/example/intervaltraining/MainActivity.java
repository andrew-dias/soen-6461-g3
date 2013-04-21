package com.example.intervaltraining;

import java.util.Date;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
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

	// application settings
	private int intervalDistance;
	private int intervalTime;
	private int timeDecrement;
	private String intervalBeep;
	
	// statistics
	private Date currentDate;
	private int lapCounter = 0;

	// screen objects
	private TextView timerTextView;
	private TextView lapTextView;

	private CountDownTimer intervalTimer;
	
	// format milliseconds into display string
	private String getTimeString(long time) {
		String str = String.format("%1$TS:%1$TL", time);
		str = str.substring(0, str.length()-2);
		return str;		
	}

	// get app settings
	private void setAppSettings() {
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

	// TODO: This doesn't work
	private void playBeep(){
		Uri beepUri = Uri.parse(intervalBeep);
		Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), beepUri);
		r.play();			
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	// cancel the timer if the user leaves the screen
	protected void onPause() {
		super.onPause();
		intervalTimer.cancel();
	}
	
	// set the interface elements
	protected void onResume() {
		super.onResume();

		// load the application settings
		setAppSettings();

		// set screen objects
		timerTextView = (TextView) this.findViewById(R.id.timerTextView);
		lapTextView = (TextView) this.findViewById(R.id.lapTextView);

		// display starting interval time
		timerTextView.setText(getTimeString(intervalTime));
		
		// the interval timer
		intervalTimer = new CountDownTimer(intervalTime, 100) {

			// update the timer display
			public void onTick(long millisUntilFinished) {
				timerTextView.setText(getTimeString(millisUntilFinished));
			}

			// TODO: Decrement the time every other lap 
			public void onFinish() {
				playBeep();
				lapTextView.setText("Lap: " + ++lapCounter);
				this.start();
			}
		};		
	}
		
	public void onStartToggleButtonClicked(View view) {
		// Is the toggle on?
		boolean on = ((ToggleButton) view).isChecked();

		if (on) {
			// TODO: Play music
			currentDate = new Date();
			intervalTimer.start();
		} else {
			intervalTimer.cancel();
			if (lapCounter > 0) {
				// generate statistics
				IntervalStatistics stats = new IntervalStatistics(currentDate, intervalTime, intervalDistance, timeDecrement, lapCounter);
				// send user to statistics screen
				Intent intent = new Intent(this, StatisticsActivity.class);
				intent.putExtra("com.example.intervaltraining.IntervalStatistics", stats);
				startActivity(intent);
				
				// TODO: change button text to 'Reset'.
			}
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
