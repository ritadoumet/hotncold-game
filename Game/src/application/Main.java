package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {

	/*
	 * Main page: 
	 * 	- Tutorial button
	 * 	- High Score button
	 * 	- Settings button
	 * 	- Start button
	 * 	- Exit button
	 *  *************************************************************
	 *  
	 * Tutorial Page:
	 * 	- Tutorial text
	 * 	- Back to main page button (top left)
	 * 
	 * High Score Page:
	 * 	- Top 5 high scores for each category(loaded from file)
	 * 	-Back to main page button
	 * 
	 * Settings Page:
	 * 	- Change timer
	 * 	- Change theme
	 *	- Back to main page button (top left)
	 *
	 * Start Page:
	 * 	- Time left (top center)
	 * 	- Clicks left (top right)
	 * 	- Go back (top left)
	 * 	- Game pane (center)
	 * 	- Games won within the time allotted (bottom center)
	 * 
	 * 
	 */

	@Override
	public void start(Stage primaryStage) {
		try {
			
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root, Settings.getStageWidth(), Settings.getStageHeight());
			Settings.loadSettings();
			HighScore.loadScores();
			scene.getStylesheets().add(getClass().getResource(Settings.getThemeFileName()).toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setAlwaysOnTop(true);
	       primaryStage.getIcons().add(new Image("icon.png"));
			primaryStage.setTitle("HotN'Cold");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
