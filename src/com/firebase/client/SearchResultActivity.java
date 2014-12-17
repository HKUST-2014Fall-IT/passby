//#CSIT6000B#  Rio He       20227977 yheas@connect.ust.hk
//#CSIT6000B#  Cedric Liang 20220395 zliangag@connect.ust.hk
//#CSIT6000B#  Kevin Wang   20225577 zwangbo@connect.ust.hk 
package com.firebase.client;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.firebase.androidchat.*;
import com.firebase.database.User;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class SearchResultActivity extends Activity {
	// user inputs room info in LoacteActiviy
	// the info is passed by intent
	private String u_room = new String();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchresult);
		this.u_room = getIntent().getStringExtra("u_room");
		
		ListView lv = (ListView) findViewById(R.id.searchResult_wrapper);
		EditText et = (EditText) findViewById(R.id.editLocate);
		et.setText(this.u_room);
		
		// init classroom store obj
		RoomInfoDatabase rid = new RoomInfoDatabase();
		// match room info from prev activity
		final ArrayList<String> list = rid.query1room(this.u_room);
        StableArrayAdapter saa = new StableArrayAdapter(SearchResultActivity.this, R.layout.listitem_searchresult, list);
        lv.setAdapter(saa);
        
        lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String chatRoom = list.get(position);
//				Log.i("chatRoom", chatRoom);
//				Log.i("User.getInstance().getNickname()", User.getInstance().getNickname());
				
				Intent intent = new Intent(SearchResultActivity.this, MainActivity.class);
				intent.putExtra("chatRoom", chatRoom);
				intent.putExtra("nickname", User.getInstance().getNickname());
				startActivity(intent);
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
	
	// ListView Adapter
	private class StableArrayAdapter extends ArrayAdapter<String>{
		private ArrayList<String> list = new ArrayList<String>();
		
		public StableArrayAdapter(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);
			for(int i=0; i<objects.size(); i++){
				this.list.add(objects.get(i));
			}
		}

		@SuppressLint("ViewHolder")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(R.layout.listitem_searchresult, parent, false);
			
			TextView tv = (TextView) rowView.findViewById(R.id.searchResult);
			tv.setText(this.list.get(position));
			return rowView;
		}	
		
	}

	
	// database to store room info
	private class RoomInfoDatabase{
		private String[] rooms;
		private String notFoundState = new String("Not Found the Room Please Input Again");
		
		private RoomInfoDatabase(){
			this.initRooms();
		}
		private void initRooms(){
			this.rooms = new String[]{"Academic Building 2464", "Academic Building 2502", "Enterprise Center 4619", "Enterprise Center 4620", "LSK Business Building 1997"};
		}
		
		// using RegEx to find search result
		public ArrayList<String> query1room(String u_room){
			ArrayList<String> result = new ArrayList<String>();
			Pattern p = Pattern.compile(u_room, Pattern.CASE_INSENSITIVE);
			
			for(int i=0; i<this.rooms.length; i++){
				Matcher m = p.matcher(this.rooms[i]);
				if(m.find()){
					result.add(this.rooms[i]);
				}else{
					// not found alert
					if(i==this.rooms.length-1&&result.isEmpty())
						result.add(notFoundState);
				}
			}
			
			return result;
		}
	}
}
















