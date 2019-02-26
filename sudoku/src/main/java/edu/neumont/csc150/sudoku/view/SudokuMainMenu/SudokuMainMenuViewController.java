package edu.neumont.csc150.sudoku.view.SudokuMainMenu;

import edu.neumont.csc150.sudoku.view.SudokuView;
import javafx.stage.Stage;

public class SudokuMainMenuViewController implements SudokuView{
	
	private Stage stage;

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void init() {
		stage.setTitle("Sudoku");
		stage.setMaximized(true);
		stage.show();
	}
}
