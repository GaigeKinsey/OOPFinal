package edu.neumont.csc150.sudoku.view.sudokumainmenu;

import java.io.File;
import java.io.IOException;

import edu.neumont.csc150.sudoku.controller.SudokuController;
import edu.neumont.csc150.sudoku.view.SudokuViewController;
import edu.neumont.csc150.sudoku.view.sudokugame.SudokuGameViewController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;

public class SudokuMainMenuViewController {

	private SudokuViewController mainView;
	private SudokuController controller;
	private MediaPlayer player;

	@FXML
	private ImageView image;

	@FXML
	private Button muteButton;

	public void onNewGame(MouseEvent e) throws IOException {
		mainView.showDifficulty();
	}

	public void onLoadGame(MouseEvent e) {
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
				new Alert(AlertType.ERROR, "An error occurred trying to load the file \nPlease select another.",
						ButtonType.OK).showAndWait();
			}
		} while (file == null);
		synchronized (SudokuGameViewController.class) {
			SudokuGameViewController.class.notifyAll();
		}
		this.mainView.showGame();
	}

	public void onMute(ActionEvent e) {
		if (this.player.isMute()) {
			this.muteButton.setText("Mute");
			this.player.setMute(false);
			this.player.play();
		} else {
			this.muteButton.setText("+ Mute");
			this.player.setMute(true);
			this.player.pause();
		}
	}

	public void onExit(MouseEvent e) {
		mainView.shutdown();
	}

	public void init(SudokuViewController sudokuViewController, SudokuController controller) {
		this.mainView = sudokuViewController;
		this.controller = controller;
		this.player = sudokuViewController.getPlayer();

		Image img = new Image("/edu/neumont/csc150/sudoku/view/sudokumainmenu/title.png");
		image.setImage(img);

		Runnable musicController = new Runnable() {
			@Override
			public void run() {
				while (true) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							if (player.isMute()) {
								muteButton.setText("+ Mute");
							} else {
								muteButton.setText("Mute");
							}
						}
					});
					synchronized (SudokuViewController.class) {
						try {
							SudokuViewController.class.wait();
						} catch (InterruptedException e) {
						}
					}
				}
			}
		};
		Thread musicThred = new Thread(musicController);
		musicThred.setDaemon(true);
		musicThred.start();
	}
}
