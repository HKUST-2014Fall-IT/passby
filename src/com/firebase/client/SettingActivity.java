package com.firebase.client;

import com.firebase.androidchat.R;
import com.firebase.database.*;

import android.R.integer;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingActivity extends Activity {
	private DatabaseManager dbManager;
	private User user;
	private Button save_btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		save_btn = (Button) this.findViewById(R.id.saveButton);
		
		// init database manager
		this.dbManager = new DatabaseManager(this);
		this.user = User.getInstance();
		
		initComponent();
		
		save_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Log.i("user.getNickname().length()", user.getNickname());
				// first time using this APP, need to set user info
				if(user.getNickname()==null || user.getNickname().length()==0){
					// set User's info
					setUser((EditText) SettingActivity.this.findViewById(R.id.nickname), (RadioGroup) SettingActivity.this.findViewById(R.id.genderGroup));
					createUser();
					Log.i("SET USER", "in create table");
				}else{
					String prevName = new String(user.getNickname());
					Log.i("user's prevName", user.getNickname());
					// set User's info
					setUser((EditText) SettingActivity.this.findViewById(R.id.nickname), (RadioGroup) SettingActivity.this.findViewById(R.id.genderGroup));
					uptUser(prevName);
					Log.i("SET USER", "in update table");
				}
				
				Intent intent = new Intent(SettingActivity.this, LocateActivity.class);
				startActivity(intent);
			}
		});
	}
	
	private void initComponent(){
		EditText et = (EditText) this.findViewById(R.id.nickname);
		RadioButton f_btn = (RadioButton) this.findViewById(R.id.fButton);
		RadioButton m_btn = (RadioButton) this.findViewById(R.id.mButton);
		
		// if user has already set info in SQLite
		ContentValues cv = query();
		if(cv.containsKey("nickname")){
			Log.i("init component", "cv is not null");
			user.setNickname(cv.getAsString("nickname"));
			user.setGender(cv.getAsInteger("gender"));
			
			et.setText(cv.getAsString("nickname"));
			if(cv.getAsInteger("gender")==0)
				f_btn.setChecked(true);
			else
				m_btn.setChecked(true);
		}else{
			Log.i("init compoent", "cv is null");
			user.setNickname("");
			user.setGender(0);
			
			et.setText("");
			f_btn.setChecked(true);
		}
		
	}
	
	// when activity is gone, close database
	@Override
	protected void onDestroy() {
		super.onDestroy();
		dbManager.closeDB();
	}

	private void setUser(EditText et, RadioGroup gender_group){
		user.setNickname(et.getText().toString());
		if(gender_group.getCheckedRadioButtonId() == R.id.mButton)
			user.setGender(1);
		else
			user.setGender(0);
		
		//User.printUser();
	}
	
	public void createUser(){
		dbManager.createUser(user);
	}
	
	public void uptUser(String prevName){
		dbManager.uptUser(prevName);
	}
	public ContentValues query(){
		return dbManager.query();
	}
}
