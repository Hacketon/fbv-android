package br.fbv;

import br.fbv.R;
import br.fbv.account.Account;
import br.fbv.account.AccountAdapter;
import br.fbv.account.IAccount;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AccountSQLite extends Activity 
{
	private static final String TAG = "GeckoNotes";
	
	private Account account;
	private IAccount accountAdapter;
	
	private EditText edtUserName;
	private EditText edtUserPasswd;
	private EditText edtUserPasswdCheck;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newaccount);
		accountAdapter = new AccountAdapter(this);
		
	} // end method onCreate
	
	public void incluir(View view)
	{
		edtUserName		= (EditText) findViewById(R.id.edtCadUser);
		edtUserPasswd	= (EditText) findViewById(R.id.edtCadPasswd);
		edtUserPasswdCheck = (EditText) findViewById(R.id.edtCadPasswdCheck);
		
		account = buscarPorNome();
		account.setUserName(edtUserName.getText().toString());
		
		if(edtUserPasswd.getText().equals(edtUserPasswdCheck.getText()))
		{
			account.setUserPasswd(edtUserPasswd.getText().toString());
		} else {
			Log.d(TAG, "Senhas diferentes");
		} // end if
		
		//verfica se ja existe o valor
		if (account == null)
		{
			accountAdapter.inserir(account);	
		}else{
			Log.d(TAG, "Usuario ja existe");
		}	

	} // end method inserir
	
	public Account buscarPorNome()
	{
		account = accountAdapter.buscarUserName(edtUserName.getText().toString());
		return account;
		
	} // end method buscarPorNome
	
} // end class AccountSQLite
