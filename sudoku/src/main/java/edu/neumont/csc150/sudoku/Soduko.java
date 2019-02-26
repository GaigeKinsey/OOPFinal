package edu.neumont.csc150.sudoku;

import java.net.URL;

import edu.neumont.csc150.sudoku.controller.SodukoController;
import edu.neumont.csc150.sudoku.view.SodukoViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Soduko extends Application{

	public static void main(String[] args) {
		Application.launch(Soduko.class, args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		URL location = this.getClass().getClassLoader().getResource("SodukoView.fxml");
		FXMLLoader loader = new FXMLLoader(location);
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		SodukoViewController viewController = loader.getController();
		viewController.setStage(stage);
		SodukoController controller = new SodukoController(viewController);
		controller.run();
	}
}
