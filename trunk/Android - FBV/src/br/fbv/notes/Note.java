package br.fbv.notes;

public class Note 
{
	private int id;
	private String title;
	private String note;
	
	/**
	 * @param id
	 * @param title
	 * @param note
	 */
	public Note(int id, String title, String note) 
	{
		super();
		this.id = id;
		this.title = title;
		this.note = note;
	
	} // end constructor Note

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	
} // end class Note
