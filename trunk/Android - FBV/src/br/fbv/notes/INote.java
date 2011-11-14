package br.fbv.notes;

public interface INote 
{
	public long inserir(Note note);
	public long exlcuir(int id);
	public long alterar(int id);
	public Note buscar(String title);	

} // end interface INote
