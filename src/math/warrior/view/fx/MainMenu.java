package math.warrior.view.fx;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import math.warrior.controller.GameMap;
import math.warrior.controller.GamePlayer;
import math.warrior.model.Database;

/**Class: MainMenu.java
 * @author: Quan Tran and Matthew Berger
 * @version 1.0
 * Date Written/Updated: 11/13/2014
 * Class Description: This is a UI Class that acts as a main menu. It is the controller that will handle the model
 * and the views shown for the game. The populated data from the database will be sent over to the Game UI
 * view class based on the player login sub-screen. Once the player is validated, the data can be read in and the
 * game can start. 
 */
public class MainMenu extends Application 
{
	//Attributes
	private Button exitButton, 	newGameButton, 	loadGameButton, submitPlayer; //Buttons
	private Scene mainMenuScene; //Holds Layout
	private VBox mainMenuGroup, newPlayerVbox, loadPlayerVbox; //Layout
	private Label welcomeLabel, passwordLabel, nameLabel; //Labels
	private TextField passwordText, nameText; //TextFields
	//Important Game Components
	private Database database;
	private GameMap map;
	private GamePlayer player;

	/**Method: instantiateWidgets
	 * Helper method to style the widget objects used in the scene of this stage. 
	 * Does font style, color, and spacing. 
	 */
	private void instantiateWidgets()
	{
		this.welcomeLabel = new Label("Welcome to Math Warrior");
		this.welcomeLabel.setFont(GameUI.TEXT_FONT_STYLE_TITLE);
		this.welcomeLabel.setTextFill(GameUI.TEXT_COLOR);
		this.exitButton = new Button("Exit Game");
		this.exitButton.setFont(GameUI.TEXT_FONT_STYLE);
		this.loadGameButton = new Button("Load Game");
		this.loadGameButton.setFont(GameUI.TEXT_FONT_STYLE);
		this.newGameButton = new Button("New Game");
		this.newGameButton.setFont(GameUI.TEXT_FONT_STYLE);
	}

	/**Method: setUpWindow
	 * Adds all widget objects to the stage. 
	 * @param stage The main stage. 
	 */
	private void setUpWindow(Stage stage)
	{
		this.mainMenuGroup = new VBox();
		this.mainMenuGroup.setStyle(GameUI.FX_BLACK_BGCOLOR);
		this.mainMenuGroup.setPadding(GameUI.WIDGET_PADDING);
		this.mainMenuGroup.setSpacing(GameUI.VBOX_SPACING);
		this.mainMenuGroup.getChildren().add(this.welcomeLabel);
		this.mainMenuGroup.getChildren().add(this.newGameButton);
		this.mainMenuGroup.getChildren().add(this.loadGameButton);
		this.mainMenuGroup.getChildren().add(this.exitButton);
		this.mainMenuScene = new Scene(mainMenuGroup,300,400);
		stage.setTitle("Math Warrior - Main Menu");
		stage.setScene(mainMenuScene);
		stage.setFullScreen(true);
		stage.show();
	}

	/**
	 * Method: start
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 * Starts the application by settup up the primary stage. 
	 */
	@Override
	public void start(Stage stage)
	{
		instantiateWidgets();
		this.database = new Database();
		this.loadGameButton.setOnAction(new EventHandler<ActionEvent>()
				{
			@Override
			public void handle(ActionEvent arg0)
			{
				loadPlayer();
			}
				});
		this.exitButton.setOnAction(new EventHandler<ActionEvent>() 
				{
			@Override
			public void handle(ActionEvent arg0) 
			{
				//Close out the game if they wish to exit.
				System.exit(0);
			}
				});
		this.newGameButton.setOnAction(new EventHandler<ActionEvent>() 
				{
			@Override
			public void handle(ActionEvent arg0) 
			{
				//this will add the player to the database as well as populate database with their default game data.
				createNewPlayer();
			}
				});
		setUpWindow(stage);
	}

