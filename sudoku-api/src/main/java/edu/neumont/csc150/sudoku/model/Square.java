package edu.neumont.csc150.sudoku.model;

public class Square {

	private int value;
	private boolean error;
	
	private boolean[] notes = new boolean[9];

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public boolean[] getNotes() {
		return notes;
	}

	public void setNotes(boolean[] notes) {
		this.notes = notes;
	}
}
