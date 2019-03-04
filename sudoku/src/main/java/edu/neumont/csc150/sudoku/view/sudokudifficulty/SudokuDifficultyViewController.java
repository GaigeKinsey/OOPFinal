package edu.neumont.csc150.sudoku.view.sudokudifficulty;

import edu.neumont.csc150.sudoku.controller.SudokuController;
import edu.neumont.csc150.sudoku.model.Difficulty;
import edu.neumont.csc150.sudoku.view.SudokuViewController;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class SudokuDifficultyViewController {

	private SudokuViewController mainView;
	private SudokuController controller;

	public void click(ActionEvent e) {
		mainView.click(e);
	}

	public void onEasy(MouseEvent e) {
		mainView.loadBoard(Difficulty.Easy);
	}

	public void onMedium(MouseEvent e) {
		mainView.loadBoard(Difficulty.Medium);
	}

	public void onHard(MouseEvent e) {
		mainView.loadBoard(Difficulty.Hard);
	}

	public void init(SudokuViewController sudokuViewController, SudokuController controller) {
		mainView = sudokuViewController;
		this.controller = controller;
	}

}
