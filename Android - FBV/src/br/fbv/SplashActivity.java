package br.fbv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity implements Runnable {

	private Handler handler;

	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		handler = new Handler();
		handler.postDelayed(this, 3000);
		
	} // end method onCreate

	protected void onPause() 
	{
		super.onPause();
		handler.removeCallbacks(this);
		
	} // end method onCreate

	public void run() 
	{
		Intent it = new Intent(this, MainActivity.class);
		startActivity(it);
		finish();
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
	
	} // end method run

} // end class SplashActivity
