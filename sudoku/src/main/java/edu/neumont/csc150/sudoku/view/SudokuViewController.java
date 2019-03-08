package edu.neumont.csc150.sudoku.view;

import java.io.IOException;
import java.net.URISyntaxException;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class SudokuViewController implements SudokuView {

	private SudokuController controller;

	private Stage stage;
	
	private MediaPlayer player;

	private Scene mainMenu;
	private Scene difficultyMenu;
	private Scene gameMenu;
	private Scene loadingMenu;

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
		this.stage.getIcons()
				.add(new Image(this.getClass().getResourceAsStream("/edu/neumont/csc150/sudoku/view/icon.png")));
		Media media = null;
		try {
			media = new Media(
					this.getClass().getResource("/edu/neumont/csc150/sudoku/view/village.mp3").toURI().toString());
		} catch (URISyntaxException e) {
		}
		player = new MediaPlayer(media);
		player.setCycleCount(MediaPlayer.INDEFINITE);
		player.play();
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
		difficulty.init(this);

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
		if (mainMenu == null) {
			mainMenu = initMainMenu();
		}
		synchronized (SudokuViewController.class) {
			SudokuViewController.class.notifyAll();
		}
		this.stage.setScene(mainMenu);
		this.stage.show();
	}

	public void showDifficulty() {
		if (difficultyMenu == null) {
			difficultyMenu = initDifficulty();
		}
		synchronized (SudokuViewController.class) {
			SudokuViewController.class.notifyAll();
		}
		this.stage.setScene(difficultyMenu);
		this.stage.show();
	}

	public void showGame() {
		if (gameMenu == null) {
			gameMenu = initGame();
		}
		synchronized (SudokuViewController.class) {
			SudokuViewController.class.notifyAll();
		}
		this.stage.setScene(gameMenu);
		this.stage.show();
	}

	public void showLoad() {
		if (loadingMenu == null) {
			loadingMenu = initLoadScreen();
		}
		this.stage.setScene(loadingMenu);
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
				synchronized (SudokuGameViewController.class) {
					SudokuGameViewController.class.notifyAll();
				}
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

	public MediaPlayer getPlayer() {
		return player;
	}

	public void setPlayer(MediaPlayer player) {
		this.player = player;
	}
}