	/**Method: loadPlayer
	 * Load the game information from the database based on the player. The player is chosen
	 * by the user typing in their password into the text field which is then referenced from the database.
	 */
	public void loadPlayer()
	{
		final Stage stage = new Stage();
		instantiateLoadPlayerWidgets();
		submitPlayer.setOnAction(new EventHandler<ActionEvent>() 
				{
			@Override
			public void handle(ActionEvent arg0) 
			{
				//Get user password
				String password = passwordText.getText().trim();
				int playerId = database.loadPlayerId(password);
				//Gather game data
				player = database.loadGamePlayer(playerId);
				map = database.loadGameMap(playerId);
				//Start the game UI
				GameUI gameWindow = new GameUI(database, player, map);
				Stage gameStage = gameWindow.getStage();
				gameStage.show();
				stage.close();
			}
				});
		setUpLoadPlayerWindow(stage);
	}

	/**Method: instantiateLoadPlayerWidgets
	 * This helper method instantiates the widgets for the new player window stage. 
	 */
	private void instantiateLoadPlayerWidgets()
	{
		this.loadPlayerVbox = new VBox();
		this.loadPlayerVbox .setStyle(GameUI.FX_BLACK_BGCOLOR);
		this.loadPlayerVbox .setPadding(GameUI.WIDGET_PADDING);
		this.loadPlayerVbox .setSpacing(GameUI.VBOX_SPACING);
		passwordLabel = new Label("Enter Password:");
		passwordLabel.setTextFill(GameUI.TEXT_COLOR);
		passwordLabel.setFont(GameUI.TEXT_FONT_STYLE);
		passwordText = new TextField();
		submitPlayer = new Button("Submit Player Information");
	}

	/**Method: setUpLoadPlayerWindow
	 * Helper method to add widget objects to the scene.
	 * @param stage The stage to hold the widget objects. 
	 */
	private void setUpLoadPlayerWindow(Stage stage)
	{
		this.loadPlayerVbox.getChildren().add(this.passwordLabel);
		this.loadPlayerVbox.getChildren().add(this.passwordText);
		this.loadPlayerVbox.getChildren().add(this.submitPlayer);
		Scene scene = new Scene(this.loadPlayerVbox, 300, 300);
		stage.setScene(scene);
		stage.setFullScreen(true);
		stage.show();
	}

	/**Method:  ratePasswordByStrongness
	 * @param password Password belonging to the player
	 * @return int in range 0..4 for representing strongness of password
	 */
	private int  ratePasswordByStrongness(String password)
	{
		/* Password must be at least 6 characters.
		 * Password is recommended to include: lower, upper, digits, special charaxters: */
		if (password.length() < 6)
			return 0;
		// Do not accept password has rate =0.
		int strongness = 0, i; 
		Character c;

		// if pass has lower case
		for (i = 0; i < password.length(); i++)
		{
			c = password.charAt(i);
			if (('a'<=c) && (c<='z')) 
			{
				strongness++;
				break;
			}
		}

		// if pass has upper case
		for (i = 0; i < password.length(); i++)
		{
			c = password.charAt(i);
			if (('A'<=c) && (c<='Z')) 
			{
				strongness++;
				break;
			}
		}

		// if pass has digits
		for (i = 0; i < password.length(); i++)
		{
			c = password.charAt(i);
			if (('0'<=c) && (c<='9')) 
			{
				strongness++;
				break;
			}
		}
		// if pass has special characters
		for (i = 0; i < password.length(); i++)
		{
			c = password.charAt(i);
			if (((('A'<=c) && (c<='Z')) == false)
					&& ((('a'<=c) && (c<='z')) == false)
					&& ((('0'<=c) && (c<='9')) == false)) 
			{
				strongness++;
				break;
			}
		}
		return strongness;
	}

	/**Method:  ratePasswordByMessage
	 * This method notifies the user the strength of their password.
	 * @param password The password the player entered
	 * @return a message corresponding to the strongness of the password.
	 */
	private String ratePasswordByMessage(String password)
	{
		String rateList[]={"UNACCEPTABLE! THE PASSWORD IS TOO SHORT", 
				"PASSWORD IS TOO WEAK", 
				"PASSWORD IS STILL WEAK",
				"PASSWORD IS STRONG",
		"PASSWORD IS VERY STRONG"};
		return rateList[ratePasswordByStrongness(password)];
	}

