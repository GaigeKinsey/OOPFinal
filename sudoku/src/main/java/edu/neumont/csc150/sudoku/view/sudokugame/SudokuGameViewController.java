package edu.neumont.csc150.sudoku.view.sudokugame;

import edu.neumont.csc150.sudoku.controller.SudokuController;
import edu.neumont.csc150.sudoku.view.SudokuViewController;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class SudokuGameViewController {
	
	SudokuViewController mainView;
	SudokuController controller;
	
	@FXML
	private GridPane sudokuBoard;
	
	public void click(ActionEvent e) {
		mainView.click(e);
	}
	
	public void onSave(ActionEvent e) {
		
	}
	
	public void onClear(ActionEvent e) {
		
	}
	
	public void onNewPuzzle(ActionEvent e) {
		
	}
	
	public void onExit(ActionEvent e) {
		
	}
	
	public void drawBoard() {
		
		PseudoClass right = PseudoClass.getPseudoClass("right");
		PseudoClass bottom = PseudoClass.getPseudoClass("bottom");
		
		for(int col=0; col<9; col++) {
			for(int row=0; row<9; row++) {
				Label cell = new Label();
				cell.getStyleClass().add("cell");
				cell.pseudoClassStateChanged(right, col == 2 || col == 5);
				cell.pseudoClassStateChanged(bottom, row == 2 || row == 5);
				
				this.sudokuBoard.add(cell, col, row);
			}
		}
	}
	
	public void displayBoard() {
		
	}

	public void init(SudokuViewController sudokuViewController, SudokuController controller) {
		mainView = sudokuViewController;
		this.controller = controller;
		drawBoard();
	}
}
