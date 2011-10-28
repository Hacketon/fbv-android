package br.fbv;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper 
{
	
	private static final String DATABASE_NAME = "noteGecko.db";
	public static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "users";

	public SQLiteHelper(Context context) 
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
	} // end constructor

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE"+ TABLE_NAME +"(userName TEXT, userPasswd TEXT)");
		
	} // end method onCreate

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		// TODO Auto-generated method stub
		if(newVersion - oldVersion > 2)
		{
			db.execSQL("DROP TABLE IF EXITS "+ TABLE_NAME);
			onCreate(db);
			
		} // end if
		
	} // end method onUpgrade

} // end class SQLiteHelper
