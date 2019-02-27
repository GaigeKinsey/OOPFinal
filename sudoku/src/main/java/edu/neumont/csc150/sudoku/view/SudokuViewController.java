package edu.neumont.csc150.sudoku.view;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SudokuViewController implements SudokuView {

	private Stage stage;
	private Scene mainMenuScene;
	private Scene difficultyScene;
	private Scene gameScene;

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Stage getStage() {
		return stage;
	}

	@Override
	public void init() throws IOException {
		stage.setTitle("Sudoku");
		stage.setResizable(false);
		stage.setMaximized(true);
		initMainMenu();
		initDifficulty();
		initGame();
		showMainMenu();
	}

	private void initMainMenu() throws IOException {
		URL location = getClass().getResource("/edu/neumont/csc150/sudoku/view/sudokumainmenu/SudokuMainMenuView.fxml");
		FXMLLoader loader = new FXMLLoader(location);
		Parent root = loader.load();
		mainMenuScene = new Scene(root);
	}

	private void initDifficulty() throws IOException {
		URL location = getClass()
				.getResource("/edu/neumont/csc150/sudoku/view/sudokudifficulty/SudokuDifficultyView.fxml");
		FXMLLoader loader = new FXMLLoader(location);
		Parent root = loader.load();
		difficultyScene = new Scene(root);
	}

	private void initGame() throws IOException {
		URL location = getClass().getResource("/edu/neumont/csc150/sudoku/view/sudokugame/SudokuGameView.fxml");
		FXMLLoader loader = new FXMLLoader(location);
		Parent root = loader.load();
		gameScene = new Scene(root);
	}

	public void showMainMenu() throws IOException {
		stage.setScene(mainMenuScene);
		stage.show();
	}

	public void showDifficulty() {
		this.stage.hide();
		this.stage.setScene(difficultyScene);
		this.stage.show();
	}

	public void showGame() {
		this.stage.setScene(gameScene);
		this.stage.show();
	}

	public void click(ActionEvent e) {
		shutdown();
	}

	public void onNewGame(MouseEvent e) {
		showDifficulty();
	}

	public void onLoadGame(MouseEvent e) {

	}

	public void shutdown() {
		stage.close();
	}
}
