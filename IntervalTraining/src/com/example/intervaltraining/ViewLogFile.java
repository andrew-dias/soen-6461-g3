package com.example.intervaltraining;

import java.text.DateFormat;
import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ViewLogFile extends Activity {

	private TextView dateTextView;
	private TextView intervalTimeTextView;
	private TextView lapDistanceTextView;
	private TextView timeDecrementTextView;
	private TextView lapsCompletedTextView;
	private TextView lastLapTimeTextView;
	private TextView totalDistanceTextView;
	private TextView totalTimeTextView;
	private TextView initialSpeedTextView;
	private TextView topSpeedTextView;
	private TextView avgSpeedTextView;
	
	String dateText,initialTimeText, lapDistanceText, timeDecrementText, lapsCompletedText,
	lastLapTimeText, totalDistanceText,totalTimeText, initialSpeedText, 
	topSpeedText,avgSpeedText;
    
		
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_log_file);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		String value=null;
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String str = null;
		    value = extras.getString(str);
		    
		}
		// set screen objects
		dateTextView = (TextView) this.findViewById(R.id.dateTextView);
		intervalTimeTextView = (TextView) this
				.findViewById(R.id.intervalTimeTextView);
		lapDistanceTextView = (TextView) this
				.findViewById(R.id.lapDistanceTextView);
		timeDecrementTextView = (TextView) this
				.findViewById(R.id.timeDecrementTextView);
		lapsCompletedTextView = (TextView) this
				.findViewById(R.id.lapsCompletedTextView);
		lastLapTimeTextView = (TextView) this
				.findViewById(R.id.lastLapTimeTextView);
		totalDistanceTextView = (TextView) this
				.findViewById(R.id.totalDistanceTextView);
		totalTimeTextView = (TextView) this
				.findViewById(R.id.totalTimeTextView);
		initialSpeedTextView = (TextView) this
				.findViewById(R.id.initialSpeedTextView);
		topSpeedTextView = (TextView) this.findViewById(R.id.topSpeedTextView);
		avgSpeedTextView = (TextView) this.findViewById(R.id.avgSpeedTextView);

		setDisplayValues(value);
	}
	
	private String doubleFormat (double num) {
		return new DecimalFormat("#.##").format(num);
	}

	private void setDisplayValues(String stats) {
		
		String[] splited = stats.split("\\|");
		dateText = splited[0];
		initialTimeText = splited[1];;
		lapDistanceText = splited[2];;
		timeDecrementText = splited[3];;
		lapsCompletedText = splited[4];;
		lastLapTimeText = splited[5];;
		totalDistanceText = splited[6];;
		totalTimeText = splited[7];;
		initialSpeedText = splited[8];;
		topSpeedText = splited[9];
		avgSpeedText = splited[10];;
	    
		dateTextView.setText(dateText);
		intervalTimeTextView.setText(initialTimeText);
		lapDistanceTextView.setText(lapDistanceText);
		timeDecrementTextView.setText(timeDecrementText);
		lapsCompletedTextView.setText(lapsCompletedText);
		lastLapTimeTextView.setText(lastLapTimeText);
		totalDistanceTextView.setText(totalDistanceText);
		totalTimeTextView.setText(totalTimeText);
		initialSpeedTextView.setText(initialSpeedText);
		topSpeedTextView.setText(topSpeedText);
		avgSpeedTextView.setText(avgSpeedText);
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
