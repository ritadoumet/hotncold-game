package application;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ColorDemo extends HBox{
	private int nbOfCircles = 5;
	private int circleRadius = 30;
	
	public ColorDemo(int nbOfCircles, int circleRadius, int spacing) {
		this.setNbOfCircles(nbOfCircles);
		this.setCircleRadius(circleRadius);
		
		Color red = (Settings.getTheme().equals(Settings.Theme.LIGHT))? Settings.getLightthemered():Settings.getDarkthemered();
		Color blue = (Settings.getTheme().equals(Settings.Theme.LIGHT))? Settings.getLightthemeblue():Settings.getDarkthemeblue();
		
		for (double i = 0; i<nbOfCircles; i++) {
			Circle circle = new Circle();
			circle.setFill(Game.interpolateColors(red, blue, (double)(i/nbOfCircles)));
			circle.setRadius(circleRadius);
			this.getChildren().add(circle);
		}
		
		this.setAlignment(Pos.BASELINE_CENTER);
		
		this.setSpacing(spacing);
	}

	public int getNbOfCircles() {
		return nbOfCircles;
	}

	public void setNbOfCircles(int nbOfCircles) {
		this.nbOfCircles = nbOfCircles;
	}

	public int getCircleRadius() {
		return circleRadius;
	}

	public void setCircleRadius(int circleRadius) {
		this.circleRadius = circleRadius;
	}
	
}
