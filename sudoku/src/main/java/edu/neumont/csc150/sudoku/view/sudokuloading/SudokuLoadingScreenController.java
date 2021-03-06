package edu.neumont.csc150.sudoku.view.sudokuloading;

import edu.neumont.csc150.sudoku.controller.SudokuController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SudokuLoadingScreenController {
	private SudokuController controller;
	
	@FXML
	private Label boardCount;
	
	@FXML
	private ImageView image;

	public void init(SudokuController controller) {
		this.controller = controller;
		Image img = new Image("/edu/neumont/csc150/sudoku/view/sudokuloading/Loading.png");
		image.setImage(img);	
		Timeline boardsMade = new Timeline(new KeyFrame(Duration.millis(25), e -> boardCountDisplay()));
		boardsMade.setCycleCount(Timeline.INDEFINITE);
		boardsMade.play();
	}

	private void boardCountDisplay() {
		this.boardCount.setText("Boards Made: " + controller.getBoardCount());
	}
}
