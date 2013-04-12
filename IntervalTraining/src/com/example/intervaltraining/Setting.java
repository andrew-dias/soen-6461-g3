package com.example.intervaltraining;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();

        getActionBar().setDisplayHomeAsUpEnabled(true);

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
/*	    	case R.id.settings_menu_item:
	        	intent = new Intent(this, SettingsActivity.class);
	        	startActivity(intent);
	            return true;
*/           	case R.id.view_log_menu_item:
		    	intent = new Intent(this, ViewLogActivity.class);
		    	startActivity(intent);
		        return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
/*
public class SettingsActivity extends Activity implements android.view.View.OnClickListener {
	EditText time3;
	EditText distance;
	EditText decrement;
	Button ok;
	Button clear;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		time3 = (EditText) this.findViewById(R.id.TimeText);
	    distance = (EditText) this.findViewById(R.id.DistanceText);
		decrement = (EditText) this.findViewById(R.id.DecrementText);
		ok=(Button) this.findViewById(R.id.OK);
		clear=(Button) this.findViewById(R.id.clear);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

}
*/