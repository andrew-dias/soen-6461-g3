package com.example.intervaltraining;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;

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

public class StatisticsActivity extends Activity implements OnClickListener {

	// screen objects
	private TextView dateTextView;
	private TextView intervalTimeTextView;
	private TextView lapDistanceTextView;
	private TextView timeDecrementTextView;
	private TextView lapsCompletedTextView;
	private TextView totalDistanceTextView;
	private TextView totalTimeTextView;
	private TextView initialSpeedTextView;
	private TextView topSpeedTextView;
	private TextView avgSpeedTextView;
    //private Button button;
	private IntervalStatistics stats;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistics);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		//button =(Button) this.findViewById(R.id.button1);
	    //button.setOnClickListener(this);
		Bundle b = getIntent().getExtras();
		stats = b
				.getParcelable("com.example.intervaltraining.IntervalStatistics");

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

	private void setDisplayValues(IntervalStatistics stats) {
		String dateText = DateFormat.getDateInstance().format(stats.getDate());
		String initialTimeText = stats.getInitialTime() / 1000 + "s";
		String lapDistanceText = stats.getLapDistance() + "m";
		String timeDecrementText = stats.getTimeDecrement() + "%";
		String lapsCompletedText = stats.getLapsCompleted() + "";
		String totalDistanceText = stats.getTotalDistance() + "m";
		String totalTimeText = stats.getTotalTime() / 1000 + "s";
		String initialSpeedText = stats.getInitialSpeed() + " km/h";
		String topSpeedText = stats.getTopSpeed() + " km/h";
		String avgSpeedText = stats.getAvgSpeed() + " km/h";

		dateTextView.setText(dateText);
		intervalTimeTextView.setText(initialTimeText);
		lapDistanceTextView.setText(lapDistanceText);
		timeDecrementTextView.setText(timeDecrementText);
		lapsCompletedTextView.setText(lapsCompletedText);
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
		// TODO Auto-generated method stub
	    String string = "hello";
		String localTime =null;
			try{
				
				localTime = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
				FileOutputStream fos = openFileOutput(localTime, Context.MODE_PRIVATE);
		        fos.write(string.getBytes());
		        fos.close();
		        ViewLogActivity.additem(localTime);		
		
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}

	@Override
	public void onClick(View arg0) {
		/*// TODO Auto-generated method stub
		String string = "hello";
		String localTime =null;
		
		if (arg0.getId()==R.id.button1){
			try{
				
				localTime = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
				FileOutputStream fos = openFileOutput(localTime, Context.MODE_PRIVATE);
		        fos.write(string.getBytes());
		        fos.close();
		        ViewLogActivity.additem(localTime);		
		
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
		}
		*/
	}
}
