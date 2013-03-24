package com.android_app.birthday_notifier;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddEntry extends Activity {

	TextView name;
	TextView number;
	Spinner month;
	Spinner day;
	Button ok;
	DataBase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_entry);
		db = new DataBase(this);
	Initialize();
	AddtoDatabase();
	}
	
	private void Initialize() {
		name = (TextView)findViewById(R.id.name);
		number = (TextView)findViewById(R.id.number);
		month = (Spinner)findViewById(R.id.month);
		day = (Spinner)findViewById(R.id.day);
		ok = (Button)findViewById(R.id.ok);	
	}

	private void AddtoDatabase() {
	ok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {	
				
				long insert = 0;
				try{
					db.open();
					insert = db.insert(name.getText().toString(),number.getText().toString(),
										month.getSelectedItem().toString(),day.getSelectedItem().toString());
					db.close();
				} catch (Exception e){
					Toast.makeText(getApplicationContext(), "Query failed " + e.toString(), Toast.LENGTH_SHORT).show();
				}
				
				if(insert != -1){
					Toast.makeText(getApplicationContext(), "Stats added", Toast.LENGTH_SHORT).show();
				}
			}		
			

			
		});
		
	}
	

}
