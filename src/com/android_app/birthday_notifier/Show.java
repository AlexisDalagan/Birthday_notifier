package com.android_app.birthday_notifier;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Show extends Activity {

	TextView t2,t1;
	DataBase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);
		t1 = (TextView)findViewById(R.id.label);
		t2 = (TextView) findViewById(R.id.contentOfMsg);
		String name = getIntent().getStringExtra("name");
		String message = "";
			try{
			db = new DataBase(this);
			db.open();
			message = db.selectOne(name);
			db.close();
			}catch(Exception e){
				t2.setText("no results");
			}
		t1.setText("NAME"+"\n"+
					"NUMBER"+"\n"+
					"BIRTHDAY");
		t2.setText(message);
	}

}
