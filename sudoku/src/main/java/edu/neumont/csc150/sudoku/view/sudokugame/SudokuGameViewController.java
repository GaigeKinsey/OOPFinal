package edu.neumont.csc150.sudoku.view.sudokugame;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import edu.neumont.csc150.sudoku.controller.SudokuController;
import edu.neumont.csc150.sudoku.model.Square;
import edu.neumont.csc150.sudoku.view.SudokuViewController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.Duration;

public class SudokuGameViewController {

	private SudokuViewController mainView;
	private SudokuController controller;
	private MediaPlayer player;

	private Map<String, Node> cells = new HashMap<>();

	private Timeline timeCount;
	private int time = 0;

	@FXML
	private GridPane sudokuBoard;

	@FXML
	private Label timer;

	@FXML
	private CheckBox notesButton;

	@FXML
	private Button muteButton;
	
	public void onSave(ActionEvent e) {
		boolean saved = false;
		do {
			FileChooser chooser = new FileChooser();
			chooser.setTitle("Select save file");
			File file = chooser.showSaveDialog(this.mainView.getStage());

			if (file == null) {
				return; // because it is void - return just exits out the method and doesn't go on
			}
			try {
				this.controller.save(file);
				saved = true;
			} catch (IOException ex) {
				new Alert(AlertType.ERROR,
						"An error occurred trying to save the file. \nPlease select another location.", ButtonType.OK)
								.showAndWait();
			}
		} while (!saved);
	}

	public void onClear(ActionEvent e) {
		controller.clearBoard();
		controller.getBoard().checkForErrors();
		displayBoard();
	}

	public void onNewPuzzle(ActionEvent e) {
		time = 0;
		timeCount.pause();
		notesButton.setSelected(false);
		mainView.loadBoard(mainView.getDifficulty());
	}

	public void onMainMenu(ActionEvent e) {
		time = 0;
		timeCount.pause();
		notesButton.setSelected(false);
		mainView.showMainMenu();
		for (int col = 0; col < 9; col++) {
			for (int row = 0; row < 9; row++) {
				controller.getBoard().getSquares()[col][row].setValue(0);
			}
		}
	}

