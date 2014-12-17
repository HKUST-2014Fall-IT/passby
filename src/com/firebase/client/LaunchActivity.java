//#CSIT6000B#  Rio He       20227977 yheas@connect.ust.hk
//#CSIT6000B#  Cedric Liang 20220395 zliangag@connect.ust.hk
//#CSIT6000B#  Kevin Wang   20225577 zwangbo@connect.ust.hk 
package com.firebase.client;

import com.firebase.androidchat.R;
import com.firebase.database.*;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class LaunchActivity extends Activity {
    private ImageView welcomeImg = null;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);
		getActionBar().hide();
		
		welcomeImg = (ImageView) this.findViewById(R.id.welcome_img);
        AlphaAnimation anima = new AlphaAnimation(0.3f, 1.0f);
        anima.setDuration(3000);// 设置动画显示时间
        welcomeImg.startAnimation(anima);
        anima.setAnimationListener(new AnimationImpl());
	}
	
	private class AnimationImpl implements AnimationListener{

		@Override
		public void onAnimationStart(Animation animation) {
			 welcomeImg.setBackgroundResource(R.drawable.start_bg);
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			new DatabaseTask(LaunchActivity.this).execute();
			finish();
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub
			
		}
	}
	
}
