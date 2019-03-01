package edu.neumont.csc150.sudoku.view;

import java.io.IOException;
import java.net.URL;

import edu.neumont.csc150.sudoku.controller.SudokuController;
import edu.neumont.csc150.sudoku.view.sudokudifficulty.SudokuDifficultyViewController;
import edu.neumont.csc150.sudoku.view.sudokugame.SudokuGameViewController;
import edu.neumont.csc150.sudoku.view.sudokumainmenu.SudokuMainMenuViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SudokuViewController implements SudokuView {

	private SudokuController controller;

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

	public void registerController(SudokuController controller) {
		this.controller = controller;
	}

	@Override
	public void init() throws IOException {
		this.stage.setTitle("Sudoku");
		this.stage.setResizable(false);
		this.stage.setWidth(1280);
		this.stage.setHeight(720);
		showMainMenu();
	}

	private void initMainMenu() throws IOException {
		URL location = getClass().getResource("/edu/neumont/csc150/sudoku/view/sudokumainmenu/SudokuMainMenuView.fxml");
		FXMLLoader loader = new FXMLLoader(location);
		Parent root = loader.load();
		SudokuMainMenuViewController mainMenu = loader.getController();
		mainMenu.init(this);
		
		mainMenuScene = new Scene(root);
	}

	private void initDifficulty() throws IOException {
		URL location = getClass()
				.getResource("/edu/neumont/csc150/sudoku/view/sudokudifficulty/SudokuDifficultyView.fxml");
		FXMLLoader loader = new FXMLLoader(location);
		Parent root = loader.load();
		SudokuDifficultyViewController difficulty = loader.getController();
		difficulty.init(this, controller);
		
		difficultyScene = new Scene(root);
	}

	private void initGame() throws IOException {
		URL location = getClass().getResource("/edu/neumont/csc150/sudoku/view/sudokugame/SudokuGameView.fxml");
		FXMLLoader loader = new FXMLLoader(location);
		Parent root = loader.load();
		SudokuGameViewController game = loader.getController();
		game.init(this, controller);
		
		gameScene = new Scene(root);
	}

	public void showMainMenu() throws IOException {
		initMainMenu();
		this.stage.setScene(mainMenuScene);
		this.stage.show();
	}

	public void showDifficulty() throws IOException {
		initDifficulty();
		this.stage.setScene(difficultyScene);
		this.stage.show();
	}

	public void showGame() throws IOException {
		initGame();
		this.stage.setScene(gameScene);
		this.stage.show();
	}

	public void click(ActionEvent e) {
		shutdown();
	}

	public void onNewGame(MouseEvent e) throws IOException {
		showDifficulty();
	}

	public void onLoadGame(MouseEvent e) {

	}

	public void shutdown() {
		stage.close();
	}
}
