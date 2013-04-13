package com.example.intervaltraining;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Setting extends Activity implements android.view.View.OnClickListener {
	EditText Time1;
	EditText Distance;
	EditText DecrementRate;
	Button save;
	Button SelectBeep;
	Button SelectMusic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		Time1 = (EditText) this.findViewById(R.id.TimeText);
	    Distance = (EditText) this.findViewById(R.id.DistanceText);
		DecrementRate = (EditText) this.findViewById(R.id.DecrementText);
		save=(Button) this.findViewById(R.id.OK);
	}
		
		//SelectBeep=(Button)
		//SelectMusic=(Button)

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setting, menu);
		return true;
	}
*/
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		System.out.println("Hellooo");
		//System.out.println(setting.SaveSetting(Distance.getText().toString()));
	}
	public void onSave(View view) {
	     // Kabloey
	
		SettingFileFacade setting = new SettingFileFacade();
		boolean result = setting.SaveSetting(Distance.getText().toString());
		Time1.setText(String.valueOf(result));
	 }
}
