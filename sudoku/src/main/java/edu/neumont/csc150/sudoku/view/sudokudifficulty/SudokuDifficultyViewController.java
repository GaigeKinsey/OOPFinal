package edu.neumont.csc150.sudoku.view.sudokudifficulty;

import edu.neumont.csc150.sudoku.view.SudokuViewController;
import javafx.event.ActionEvent;

public class SudokuDifficultyViewController {
	public void click(ActionEvent e) {
		SudokuViewController.getInstance().click(e);
	}
}
