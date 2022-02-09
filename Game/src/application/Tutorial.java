package application;


import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;

public class Tutorial extends BorderPane{

	public Tutorial() {
	
		TopNav topNav = new TopNav("Tutorial");
		Label tutorialText = new Label("The goal of this game is to find the location of a specified box.\nAs you click on boxes, the colors will guide you to your goal:\n The redder it is, the closer you are.\n The bluer it is, the farther away from your goal.\n\n The ultimate goal of the game is to find\n as many boxes as possible,\n in the alloted time");
		tutorialText.setTextAlignment(TextAlignment.CENTER);
		ColorDemo colorDemo = new ColorDemo(5,30, -15);
		this.setCenter(tutorialText);
		this.setTop(topNav);
		this.setBottom(colorDemo);
		
	}
}
