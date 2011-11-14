package br.fbv.notes;

import br.fbv.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNoteActivity extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addnote);
			
		Button salvar = (Button) findViewById(R.id.btnSave);
		
		salvar.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {

				EditText noteTitle = (EditText) findViewById(R.id.noteTitle);
				EditText noteBody = (EditText) findViewById(R.id.noteBody);
				
				String title = noteTitle.getText().toString();
				String body = noteBody.getText().toString();
				
				AlertDialog.Builder dialog = new AlertDialog.Builder(AddNoteActivity.this);
				dialog.setTitle(title);
				dialog.setMessage(body);
				dialog.setNeutralButton("OK", null);
				dialog.show();	
			}
		});
	}

}
