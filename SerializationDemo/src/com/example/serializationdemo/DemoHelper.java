package com.example.serializationdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DemoHelper extends SQLiteOpenHelper{

 public DemoHelper(Context context) {
  super(context,"Demo.db",null,1);
// TODO Auto-generated constructor stub 
 }

 @Override
 public void onCreate(SQLiteDatabase db) {
 db.execSQL("CREATE TABLE DemoTable(id INTEGER PRIMARY KEY AUTOINCREMENT,model BLOB);");	
 Log.v("AUTOHELP:","TABLE CREATED");
 }

 public void insert(byte[] model){
	 ContentValues cv=new ContentValues();
	 cv.put("model",model);

	 getWritableDatabase().insert("DemoTable",null,cv);
	 Log.e("ORG HELPER :","VALUES INSERTED");
	}
 
public Cursor getAll(){
	return(getWritableDatabase().rawQuery("SELECT * FROM DemoTable",null));	
}

public void Delete()
{
	 getWritableDatabase().delete("AMSUserTable",null ,null);
}



@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	
}

}
