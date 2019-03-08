package edu.neumont.csc150.sudoku;

import edu.neumont.csc150.sudoku.controller.SudokuController;
import edu.neumont.csc150.sudoku.view.SudokuViewController;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Sudoku extends Application {

	public static void main(String[] args) {
		Application.launch(Sudoku.class, args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		SudokuViewController viewController = new SudokuViewController();
		viewController.setStage(stage);
		SudokuController controller = new SudokuController(viewController);
		Font.loadFont(getClass().getResourceAsStream("/edu/neumont/csc150/sudoku/view/lastninja.ttf"), 25);
		controller.run();
	}
}
