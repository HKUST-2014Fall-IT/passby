package com.firebase.database;

import com.firebase.client.LocateActivity;
import com.firebase.client.SettingActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.sax.StartElementListener;
import android.util.Log;

public class DatabaseTask extends AsyncTask<String, String, String> {
	private DatabaseManager dbManager;
	private Activity activity;
	private String flag;
 
	public DatabaseTask(Activity activity){
		this.activity = activity;
		this.dbManager = new DatabaseManager(activity);
	}

	@Override
	protected String doInBackground(String... params) {
		// do query
		ContentValues cv = this.dbManager.query();
		if(cv.containsKey("nickname"))
			flag = "true"; // has set user's info
		else
			flag = "false"; // hasnt set user's info
		return flag;
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		Intent intent;
		
		if(flag.equals("true"))
			intent = new Intent(this.activity, LocateActivity.class);
		else
			intent = new Intent(this.activity, SettingActivity.class);
		this.activity.startActivity(intent);
	}
	
	
}


