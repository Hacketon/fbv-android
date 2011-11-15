package br.fbv.notes;

import java.util.List;

import br.fbv.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NoteAdapter extends BaseAdapter {

	private Context context;
	private List<Note> lista;

	public NoteAdapter(Context context, List<Note> lista) {
		this.context = context;
		this.lista = lista;
	}

	public int getCount() {
		return lista.size();
	}

	public Object getItem(int position) {
		Note nota = lista.get(position);
		return nota;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		Note nota = lista.get(position);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.note_detalhes, null);
		TextView textName = (TextView) v.findViewById(R.id.nome);
		textName.setText(nota.getTitle());
		
		return v;
	}

}
