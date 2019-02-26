package edu.neumont.csc150.sudoku.controller;

import edu.neumont.csc150.sudoku.view.SudokuView;

public class SudokuController {
	
	private SudokuView view;

	public SudokuController(SudokuView viewController) {
		this.view = viewController;
	}

	public void run() {
		view.init();
	}

}
