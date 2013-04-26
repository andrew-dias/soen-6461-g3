package com.example.intervaltraining;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Calendar;

import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StatisticsActivity extends Activity  {

	// screen objects
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
	
	private IntervalStatistics stats;
	String dateText,initialTimeText, lapDistanceText, timeDecrementText, lapsCompletedText,
	lastLapTimeText, totalDistanceText,totalTimeText, initialSpeedText, 
	topSpeedText,avgSpeedText;
    
		
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistics);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Bundle b = getIntent().getExtras();
		stats = b
				.getParcelable("com.example.intervaltraining.IntervalStatistics");

		
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

		setDisplayValues(stats);
	}
	
	private String doubleFormat (double num) {
		return new DecimalFormat("#.##").format(num);
	}

	private void setDisplayValues(IntervalStatistics stats) {
		dateText = DateFormat.getDateInstance().format(stats.getDate());
		initialTimeText = stats.getInitialTime() / 1000 + "s";
		lapDistanceText = stats.getLapDistance() + "m";
		timeDecrementText = stats.getTimeDecrement() + "%";
		lapsCompletedText = stats.getLapsCompleted() + "";
		lastLapTimeText = stats.getLastLapTime() / 1000 + "s";
		totalDistanceText = stats.getTotalDistance() + "m";
		totalTimeText = stats.getTotalTime() / 1000 + "s";
		initialSpeedText = doubleFormat(stats.getInitialSpeed()) + " km/h";
		topSpeedText = doubleFormat(stats.getTopSpeed()) + " km/h";
		avgSpeedText = doubleFormat(stats.getAvgSpeed()) + " km/h";
	    
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

	public void saveStatistics(View view) {
		// TODO: save statistics
		Toast.makeText(this, "Statistics saved", Toast.LENGTH_SHORT).show();
	    String Data = null;
        String localTime = null;
		String filename = null;
        try {
        	Data = dateText+"|"+initialTimeText+"|"+lapDistanceText+"|"+ timeDecrementText+"|"+ lapsCompletedText
        			+"|"+lastLapTimeText+"|"+ totalDistanceText+"|"+totalTimeText+"|"+ initialSpeedText+"|"+topSpeedText+"|"+avgSpeedText;
			localTime = java.text.DateFormat.getDateTimeInstance().format(
					Calendar.getInstance().getTime());
			FileOutputStream fos = openFileOutput(localTime,
					Context.MODE_PRIVATE);
		    
			fos.write(Data.getBytes());
		    fos.close();
		
			Intent intent = new Intent(this, ViewLogActivity.class);
			intent.putExtra(filename, localTime);
			startActivity(intent);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		
	}

	
}
