package com.firebase.client;

import com.firebase.*;
import com.firebase.androidchat.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
}
