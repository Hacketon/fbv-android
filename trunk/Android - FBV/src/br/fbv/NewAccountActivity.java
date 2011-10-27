package br.fbv;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class NewAccountActivity extends Activity implements OnClickListener
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form_cad_user);
		final Button btnSaveUser = (Button) findViewById(R.id.btnSaveUser);
		btnSaveUser.setOnClickListener(this);
		
	} // end method onCreate

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		// recupera usuario
		EditText edtCadUser = (EditText) findViewById(R.id.edtCadUser);
		String stringCadUser = edtCadUser.getText().toString();
		
		// recupera senha
		EditText edtCadPasswd = (EditText) findViewById(R.id.edtCadPasswd);
		String stringCadPasswd = edtCadPasswd.getText().toString();
		
		// recupera redigitacao senha
		EditText edtCadPasswdCheck = (EditText) findViewById(R.id.edtCadPasswdCheck);
		String stringCadPasswdCheck = edtCadPasswdCheck.getText().toString();
		
	} // end method onClick	
	
} // end class NewAccountActivity
