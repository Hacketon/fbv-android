package br.fbv;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button btnNewNote = (Button) findViewById(R.id.btnNewNote);
		Button btnAbout = (Button) findViewById(R.id.btnAbout);

		btnNewNote.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				
			}
		});

		btnAbout.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

			}
		});

	}

}
