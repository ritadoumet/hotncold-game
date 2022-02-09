package application;

import application.Settings.Theme;
import application.Settings.Timer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class ChangeSettings extends BorderPane{
	public ChangeSettings() {
		TopNav topNav = new TopNav("Change Settings");
		ColorDemo colorDemo = new ColorDemo(5, 30, -15);
		
		GridPane grid = new GridPane();
		
		grid.setHgap(40);
		grid.setVgap(15);
		
		BorderPane.setAlignment(grid, Pos.CENTER);
		BorderPane.setMargin(grid, new Insets(40,20,40,20));
		Settings.Timer timer = Settings.getTime();
		Settings.Theme theme = Settings.getTheme();
		
		Label themeText = new Label("Theme: ");
		Label timerText = new Label("Time (in seconds): ");
		
		RadioButton light = new RadioButton("Light");
		RadioButton dark = new RadioButton("Dark");
		
		Label changesSavedLabel = new Label("Changes Saved!");
		changesSavedLabel.setVisible(false);
		
		ToggleGroup themeToggleGroup = new ToggleGroup();
		
		switch(theme) {
		case DARK:dark.setSelected(true);
			break;
		case LIGHT:light.setSelected(true);
			break;
		default:
			break;
		
		}
		
		light.setToggleGroup(themeToggleGroup);
		dark.setToggleGroup(themeToggleGroup);
		EventHandler<ActionEvent> setInvisible = e->{
			changesSavedLabel.setVisible(false);
		};
		Timeline t = new Timeline(new KeyFrame(Duration.millis(1500), setInvisible));
		t.setCycleCount(1);
		t.play();
				// listen to changes in selected toggle
				themeToggleGroup.selectedToggleProperty().addListener((observable, oldVal, newVal) -> {
					if (newVal.equals(light)) {
						Settings.updateSettings(Theme.LIGHT, timer);
					}
					else Settings.updateSettings(Theme.DARK, timer);
					changesSavedLabel.setVisible(true);
					t.play();
					
				});
				
		RadioButton fortyFive = new RadioButton("45 ");
		RadioButton sixty = new RadioButton("60 ");
		RadioButton hundredTwenty = new RadioButton("120 ");
		
		ToggleGroup timerToggleGroup = new ToggleGroup();
		
		switch(timer) {
		case FOURTY_FIVE_SECS: fortyFive.setSelected(true);
			break;
		case HUNDRED_TWENTY_SECS:hundredTwenty.setSelected(true);
			break;
		case SIXTY_SECS:sixty.setSelected(true);
			break;
		default: sixty.setSelected(true);
			break;
		
		}
		fortyFive.setToggleGroup(timerToggleGroup);
		sixty.setToggleGroup(timerToggleGroup);
		hundredTwenty.setToggleGroup(timerToggleGroup);
		
		timerToggleGroup.selectedToggleProperty().addListener((observable, oldVal, newVal) ->{
			if (newVal.equals(fortyFive)) {
				Settings.updateSettings(theme, Timer.FOURTY_FIVE_SECS);
			}
			else if(newVal.equals(sixty)) {
				Settings.updateSettings(theme, Timer.SIXTY_SECS);
			}
			else Settings.updateSettings(theme, Timer.HUNDRED_TWENTY_SECS);
			changesSavedLabel.setVisible(true);
			t.play();
		});
				
		GridPane.setColumnIndex(themeText, 0);
		GridPane.setColumnIndex(timerText, 0);
		GridPane.setRowIndex(themeText, 0);
		GridPane.setRowIndex(timerText, 1);
		
		GridPane.setColumnIndex(light, 1);
		GridPane.setColumnIndex(dark, 2);
		GridPane.setRowIndex(light, 0);
		GridPane.setRowIndex(dark, 0);
		
		GridPane.setColumnIndex(fortyFive, 1);
		GridPane.setColumnIndex(sixty, 2);
		GridPane.setColumnIndex(hundredTwenty, 3);
		GridPane.setRowIndex(hundredTwenty, 1);
		GridPane.setRowIndex(sixty, 1);
		GridPane.setRowIndex(fortyFive, 1);
		
		GridPane.setColumnIndex(changesSavedLabel, 0);
		GridPane.setColumnSpan(changesSavedLabel, 4);
		GridPane.setHalignment(changesSavedLabel, HPos.CENTER);
		GridPane.setMargin(changesSavedLabel, new Insets(40, 20, 40, 20));
		GridPane.setRowIndex(changesSavedLabel, 2);
		
		grid.getChildren().addAll(themeText, timerText, light, dark, fortyFive, sixty, hundredTwenty, changesSavedLabel);
		this.setTop(topNav);
		this.setCenter(grid);
		this.setBottom(colorDemo);
	}

}
