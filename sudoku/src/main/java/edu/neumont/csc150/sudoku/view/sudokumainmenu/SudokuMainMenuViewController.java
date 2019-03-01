package edu.neumont.csc150.sudoku.view.sudokumainmenu;

import java.io.IOException;

import edu.neumont.csc150.sudoku.view.SudokuViewController;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class SudokuMainMenuViewController {
	
	SudokuViewController mainView;
	
	public void click(ActionEvent e) {
		mainView.click(e);
	}

	public void onNewGame(MouseEvent e) throws IOException {
		mainView.showDifficulty();
	}

	public void onLoadGame(MouseEvent e) {

	}
	
	public void onExit(MouseEvent e) {
		
	}

	public void init(SudokuViewController sudokuViewController) {
		mainView = sudokuViewController;
	}
}
