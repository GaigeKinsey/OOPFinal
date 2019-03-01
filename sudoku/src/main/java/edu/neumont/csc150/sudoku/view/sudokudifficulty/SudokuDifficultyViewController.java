package edu.neumont.csc150.sudoku.view.sudokudifficulty;

import java.io.IOException;

import edu.neumont.csc150.sudoku.controller.SudokuController;
import edu.neumont.csc150.sudoku.view.SudokuViewController;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class SudokuDifficultyViewController {
	
	private SudokuViewController mainView;
	private SudokuController controller;
	
	public void click(ActionEvent e) {
		mainView.click(e);
	}
	
	public void onEasy(MouseEvent e) throws IOException {
		controller.makeBoard("Easy");
		mainView.showGame();
	}
	
	public void onMedium(MouseEvent e) throws IOException {
		controller.makeBoard("Medium");
		mainView.showGame();
	}
	
	public void onHard(MouseEvent e) throws IOException {
		controller.makeBoard("Hard");
		mainView.showGame();
	}

	public void init(SudokuViewController sudokuViewController, SudokuController controller) {
		mainView = sudokuViewController;
		this.controller = controller;
	}
	
}
