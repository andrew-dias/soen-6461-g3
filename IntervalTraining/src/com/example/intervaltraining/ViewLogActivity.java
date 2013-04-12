package com.example.intervaltraining;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
public class ViewLogActivity extends Activity implements android.view.View.OnClickListener {
	Button ok;
	Spinner sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_log);
		ok=(Button) this.findViewById(R.id.OK);
		sp = (Spinner) findViewById(R.id.spinner);
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
	    	case R.id.settings_menu_item:
	        	intent = new Intent(this, SettingsActivity.class);
	        	startActivity(intent);
	            return true;
/*           	case R.id.view_log_menu_item:
		    	intent = new Intent(this, ViewLogActivity.class);
		    	startActivity(intent);
		        return true;
*/	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
