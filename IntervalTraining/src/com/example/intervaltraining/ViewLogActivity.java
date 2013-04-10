package com.example.intervaltraining;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
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
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_log, menu);
		return true;
	}
*/
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
