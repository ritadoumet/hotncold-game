package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EndOfGameController {

	 private Stage stage;
	 private Scene scene;
	 private Parent root;
	 @FXML private Label score;
	 @FXML private Label nbOfRounds;
	 @FXML private Label reason;
	 @FXML private Label newHighScore;
	 @FXML private HBox bottomHbox;
	 @FXML private VBox vbox;
	 void initialize() {}
	
	void initData(int score, int nbOfRounds, Boolean timeOver, Boolean newHighScore) {
		this.vbox.getChildren().add(new ColorDemo(15,10,5));
		this.vbox.setSpacing(10);
		this.score.setText(score+"");
		System.out.println(score);
		this.nbOfRounds.setText(nbOfRounds+"");
		if (timeOver) {
			this.reason.setText("Time Over");
		}
		else {
			this.reason.setText("No more clicks left!");
		}
		if (newHighScore) {
			this.newHighScore.setText("New High Score!");
			this.newHighScore.getStyleClass().add("new-high-score-label");
		}
		
		this.bottomHbox.setAlignment(Pos.CENTER);
		this.bottomHbox.setSpacing(Settings.getGamePaneWidth()/3);
		
		
				
	}
	
	@FXML public void highScorePage(ActionEvent event) throws IOException{
		root = new HighScore();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root, Settings.getStageWidth(), Settings.getStageHeight());
		scene.getStylesheets().add(getClass().getResource(Settings.getThemeFileName()).toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

	@FXML public void newGame(ActionEvent event) throws IOException{
		root = new Game();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root, Settings.getStageWidth(), Settings.getStageHeight());
		scene.getStylesheets().add(getClass().getResource(Settings.getThemeFileName()).toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
}
