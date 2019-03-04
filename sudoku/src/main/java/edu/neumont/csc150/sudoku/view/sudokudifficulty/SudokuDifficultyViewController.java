package edu.neumont.csc150.sudoku.view.sudokudifficulty;

import edu.neumont.csc150.sudoku.controller.SudokuController;
import edu.neumont.csc150.sudoku.model.Difficulty;
import edu.neumont.csc150.sudoku.view.SudokuViewController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class SudokuDifficultyViewController {

	private SudokuViewController mainView;
	private SudokuController controller;

	@FXML
	private Label easy;
	@FXML
	private Label medium;
	@FXML
	private Label hard;

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
