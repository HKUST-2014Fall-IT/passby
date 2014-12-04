package com.firebase.client;

import java.util.Set;

import com.firebase.*;
import com.firebase.androidchat.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LocateActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locate);
		
		Button manualLocate = (Button) findViewById(R.id.manualLocate);
		
		manualLocate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText et = (EditText) findViewById(R.id.editLocate);
				
				// jump to search result activity, show search result
				Intent intent = new Intent(LocateActivity.this, SearchResultActivity.class);
				intent.putExtra("u_room", et.getText().toString());
				LocateActivity.this.startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();  
	    inflater.inflate(R.menu.menu, menu);  
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == R.id.app_settings){
			Intent intent = new Intent(this, SettingActivity.class);
			startActivity(intent);
		}
			
		return true;
	}
	
	
}
