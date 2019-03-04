package edu.neumont.csc150.sudoku.view;

import java.io.IOException;
import java.net.URL;

import edu.neumont.csc150.sudoku.controller.SudokuController;
import edu.neumont.csc150.sudoku.model.Difficulty;
import edu.neumont.csc150.sudoku.view.sudokudifficulty.SudokuDifficultyViewController;
import edu.neumont.csc150.sudoku.view.sudokugame.SudokuGameViewController;
import edu.neumont.csc150.sudoku.view.sudokumainmenu.SudokuMainMenuViewController;
import javafx.application.Platform;
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
	private Scene loadScreenScene;

	private Difficulty difficulty;

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
	public void init() {
		this.stage.setTitle("Sudoku");
		this.stage.setResizable(false);
		this.stage.setWidth(1280);
		this.stage.setHeight(720);
		showMainMenu();
	}

	private void initMainMenu() {
		URL location = getClass().getResource("/edu/neumont/csc150/sudoku/view/sudokumainmenu/SudokuMainMenuView.fxml");
		FXMLLoader loader = new FXMLLoader(location);
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
		}
		SudokuMainMenuViewController mainMenu = loader.getController();
		mainMenu.init(this, controller);

		mainMenuScene = new Scene(root);
	}

	private void initDifficulty() {
		URL location = getClass()
				.getResource("/edu/neumont/csc150/sudoku/view/sudokudifficulty/SudokuDifficultyView.fxml");
		FXMLLoader loader = new FXMLLoader(location);
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
		}
		SudokuDifficultyViewController difficulty = loader.getController();
		difficulty.init(this, controller);

		difficultyScene = new Scene(root);
	}

	private void initGame() {
		URL location = getClass().getResource("/edu/neumont/csc150/sudoku/view/sudokugame/SudokuGameView.fxml");
		FXMLLoader loader = new FXMLLoader(location);
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
		}
		SudokuGameViewController game = loader.getController();
		game.init(this, controller);

		gameScene = new Scene(root);
	}

	private void initLoadScreen() {
		URL location = getClass()
				.getResource("/edu/neumont/csc150/sudoku/view/sudokuloading/SudokuLoadingScreenView.fxml");
		FXMLLoader loader = new FXMLLoader(location);
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
		}

		loadScreenScene = new Scene(root);
	}

	public void showMainMenu() {
		initMainMenu();
		this.stage.setScene(mainMenuScene);
		this.stage.show();
	}

	public void showDifficulty() {
		initDifficulty();
		this.stage.setScene(difficultyScene);
		this.stage.show();
	}

	public void showGame() {
		initGame();
		this.stage.setScene(gameScene);
		this.stage.show();
	}

	public void showLoad() {
		initLoadScreen();
		this.stage.setScene(loadScreenScene);
		this.stage.show();
	}

	public void loadBoard(Difficulty difficulty) {
		this.showLoad();
		Runnable task = new Runnable() {
			@Override
			public void run() {
				Runnable showGame = new Runnable() {
					@Override
					public void run() {
						setDifficulty(difficulty);
						showGame();
					}
				};
				controller.makeBoard(difficulty);
				Platform.runLater(showGame);
			}
		};
		new Thread(task).start();
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

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
}
