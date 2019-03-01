package edu.neumont.csc150.sudoku.controller;

import java.io.IOException;
import java.util.Random;

import edu.neumont.csc150.sudoku.model.Board;
import edu.neumont.csc150.sudoku.view.SudokuView;

public class SudokuController {

	private SudokuView view;

	private Board board;

	public SudokuController(SudokuView viewController) {
		this.view = viewController;
		this.view.registerController(this);
	}

	public void run() throws IOException {
		view.init();
	}

	public void makeBoard(String difficulty) {
		do {
			switch (difficulty) {
			case "Easy":
				generateBoard(difficulty);
				break;
			case "Medium":
				generateBoard(difficulty);
				break;
			case "Hard":
				generateBoard(difficulty);
				break;
			default:
				throw new IllegalArgumentException("difficulty does not exist. " + difficulty);
			}
		} while (!checkBoard());
	}

	private void generateBoard(String difficulty) {
		int hints = 0;
		switch (difficulty) {
		case "Easy":
			hints = 34;
			break;
		case "Medium":
			hints = 30;
			break;
		case "Hard":
			hints = 26;
			break;
		}

		board = new Board();

		int col;
		int row;
		int value;
		Random rng = new Random();
		
		int x = 0;
		do {
			col = rng.nextInt(9);
			row = rng.nextInt(9);
			value = rng.nextInt(9) + 1;
			if (board.checkSquare(col, row, value)) {
				board.setSquare(col, row, value);
				x++;
			}
		} while (x < hints);
	}

	private boolean checkBoard() {
		return true;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
}
