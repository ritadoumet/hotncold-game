package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class HighScore extends BorderPane{

	private static Integer high45, high60, high120;
	private static LocalDateTime date45, date60, date120;
	
	public HighScore() {
		TopNav topNav = new TopNav("High Scores");
		loadScores();
		GridPane scoresGrid = new GridPane();

		scoresGrid.setHgap(40);
		scoresGrid.setVgap(15);
		
		BorderPane.setAlignment(scoresGrid, Pos.CENTER);
		BorderPane.setMargin(scoresGrid, new Insets(40,20,40,20));
		
		//Column #0
		Label high_45_title=new Label("Top 45 seconds: ");
		Label high_60_title = new Label("Top 60 seconds: ");
		Label high_120_title = new Label("Top 120 seconds: ");
		GridPane.setColumnIndex(high_120_title, 0);
		GridPane.setColumnIndex(high_60_title, 0);
		GridPane.setColumnIndex(high_45_title, 0);
		GridPane.setRowIndex(high_45_title, 0);
		GridPane.setRowIndex(high_120_title, 2);
		GridPane.setRowIndex(high_60_title, 1);
		
		//Column #1
		Label high120_text = new Label(high120.toString());
		Label high60_text = new Label(high60.toString());
		Label high45_text = new Label(high45.toString());
		GridPane.setColumnIndex(high45_text, 1);
		GridPane.setColumnIndex(high60_text, 1);
		GridPane.setColumnIndex(high120_text, 1);
		GridPane.setRowIndex(high45_text, 0);
		GridPane.setRowIndex(high60_text, 1);
		GridPane.setRowIndex(high120_text, 2);
		
		//Column #2
		Label high120_date_text = new Label(date120.getHour()+":"+date120.getMinute()+" "+date120.getDayOfMonth()+"/"+date120.getMonthValue()+"/"+date120.getYear());
		Label high60_date_text = new Label(date60.getHour()+":"+date60.getMinute()+" "+date60.getDayOfMonth()+"/"+date60.getMonthValue()+"/"+date60.getYear());
		Label high45_date_text = new Label(date45.getHour()+":"+date45.getMinute()+" "+date45.getDayOfMonth()+"/"+date45.getMonthValue()+"/"+date45.getYear());
		GridPane.setColumnIndex(high120_date_text, 2);
		GridPane.setColumnIndex(high60_date_text, 2);
		GridPane.setColumnIndex(high45_date_text, 2);
		GridPane.setRowIndex(high120_date_text, 2);
		GridPane.setRowIndex(high60_date_text, 1);
		GridPane.setRowIndex(high45_date_text, 0);
		
		if (high120==0) {
			high120_text.setText("No High Score Yet");
			high120_date_text.setText("");
		}
		if (high60==0) {
			high60_text.setText("No High Score Yet");
			high60_date_text.setText("");
		}
		if (high45==0) {
			high45_text.setText("No High Score Yet");
			high45_date_text.setText("");
		}
		scoresGrid.getChildren().addAll(high120_date_text, high60_date_text, high45_date_text,high120_text, high60_text, high45_text, high_120_title, high_60_title, high_45_title);
		
		this.setTop(topNav);
		this.setCenter(scoresGrid);
		this.setBottom(new ColorDemo(5,30, -15));
		
	}

	public static void loadScores() {
		
		 File myObj = new File("scores.txt");
	      Scanner myReader = null;
	      int linesRead = 0, year, month, day, hour, minute, high;
		try {
			myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				linesRead++;
		        String data = myReader.nextLine();
		        String arr[] = data.split("[T -/:]"); 
		        
		        high = Integer.parseInt(arr[0]);
		        year = Integer.parseInt(arr[1]);
	        	month = Integer.parseInt(arr[2]);
	        	day = Integer.parseInt(arr[3]);
	        	hour = Integer.parseInt(arr[4]);
	        	minute = Integer.parseInt(arr[5]);
	        	LocalDateTime ldt = LocalDateTime.of(year, month, day, hour, minute);
		        switch(linesRead) {
			        case 1: 
			        	setHigh45(high);
			        	setDate45(ldt);
			        	break;
			        case 2:
			        	setHigh60(high);
			        	setDate60(ldt); 
			        	break;
			        case 3: 
			        	setHigh120(high); 
			        	setDate120(ldt);
			        	break;
			        default: break;
		        }
		        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	      
		
	}
	
	public static Boolean updateHighScore(int score, Settings.Timer timer) {
		//returns true if new high, false otherwise
		Boolean updated = false;
		switch(timer) {
		case FOURTY_FIVE_SECS: if (score>high45) {setHigh45(score); setDate45(LocalDateTime.now());updated=true;}
			break;
		case HUNDRED_TWENTY_SECS:if (score>high120) {setHigh120(score); setDate120(LocalDateTime.now());updated=true;}
			break;
		case SIXTY_SECS:if (score>high60) {setHigh60(score); setDate60(LocalDateTime.now());updated=true;}
			break;
		default:
			break;
		
		}
		
		try {
			Writer fileWriter = new FileWriter("scores.txt");
			fileWriter.write(high45+" "+date45+"\n"+high60+" "+date60+"\n"+high120+" "+date120);
			fileWriter.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return updated;
		
	}

	public int getHigh45() {
		return high45;
	}

	public static void setHigh45(int high45) {
		HighScore.high45 = high45;
	}

	public int getHigh60() {
		return high60;
	}

	public static void setHigh60(int high60) {
		HighScore.high60 = high60;
	}

	public int getHigh120() {
		return high120;
	}

	public static void setHigh120(int high120) {
		HighScore.high120 = high120;
	}

	public LocalDateTime getDate45() {
		return date45;
	}

	public static void setDate45(LocalDateTime date45) {
		HighScore.date45 = date45;
	}

	public LocalDateTime getDate60() {
		return date60;
	}

	public static void setDate60(LocalDateTime date60) {
		HighScore.date60 = date60;
	}

	public LocalDateTime getDate120() {
		return date120;
	}

	public static void setDate120(LocalDateTime date120) {
		HighScore.date120 = date120;
	}
}
