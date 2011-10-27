package br.fbv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        
        final Button btnCadUser = (Button) findViewById(R.id.btnCadUser);
        btnCadUser.setOnClickListener(this);
        
    } // end method onCreate

	public void onClick(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(this, NewAccountActivity.class));
		finish();
		
	} // end method onClick
        
} // end class MainActivity