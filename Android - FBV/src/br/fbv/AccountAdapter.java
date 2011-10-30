package br.fbv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class AccountAdapter implements IAccount 
{
	private SQLiteDatabase db = null;
	private SQLiteHelper accountDBHelper = null;
	private static final String CATEGORIA = "AccountAdapter";

	/**
	 * 
	 */
	public AccountAdapter(Context context) {
		// TODO Auto-generated constructor stub
		accountDBHelper = new SQLiteHelper(context);
	} // end construtctor

	public long inserir(Account account) throws SQLException 
	{
		// TODO Auto-generated method stub
		
		// cria ou abre o banco para leitura e gravacao
		this.db = accountDBHelper.getWritableDatabase();

		// representa um conjunto de valores dos comando SQL
		ContentValues contentValues = new ContentValues();
		
		contentValues.put("userName", account.getUserName());
		contentValues.put("userPasswd", account.getUserPasswd());
		
		/* db.insert recebe o nome da tabela e o Content Values
		 * como parametro, e insere na base de dados.
		 */
		long retorno = db.insert("users", null, contentValues);
		
		// fecha a conexao com o banco
		db.close();
		
		/* apenas para registro do programador para saber se foi
		 * criado com sucesso o registro no banco de dados 
		 */
		Log.i(CATEGORIA, "Registro Criado com Sucesso!");
		
		return retorno;
		
	} // end method inserir

	public Account buscarUserName(String userName) 
	{
		// TODO Auto-generated method stub
		Account accounts;
		String[] columns = {"userName", "userPasswd"};
		String[] args	 = {userName};
		
		// cria ou abre um banco para leitura
		this.db = accountDBHelper.getReadableDatabase();
		
		Cursor cursor = db.query("users", columns, "nome = ?", args, null, null, userName);
		cursor.moveToFirst();
		
		accounts = new Account();
		accounts.setUserName(cursor.getString(0));
		accounts.setUserPasswd(cursor.getString(1));
		
		return accounts;
		
	} // end method buscarUserName

} // end class AccountAdapter
