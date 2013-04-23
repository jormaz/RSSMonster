package com.example.rssmonster;


import java.util.Timer;
import java.util.TimerTask;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;




public class SplashActivity extends Activity implements OnClickListener{
	private static final String TAG = "SplashActivity";
	public Boolean inicioPulsado = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_about);
		setContentView(R.layout.splash );
		
		//TextView text=(TextView)findViewById(R.id.textSubtitle);
		//text.setVisibility(1);
		
		Log.d(TAG,"onCreate");
		
		ImageButton btn = (ImageButton) findViewById(R.id.button1);
		btn.setOnClickListener(this);
		
		
		Timer myTimer = new Timer();
		myTimer.schedule(new TimerTask() {
			@Override
			public void run() {
			timerMethod();
			}
			},10000);
		
	}
	
	private void timerMethod(){
		Context context = this;			
		Intent intent = new Intent( context,ArticleListActivity.class);
		if (inicioPulsado!=true) startActivity(intent);
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.button1 :
			Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);  
			vibrator.vibrate(100);  
			//Alternativa a la redirección del MainActivity
			inicioPulsado=true;
			Context context = this;			
			Intent intent = new Intent( context,ArticleListActivity.class);
			startActivity(intent);
			
			break;
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash , menu);
		return true;
	}

}

