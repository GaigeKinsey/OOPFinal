package edu.neumont.csc150.sudoku.view.sudokumainmenu;

import java.io.File;
import java.io.IOException;

import edu.neumont.csc150.sudoku.controller.SudokuController;
import edu.neumont.csc150.sudoku.view.SudokuViewController;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class SudokuMainMenuViewController {
	
	SudokuViewController mainView;
	SudokuController controller;
	
	public void click(ActionEvent e) {
		mainView.click(e);
	}

	public void onNewGame(MouseEvent e) throws IOException {
		mainView.showDifficulty();
	}

	public void onLoadGame(ActionEvent e) {
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
				new Alert(AlertType.ERROR, "An error occurred trying to load the file \nPlease select another.", ButtonType.OK).show();
			}
		} while (file == null);
	}
	
	public void onExit(MouseEvent e) {
		mainView.shutdown();
	}

	public void init(SudokuViewController sudokuViewController, SudokuController controller) {
		mainView = sudokuViewController;
		this.controller = controller;
	}
}
