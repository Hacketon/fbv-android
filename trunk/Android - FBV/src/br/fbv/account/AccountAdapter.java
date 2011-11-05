package br.fbv.account;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class AccountAdapter implements IAccount 
{
	private static final String TAG = "GeckoNotes"; 
	private SQLiteDatabase db = null;
	private AccountDBHelper accountDBHelper = null;
	
	/**
	 * @param context
	 */
	public AccountAdapter(Context context) 
	{
		// TODO Auto-generated constructor stub
		accountDBHelper = new AccountDBHelper(context);
		
	} // end constructor	

	public long inserir(Account account) throws SQLException
	{
		// TODO Auto-generated method stub
		
		// cria ou abre um banco para leitura
		this.db = accountDBHelper.getWritableDatabase();
		
		// representa um conjunto de valores dos comandos SQL
		ContentValues cv = new ContentValues();
		
		cv.put("id", account.getId());
		cv.put("user_name", account.getUserName());
		cv.put("user_passwd", account.getUserPasswd());
		
		int retorno = db.update("users", cv, "id = ?", new String[]{account.getId().toString()});
		
		// fecha conexao com banco
		db.close();
		
		// log apenas para programado verificar se esta tudo certo
		Log.i(TAG, "Registro atualizado com sucesso!");
		
		return retorno;
		
	} // end method inserir

	public Account buscarUserName(String userName) 
	{
		// TODO Auto-generated method stub
		Account account;
		String[] columns = new String[]{"id", "user_name"};
		String[] args 	 = {userName};
		
		this.db = accountDBHelper.getWritableDatabase();
		
		Cursor cursor = db.query("users", columns, "user_name = ?", args, null, null, "user_name");
		cursor.moveToFirst();
		
		account = new Account();
		account.setId((int)cursor.getLong(0));
		account.setUserName(cursor.getString(1));
		
		return account;
		
	} // end method buscarUserName

} // end class AccountAdapter
