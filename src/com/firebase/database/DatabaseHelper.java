package com.firebase.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "PASSERBY.db";
	public static final String TABLE_NAME = "UserTable";
	
	public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@SuppressLint("NewApi") 
	public DatabaseHelper(Context context, String name, CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		// TODO Auto-generated constructor stub
	}
	
	public DatabaseHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// when creating database, onCreate will be called
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		// create SQL statement
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("CREATE TABLE " + TABLE_NAME + " (");
		sBuffer.append("uid INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
		sBuffer.append("nickname VARCHAR,");
		sBuffer.append("gender INTEGER)");
		
		// execute creation
		db.execSQL(sBuffer.toString());
	}

	// when version is changed, onUpgrade will be called
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		 onCreate(db);
	}

	// everytime db is openned, onOpen will be executed
	@Override
	public void onOpen(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		super.onOpen(db);
	}

	
}





















