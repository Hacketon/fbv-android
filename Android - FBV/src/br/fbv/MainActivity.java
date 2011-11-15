package br.fbv;

import br.fbv.notes.AddNoteActivity;
import br.fbv.notes.NoteListActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	Intent about;
	Intent newNote;
	Intent noteList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button btnNewNote = (Button) findViewById(R.id.btnNewNote);
		Button btnListNotes = (Button) findViewById(R.id.btnListNote);
		Button btnAbout = (Button) findViewById(R.id.btnAbout);

		about = new Intent(this, AboutActivity.class);
		newNote = new Intent(this, AddNoteActivity.class);
		noteList = new Intent( this, NoteListActivity.class);
		
		btnNewNote.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(newNote);
			}
		});

		btnListNotes.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivityForResult(noteList, 1);
			}
		});

		btnAbout.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(about);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if( requestCode == 1 ){
			if(resultCode == RESULT_OK )
			{
				startActivityForResult(noteList, 1);
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}