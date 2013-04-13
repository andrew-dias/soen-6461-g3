package com.example.intervaltraining;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	/*
	 * EditText txt; Button setting; Button viewlog; Chronometer chrono;
	 * ToggleButton toggle; long time = 0;
	 */

	private int distance = 15;
	private int lapTime = 5000;
	private int decrement = 10;
	private int lapCounter = 0;

	private CountDownTimer timer;
	private TextView timerTextView;
	private TextView lapTextView;

	private String getTimeString(long time) {
		String format = String.format("%%0%dd", 2);
		time = time / 1000;
		String seconds = String.format(format, time % 60);
		String minutes = String.format(format, (time % 3600) / 60);
		return (minutes + ":" + seconds);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		timerTextView = (TextView) this.findViewById(R.id.timerTextView);
		lapTextView = (TextView) this.findViewById(R.id.lapTextView);

		timerTextView.setText(getTimeString(lapTime));
		/*
		 * setting = (Button) this.findViewById(R.id.setting); viewlog =
		 * (Button) this.findViewById(R.id.viewlog); chrono = (Chronometer)
		 * this.findViewById(R.id.chrono); toggle = (ToggleButton)
		 * this.findViewById(R.id.startToggleButton);
		 * setting.setOnClickListener(this); viewlog.setOnClickListener(this);
		 * toggle.setOnClickListener(this);
		 */
		timer = new CountDownTimer(lapTime, 100) {

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

		/*
		 * if (view.getId() == R.id.setting) { Intent myIntent = new
		 * Intent(this, SettingsActivity.class); startActivity(myIntent); } if
		 * (view.getId() == R.id.viewlog) { Intent myIntent = new Intent(this,
		 * ViewLogActivity.class); startActivity(myIntent); }
		 */}

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
