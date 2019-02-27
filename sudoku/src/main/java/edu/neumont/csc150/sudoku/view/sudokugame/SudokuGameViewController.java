package edu.neumont.csc150.sudoku.view.sudokugame;

import edu.neumont.csc150.sudoku.view.SudokuViewController;
import javafx.event.ActionEvent;

public class SudokuGameViewController {
	public void click(ActionEvent e) {
		SudokuViewController.getInstance().click(e);
	}
}
