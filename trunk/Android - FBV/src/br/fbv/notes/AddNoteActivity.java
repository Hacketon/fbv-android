package br.fbv.notes;

import br.fbv.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNoteActivity extends Activity {
	
	EditText edtTitleNote;
	EditText edtBodyNote;
	Button btnSaveNote;
	SQLiteDatabase bd = null;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addnote);
		
		try {
			edtTitleNote = (EditText) findViewById(R.id.noteTitle);
			edtBodyNote = (EditText) findViewById(R.id.noteBody);
			btnSaveNote = (Button) findViewById(R.id.btnSave);
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(this, "Erro ao recuperar dados!", Toast.LENGTH_LONG).show();
		}
		
		btnSaveNote.setOnClickListener(new View.OnClickListener() {
			
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
