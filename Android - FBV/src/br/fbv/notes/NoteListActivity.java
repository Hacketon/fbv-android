package br.fbv.notes;

import java.io.Serializable;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import br.fbv.MainActivity;
import br.fbv.R;

public class NoteListActivity extends Activity implements OnItemClickListener, Serializable {

	private static final long serialVersionUID = 1L;
	private ListView list;
	ArrayList<Note> notes;
	SQLiteDatabase bd = null;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.notes);

		notes = new ArrayList<Note>();
		
		loadNotes();
	}

	public void loadNotes() {
		dataBase();

		try {
			String sql = "SELECT * FROM notes ORDER BY _id";

			Cursor c = bd.rawQuery(sql, null);

			int id = c.getColumnIndex("_id");
			int titlesIndex = c.getColumnIndex("title");
			
			Note aux;

			c.moveToFirst();
			while (!c.isAfterLast()) {
				
				int idNote = c.getInt(id);
				String titleNote = c.getString(titlesIndex);
				
				aux = new Note(idNote, titleNote);
				notes.add(aux);
				c.moveToNext();
			}
			c.close();
			bd.close();

			NoteAdapter adapter = new NoteAdapter(this, notes);

			list = (ListView) findViewById(R.id.noteList);
			list.setAdapter(adapter);
			
			list.setOnItemClickListener(this);
			
		} catch (SQLException e) {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
			alertDialog.setNegativeButton("Ok",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							// edtTitleNote.setText(null);
							// edtBodyNote.setText(null);
							Intent intent = new Intent(NoteListActivity.this,
									MainActivity.class);
							startActivity(intent);
						} // end method onClick
					});
			alertDialog.show();
		}
	}

	public void dataBase() {
		try {
			String nameDataBase = "gecko_note";
			bd = openOrCreateDatabase(nameDataBase, MODE_WORLD_READABLE, null);
			String sql = "CREATE TABLE IF NOT EXISTS notes "
					+ "(_id INTEGER PRIMARY KEY, " + " title TEXT, "
					+ " note TEXT);";
			bd.execSQL(sql);

		} catch (SQLException e) {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
			alertDialog.setNegativeButton("Ok",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(NoteListActivity.this,
									MainActivity.class);
							startActivity(intent);
						} // end method onClick
					});
			alertDialog.show();
		} // end try/catch

	} // end method dataBase

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
		Note nota = (Note) list.getAdapter().getItem(position);
		
		int noteID = nota.get_id();
		
		Intent intent = new Intent(this, UpdateNoteActivity.class);
		
		String aux = Integer.toString(noteID);
		
		intent.putExtra("nota", aux);

		//startActivityForResult(intent, -1);
		startActivity(intent);
		
	}

} // end class NoteListActivity
