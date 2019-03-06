package edu.neumont.csc150.sudoku.view.sudokumainmenu;

import java.io.File;
import java.io.IOException;

import edu.neumont.csc150.sudoku.controller.SudokuController;
import edu.neumont.csc150.sudoku.view.SudokuViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class SudokuMainMenuViewController {

	SudokuViewController mainView;
	SudokuController controller;

	@FXML
	private ImageView image;

	public void click(ActionEvent e) {
		mainView.click(e);
	}

	public void onNewGame(MouseEvent e) throws IOException {
		mainView.showDifficulty();
	}

	public void onLoadGame(MouseEvent e) {
		File file = null;
		do {
			FileChooser chooser = new FileChooser();
			chooser.setTitle("Select saved file");
			file = chooser.showOpenDialog(this.mainView.getStage());
			if (file == null) {
				return;
			}
			try {
				this.controller.load(file);
			} catch (IOException | ClassNotFoundException ex) {
				file = null;
				new Alert(AlertType.ERROR, "An error occurred trying to load the file \nPlease select another.",
						ButtonType.OK).show();
			}
		} while (file == null);
		this.mainView.showGame();
	}

	public void onExit(MouseEvent e) {
		mainView.shutdown();
	}

	public void init(SudokuViewController sudokuViewController, SudokuController controller) {
		mainView = sudokuViewController;
		this.controller = controller;;
		Image img = new Image("/edu/neumont/csc150/sudoku/view/sudokumainmenu/title.png");
		image.setImage(img);
	}
}
