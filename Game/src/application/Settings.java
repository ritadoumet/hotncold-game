package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
import javafx.scene.paint.Color;

public class Settings {



	private Settings() {
		
	}
	private static Settings settings = null;
	private static Timer time = Timer.SIXTY_SECS;
	private static final int stageWidth = 450;
	private static final int stageHeight = 550;
	private static final int gamePaneWidth = 400;
	private static final int gamePaneHeight = 400;
	private static final int initialDimensionX = 4;
	private static final int initialDimensionY = 4;
	private static final Color lightThemeRed = Color.web("#DD0030"), lightThemeBlue = Color.web("#2500E0"),
								darkThemeRed = Color.web("#B00040"), darkThemeBlue = Color.web("#302097");
	private static final String lightThemeCSS = "light.css";
	private static final String darkThemeCSS = "dark.css";
	private static String themeFileName = lightThemeCSS;
	private static Theme theme = Theme.LIGHT;
	private static int maxClicks = 4;
	public Settings getSettings() {
		if (Settings.settings==null) {
			settings = new Settings();
		}
		return settings;
	}
	public static String getThemeFileName() {
		return themeFileName;
	}

	public static void setThemeFileName(String themeFileName) {
		Settings.themeFileName = themeFileName;
	}
	public static Timer getTime() {
		return time;
	}

	public static void setTime(Timer time) {
		Settings.time = time;
	}

	public static int getStageWidth() {
		return stageWidth;
	}

	public static int getStageHeight() {
		return stageHeight;
	}

	public static int getGamePaneWidth() {
		return gamePaneWidth;
	}

	public static int getGamePaneHeight() {
		return gamePaneHeight;
	}

	public static Theme getTheme() {
		return theme;
	}

	public static void setTheme(Theme theme) {
		Settings.theme = theme;
		if (theme.equals(Theme.LIGHT)) {
			Settings.setThemeFileName(lightThemeCSS);
		}
		else Settings.setThemeFileName(darkThemeCSS);
	}

	public static int getMaxClicks() {
		return maxClicks;
	}

	public enum Timer{
		FOURTY_FIVE_SECS, SIXTY_SECS, HUNDRED_TWENTY_SECS
	};
	
	public enum Theme{
		DARK, LIGHT
	};
	

	public static void updateSettings(Theme theme, Timer time) {
		Settings.setTheme(theme);
		Settings.setTime(time);
		try {
			Writer fileWriter = new FileWriter("settings.txt");
			if (theme.equals(Theme.LIGHT)) {
				fileWriter.write("light");
			}
			else fileWriter.write("dark");
			switch(time) {
			case FOURTY_FIVE_SECS: fileWriter.write(" 45");
				break;
			case HUNDRED_TWENTY_SECS:fileWriter.write(" 120");
				break;
			case SIXTY_SECS:fileWriter.write(" 60");
				break;
			default:
				break;
			
			}
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void loadSettings() {
		try {
		      File myObj = new File("settings.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        String arg[] = data.split(" ");
		        if (arg[0].equals("light")) {
		        	Settings.setTheme(Theme.LIGHT);
		        }
		        else {
		        	Settings.setTheme(Theme.DARK);
		        }
		        int time = Integer.parseInt(arg[1]);
		        switch(time) {
		        case 45: Settings.setTime(Timer.FOURTY_FIVE_SECS);break;
		        case 60: Settings.setTime(Timer.SIXTY_SECS);break;
		        case 120: Settings.setTime(Timer.HUNDRED_TWENTY_SECS); break;
		        default: Settings.setTime(Timer.SIXTY_SECS);break;
		        }
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      e.printStackTrace();
		    }
		
	}

	
	public static int getInitialdimensionx() {
		return initialDimensionX;
	}

	public static int getInitialdimensiony() {
		return initialDimensionY;
	}

	public static Color getLightthemered() {
		return lightThemeRed;
	}

	public static Color getLightthemeblue() {
		return lightThemeBlue;
	}

	public static Color getDarkthemered() {
		return darkThemeRed;
	}

	public static Color getDarkthemeblue() {
		return darkThemeBlue;
	}


}

	