package com.android_app.birthday_notifier;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainMenu extends ListActivity {

	String[] menu = {"Start Birthday Notifier Service",
					"Add Entry","Show Entries","Exit"};  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(MainMenu.this,android.R.layout.simple_list_item_1,menu));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		 switch( position )
		    {
		       case 0:  
		    	   Intent sendIntent = new Intent(Intent.ACTION_VIEW);         
		    	   sendIntent.setData(Uri.parse("sms:"));
		                break;
		       case 1:  
		    	   		Intent newActivity_1 = new Intent(this, AddEntry.class);     
		                startActivity(newActivity_1);
		                break;
		       case 2:  
		    	   		Intent newActivity_2 = new Intent(this, ShowEntries.class);     
		                startActivity(newActivity_2);
		                break;
		       case 3: 
		    	   finish();
		                break;
		      default:
		                break;
		    }
	}


}
