package br.fbv;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteAdapter 
{
	private SQLiteDatabase db = null;
	private SQLiteHelper dbHelper = null;
	
	public SQLiteAdapter(Context context) 
	{
		// TODO Auto-generated constructor stub
		dbHelper = new SQLiteHelper(context);
		
	} // end constructor
	
	public long insert(String uuserName, String uuserPasswd)
	{
		this.db = dbHelper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("userName", uuserName);
		cv.put("userPasswd", uuserPasswd);
		long retorno = db.insert("users", null, cv);
		db.close();
		
		return retorno;
		
	} // end method insert

} // end class SQLiteAdapter
