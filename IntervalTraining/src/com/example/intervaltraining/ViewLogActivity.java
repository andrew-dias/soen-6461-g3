package com.example.intervaltraining;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class ViewLogActivity extends ListActivity implements OnClickListener {
	
	static ArrayList<String> listItems=new ArrayList<String>();
	static ArrayAdapter<String> adapter = null;
	
	@Override
	
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_view_log);
	
		
		final ListView list = getListView();
        adapter=new ArrayAdapter<String>(this,
        android.R.layout.simple_list_item_1,
            listItems);
		//getActionBar().setDisplayHomeAsUpEnabled(true);
		setListAdapter(adapter);
        
		
		
		list.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				Object o = list.getItemAtPosition(arg2);
			    String filename = (String)o;
	        	String line=null;
		        String res= null;
		        try {
		        	
		        	InputStream in = openFileInput(filename);
	                               
		            if (in != null) {
		            	int i =0;
		            	InputStreamReader input = new InputStreamReader(in);
	                    BufferedReader br = new BufferedReader(input);
		            	while (( line = br.readLine()) != null) {
		            		res = line;
		                    
		                    }
		                        in.close();
		                        Toast.makeText(getApplicationContext(),"File Data == " + res,Toast.LENGTH_LONG).show();
		                  }
		            } catch(Exception e){
		                 Toast.makeText(getApplicationContext(),e.toString() +   e.getMessage(),Toast.LENGTH_LONG).show();
		            }
		                       
			}

        	
        	
        });
        
     
    }
	
	public static void additem(String name){
    	listItems.add(name);
        adapter.notifyDataSetChanged();
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
