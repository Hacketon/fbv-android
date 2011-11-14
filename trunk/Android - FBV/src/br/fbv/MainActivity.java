package br.fbv;

import br.fbv.notes.AddNoteActivity;
import br.fbv.notes.NoteListActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button btnNewNote = (Button) findViewById(R.id.btnNewNote);
		Button btnListNotes = (Button) findViewById(R.id.btnListNote);
		Button btnAbout = (Button) findViewById(R.id.btnAbout);

		final Intent about = new Intent(this, AboutActivity.class);
		final Intent newNote = new Intent(this, AddNoteActivity.class);
		final Intent noteList = new Intent( this, NoteListActivity.class);

		btnNewNote.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(newNote);
			}
		});

		btnListNotes.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(noteList);
			}
		});

		btnAbout.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(about);
			}
		});
	}
}