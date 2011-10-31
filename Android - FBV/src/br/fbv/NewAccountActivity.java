package br.fbv;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewAccountActivity extends Activity implements OnClickListener
{
	private Account account;
	private IAccount accountAdapter;
//	private String userName;
	
	private EditText edtUserName;
	private EditText edtUserPasswd;
	private EditText edtUserPasswdCheck;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form_cad_user);
		accountAdapter = new AccountAdapter(this);
		
		Button btnSaveUser = (Button) findViewById(R.id.btnSaveUser);
		
		btnSaveUser.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				salvar();
				Toast.makeText(NewAccountActivity.this, "Usu‡rio cadastrado com sucesso!", Toast.LENGTH_SHORT);
				finish();
				
			} // end method onClick
			
		}); // end btnSaveUser
		
	} // end method onCreate

	public void salvar()
	{
		edtUserName 		= (EditText) findViewById(R.id.edtCadUser);
		edtUserPasswd 		= (EditText) findViewById(R.id.edtCadPasswd);
		edtUserPasswdCheck  = (EditText) findViewById(R.id.edtCadPasswdCheck);
		account = buscarUserName();
		
		account.setUserName(this.edtUserName.getText().toString());
		
		while(true) {
			if(edtUserPasswd.equals(edtUserPasswdCheck))
			{
				account.setUserPasswd(this.edtUserPasswd.getText().toString());
			} else {
				Toast.makeText(this, "Senhas Diferentes!", Toast.LENGTH_SHORT);
			} // end if
			
			// verifica se o usuario ja existe
			if(account == null)
			{
				accountAdapter.inserir(account);
				break;
			} else {
				Toast.makeText(NewAccountActivity.this, "Usu‡rio J‡ Cadastrado!", Toast.LENGTH_SHORT);
				
			} // end if
			
		} // end while
		
	} // end method salvar

	public Account buscarUserName()
	{
		account = accountAdapter.buscarUserName(edtUserName.getText().toString());
		return account;
		
	} // end method buscarUserName

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
} // end class NewAccountActivity
