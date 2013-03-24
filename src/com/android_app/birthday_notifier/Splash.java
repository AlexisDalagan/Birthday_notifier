package com.android_app.birthday_notifier;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(5000);
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}
				finally{
					Intent intent = new Intent(Splash.this,MainMenu.class);
					startActivity(intent);
					finish();
				}
			}
		
		};
		timer.start();
	}


}
