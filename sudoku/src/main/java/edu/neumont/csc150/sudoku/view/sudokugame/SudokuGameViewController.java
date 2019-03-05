package edu.neumont.csc150.sudoku.view.sudokugame;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import edu.neumont.csc150.sudoku.controller.SudokuController;
import edu.neumont.csc150.sudoku.model.Square;
import edu.neumont.csc150.sudoku.view.SudokuViewController;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class SudokuGameViewController {

	private SudokuViewController mainView;
	private SudokuController controller;

	private Map<String, Label> cells = new HashMap<>();

	@FXML
	private GridPane sudokuBoard;

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
								.show();
			}
		} while (!saved);
	}

	public void onClear(ActionEvent e) {
		controller.clearBoard();
		displayBoard();
	}

	public void onNewPuzzle(ActionEvent e) {
		mainView.loadBoard(mainView.getDifficulty());
	}

	public void onMainMenu(ActionEvent e) {
		mainView.showMainMenu();
	}

	public void onToggled(ActionEvent e) {
		CheckMenuItem notesBox = (CheckMenuItem) e.getSource();
		if (notesBox.isSelected()) {
			// Method call to display the notes in empty boxes TODO

		} else {
			// Method call to display normal board
			displayBoard();
		}
	}

	public void drawBoard() {

		PseudoClass right = PseudoClass.getPseudoClass("right");
		PseudoClass bottom = PseudoClass.getPseudoClass("bottom");
		PseudoClass selected = PseudoClass.getPseudoClass("selected");

		for (int col = 0; col < 9; col++) {
			for (int row = 0; row < 9; row++) {
				Label cell = new Label();
				cell.getStyleClass().add("cell");
				cell.pseudoClassStateChanged(right, col == 2 || col == 5);
				cell.pseudoClassStateChanged(bottom, row == 2 || row == 5);
				if (!controller.getBoard().getSquares()[col][row].isHint()) {
					cell.setOnMouseClicked(new EventHandler<MouseEvent>() {

						@Override
						public void handle(MouseEvent mouseEvent) {
							cell.requestFocus();
							resetSelected();
							cell.pseudoClassStateChanged(selected, true);
							cell.setOnKeyPressed(new EventHandler<KeyEvent>() {

								@Override
								public void handle(KeyEvent keyEvent) {
									if (keyEvent.getCode().isDigitKey()) {
										controller.getBoard().setSquare(Integer.parseInt(cell.getId().split("x")[0]),
												Integer.parseInt(cell.getId().split("x")[1]),
												Integer.parseInt(keyEvent.getCode().getName()));
									}
									if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
										controller.getBoard().setSquare(Integer.parseInt(cell.getId().split("x")[0]),
												Integer.parseInt(cell.getId().split("x")[1]), 0);
									}
									controller.getBoard().checkForErrors();
									displayBoard();
								}
							});
						}
					});
				}

				cell.setId("" + col + "x" + row);
				this.cells.put(cell.getId(), cell);
				this.sudokuBoard.add(cell, col, row);
			}
		}
		displayBoard();
	}

	private void resetSelected() {
		PseudoClass selected = PseudoClass.getPseudoClass("selected");

		for (int col = 0; col < 9; col++) {
			for (int row = 0; row < 9; row++) {
				Label cell = this.cells.get("" + col + "x" + row);
				cell.pseudoClassStateChanged(selected, false);
			}
		}
	}

	public void displayBoard() {
		for (int col = 0; col < 9; col++) {
			for (int row = 0; row < 9; row++) {
				Label cell = cells.get("" + col + "x" + row);
				Square currentSquare = controller.getBoard().getSquares()[col][row];
				int num = currentSquare.getValue();
				if (num != 0) {
					if (currentSquare.isHint()) {
						cell.setTextFill(Color.BLACK);
					}
					if (!currentSquare.isHint()) {
						cell.setTextFill(Color.DARKCYAN);
					}
					if (currentSquare.isError()) {
						cell.setTextFill(Color.DEEPPINK);
					}
					cell.setText("" + num);
				} else {
					cell.setText("  ");
				}
			}
		}
	}

	public void init(SudokuViewController sudokuViewController, SudokuController controller) {
		mainView = sudokuViewController;
		this.controller = controller;
		drawBoard();
	}
}
