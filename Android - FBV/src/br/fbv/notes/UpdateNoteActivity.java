package br.fbv.notes;

import java.io.Serializable;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import br.fbv.AboutActivity;
import br.fbv.MainActivity;
import br.fbv.R;

public class UpdateNoteActivity extends Activity implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int CREATE = Menu.FIRST;
	private static final int LIST = Menu.FIRST + 1;
	private static final int MAIN = Menu.FIRST + 2;
	private static final int ABOUT = Menu.FIRST + 3;
	private static final int CLOSE = Menu.FIRST + 4;

	private int idNote = 0;
	// Button btnSaveNote;
	SQLiteDatabase bd = null;
	// NotificationManager nm;

	EditText title;
	EditText body;

	Button btnUpdate;
	Button btnDelete;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.updatenote);

		title = (EditText) findViewById(R.id.noteTitleUpdate);
		body = (EditText) findViewById(R.id.noteBodyUpdate);

		setID();

		loadButtons();

		fillData();
	}

	public void setID() {
		Intent intent = getIntent();

		String aux = (String) intent.getSerializableExtra("nota");

		idNote = Integer.parseInt(aux);
	}

	public void loadButtons() {

		btnUpdate = (Button) findViewById(R.id.btnUpdateNote);
		btnDelete = (Button) findViewById(R.id.btnDeleteNote);

		btnUpdate.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				editNote();
			}
		});

		btnDelete.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				deleteNote(idNote);
			}
		});
	}

	public void fillData() {
		dataBase();

		int id = idNote;
		try {
			String sql = "SELECT * FROM notes WHERE _id = " + id;

			Cursor c = bd.rawQuery(sql, null);

			int titleIndex = c.getColumnIndex("title");
			int bodyIndex = c.getColumnIndex("note");

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
							finish();
						} // end method onClick
					});
			alertDialog.show();
		}
	}

	public void deleteNote(int id) {
		dataBase();
		try {
			String sql = "DELETE FROM notes WHERE _id = " + id;
			bd.execSQL(sql);
		} catch (SQLException e) {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
			alertDialog.setNegativeButton("Ok",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							finish();
						} // end method onClick
					});
			alertDialog.show();
		}
		bd.close();
		notificator();
		setResult(RESULT_OK, null);
		finish();
	}

	public void editNote() {
		dataBase();
		try {
			String sql = "UPDATE notes SET title = '"
					+ title.getText().toString() + "', " + "note = '"
					+ body.getText().toString() + "' WHERE _id = " + idNote;
			bd.execSQL(sql);
			bd.close();
		} catch (SQLException e) {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
			alertDialog.setNegativeButton("Ok",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							title.setText(null);
							body.setText(null);
							finish();
						} // end method onClick

					});
			alertDialog.show();

		} // end try/catch
		setResult(RESULT_OK, null);
		finish();
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
							finish();
						} // end method onClick
					});
			alertDialog.show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		menu.add(0, CREATE, 0, "Criar nota");
		menu.add(0, LIST, 0, "Listar notas");
		menu.add(0, MAIN, 0, "Tela principal");
		menu.add(0, ABOUT, 0, "About");
		menu.add(0, CLOSE, 0, "Fechar");

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case CREATE:
			Intent int1 = new Intent(this, AddNoteActivity.class);
			startActivity(int1);
			return true;
		case LIST:
			Intent int2 = new Intent(this, NoteListActivity.class);
			startActivity(int2);
			return true;
		case MAIN:
			Intent int3 = new Intent(this, MainActivity.class);
			startActivity(int3);
			return true;
		case ABOUT:
			Intent int4 = new Intent(this, AboutActivity.class);
			startActivity(int4);
			return true;
		case CLOSE:
			UpdateNoteActivity.this.finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public void notificator(){
		
		Context context = getApplicationContext();
		
		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		
		Intent intent = new Intent( context, MainActivity.class);
		
		Notification notification = new Notification(R.drawable.arrow, "Nota excluída", System.currentTimeMillis());
		
		PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);
		
		notification.setLatestEventInfo(context, "Notificação", "Nota excluída com sucesso", p);
		
		notificationManager.notify(R.string.notificator, notification);
	}
}