package edu.neumont.csc150.sudoku.view;

import edu.neumont.csc150.sudoku.controller.SudokuController;

public interface SudokuView {

	void init();

	void registerController(SudokuController sudokuController);

	void showGame();

}
