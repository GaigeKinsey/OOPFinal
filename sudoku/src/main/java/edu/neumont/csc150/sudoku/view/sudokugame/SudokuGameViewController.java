package edu.neumont.csc150.sudoku.view.sudokugame;

import edu.neumont.csc150.sudoku.view.SudokuViewController;
import javafx.event.ActionEvent;

public class SudokuGameViewController {
	
	SudokuViewController mainView;
	
	public void click(ActionEvent e) {
		mainView.click(e);
	}
	
	public void onSave(ActionEvent e) {
		
	}
	
	public void onRestart(ActionEvent e) {
		
	}
	
	public void onNewPuzzle(ActionEvent e) {
		
	}
	
	public void onExit(ActionEvent e) {
		
	}

	public void init(SudokuViewController sudokuViewController) {
		mainView = sudokuViewController;
	}
}
