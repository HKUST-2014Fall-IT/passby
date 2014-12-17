//#CSIT6000B#  Rio He       20227977 yheas@connect.ust.hk
//#CSIT6000B#  Cedric Liang 20220395 zliangag@connect.ust.hk
//#CSIT6000B#  Kevin Wang   20225577 zwangbo@connect.ust.hk 
package com.firebase.database;

import android.util.Log;

public class User {
	static User instance = new User();
	public static User getInstance(){
		return instance;
	}
	
	private String nickname = new String();
	// female - 0; male - 1
	private int gender = 0;
	
	private User() {}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getGender() {
		return this.gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}
	
	public static void printUser(){
		Log.i("user's nickname", instance.getNickname());
		Log.i("user's gender", Integer.toString(instance.getGender()));
	}

}

