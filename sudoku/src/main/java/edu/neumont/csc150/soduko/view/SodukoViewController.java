package edu.neumont.csc150.soduko.view;

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
