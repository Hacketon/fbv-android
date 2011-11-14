package br.fbv.notes;

import br.fbv.MainActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.widget.EditText;

public class UpdateNoteActivity extends Activity 
{
	
	EditText edtTitleNote;
	EditText edtBodyNote;
	Button btnSaveNote;
	SQLiteDatabase bd = null;
	NotificationManager nm;
	
	public void editNote()
	{
		dataBase();
		try {
			String sql = "UPDATE notes SET (title, note) VALUES ('" + 
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
					Intent intent = new Intent(UpdateNoteActivity.this, MainActivity.class);
					startActivity(intent);
				} // end method onClick
				
			});
			alertDialog.show();
			
		} // end try/catch
		
	} // end method editNote
	
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
					Intent intent = new Intent(UpdateNoteActivity.this, MainActivity.class);
					startActivity(intent);
				} // end method onClick
				
			});
			
			alertDialog.show();
		} // end try/catch
		
	} // end method dataBase

} // end class EditNoteActivity
