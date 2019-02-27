package edu.neumont.csc150.sudoku.view;

import java.io.IOException;
import java.net.URL;

import edu.neumont.csc150.sudoku.controller.SudokuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SudokuViewController implements SudokuView {
	
	private static SudokuViewController instance;
	private SudokuController controller;

	private Stage stage;
	private Scene mainMenuScene;
	private Scene difficultyScene;
	private Scene gameScene;

	public SudokuViewController() {
		setInstance(this);
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Stage getStage() {
		return stage;
	}
	
	public void registerController(SudokuController controller) {
		this.controller = controller;
	}

	@Override
	public void init() throws IOException {
		this.stage.setTitle("Sudoku");
		this.stage.setResizable(false);
		this.stage.setMinWidth(1280);
		this.stage.setMinHeight(720);
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

	public static SudokuViewController getInstance() {
		return instance;
	}

	public static void setInstance(SudokuViewController instance) {
		SudokuViewController.instance = instance;
	}
}
