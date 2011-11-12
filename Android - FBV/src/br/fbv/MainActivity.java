package br.fbv;

import br.fbv.account.NewAccountActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity 
{
	Intent i = new Intent( this, NewAccountActivity.class);

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(i);
	}
}
