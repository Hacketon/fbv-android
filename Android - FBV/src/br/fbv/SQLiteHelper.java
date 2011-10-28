package br.fbv;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper 
{
	
	private String[] scriptSQLCreate;
	private String scriptSQLDelete;

	public SQLiteHelper(Context context, String nomeBanco, int versao, 
			String[] scriptSQLCreate, String scriptSQLDelete) 
	{
		super(context, nomeBanco, null, versao);
		this.scriptSQLCreate = scriptSQLCreate;
		this.scriptSQLDelete = scriptSQLDelete;
		
	} // end constructor

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		// TODO Auto-generated method stub
		int qtdScripts = scriptSQLCreate.length;
		
		for (int i = 0; i < qtdScripts; i++) {
			String sql = scriptSQLCreate[i];
			db.execSQL(sql);
		}

	} // end method onCreate

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		// TODO Auto-generated method stub
		db.execSQL(scriptSQLDelete);
		onCreate(db);

	} // end method onUpgrade

} // end class SQLiteHelper
