package com.firebase.client;

import java.util.ArrayList;
import java.util.List;

import com.firebase.androidchat.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SearchResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchresult);
		
		ListView lv = (ListView) findViewById(R.id.searchResult_wrapper);
		String[] values = new String[]{"test1", "test2", "test3"};
		
		final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
          list.add(values[i]);
          Log.i("list:", list.get(i));
        }
        
        StableArrayAdapter saa = new StableArrayAdapter(SearchResultActivity.this, R.layout.listitem_searchresult, list);
        lv.setAdapter(saa);
	}
	
	private class StableArrayAdapter extends ArrayAdapter<String>{
		private ArrayList<String> list = new ArrayList<String>();
		
		public StableArrayAdapter(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);
			for(int i=0; i<objects.size(); i++){
				this.list.add(objects.get(i));
			}
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(R.layout.listitem_searchresult, parent, false);
			
			TextView tv = (TextView) rowView.findViewById(R.id.searchResult);
			tv.setText(this.list.get(position));
			return rowView;
		}	
		
	}
}
