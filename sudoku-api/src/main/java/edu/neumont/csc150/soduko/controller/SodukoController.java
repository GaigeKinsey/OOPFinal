package edu.neumont.csc150.soduko.controller;

import edu.neumont.csc150.soduko.view.SodukoView;

public class SodukoController {
	
	private SodukoView view;

	public SodukoController(SodukoView viewController) {
		this.view = viewController;
	}

	public void run() {
		view.init();
	}

}