	public void onKeyPressed(KeyEvent e) {
		if (e.getCode().equals(KeyCode.M)) {
			if (this.player.isMute()) {
				muteButton.setText("Mute");
				this.player.setMute(false);
				this.player.play();
			} else {
				muteButton.setText("+ Mute");
				this.player.setMute(true);
				this.player.pause();
			}
		}
		if (e.getCode().equals(KeyCode.N)) {
			notesButton.setSelected(!notesButton.isSelected());
		}
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

	private EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent mouseEvent) {
			PseudoClass selected = PseudoClass.getPseudoClass("selected");
			Node cell = (Node) mouseEvent.getSource();
			cell.requestFocus();
			resetSelected();
			cell.pseudoClassStateChanged(selected, true);
			cell.setOnKeyPressed(keyHandler);
		}
	};

	private EventHandler<KeyEvent> keyHandler = new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent keyEvent) {
			Node cell = (Node) keyEvent.getSource();
			controller.getBoard().getSquare(Integer.parseInt(cell.getId().split("x")[0]),
					Integer.parseInt(cell.getId().split("x")[1])).setModified(true);
			if (notesButton.isSelected()) {
				if (keyEvent.getCode().isDigitKey()) {
					if (!keyEvent.getCode().getName().matches("0")) {
						int index = Integer.parseInt(keyEvent.getCode().getName()) - 1;
						Square currentSquare = controller.getBoard().getSquare(
								Integer.parseInt(cell.getId().split("x")[0]),
								Integer.parseInt(cell.getId().split("x")[1]));
						boolean[] notes = currentSquare.getNotes();
						notes[index] = !notes[index];
						currentSquare.setNotes(notes);
					}
				}
			} else {
				if (keyEvent.getCode().isDigitKey()) {
					Square currentSquare = controller.getBoard().getSquare(Integer.parseInt(cell.getId().split("x")[0]),
							Integer.parseInt(cell.getId().split("x")[1]));
					currentSquare.setValue(Integer.parseInt(keyEvent.getCode().getName()));
				}
				if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
					Square currentSquare = controller.getBoard().getSquare(Integer.parseInt(cell.getId().split("x")[0]),
							Integer.parseInt(cell.getId().split("x")[1]));
					currentSquare.setValue(0);
				}
				controller.getBoard().checkForErrors();
			}
			// Make new cell based on the values in the board
			displayBoard();
		}
	};

	public void formatCell(Node cell, int col, int row) {

		PseudoClass right = PseudoClass.getPseudoClass("right");
		PseudoClass bottom = PseudoClass.getPseudoClass("bottom");

		cell.pseudoClassStateChanged(right, col == 2 || col == 5);
		cell.pseudoClassStateChanged(bottom, row == 2 || row == 5);

		if (!controller.getBoard().getSquares()[col][row].isHint()) {
			cell.setOnMouseClicked(mouseHandler);
		}
		cell.setId("" + col + "x" + row);

		this.cells.put(cell.getId(), cell);

		if (controller.getBoard().checkForWin()) {
			win();
		}
	}

	private void resetSelected() {
		PseudoClass selected = PseudoClass.getPseudoClass("selected");

		for (int col = 0; col < 9; col++) {
			for (int row = 0; row < 9; row++) {
				Node cell = this.cells.get("" + col + "x" + row);
				cell.pseudoClassStateChanged(selected, false);
			}
		}
	}

	private void win() {
		ButtonType newBoard = new ButtonType("New Board");
		ButtonType mainMenu = new ButtonType("Main Menu");
		Optional<ButtonType> winAlert = new Alert(AlertType.INFORMATION,
				"You have won! Would you like to make another board of the same difficulty, or return to the main menu?",
				newBoard, mainMenu).showAndWait();
		if (winAlert.isPresent()) {
			if (winAlert.get().equals(newBoard)) {
				mainView.loadBoard(mainView.getDifficulty());
			} else if (winAlert.get().equals(mainMenu)) {
				mainView.showMainMenu();
			}
		}
	}

	public void displayBoard() {
		for (int col = 0; col < 9; col++) {
			for (int row = 0; row < 9; row++) {
				Square currentSquare = controller.getBoard().getSquare(col, row);
				if (currentSquare.isModified()) {
					int num = currentSquare.getValue();
					if (num == 0) {
						sudokuBoard.add(makeHintCell(currentSquare, col, row), col, row);
					} else {
						sudokuBoard.add(makeNormalCell(col, row, num, currentSquare), col, row);
					}
					currentSquare.setModified(false);
				}
			}
		}
	}

	private Label makeNormalCell(int c, int r, int num, Square currentSquare) {
		Label label = new Label();
		label.getStyleClass().add("cell");
		if (num != 0) {
			if (currentSquare.isHint()) {
				label.setTextFill(Color.BLACK);
			} else {
				label.setTextFill(Color.DEEPSKYBLUE);
			}
			if (currentSquare.isError()) {
				label.setTextFill(Color.RED);
			}
			label.setText("" + num);
		} else {
			label.setText("  ");
		}
		formatCell(label, c, r);

		return label;
	}

	private GridPane makeHintCell(Square currentSquare, int c, int r) {
		GridPane gridPane = new GridPane();
		gridPane.getStyleClass().add("notes");
		int index = 0;
		for (int col = 0; col < 3; col++) {
			for (int row = 0; row < 3; row++) {
				boolean note = currentSquare.getNotes()[index];
				index++;
				Label noteLabel = new Label();
				noteLabel.getStyleClass().add("note");
				if (note) {
					noteLabel.setText("" + index);
					gridPane.add(noteLabel, row, col);
				} else {
					noteLabel.setText("  ");
					gridPane.add(noteLabel, row, col);
				}
			}
		}
		formatCell(gridPane, c, r);

		return gridPane;
	}

	public void onGiveUp(ActionEvent e) {
		timeCount.stop();
		Optional<ButtonType> answer = new Alert(AlertType.WARNING,
				"Are you sure you would like to give up? This will prevent you from continuing the solve and show you the solution to the puzzle.",
				ButtonType.NO, ButtonType.YES).showAndWait();

		if (answer.isPresent()) {
			if (answer.get().equals(ButtonType.YES)) {
				int[][] squares = controller.getBoard().getSolvedBoard();
				PseudoClass right = PseudoClass.getPseudoClass("right");
				PseudoClass bottom = PseudoClass.getPseudoClass("bottom");
				for (int col = 0; col < 9; col++) {
					for (int row = 0; row < 9; row++) {
						Label label = new Label("" + squares[col][row]);
						label.getStyleClass().add("cell");
						label.pseudoClassStateChanged(right, col == 2 || col == 5);
						label.pseudoClassStateChanged(bottom, row == 2 || row == 5);
						sudokuBoard.add(label, col, row);
					}
				}
			} else {
				timeCount.play();
			}
		}
	}

	private void timer() {
		time++;
		int milis = time % 10;
		int seconds = (time / 10) % 60;
		int minutes = time / 10 / 60;

		String secondsString = seconds < 10 ? "0" + seconds : "" + seconds;

		timer.setText("Time: " + minutes + ":" + secondsString + "." + milis);
	}

	public void init(SudokuViewController sudokuViewController, SudokuController controller) {
		this.mainView = sudokuViewController;
		this.controller = controller;
		this.player = sudokuViewController.getPlayer();
		
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

		Runnable displayRefresh = new Runnable() {
			@Override
			public void run() {
				while (true) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							initHelper();
						}
					});
					try {
						synchronized (SudokuGameViewController.class) {
							SudokuGameViewController.class.wait();
						}
					} catch (InterruptedException e) {
					}
				}
			}
		};
		Thread refresh = new Thread(displayRefresh);
		refresh.setDaemon(true);
		refresh.start();
	}

	private void initHelper() {
		for (int col = 0; col < 9; col++) {
			for (int row = 0; row < 9; row++) {
				formatCell(new Label(), col, row);
				controller.getBoard().getSquare(col, row).setModified(true);
			}
		}

		time = 0;
		if (timeCount != null) {
			timeCount.stop();
		}
		timeCount = new Timeline(new KeyFrame(Duration.millis(100), e -> timer()));
		timeCount.setCycleCount(Timeline.INDEFINITE);
		timeCount.play();

		displayBoard();
	}
}
