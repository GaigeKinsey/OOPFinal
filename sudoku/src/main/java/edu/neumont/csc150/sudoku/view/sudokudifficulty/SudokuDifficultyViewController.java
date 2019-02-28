package edu.neumont.csc150.sudoku.view.sudokudifficulty;

import java.io.IOException;

import edu.neumont.csc150.sudoku.view.SudokuViewController;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class SudokuDifficultyViewController {
	public void click(ActionEvent e) {
		SudokuViewController.getInstance().click(e);
	}
	
	public void onEasy(MouseEvent e) throws IOException {
		SudokuViewController.getInstance().showGame();
	}
	
	public void onMedium(MouseEvent e) throws IOException {
		SudokuViewController.getInstance().showGame();
	}
	
	public void onHard(MouseEvent e) throws IOException {
		SudokuViewController.getInstance().showGame();
	}
	
}
