package edu.neumont.csc150.sudoku;

import java.net.URL;

import edu.neumont.csc150.sudoku.controller.SudokuController;
import edu.neumont.csc150.sudoku.view.SudokuViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Sudoku extends Application{

	public static void main(String[] args) {
		Application.launch(Sudoku.class, args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		URL location = this.getClass().getClassLoader().getResource("SudokuView.fxml");
		FXMLLoader loader = new FXMLLoader(location);
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		SudokuViewController viewController = loader.getController();
		viewController.setStage(stage);
		SudokuController controller = new SudokuController(viewController);
		controller.run();
	}
}