	/**Method: createNewPlayer
	 * This method will put the new player in the database by getting information from the view.
	 * The user will then be returned back to the main menu screen to load their game.
	 */
	private void createNewPlayer()
	{
		final Stage stage = new Stage();
		stage.setTitle("Create Player");
		instantiateNewPlayerWidgets();
		submitPlayer.setOnAction(new EventHandler<ActionEvent>() 
				{
			@Override
			public void handle(ActionEvent arg0) 
			{
				try
				{
					String password = passwordText.getText().trim();
					String userName = nameText.getText().trim();
					passwordLabel.setText(ratePasswordByMessage(password));
					if (ratePasswordByStrongness(password) > 2)
					{
						//query that inserts player into database with defaults.
						database.genNewPlayer(userName, password);
						//add records to all tables in database for the new player based on their id
						int playerID = database.loadPlayerId(password);
						database.genNewInventory(playerID);
						database.genRoomRecordsForNewPlayer(playerID);
						database.genLocationForNewPlayer(playerID);
						database.genMonsterRecordsForNewPlayer(playerID);
						database.genPuzzleRecordsForNewPlayer(playerID);
						database.genItemRecordsForNewPlayer(playerID);
						//terminate window
						stage.close();
					}
				}
				catch(NullPointerException npe)
				{
					passwordText.setText("Player not found, try another password.");
				}
				catch(Exception e)
				{
					passwordText.setText("Player not found, try another password.");
				}
			}
				});
		setUpNewPlayerWindow(stage);
	}

	/**Method: setUpNewPlayerWindow
	 * Helper method to add widget objects to the scene.
	 * @param stage The stage to hold the widget objects. 
	 */
	private void setUpNewPlayerWindow(Stage stage)
	{
		this.newPlayerVbox.getChildren().add(this.nameLabel);
		this.newPlayerVbox.getChildren().add(this.nameText);
		this.newPlayerVbox.getChildren().add(this.passwordLabel);
		this.newPlayerVbox.getChildren().add(this.passwordText);
		this.newPlayerVbox.getChildren().add(this.submitPlayer);
		Scene scene = new Scene(this.newPlayerVbox, 300, 300);
		stage.setScene(scene);
		stage.setFullScreen(true);
		stage.show();
	}

	/**Method: instantiateNewPlayerWidgets
	 * This helper method instantiates the widgets for the new player window stage. 
	 */
	private void instantiateNewPlayerWidgets()
	{
		newPlayerVbox = new VBox();
		newPlayerVbox.setStyle(GameUI.FX_BLACK_BGCOLOR);
		newPlayerVbox.setPadding(GameUI.WIDGET_PADDING);
		newPlayerVbox.setSpacing(GameUI.VBOX_SPACING);
		nameLabel = new Label("Enter Player Name:");
		nameLabel.setFont(GameUI.TEXT_FONT_STYLE);
		nameLabel.setTextFill(GameUI.TEXT_COLOR);
		nameText = new TextField();
		passwordLabel = new Label("Enter Password:");
		passwordLabel.setTextFill(GameUI.TEXT_COLOR);
		passwordLabel.setFont(GameUI.TEXT_FONT_STYLE);
		passwordText = new TextField();
		submitPlayer = new Button("Submit Player Information");
	}

	/**Method: playMusic
	 * This starts music by loading the mp3 file above the src directory in the project folder. 
	 */
	public static void playMusic()
	{
		try 
		{
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("GameMusic.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start( );
		} 
		catch (UnsupportedAudioFileException e)
		{
			System.out.println("Cannot use supported audio file mp3.");
		}
		catch(FileNotFoundException fnfe)
		{
			System.out.println("Cannot find music file.");
		}
		catch (IOException ioe)
		{
			System.out.println("Cannot establish input stream");
		} 
		catch (LineUnavailableException e)
		{
			System.out.println("Line unavailable.");
		}
	}

	/**Method: main
	 * Starts the Application, first by starting music and then by launching the main menu for the game. 
	 * @param args Arguments. 
	 */
	public static void main(String[] args)
	{
		playMusic();
		launch(args);
	}
}

