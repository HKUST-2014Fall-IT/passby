package com.firebase.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseManager {
	private DatabaseHelper dbHelper;
	private SQLiteDatabase db;
	private User user = User.getInstance();
	
	public DatabaseManager() {
		// TODO Auto-generated constructor stub
	}
	public DatabaseManager(Context context){
		dbHelper = new DatabaseHelper(context);
		// to ensure mContext.openOrCreateDatabase() is called in db.getWritableDatabase()
		// context should be inited,
		// new DatabaseManager(Context context) will be called in Activity's onCreate()
		db = dbHelper.getWritableDatabase();
	}

	// create a new user
	public void createUser(User user){
		db.execSQL("INSERT INTO " + DatabaseHelper.TABLE_NAME
				+ " VALUES(null, ?, ?)", new Object[] { user.getNickname(),
				user.getGender()});
	}
	
	// update the user's info
	public void uptUser(String prevName){
		ContentValues cv = new ContentValues();
		cv.put("nickname", user.getNickname());
		cv.put("gender", user.getGender());
		
		db.update(DatabaseHelper.TABLE_NAME, cv, "nickname = ?",
				new String[] { prevName });
	}
	
	// query
	public Cursor queryTheCursor(){
		Cursor c = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME, null);
		//Log.i("nickname", c.getString(c.getColumnIndex("nickname")));
		return c;
	}
	
	public ContentValues query(){
		ContentValues cv = new ContentValues();
		Cursor c = queryTheCursor();
		while(c.moveToNext()){
			Log.i("in query!!", "in query");
			
			cv.put("nickname", c.getString(c.getColumnIndex("nickname")));
			cv.put("gender", c.getInt(c.getColumnIndex("gender")));
		}
		
		Log.i("cv nickname", "!!!");
		c.close();
		return cv;
	}
	
	
	// close database to release memory
	public void closeDB(){
	    db.close();
	}
}



















