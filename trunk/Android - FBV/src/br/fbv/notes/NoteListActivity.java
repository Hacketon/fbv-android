package br.fbv.notes;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.fbv.MainActivity;
import br.fbv.R;

public class NoteListActivity extends Activity {

	private ListView list;
	ArrayList<String> titles;
	SQLiteDatabase bd = null;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.notes);

		titles = new ArrayList<String>();
		
		// String[] notas = new String[] { "001", "002", "003" };
		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, notas);
		//
		// list = (ListView) findViewById(R.id.noteList);
		// list.setAdapter(adapter);

		loadNotes();
	}

	public void loadNotes() {
		dataBase();

		try {
			String sql = "SELECT * FROM notes ORDER BY _id";

			Cursor c = bd.rawQuery(sql, null);

			int titlesIndex = c.getColumnIndex("title");

			c.moveToFirst();
			while (!c.isAfterLast()) {
				String titleNote = c.getString(titlesIndex);
				titles.add(titleNote);
				c.moveToNext();
			}
			c.close();
			bd.close();

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, titles);

			list = (ListView) findViewById(R.id.noteList);
			list.setAdapter(adapter);
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

} // end class NoteListActivity
