//#CSIT6000B#  Rio He       20227977 yheas@connect.ust.hk
//#CSIT6000B#  Cedric Liang 20220395 zliangag@connect.ust.hk
//#CSIT6000B#  Kevin Wang   20225577 zwangbo@connect.ust.hk 
package com.firebase.client;

import com.firebase.androidchat.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LocateActivity extends Activity {
	
	private LocationManager locationManager;
	//private StringBuffer sb = new StringBuffer();
	private Location location;
	private double x;
	private double y;
	
	private final double CLASS_2464_Y = 114.26333902908577;
	private final double CLASS_2464_X = 22.33760550858573;
	
	private final double CLASS_LSK_Y = 114.26464130298577;
	private final double CLASS_LSK_X = 22.33347407989984;
	
	private final double CLASS_4619_Y = 114.26401378535724;
	private final double CLASS_4619_X = 22.335136092645868;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locate);
		
		x = 0.0;
		y = 0.0;
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,  
                2000, 8, new LocationListener() {  
  
                    @Override  
                    public void onStatusChanged(String provider, int status,  
                            Bundle extras) {  
                    }

					@Override
					public void onLocationChanged(Location location) {
						// TODO Auto-generated method stub
						location = locationManager  
				                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
					}

					@Override
					public void onProviderEnabled(String provider) {
						// TODO Auto-generated method stub
						location = locationManager  
				                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
					}

					@Override
					public void onProviderDisabled(String provider) {
						// TODO Auto-generated method stub
						
					}  
                });  
		
		Button manualLocate = (Button) findViewById(R.id.manualLocate);
		Button autoLocate = (Button) findViewById(R.id.autoLocate);
		
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
		
		autoLocate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				location = locationManager  
		                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
				//get the locate 
				if (location != null) {   
		            //sb.append("实时的位置信息：\n经度：");  
		            //sb.append(location.getLongitude());  
		            //sb.append("\n纬度：");  
		            //sb.append(location.getLatitude());  
		            x = location.getLatitude();
		            y = location.getLongitude();
		        } else {  
		            // 如果传入的Location对象为空则清空EditText  
		            //sb.append("nonono");
		            x = 0;
		            y = 0;
		        }
				//Log.i("location", sb.toString());
				double distance_2464 = Math.pow(x - CLASS_2464_X, 2) + Math.pow(y - CLASS_2464_Y, 2);
				double distance_lsk = Math.pow(x - CLASS_LSK_X, 2) + Math.pow(y - CLASS_LSK_Y, 2);
				double distance_4619 = Math.pow(x - CLASS_4619_X, 2) + Math.pow(y - CLASS_4619_Y, 2);
				// jump to search result activity, show search result
				if(distance_2464 <= distance_lsk && distance_2464 <= distance_4619){
					Intent intent = new Intent(LocateActivity.this, SearchResultActivity.class);
					intent.putExtra("u_room", "Academic");
					LocateActivity.this.startActivity(intent);
				}
				else if(distance_lsk <= distance_2464 && distance_lsk <= distance_4619){
					Intent intent = new Intent(LocateActivity.this, SearchResultActivity.class);
					intent.putExtra("u_room", "lsk");
					LocateActivity.this.startActivity(intent);
				}
				else{
					Intent intent = new Intent(LocateActivity.this, SearchResultActivity.class);
					intent.putExtra("u_room", "Enterprise");
					LocateActivity.this.startActivity(intent);
				}
				
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
