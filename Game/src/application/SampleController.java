package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
public class SampleController {
	
	 private Stage stage;
	 private Scene scene;
	 private Parent root;
	
	@FXML public void tutorialPage(ActionEvent event) throws IOException{
			root = new Tutorial();
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root, Settings.getStageWidth(), Settings.getStageHeight());
		scene.getStylesheets().add(getClass().getResource(Settings.getThemeFileName()).toExternalForm());
		  stage.setScene(scene);
		  stage.show();
	}
	
	@FXML public void highScorePage(ActionEvent event) throws IOException{
		root = new HighScore();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root, Settings.getStageWidth(), Settings.getStageHeight());
		scene.getStylesheets().add(getClass().getResource(Settings.getThemeFileName()).toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML public void changeSettingsPage(ActionEvent event) throws IOException{
		root = new ChangeSettings();
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root, Settings.getStageWidth(), Settings.getStageHeight());
			scene.getStylesheets().add(getClass().getResource(Settings.getThemeFileName()).toExternalForm());
		  stage.setScene(scene);
		  stage.show();
	}
	
	@FXML public void startGame(ActionEvent event) throws IOException{
		root = new Game();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root, Settings.getStageWidth(), Settings.getStageHeight());
		scene.getStylesheets().add(getClass().getResource(Settings.getThemeFileName()).toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	
}
