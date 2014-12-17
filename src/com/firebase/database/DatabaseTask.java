//#CSIT6000B#  Rio He       20227977 yheas@connect.ust.hk
//#CSIT6000B#  Cedric Liang 20220395 zliangag@connect.ust.hk
//#CSIT6000B#  Kevin Wang   20225577 zwangbo@connect.ust.hk 
package com.firebase.database;

import com.firebase.client.LocateActivity;
import com.firebase.client.SettingActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;

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
		if(cv.containsKey("nickname")){
			flag = "true"; // has set user's info
			
			// init User obj
			User.getInstance().setNickname(cv.getAsString("nickname"));
			User.getInstance().setGender(cv.getAsInteger("gender"));
		}
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


