package application;

import java.io.IOException;

import application.Settings.Timer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Game extends BorderPane{

	//CREATE TIMELINE ANIMATION IN THIS CLASS: UPDATE TIME LEFT EVERY SEC.
	//IF TIMELEFT<=0, END OF GAME. UPDATE HIGH SCORE
	
	//create methods: game over (when you click more than the max =>display score page)
	// 				  end of game (when timer is over =>display score page)
	//				  win round: new round (this.setCenter(new Round(this, maxClicks)) + update game score + update max clicks 
	
	//
	//
	private Integer nbOfRounds = 0;
	private int score = 0;
	private Color red = (Settings.getTheme().equals(Settings.Theme.LIGHT))? Settings.getLightthemered():Settings.getDarkthemered();
	private Color blue = (Settings.getTheme().equals(Settings.Theme.LIGHT))? Settings.getLightthemeblue():Settings.getDarkthemeblue();
	private int initialDimensionX = Settings.getInitialdimensionx();
	private int initialDimensionY = Settings.getInitialdimensiony();
	private int maxClicks = Settings.getMaxClicks();
	Timer timer = Settings.getTime();
	Timeline timerTimeline;
	private int timeLeft;
	Label nbOfRounds_Label;
	Label nbOfClicksLeft_Label;
	Round round;
	HBox bottomHbox;
	public Game() {
		
		switch(timer) {
		case FOURTY_FIVE_SECS: setTimeLeft(45);
			break;
		case HUNDRED_TWENTY_SECS: setTimeLeft(120);
			break;
		case SIXTY_SECS:setTimeLeft(60);
			break;
		default:
			break;
		}
		
		TopNav topNav = new TopNav("HotN'Cold");
		Clock clock = new Clock(timeLeft);
		topNav.getChildren().add(clock);
		StackPane.setAlignment(clock, Pos.BASELINE_RIGHT);
		this.setTop(topNav);
		
		EventHandler<ActionEvent> eventHandler = e->{
			clock.updateClock();
		};
		
		timerTimeline= new Timeline(new KeyFrame(Duration.millis(1000), eventHandler));
		timerTimeline.setCycleCount(Timeline.INDEFINITE);
		timerTimeline.play();
		
		
		round = new Round();
		nbOfRounds_Label = new Label("Rounds: "+nbOfRounds.toString());
		nbOfClicksLeft_Label = new Label("Clicks left: "+round.maxNbClicksInRound);
		bottomHbox = new HBox();
		bottomHbox.getChildren().add(nbOfClicksLeft_Label);
		bottomHbox.getChildren().add(nbOfRounds_Label);
		bottomHbox.setAlignment(Pos.CENTER);
		bottomHbox.setSpacing(Settings.getGamePaneWidth()/3);
		
		this.setBottom(bottomHbox);
		this.setCenter(round);
	}


	
	private class Clock extends Label{
		private int sec;
		private int min;
		public Clock(int timeLeft) {
			min = timeLeft/60;
			sec = timeLeft - min*60;
			
			this.setText(min+":"+sec);
		}
		public void updateClock() {
			if (this.sec>0) {
				this.sec--;
				this.setText(min+":"+sec);
				
			}
			else {
				if (this.min>0) {
					this.min--;
					this.sec=59;
					this.setText(min+":"+sec);
				}
				else {this.setText(min+":"+sec);endOfGame(true, nbOfRounds, score);}
			}
			
		}
	}

	private class Round extends GridPane {
		
		int nbOfClicksInRound;
		int maxNbClicksInRound;
		int roundDimensionX, roundDimensionY;
		int randLocationX;
		int randLocationY;

		boolean clicked[][];
		
		public Round() {
			
			nbOfRounds++;
			updateNbOfRoundsLabel(nbOfRounds);
			
			nbOfClicksInRound = 0;
			
			this.setPrefWidth(Settings.getGamePaneWidth());
			this.setPrefHeight(Settings.getGamePaneHeight());
			
			this.roundDimensionX = initialDimensionX + nbOfRounds - 1;
			this.roundDimensionY = initialDimensionY + nbOfRounds - 1;
			
			clicked = new boolean[roundDimensionX][roundDimensionY];
			
			maxNbClicksInRound = (int) Math.floor(maxClicks + 0.8*nbOfRounds);
			
			randLocationX = (int)(Math.random() * this.roundDimensionX);
			randLocationY = (int)(Math.random() * this.roundDimensionY);
			
			updateNbOfClicksLeft(maxNbClicksInRound);
			
			int i, j;
			
			for (i = 0; i< roundDimensionX; i++) {
				for (j = 0; j< roundDimensionY; j++) {
					
					Rectangle rect = new Rectangle();
					rect.setWidth(Settings.getGamePaneWidth()/roundDimensionX);
					rect.getStyleClass().add("rect");
					rect.setHeight(Settings.getGamePaneHeight()/roundDimensionY);
					
					rect.setArcWidth(15);
					rect.setArcHeight(15);
					rect.setCursor(Cursor.HAND);
					Round.setColumnIndex(rect, i);
					Round.setRowIndex(rect, j);
					
					clicked[i][j]=false;
					
					double factor =1.0 - Math.sqrt(Math.pow(i - randLocationX, 2) + Math.pow(j - randLocationY, 2))/Math.sqrt(Math.pow(roundDimensionX , 2)+Math.pow(roundDimensionY, 2));
					int ii = i, jj=j;
					rect.setOnMouseClicked(e->{
						if (!clicked[ii][jj]) {
							clicked[ii][jj]=true;

							nbOfClicksInRound++;
							updateNbOfClicksLeft(maxNbClicksInRound - nbOfClicksInRound);
							if (nbOfClicksInRound>=maxNbClicksInRound) {
								endOfGame(false, nbOfRounds, score);
							}
							else {
								rect.setFill(interpolateColors(red, blue, factor));
								if (ii==randLocationX && jj==randLocationY) {
									setScore(getScore() + 100 + (maxNbClicksInRound - nbOfClicksInRound)*30);
									newRound();
								}
							}
							
						}
						
					});
					
					this.getChildren().add(rect);
				}
			}
		}
	}
	public void endOfGame(Boolean timeOver, int nbOfRounds, int score) {
		Boolean newHighScore = HighScore.updateHighScore(score, timer);
		this.timerTimeline.stop();
		try {
			FXMLLoader loader = new FXMLLoader(
				    getClass().getResource(
				      "EndOfGame.fxml"
				    )
				  );
			
			this.setBottom(null);
			BorderPane endOfGamePane = (BorderPane)loader.load();
			EndOfGameController controller = loader.getController();
			
			controller.initData(score, nbOfRounds, timeOver, newHighScore);
;			 this.setCenter(endOfGamePane);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	

	public void updateNbOfClicksLeft(int i) {
		if (nbOfClicksLeft_Label!=null)
		nbOfClicksLeft_Label.setText("Clicks left: "+i);
	}

	public void updateNbOfRoundsLabel(Integer nbOfRounds2) {
		if (nbOfRounds_Label!=null)
		nbOfRounds_Label.setText("Rounds: "+nbOfRounds2);
		
	}

	public void newRound() {
		this.round = new Round();
		this.setCenter(round);
	}

	public static Paint interpolateColors(Color red, Color blue, double factor) {
		
		double redVal = red.getRed()*factor + blue.getRed()*(1-factor);
		double blueVal = red.getBlue()*factor + blue.getBlue()*(1-factor);
		double greenVal = red.getGreen()*factor + blue.getGreen()*(1-factor);
		return new Color(redVal,greenVal, blueVal, 1);
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}
	public int getNbOfRounds() {
		return nbOfRounds;
	}

	public void setNbOfRounds(int nbOfRounds) {
		this.nbOfRounds = nbOfRounds;
	}

	public int getMaxClicks() {
		return maxClicks;
	}

	public void setMaxClicks(int maxClicks) {
		this.maxClicks = maxClicks;
	}

	public int getTimeLeft() {
		return timeLeft;
	}

	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}
	
}


