package br.fbv.notes;

import br.fbv.MainActivity;
import br.fbv.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
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
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(this, "Erro ao recuperar dados do título e corpo da nota!", Toast.LENGTH_LONG).show();
		} // end try/catch
		
		try {
			btnSaveNote = (Button) findViewById(R.id.btnSave);
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(this, "Erro ao recuperar o botão Salvar!", Toast.LENGTH_LONG).show();
		}
		
		btnSaveNote.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				try {
					saveNote();
					
					AlertDialog.Builder adb = new AlertDialog.Builder(AddNoteActivity.this);
					adb.setTitle("Confirmação");
					adb.setMessage("Nota Adicionada com Sucesso!");
					adb.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							edtTitleNote.setText(null);
							edtBodyNote.setText(null);
							Intent intent = new Intent(AddNoteActivity.this, MainActivity.class);
							startActivity(intent);
							finish();
						
						} // end method onClick
						
					});
					
					adb.show();

				} catch (SQLException e) {
					// TODO: handle exception
					AlertDialog.Builder adb = new AlertDialog.Builder(AddNoteActivity.this);
					adb.setTitle("Erro");
					adb.setMessage("Nota não adicionada!");
					adb.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							finish();
						} // end method onClick
						
					});
					adb.show();
				} // end try/catch
				
			} // end method onClick
			
		});
	}
	
	public void saveNote()
	{
		dataBase();
		try {
			String sql = "INSERT INTO notes (title, note) VALUES ('" + 
					   edtTitleNote.getText().toString() + "','" + 
					   edtBodyNote.getText().toString() + "')";
			bd.execSQL(sql);
			bd.close();
		} catch (SQLException e) {
			// TODO: handle exception
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
			alertDialog.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) 
				{
					// TODO Auto-generated method stub
					edtTitleNote.setText(null);
					edtBodyNote.setText(null);
					Intent intent = new Intent(AddNoteActivity.this, MainActivity.class);
					startActivity(intent);
				} // end method onClick
				
			});
			alertDialog.show();
			
		} // end try/catch
		
	} // end method saveNote
	
	public void dataBase()
	{
		try {
			String nameDataBase = "gecko_note";
			bd = openOrCreateDatabase(nameDataBase, MODE_WORLD_READABLE, null);			
			String sql = "CREATE TABLE IF NOT EXISTS notes " +
					 "(_id INTERGER PRIMARY KEY, " +
					 " title TEXT, " + 
					 " note TEXT);";
			bd.execSQL(sql);
			
		} catch (SQLException e) {
			// TODO: handle exception
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
			alertDialog.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) 
				{
					// TODO Auto-generated method stub
					Intent intent = new Intent(AddNoteActivity.this, MainActivity.class);
					startActivity(intent);
				} // end method onClick
				
			});
			
			alertDialog.show();
		} // end try/catch
		
	} // end method dataBase

}
