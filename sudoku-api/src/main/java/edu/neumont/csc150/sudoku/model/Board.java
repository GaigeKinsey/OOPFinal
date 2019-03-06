package edu.neumont.csc150.sudoku.model;

import java.io.Serializable;

public class Board implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final int BOARDWIDTH = 9, BOARDHEIGHT = 9;

	private Square[][] squares = new Square[BOARDWIDTH][BOARDHEIGHT];
	private int[][] solvedBoard;
	private boolean checkError;

	public Board() {
		initBoard();
	}

	public void initBoard() {
		for (int col = 0; col < BOARDWIDTH; col++) {
			for (int row = 0; row < BOARDHEIGHT; row++) {
				squares[col][row] = new Square();
			}
		}
	}

	public void checkForErrors() {
		boolean hasError = false;
		for (int col = 0; col < BOARDWIDTH; col++) {
			for (int row = 0; row < BOARDHEIGHT; row++) {
				if (!checkSquare(col, row, getSquare(col, row).getValue())) {
					getSquare(col, row).setError(true);
					hasError = true;
				} else {
					getSquare(col, row).setError(false);
				}
			}
		}
		setCheckError(hasError);
	}

	public boolean checkForWin() {
		boolean won = true;
		for (Square[] squares : this.squares) {
			for (Square square : squares) {
				if (square.getValue() == 0) {
					won = false;
					break;
				}
			}
			if (!won) {
				break;
			}
		}
		return won && !checkError;
	}
	
	public void setSquare(int col, int row, int value) {
		squares[col][row].setValue(value);
	}

	public Square getSquare(int col, int row) {
		return squares[col][row];
	}

	public boolean checkSquare(int col, int row, int value) {
		if (value == 0) {
			return true;
		}
		if (checkCol(col, value) || checkRow(row, value) || checkRegion(col, row, value)) {
			return false;
		}
		return true;
	}

	private boolean checkCol(int col, int value) {
		int instances = 0;
		for (int row = 0; row < BOARDHEIGHT; row++) {
			if (value == squares[col][row].getValue()) {
				instances++;
				if (instances >= 2) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkRow(int row, int value) {
		int instances = 0;
		for (int col = 0; col < BOARDHEIGHT; col++) {
			if (value == squares[col][row].getValue()) {
				instances++;
				if (instances >= 2) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkRegion(int col, int row, int value) {
		int colStart = col - col % 3;
		int rowStart = row - row % 3;

		int currentRow;

		int instances = 0;
		for (int colLoops = 0; colLoops < 3; colLoops++) {
			currentRow = rowStart;
			for (int rowLoops = 0; rowLoops < 3; rowLoops++) {
				if (value == squares[colStart][currentRow].getValue()) {
					instances++;
					if (instances >= 2) {
						return true;
					}
				}
				currentRow++;
			}
			colStart++;
		}
		return false;
	}

	public Square[][] getSquares() {
		return squares;
	}

	public void setSquares(Square[][] squares) {
		this.squares = squares;
	}

	public int[][] getSolvedBoard() {
		return solvedBoard;
	}

	public void setSolvedBoard(int[][] solvedBoard) {
		this.solvedBoard = solvedBoard;
	}

	public boolean isCheckError() {
		return checkError;
	}

	public void setCheckError(boolean checkError) {
		this.checkError = checkError;
	}
}
