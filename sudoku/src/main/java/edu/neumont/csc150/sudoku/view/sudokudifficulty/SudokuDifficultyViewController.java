package edu.neumont.csc150.sudoku.view.sudokudifficulty;

import java.io.IOException;

import edu.neumont.csc150.sudoku.view.SudokuViewController;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class SudokuDifficultyViewController {
	
	SudokuViewController mainView;
	
	public void click(ActionEvent e) {
		mainView.click(e);
	}
	
	public void onEasy(MouseEvent e) throws IOException {
		mainView.showGame();
	}
	
	public void onMedium(MouseEvent e) throws IOException {
		mainView.showGame();
	}
	
	public void onHard(MouseEvent e) throws IOException {
		mainView.showGame();
	}

	public void init(SudokuViewController sudokuViewController) {
		mainView = sudokuViewController;
	}
	
}
