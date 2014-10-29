package math.warrior.view;

//Imports
import javafx.application.Application;
import javafx.geometry.Insets;
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
import javafx.stage.Stage;
import math.warrior.model.GamePlayer;

/**Class: GameUI.java
 * @author: Matthew Berger
 * @version 1.0
 * Date Written/Updated: Oct 19, 2014
 * Class Description: This is a UI Class that implements the user interface for the game. 
 * It uses a border pane with four sections: left, center, right, and bottom. Each section
 * holds different UI components for the game to represent different functions of the game. 
 */
public class GameUI extends Application
{
	//Attributes: Layout Types
	private Scene mainScene;
	private BorderPane borderPane;
	private GridPane gridPane;
	private VBox helpCommandViewArea;
	private VBox textCommandViewArea;
	private ScrollPane scrollPane;
	private TextHandlerListener listener;

	//Attribute: Important Widget Objects in Layout Types
	private final Image IMAGE = new Image("file:mathwarrior.gif");;
	private Label textDisplayLabel;
	private Label commandHelpMenuLabel;
	private TextField commandEntry;
	private TextArea commandResultBox;

	//Stryling Constants for UI Design
	/*private Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
	private final double MAX_WINDOW_HEIGHT = visualBounds.getMaxX();
	private final double MAX_WINDOW_WIDTH =  visualBounds.getMaxY(); */
	private final double MAX_WINDOW_HEIGHT = 700;
	private final double MAX_WINDOW_WIDTH = 1100;
	private final double MAX_BUTTON_HEIGHT = 50;
	private final double MAX_BUTTON_WIDTH = 70;
	private final int VBOX_SPACING = 3;
	private final Insets WIDGET_PADDING = new Insets(10);
	private final Color TEXT_COLOR = Color.RED;
	private final String FX_BLACK_BGCOLOR = "-fx-background-color: black;";
	private final Font TEXT_FONT_STYLE = Font.font("Arial", 14);
	private final Font TEXT_FONT_STYLE_TITLE = Font.font("Verdana", 15);

	//Permanent Commands - TO DO: Must replace with commands from DBMS
	private String[] textCommands = {"Save Game", "Exit Game", "Move up", "Move Down", "Move Right", "Move Left", "Use Weapon", "Hint", "Display Stats"};
	//private GameMap map;
	private GamePlayer player;
	
	/**Method: createLeftPane
	 * This sets up the left side of the border pane with UI components. 
	 * Also handles styling and dimensions of UI components. 
	 */
	private void createLeftPane()
	{
		this.helpCommandViewArea = new VBox(this.VBOX_SPACING);
		this.commandHelpMenuLabel = new Label("Help Commands List");
		this.commandHelpMenuLabel.setTextFill(this.TEXT_COLOR);
		this.commandHelpMenuLabel.setFont(this.TEXT_FONT_STYLE_TITLE);
		this.helpCommandViewArea.getChildren().add(this.commandHelpMenuLabel);
		for (String element: this.textCommands)
		{
			Label helpCommand = new Label(element);
			helpCommand.setTextFill(this.TEXT_COLOR);
			helpCommand.setFont(TEXT_FONT_STYLE);
			this.helpCommandViewArea.getChildren().add(helpCommand);
		}
		this.helpCommandViewArea.setPadding(this.WIDGET_PADDING);
		this.borderPane.setLeft(this.helpCommandViewArea);
	}

	/**Method: createCenterPane
	 * This method sets up the UI components for the center of the border pane. 
	 * This also handles styling and dimensions of UI components. 
	 */
	private void createCenterPane()
	{
		this.gridPane = new GridPane();
		this.gridPane.setPadding(this.WIDGET_PADDING);
		Button button;
		//TO DO: Add room objects to buttons - Another Class
		for (int rows = 0; rows < 10; rows++)
		{
			for (int columns = 0; columns < 5; columns++)
			{
				button = new Button();
				if (columns == 0 && rows == 0)
					button.setBackground(new Background(new BackgroundImage(this.IMAGE, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, new BackgroundSize(50, 50, false, false, true, false))));
				//button.setStyle(this.FX_WHITE_BGCOLOR);
				//button.setText(room.getID() + "");
				button.setTextFill(this.TEXT_COLOR);
				//button.setBackground(new Background(new BackgroundImage(this.IMAGE, null, null, null, new BackgroundSize(50, 50, false, false, true, false))));
				button.setMinHeight(this.MAX_BUTTON_HEIGHT);
				button.setMinWidth(this.MAX_BUTTON_WIDTH);
				this.gridPane.add(button, rows, columns);
			}
		}
		this.gridPane.setStyle(this.FX_BLACK_BGCOLOR);
		this.borderPane.setCenter(this.gridPane);
	}

	/**Method: createRightPane
	 * This method sets up the UI components for the right side of the border pane.
	 * This also handles styling of UI components and dimensions. 
	 */
	private void createRightPane()
	{
		this.textCommandViewArea = new VBox(this.VBOX_SPACING);
		this.textCommandViewArea.setPadding(this.WIDGET_PADDING);
		this.commandEntry = new TextField("");
		this.listener = new TextHandlerListener(this.commandResultBox, this.commandEntry, //this.map, 
				this.player);
		this.commandEntry.setOnAction(listener);
		this.textDisplayLabel = new Label("Enter Command:");
		this.textDisplayLabel.setFont(this.TEXT_FONT_STYLE_TITLE);
		this.textDisplayLabel.setTextFill(this.TEXT_COLOR);
		this.textCommandViewArea.getChildren().add(this.textDisplayLabel);
		this.textCommandViewArea.getChildren().add(this.commandEntry);
		this.borderPane.setRight(this.textCommandViewArea);
	}

	/**Method: createBottomPane
	 * This method sets up the UI components for the bottom of the border pane. 
	 * This also handles styling and dimensions of UI components. 
	 */
	private void createBottomPane()
	{
		this.commandResultBox = new TextArea(); 
		this.commandResultBox.setWrapText(true);
		this.commandResultBox.setMinWidth(this.MAX_WINDOW_WIDTH);
		this.commandResultBox.setStyle(FX_BLACK_BGCOLOR);
		this.commandResultBox.setFont(this.TEXT_FONT_STYLE_TITLE);
		this.scrollPane = new ScrollPane(this.commandResultBox);
		this.scrollPane.setStyle(this.FX_BLACK_BGCOLOR);
		this.borderPane.setBottom(this.scrollPane);
	}

	/**Method: start
	 * This is called to show the display window to the user. 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		this.borderPane = new BorderPane();
		this.borderPane.setStyle(this.FX_BLACK_BGCOLOR);
		this.createLeftPane();
		this.createCenterPane();
		this.createBottomPane();
		this.createRightPane();
		this.mainScene = new Scene(this.borderPane);
		primaryStage.setTitle("Math Warrior");
		primaryStage.setScene(this.mainScene);
		primaryStage.setWidth(this.MAX_WINDOW_WIDTH);
		primaryStage.setHeight(this.MAX_WINDOW_HEIGHT);
		primaryStage.setMinHeight(this.MAX_WINDOW_HEIGHT);
		primaryStage.setMinWidth(this.MAX_WINDOW_WIDTH);
		primaryStage.show();
	}

	/**
	 * Tester main for UI
	 * @param args
	 */
	public static void main(String[] args)
	{
		launch(args);
	}

}
