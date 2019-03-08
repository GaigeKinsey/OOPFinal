package edu.neumont.csc150.sudoku.view.sudokudifficulty;

import edu.neumont.csc150.sudoku.model.Difficulty;
import edu.neumont.csc150.sudoku.view.SudokuViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;

public class SudokuDifficultyViewController {

	private SudokuViewController mainView;
	private MediaPlayer player;
	
	@FXML
	private ImageView image;
	
	@FXML
	private Button muteButton;

	public void onEasy(MouseEvent e) {
		mainView.loadBoard(Difficulty.Easy);
	}

	public void onMedium(MouseEvent e) {
		mainView.loadBoard(Difficulty.Medium);
	}

	public void onHard(MouseEvent e) {
		mainView.loadBoard(Difficulty.Hard);
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

	public void init(SudokuViewController sudokuViewController) {
		this.mainView = sudokuViewController;
		this.player = sudokuViewController.getPlayer();
		
		Image img = new Image("/edu/neumont/csc150/sudoku/view/sudokudifficulty/DifficultyTitle.png");
		image.setImage(img);
		
		if(player.isMute()) {
			this.muteButton.setText("+ Mute");
		} else {
			this.muteButton.setText("Mute");
		}
	}

}
