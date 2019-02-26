package edu.neumont.csc150.sudoku.view;

import edu.neumont.csc150.sudoku.view.SodukoView;
import javafx.stage.Stage;

public class SodukoViewController implements SodukoView{
	
	private Stage stage;

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void init() {
		stage.setTitle("Soduko");
		stage.setMaximized(true);
		stage.show();
	}
}
