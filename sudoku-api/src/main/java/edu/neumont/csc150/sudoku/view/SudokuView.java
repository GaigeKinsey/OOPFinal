package edu.neumont.csc150.sudoku.view;

import java.io.IOException;

import edu.neumont.csc150.sudoku.controller.SudokuController;

public interface SudokuView {

	void init() throws IOException;

	void registerController(SudokuController sudokuController);

}
