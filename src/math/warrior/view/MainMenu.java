package math.warrior.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import math.warrior.model.Database;
import math.warrior.model.GameMap;
import math.warrior.model.GamePlayer;

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
		stage.sizeToScene();
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
		stage.show();
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
				{//TO DO: validate user-name and password before adding player.
					String password = passwordText.getText().trim();
					String userName = nameText.getText().trim();
					//query that inserts player into database with defaults.
					database.executeQuery("INSERT INTO [Players] ([PlayerName], [MaximumHealth], [HealthPoints], [Strength], "
							+ "[Score], [Password]) VALUES ('" + userName + "', 100, 100, 5, 0, '"+ password + "');");
					//add records to all tables in database for the new player based on their id
					int playerID = database.loadPlayerId(password);
					database.genRoomRecordsForNewPlayer(playerID);
					database.genLocationForNewPlayer(playerID);
					database.genMonsterRecordsForNewPlayer(playerID);
					database.genPuzzleRecordsForNewPlayer(playerID);
					database.genItemRecordsForNewPlayer(playerID);
					//terminate window
					stage.close();
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

	public static void main(String[] args)
	{
		launch(args);
	}
}
