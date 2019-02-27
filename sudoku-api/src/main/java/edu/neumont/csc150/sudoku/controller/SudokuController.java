package edu.neumont.csc150.sudoku.controller;

import java.io.IOException;

import edu.neumont.csc150.sudoku.view.SudokuView;

public class SudokuController {
	
	private SudokuView view;

	public SudokuController(SudokuView viewController) {
		this.view = viewController;
		this.view.registerController(this);
	}

	public void run() throws IOException {
		view.init();
	}

}
