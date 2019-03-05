package edu.neumont.csc150.sudoku.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

import edu.neumont.csc150.sudoku.model.Board;
import edu.neumont.csc150.sudoku.model.Difficulty;
import edu.neumont.csc150.sudoku.model.Square;
import edu.neumont.csc150.sudoku.view.SudokuView;

public class SudokuController {

	private SudokuView view;

	private Board board;

	private int solutions = 0;

	public SudokuController(SudokuView viewController) {
		this.view = viewController;
		this.view.registerController(this);
	}

	public void run() {
		view.init();
	}

	public void clearBoard() {
		for (Square[] squares : board.getSquares()) {
			for (Square square : squares) {
				if (!square.isHint()) {
					square.setValue(0);
				}
			}
		}
	}

	public void makeBoard(Difficulty difficulty) {
		int[][] intVals;
		int boardCount = 0;
		do {
			solutions = 0;
			generateBoard(difficulty);
			// Get all the ints from the board to check
			intVals = new int[board.getSquares().length][board.getSquares().length];
			Square[][] squares = board.getSquares();
			for (int x = 0; x < squares.length; x++) {
				for (int y = 0; y < squares.length; y++) {
					intVals[x][y] = squares[x][y].getValue();
				}
			}
			checkBoard(intVals, board.getSquares().length);
			boardCount++;
		} while (solutions != 1);
		System.out.println("Boards made: " + boardCount);
	}

	private void generateBoard(Difficulty difficulty) {
		int hints = 0;
		if (difficulty == null) {
			hints = 30;
		} else {
			switch (difficulty) {
			case Easy:
				hints = 34;
				break;
			case Medium:
				hints = 30;
				break;
			case Hard:
				hints = 26;
				break;
			}
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
			if (board.getSquare(col, row).getValue() == 0) {
				value = rng.nextInt(9) + 1;
				board.setSquare(col, row, value);
				if (board.checkSquare(col, row, value)) {
					board.getSquares()[col][row].setHint(true);
					x++;
				} else {
					board.setSquare(col, row, 0);
				}
			}
		} while (x < hints);
	}

	private boolean checkBoard(int[][] board, int boardSize) {
		int col = -1;
		int row = -1;
		boolean empty = false;
		// Check to see if there is still an empty spot on the board
		for (int x = 0; x < boardSize; x++) {
			for (int y = 0; y < boardSize; y++) {
				// If the value at this index is 0
				if (board[x][y] == 0) {
					// Then this space is empty, start here
					col = x;
					row = y;
					empty = true;
					break;
				}
			}
			// If we found an empty square, no sense in looping here
			if (empty) {
				break;
			}
		}

		// space, if more than one solution then board is not unique
		if (solutions > 1) {
			return true;
		}

		// If no empty space found, board is filled, count up one solution, and clear
		if (!empty) {
			solutions++;
			return false;
		}

		// If there was an empty space, fill it
		for (int num = 1; num <= boardSize; num++) {
			// If it can be placed here, do it
			if (spotValid(board, col, row, num)) {
				board[col][row] = num;
				// Loop again for the next empty space if there isn't more than 1 solution found
				if (checkBoard(board, boardSize)) {
					return true;
				} else {
					// replace this spot to backtrack
					board[col][row] = 0;
				}
			}
		}
		return false;
	}

	private boolean spotValid(int[][] board, int col, int row, int num) {
		if (checkCol(board, col, num) || checkRow(board, row, num) || checkRegion(board, col, row, num)) {
			return false;
		}
		return true;
	}

	private boolean checkCol(int[][] intBoard, int col, int num) {
		for (int row = 0; row < intBoard.length; row++) {
			if (num == intBoard[col][row]) {
				return true;
			}
		}
		return false;
	}

	private boolean checkRow(int[][] intBoard, int row, int num) {
		for (int col = 0; col < intBoard.length; col++) {
			if (num == intBoard[col][row]) {
				return true;
			}
		}
		return false;
	}

	private boolean checkRegion(int[][] intBoard, int col, int row, int num) {
		int colStart = col - col % 3;
		int rowStart = row - row % 3;

		int currentRow;

		for (int colLoops = 0; colLoops < 3; colLoops++) {
			currentRow = rowStart;
			for (int rowLoops = 0; rowLoops < 3; rowLoops++) {
				if (num == intBoard[colStart][currentRow]) {
					return true;
				}
				currentRow++;
			}
			colStart++;
		}
		return false;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void save(File file) throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
			out.writeObject(this.board);
		}
	}

	public void load(File file) throws ClassNotFoundException, IOException {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
			this.board = (Board) in.readObject();
		}
	}
}
