package br.fbv.notes;

import br.fbv.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NoteListActivity extends Activity {
	
	public ListView list;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.notes);

		String[] notas = new String[] { "001", "002", "003" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, notas);
		
		list = (ListView) findViewById(R.id.noteList);
		list.setAdapter(adapter);
	}

	
} // end class NoteListActivity
