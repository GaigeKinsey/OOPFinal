package edu.neumont.csc150.sudoku.view;

import java.io.IOException;
import java.net.URL;

import edu.neumont.csc150.sudoku.controller.SudokuController;
import edu.neumont.csc150.sudoku.model.Difficulty;
import edu.neumont.csc150.sudoku.view.sudokudifficulty.SudokuDifficultyViewController;
import edu.neumont.csc150.sudoku.view.sudokugame.SudokuGameViewController;
import edu.neumont.csc150.sudoku.view.sudokuloading.SudokuLoadingScreenController;
import edu.neumont.csc150.sudoku.view.sudokumainmenu.SudokuMainMenuViewController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SudokuViewController implements SudokuView {

	private SudokuController controller;

	private Stage stage;

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
		this.stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/edu/neumont/csc150/sudoku/view/icon.png")));
		showMainMenu();
	}

	private Scene initMainMenu() {
		URL location = getClass().getResource("/edu/neumont/csc150/sudoku/view/sudokumainmenu/SudokuMainMenuView.fxml");
		FXMLLoader loader = new FXMLLoader(location);
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
		}
		SudokuMainMenuViewController mainMenu = loader.getController();
		mainMenu.init(this, controller);

		return new Scene(root);
	}

	private Scene initDifficulty() {
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

		return new Scene(root);
	}

	private Scene initGame() {
		URL location = getClass().getResource("/edu/neumont/csc150/sudoku/view/sudokugame/SudokuGameView.fxml");
		FXMLLoader loader = new FXMLLoader(location);
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
		}
		SudokuGameViewController game = loader.getController();
		game.init(this, controller);

		return new Scene(root);
	}

	private Scene initLoadScreen() {
		URL location = getClass()
				.getResource("/edu/neumont/csc150/sudoku/view/sudokuloading/SudokuLoadingScreenView.fxml");
		FXMLLoader loader = new FXMLLoader(location);
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
		}
		SudokuLoadingScreenController load = loader.getController();
		load.init(controller);

		return new Scene(root);
	}

	public void showMainMenu() {
		this.stage.setScene(initMainMenu());
		this.stage.show();
	}

	public void showDifficulty() {
		this.stage.setScene(initDifficulty());
		this.stage.show();
	}

	public void showGame() {
		this.stage.setScene(initGame());
		this.stage.show();
	}

	public void showLoad() {
		this.stage.setScene(initLoadScreen());
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
