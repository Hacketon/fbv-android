package br.fbv.account;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AccountDBHelper extends SQLiteOpenHelper 
{
	private static final String TAG = "GeckoNotes";
	private static final String DATABASE_NAME = "gecko_notes.db";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME	  = "users";

	public AccountDBHelper(Context context) 
	{
		// TODO Auto-generated constructor stub
		super(context, DATABASE_NAME, null, DATABASE_VERSION);		
		
	} // end constructor

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		// TODO Auto-generated method stub
		Log.d(TAG, "Criando a tabela no banco");
		db.execSQL("CREATE TABLE "+ TABLE_NAME +"(id INTEGER primary key autoincrement, " +
				"user_name TEXT, user_passwd TEXT)");

	} // end method onCreate

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		// TODO Auto-generated method stub
		Log.d(TAG, "Verificando atualizacao do banco de dados");
		if((newVersion - oldVersion) > 2)
		{
			Log.d(TAG, "Ha necessidade de atualizar o banco");
			db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
			onCreate(db);
		
		} else {
			Log.d(TAG, "A versao do banco de dados e praticamente a mesma. Os registros serao preservados.");
			
		} // end if/else

	} // end method onUpgrade

} // end class AccountDBHelper
