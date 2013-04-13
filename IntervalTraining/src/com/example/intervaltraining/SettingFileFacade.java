package com.example.intervaltraining;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import android.os.Environment;


public class SettingFileFacade {
	
	private File file = new File( Environment.getExternalStorageDirectory() + File.separator + "setting.txt");
	
	
	public boolean SaveSetting(String Distance)
	{
		try
		{
		    if(!file.exists())	file.createNewFile();
			String stringTobeSaved = Distance + "|" ;
			//write the bytes in file
			OutputStream fo = new FileOutputStream(file);
			     
			fo.write(stringTobeSaved.getBytes("UTF-8"));
			fo.close();
		}
		catch(Exception e)
		{	
			return false;
		}
		return true;
	}
	
	

}
