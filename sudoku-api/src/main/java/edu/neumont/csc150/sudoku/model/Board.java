package edu.neumont.csc150.sudoku.model;

import java.sql.RowIdLifetime;

public class Board {

	private final int BOARDWIDTH = 9, BOARDHEIGHT = 9;

	private Square[][] squares = new Square[BOARDWIDTH][BOARDHEIGHT];
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
	
	public void setSquare(int col, int row, int value) {
		squares[col][row].setValue(value);
	}

	public boolean checkSquare(int col, int row, int value) {
		if (checkCol(col, value) || checkRow(row, value) || checkRegion(col, row, value)) {
			return false;
		}
		return true;
	}

	private boolean checkCol(int col, int value) {
		boolean matching = false;
		for (int row = 0; row < BOARDHEIGHT; row++) {
			if (value == squares[col][row].getValue()) {
				matching = true;
			}
		}
		return matching;
	}

	private boolean checkRow(int row, int value) {
		boolean matching = false;
		for (int col = 0; col < BOARDHEIGHT; col++) {
			if (value == squares[col][row].getValue()) {
				matching = true;
			}
		}
		return matching;
	}

	private boolean checkRegion(int col, int row, int value) {
		int colStart = col - col % 3;
		int rowStart = row - row % 3;
		
		int currentRow;
		
		boolean matching = false;
		
		for (int colLoops = 0; colLoops < 3; colLoops++) {
			currentRow = rowStart;
			for (int rowLoops = 0; rowLoops < 3; rowLoops++) {
				if (value == squares[colStart][currentRow].getValue()) {
					matching = true;
				}
				currentRow++;
			}
			colStart++;
		}
		return matching;
	}

	public Square[][] getSquares() {
		return squares;
	}

	public void setSquares(Square[][] squares) {
		this.squares = squares;
	}

	public boolean isCheckError() {
		return checkError;
	}

	public void setCheckError(boolean checkError) {
		this.checkError = checkError;
	}

}
