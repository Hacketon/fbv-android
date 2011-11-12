package br.fbv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import br.fbv.account.NewAccountActivity;
import br.fbv.notes.NoteListActivity;

public class SplashActivity extends Activity implements Runnable {

	private Handler handler;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.splash);

		handler = new Handler();

		handler.postDelayed(this, 2000);
	}

	protected void onPause() {
		super.onPause();

		handler.removeCallbacks(this);
	}

	public void run() {
		Intent it = new Intent(this, NoteListActivity.class);
		Intent cadastro = new Intent(this, NewAccountActivity.class);

		startActivity(cadastro);

		finish();

		overridePendingTransition(android.R.anim.fade_in,
				android.R.anim.fade_out);
	}

}
