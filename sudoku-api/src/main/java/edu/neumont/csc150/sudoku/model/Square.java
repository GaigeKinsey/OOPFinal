package edu.neumont.csc150.sudoku.model;

import java.io.Serializable;

public class Square implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int value;
	private boolean isHint;

	private boolean error;
	private boolean modified = true;

	private boolean[] notes = new boolean[9];

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isHint() {
		return isHint;
	}

	public void setHint(boolean isHint) {
		this.isHint = isHint;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		if (this.error != error) {
			this.error = error;
			this.setModified(true);
		}
	}

	public boolean[] getNotes() {
		return notes;
	}

	public void setNotes(boolean[] notes) {
		this.notes = notes;
	}

	public boolean isModified() {
		return modified;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}
}
