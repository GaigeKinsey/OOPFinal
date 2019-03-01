package edu.neumont.csc150.sudoku.controller;

import java.io.IOException;
import java.util.Random;

import edu.neumont.csc150.sudoku.model.Board;
import edu.neumont.csc150.sudoku.model.Square;
import edu.neumont.csc150.sudoku.view.SudokuView;

public class SudokuController {

	private SudokuView view;

	private Board board;

	private int solutions;

	public SudokuController(SudokuView viewController) {
		this.view = viewController;
		this.view.registerController(this);
	}

	public void run() throws IOException {
		view.init();
	}

	public void makeBoard(String difficulty) {
		int[][] intVals;
		do {
			solutions = 0;
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
			// Get all the ints from the board to check
			intVals = new int[board.getSquares().length][board.getSquares().length];
			Square[][] squares = board.getSquares();
			for (int x = 0; x < squares.length; x++) {
				for (int y = 0; y < squares.length; y++) {
					intVals[x][y] = squares[x][y].getValue();
				}
			}
			System.out.println("new board");
			checkBoard(intVals, board.getSquares().length);
		} while (solutions != 1);
		System.out.println("Done");
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

		// If no empty space found, board is filled, count up one solution, and clear
		// space, if more than one solution then board is not unique
		if (!empty) {
			solutions++;
			System.out.println(solutions);
			return true;
		}

		// If there was an empty space, fill it
		for (int num = 1; num <= boardSize; num++) {
			// If it can be placed here, do it
			if (spotValid(board, col, row, num)) {
				board[col][row] = num;
				// Loop again for the next empty space if there isn't more than 1 solution found
				if (solutions <= 1 && checkBoard(board, boardSize)) {
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
		boolean matching = false;
		for (int row = 0; row < intBoard.length; row++) {
			if (num == intBoard[col][row]) {
				matching = true;
			}
		}
		return matching;
	}
	
	private boolean checkRow(int[][] intBoard, int row, int num) {
		boolean matching = false;
		for (int col = 0; col < intBoard.length; col++) {
			if (num == intBoard[col][row]) {
				matching = true;
			}
		}
		return matching;
	}
	
	private boolean checkRegion(int[][] intBoard, int col, int row, int num) {
		int colStart = col - col % 3;
		int rowStart = row - row % 3;
		
		int currentRow;
		
		boolean matching = false;
		
		for (int colLoops = 0; colLoops < 3; colLoops++) {
			currentRow = rowStart;
			for (int rowLoops = 0; rowLoops < 3; rowLoops++) {
				if (num == intBoard[colStart][currentRow]) {
					matching = true;
				}
				currentRow++;
			}
			colStart++;
		}
		return matching;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
}
