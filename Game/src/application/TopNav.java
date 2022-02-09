package application;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class TopNav extends StackPane{

	public TopNav(String str) {
		Label title = new Label(str);
		title.getStyleClass().add("title");
		ReturnButton returnButton = new ReturnButton("Return");
		StackPane.setAlignment(returnButton, Pos.BASELINE_LEFT);
		StackPane.setAlignment(title, Pos.BASELINE_CENTER);
		this.getChildren().addAll(returnButton, title);
	}
}
