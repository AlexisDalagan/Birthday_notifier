package com.android_app.birthday_notifier;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase {

	public static final String DB_NAME = "DB_Birthday";
	public static final int DB_VER = 3;
	public static final String TBL_NAME = "Tbl_List";
	public static final String TBL_COL_1 = "name";
	public static final String TBL_COL_2 = "number";
	public static final String TBL_COL_3 = "month";
	public static final String TBL_COL_4 = "day";

	private SQLiteDatabase db;
	private final Context context;
	private dbHelper helper;

	public DataBase(Context context){
		this.context = context;
	}
	
	public class dbHelper extends SQLiteOpenHelper {

		public dbHelper(Context context) {
			super(context, DB_NAME, null, DB_VER);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + TBL_NAME + " ("+
					TBL_COL_1 + " TEXT NOT NULL, " + 
					TBL_COL_2 + " TEXT NOT NULL, " +
					TBL_COL_3 + " TEXT NOT NULL, " +
					TBL_COL_4 + " TEXT NOT NULL);");

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + TBL_NAME);
			onCreate(db);
		}

	}
	
	public void open(){
		helper = new dbHelper(context);
		db = helper.getWritableDatabase();
	}
	
	public void close(){
		db.close();
	}
	
	public long insert(String name,String number, String month
			,String day){
		ContentValues cont = new ContentValues();
		cont.put(TBL_COL_1,name);
		cont.put(TBL_COL_2,number);
		cont.put(TBL_COL_3,month);
		cont.put(TBL_COL_4,day);
		return db.insert(TBL_NAME, null, cont);
	}
	public void delete()
	{
		db.delete(TBL_NAME, null, null);
	}
	
	public String selectOne(String name){
		String msg = "";
		String[] cols = new String[]{TBL_COL_1,TBL_COL_2,TBL_COL_3,TBL_COL_4};
		Cursor c = db.query(TBL_NAME, cols, TBL_COL_1 + " = ? ", new String[]{ name } , null, null, null);
		for(c.moveToFirst(); !c.isAfterLast() ; c.moveToNext()){
			msg += c.getString(0) 
				+ "\n" + c.getString(1) 
				+ "\n" + c.getString(2)+"  " + c.getString(3);
		}
		return msg;
	}
	
	public ArrayList<String> selectAll(){
		ArrayList<String> result = new ArrayList<String>();
		String[] cols = new String[]{TBL_COL_2,TBL_COL_1
		};
		Cursor c = db.query(TBL_NAME, cols, null, null, null, null, null);
		for(c.moveToFirst(); !c.isAfterLast() ; c.moveToNext()){
			result.add(c.getString(1));
		}
		return result;
	}
}
