package edu.neumont.csc150.sudoku.view.sudokuloading;

import edu.neumont.csc150.sudoku.controller.SudokuController;
import edu.neumont.csc150.sudoku.view.SudokuViewController;

public class SudokuLoadingScreenViewController {
	
	private SudokuViewController mainView;
	private SudokuController controller;

	public void init(SudokuViewController sudokuViewController, SudokuController controller) {
		mainView = sudokuViewController;
		this.controller = controller;
	}

}
