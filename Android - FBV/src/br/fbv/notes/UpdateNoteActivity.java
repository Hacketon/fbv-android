package br.fbv.notes;

import java.io.Serializable;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import br.fbv.MainActivity;
import br.fbv.R;

public class UpdateNoteActivity extends Activity implements Serializable {

	private static final long serialVersionUID = 1L;

	//Button btnSaveNote;
	SQLiteDatabase bd = null;
	//NotificationManager nm;

	TextView title;
	TextView body;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();

		String aux = (String) intent.getSerializableExtra("nota");

		int idNote = Integer.parseInt(aux);

		title = (TextView) findViewById(R.id.noteTitleUpdate);
		body = (TextView) findViewById(R.id.noteBodyUpdate);
		
		fillData(idNote);

		setContentView(R.layout.updatenote);
	}

	public void fillData(int id) {
		dataBase();

		try {
			String sql = "SELECT * FROM notes WHERE _id = " + id;

			Cursor c = bd.rawQuery(sql, null);

			int titleIndex = c.getColumnIndex("title");
			int bodyIndex = c.getColumnIndex("body");

			c.moveToFirst();

			while (!c.isAfterLast()) {

				String titleNote = c.getString(titleIndex);
				String bodyNote = c.getString(bodyIndex);

				title.setText(titleNote);
				body.setText(bodyNote);

				c.moveToNext();
			}
			c.close();
			bd.close();

		} catch (SQLException e) {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
			alertDialog.setNegativeButton("Ok",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							// edtTitleNote.setText(null);
							// edtBodyNote.setText(null);
							Intent intent = new Intent(UpdateNoteActivity.this,
									MainActivity.class);
							startActivity(intent);
						} // end method onClick
					});
			alertDialog.show();
		}
	}

	public void editNote() {
		dataBase();
		try {
			String sql = "UPDATE notes SET (title, note) VALUES ('"
					+ title.getText().toString() + "','"
					+ body.getText().toString() + "')";
			bd.execSQL(sql);
			bd.close();
		} catch (SQLException e) {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
			alertDialog.setNegativeButton("Ok",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							title.setText(null);
							body.setText(null);
							Intent intent = new Intent(UpdateNoteActivity.this,
									MainActivity.class);
							startActivity(intent);
						} // end method onClick

					});
			alertDialog.show();

		} // end try/catch

	} // end method editNote

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
							Intent intent = new Intent(UpdateNoteActivity.this,
									MainActivity.class);
							startActivity(intent);
						} // end method onClick

					});

			alertDialog.show();
		} // end try/catch

	} // end method dataBase

} // end class EditNoteActivity
