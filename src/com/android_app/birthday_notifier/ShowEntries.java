package com.android_app.birthday_notifier;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ShowEntries extends Activity{

	TextView text;
	ListView lv;
	DataBase d;
	Button del;
	ArrayList<String> array;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_entries);
		
		array = new ArrayList<String>();
		lv = (ListView) findViewById(R.id.dates);
		del = (Button)findViewById(R.id.delete);
			try{
			d = new DataBase(this);
			d.open();
			array.addAll(d.selectAll());
			d.close();
			}catch(Exception e){
				text.setText("no results");
			}
			
		lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array));
		lv.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long arg3) {
				// TODO Auto-generated method stub
				String name = array.get(position);
				Intent i = new Intent(ShowEntries.this,Show.class);
				i.putExtra("name",name);
				startActivity(i);
			}
			
		});
	del.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			d.open();
			d.delete();
			d.close();
			finish();
			
		}
	});
	
	
	}

}
