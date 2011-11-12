package br.fbv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import br.fbv.account.NewAccountActivity;

public class MainActivity extends Activity 
{
	Intent it = new Intent( this, NewAccountActivity.class);
	
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button btnLogin = (Button) findViewById( R.id.botaoWelcome);
		
		btnLogin.setOnClickListener( new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(it);
			}
		});
	}
}
