package br.fbv.notes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.fbv.AboutActivity;
import br.fbv.MainActivity;
import br.fbv.R;

public class AddNoteActivity extends Activity {

	EditText edtTitleNote;
	EditText edtBodyNote;
	Button btnSaveNote;
	SQLiteDatabase bd = null;
	
	private static final int CLOSE = Menu.FIRST;
	private static final int LIST = Menu.FIRST + 1;
	private static final int MAIN = Menu.FIRST + 2;
	private static final int ABOUT = Menu.FIRST + 3;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addnote);
		
		try {
			edtTitleNote = (EditText) findViewById(R.id.noteTitle);
			edtBodyNote = (EditText) findViewById(R.id.noteBody);
		} catch (Exception e) {
			Toast.makeText(this,
					"Erro ao recuperar dados do titulo e corpo da nota!",
					Toast.LENGTH_LONG).show();
		} // end try/catch

		try {
			btnSaveNote = (Button) findViewById(R.id.btnSave);
		} catch (Exception e) {
			Toast.makeText(this, "Erro ao recuperar o botao Salvar!",
					Toast.LENGTH_LONG).show();
		}

		btnSaveNote.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				try {
					saveNote();

					AlertDialog.Builder adb = new AlertDialog.Builder(
							AddNoteActivity.this);
					adb.setTitle(edtTitleNote.getText().toString());
					adb.setMessage("Nota Adicionada com Sucesso!");
					adb.setNegativeButton("Ok",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									finish();
								} // end method onClick
							});
					adb.show();
				} catch (SQLException e) {
					AlertDialog.Builder adb = new AlertDialog.Builder(
							AddNoteActivity.this);
					adb.setTitle("Erro");
					adb.setMessage("Nota nao adicionada!");
					adb.setNegativeButton("Ok",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									finish();
								} // end method onClick
							});
					adb.show();
				} // end try/catch

			} // end method onClick

		});
	}

	public void saveNote() {
		dataBase();
		try {
			String sql = "INSERT INTO notes (title, note) VALUES ('"
					+ edtTitleNote.getText().toString() + "','"
					+ edtBodyNote.getText().toString() + "')";
			bd.execSQL(sql);
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

		} // end try/catch

	} // end method saveNote

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
		} // end try/catch
	} // end method dataBase

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		menu.add(0, LIST, 0, "Listar notas");
		menu.add(0, MAIN, 0, "Tela principal");
		menu.add(0, ABOUT, 0, "About");
		menu.add(0, CLOSE, 0, "Fechar");

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case LIST:
			Intent int1 = new Intent(this, NoteListActivity.class);
			startActivity(int1);
			return true;
		case MAIN:
			Intent int2 = new Intent(this, MainActivity.class);
			startActivity(int2);
			return true;
		case ABOUT:
			Intent int3 = new Intent(this, AboutActivity.class);
			startActivity(int3);
			return true;
		case CLOSE:
			AddNoteActivity.this.finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}