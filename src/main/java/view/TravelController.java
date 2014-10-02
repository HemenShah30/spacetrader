package view;

import controller.GameEngine;
import model.Planet;
import model.Universe;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class TravelController {

	@FXML
	private Canvas miniMap;

	@FXML
	private Button backBtn;

	@FXML
	private Canvas localMap;

	@FXML
	private Button goBtn;

	@FXML
	private Button nextBtn;
	
	private GraphicsContext localGC;

	@FXML
	void showNextPlanet(Event e) {

	}

	@FXML
	void selectRegionOfSpace(Event e) {

	}

	@FXML
	void selectPlanet(Event e) {

	}

	@FXML
	void backToPlanetScreen(Event e) {

	}

	@FXML
	void goToSelectedPlanet(Event e) {

	}
	
	@FXML
	void loadUniverse(Event e) {
		localGC = localMap.getGraphicsContext2D();
		Universe u = GameEngine.getGameEngine().getUniverse();
		int x, y;
		int rad = 3;
		localGC.setFill(Color.DARKTURQUOISE);
		for (Planet p: u.getPlanets()) {
			x = p.getLocation().getX();
			y = p.getLocation().getY();
			localGC.fillOval(x, y, rad, rad);
		}
	}

}

