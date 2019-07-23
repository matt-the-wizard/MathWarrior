package math.warrior.view.fx;

//Imports
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import math.warrior.controller.GameMap;
import math.warrior.controller.GamePlayer;
import math.warrior.model.Database;

/**Class: GameUI.java
 * @author: Matthew Berger
 * @version 1.0
 * Date Written/Updated: 11/24/2014
 * Class Description: This is a UI Class that implements the user interface for the game. 
 * It uses a border pane with four sections: left, center, right. Each section
 * holds different UI components for the game to represent different functions of the game. 
 */
public class GameUI 
{
	//Attributes: Layout Types
	private Stage primaryStage;
	private Scene mainScene;
	private BorderPane borderPane;
	private VBox helpCommandViewArea, textCommandViewArea;
	private ScrollPane scrollPane;
	private TextHandlerListener listener;

	//Attribute: Important Widget Objects in Layout Types
	public static final Image IMAGE = new Image("file:mathwarrior.gif");;
	private Label textDisplayLabel, commandHelpMenuLabel;
	private TextField commandEntry;
	private TextArea commandResultBox;

	//Styling Constants for UI Design
	//private Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
	//private final double MAX_WINDOW_HEIGHT = visualBounds.getMaxX();
	//private final double MAX_WINDOW_WIDTH =  visualBounds.getMaxY(); 
	public static final double MAX_WINDOW_HEIGHT = 700;
	public static final double MAX_WINDOW_WIDTH = 1100;
	public static final double MAX_BUTTON_HEIGHT = 50;
	public static final double MAX_BUTTON_WIDTH = 70;
	public static  int VBOX_SPACING = 3;
	public static  Insets WIDGET_PADDING = new Insets(10);
	public static final Color TEXT_COLOR = Color.RED;
	public static final String FX_BLACK_BGCOLOR = "-fx-background-color: black;";
	public static final Font TEXT_FONT_STYLE = Font.font("Arial", 18);
	public static final Font TEXT_FONT_STYLE_TITLE = Font.font("Verdana", 20);
	
	//Commands
	private String[] textCommands = {"Save Game", "Exit Game", "Edit Game", "Show Location", "Move Up", "Move Down", "Move Right", "Move Left", "Use Weapon", "Hint", "Display Stats", "Use Item ____", "Add Item _____", "Drop Item _____"};
	
	//Game Components 
	private GameMap map;
	private GamePlayer player;
	private Database database;
	
	/**Constructor
	 * This constructor instantiates all widget objects for the view by calling helper methods that 
	 * set up certain sections of the border pane. The main stage is then set up but not shown, it 
	 * will be accessed from an instance method so it can be handled and decided when to be shown.
	 * All game components needed are passed into this view for handling flow of MVC. The Listener acts
	 * as the controller for this view by handling commands, it then accesses the model and then updates the view.
	 * @param database The game database
	 * @param player The game player
	 * @param map The game map
	 */
	public GameUI(Database database, GamePlayer player, GameMap map)
	{
		this.primaryStage = new Stage();
		this.borderPane = new BorderPane();
		this.borderPane.setStyle(FX_BLACK_BGCOLOR);
		this.database = database;
		this.player = player;
		this.map = map;
		this.createLeftPane();
		this.createCenterPane();
		this.createRightPane();
		this.mainScene = new Scene(this.borderPane);
		primaryStage.setTitle("Math Warrior");
		primaryStage.setScene(this.mainScene);
		primaryStage.setWidth(MAX_WINDOW_WIDTH);
		primaryStage.setHeight(MAX_WINDOW_HEIGHT);
		primaryStage.setMinHeight(MAX_WINDOW_HEIGHT);
		primaryStage.setMinWidth(MAX_WINDOW_WIDTH);
		primaryStage.setFullScreen(true);
		//primaryStage.show();
	}
	
	/**Method: createLeftPane
	 * This sets up the left side of the border pane with UI components. 
	 * Also handles styling and dimensions of UI components. 
	 */
	private void createLeftPane()
	{
		this.helpCommandViewArea = new VBox(VBOX_SPACING);
		this.commandHelpMenuLabel = new Label("Help Commands List");
		this.commandHelpMenuLabel.setTextFill(TEXT_COLOR);
		this.commandHelpMenuLabel.setFont(TEXT_FONT_STYLE_TITLE);
		this.helpCommandViewArea.getChildren().add(this.commandHelpMenuLabel);
		for (String element: this.textCommands)
		{
			Label helpCommand = new Label(element);
			helpCommand.setTextFill(TEXT_COLOR);
			helpCommand.setFont(TEXT_FONT_STYLE);
			this.helpCommandViewArea.getChildren().add(helpCommand);
		}
		this.helpCommandViewArea.setPadding(WIDGET_PADDING);
		this.borderPane.setLeft(this.helpCommandViewArea);
	}

	/**Method: createRightPane
	 * This method sets up the UI components for the right side of the border pane.
	 * This also handles styling of UI components and dimensions. 
	 */
	private void createRightPane()
	{
		this.textCommandViewArea = new VBox(VBOX_SPACING);
		this.textCommandViewArea.setPadding(WIDGET_PADDING);
		this.commandEntry = new TextField("");
		this.listener = new TextHandlerListener(this.commandResultBox, this.commandEntry, this.map, 
				this.player, this.database);
		this.commandEntry.setOnAction(listener);
		this.textDisplayLabel = new Label("Enter Command:");
		this.textDisplayLabel.setFont(TEXT_FONT_STYLE_TITLE);
		this.textDisplayLabel.setTextFill(TEXT_COLOR);
		this.textCommandViewArea.getChildren().add(textDisplayLabel);
		this.textCommandViewArea.getChildren().add(commandEntry);
		this.borderPane.setRight(this.textCommandViewArea);
	}

	/**Method: createCenterPane
	 * This method sets up the UI components for the center of the border pane. 
	 * This also handles styling and dimensions of UI components. 
	 */
	private void createCenterPane()
	{
		this.commandResultBox = new TextArea(); 
		this.commandResultBox.setWrapText(true);
		//this.commandResultBox.setMinWidth(MAX_WINDOW_WIDTH);
		this.commandResultBox.setStyle(FX_BLACK_BGCOLOR);
		this.commandResultBox.setFont(TEXT_FONT_STYLE_TITLE);
		this.scrollPane = new ScrollPane(this.commandResultBox);
		this.scrollPane.setStyle(FX_BLACK_BGCOLOR);
		this.scrollPane.setFitToWidth(true);
		this.scrollPane.setFitToHeight(true);
		this.borderPane.setCenter(this.scrollPane);
	}
	
	/**Method: getStage
	 * Accessor for the main stage that holds the game user interface. Created so it could be 
	 * accessed from another view, such as the main menu user interface. 
	 * @return The main stage of the GameUI
	 */
	public Stage getStage()
	{
		return this.primaryStage;
	}

}
