package edu.neumont.csc150.sudoku.view.sudokudifficulty;

import edu.neumont.csc150.sudoku.model.Difficulty;
import edu.neumont.csc150.sudoku.view.SudokuViewController;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SudokuDifficultyViewController {

	private SudokuViewController mainView;
	
	@FXML
	private ImageView image;

	public void onEasy(MouseEvent e) {
		mainView.loadBoard(Difficulty.Easy);
	}

	public void onMedium(MouseEvent e) {
		mainView.loadBoard(Difficulty.Medium);
	}

	public void onHard(MouseEvent e) {
		mainView.loadBoard(Difficulty.Hard);
	}

	public void init(SudokuViewController sudokuViewController) {
		mainView = sudokuViewController;
		Image img = new Image("/edu/neumont/csc150/sudoku/view/sudokudifficulty/DifficultyTitle.png");
		image.setImage(img);
	}

}
