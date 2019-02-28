package edu.neumont.csc150.sudoku.view.sudokumainmenu;

import java.io.IOException;

import edu.neumont.csc150.sudoku.view.SudokuViewController;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class SudokuMainMenuViewController {
	public void click(ActionEvent e) {
		SudokuViewController.getInstance().click(e);
	}

	public void onNewGame(MouseEvent e) throws IOException {
		SudokuViewController.getInstance().showDifficulty();
	}

	public void onLoadGame(MouseEvent e) {

	}
	
	public void onExit(MouseEvent e) {
		
	}
}
