package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ReturnButton extends Button{

	public ReturnButton(String str) {
		super(str);
		this.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
			 Stage stage;
			 Scene scene;
			 Parent root;
			 
			try {
				root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				  scene = new Scene(root, Settings.getStageWidth(), Settings.getStageHeight());
				  System.out.println(Settings.getThemeFileName());
				  scene.getStylesheets().add(getClass().getResource(Settings.getThemeFileName()).toExternalForm());
				  stage.setScene(scene);
				  stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
			  }
		});
	}
}
