package math.warrior.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import math.warrior.model.Database;
import math.warrior.model.GameMap;
import math.warrior.model.GamePlayer;
import math.warrior.model.InvalidCommandException;

/**Class: TextHandlerListener.java
 * @author: Matthew Berger
 * @version 1.0
 * Date Written/Updated: Oct 20, 2014
 * Class Description: This is the listener attached to the text input box that takes in player commands.
 * This will do all business logic handling and will access the model and update the view. 
 */

//edited Some things to work with the InvalidCommandException class -Brock
public class TextHandlerListener implements EventHandler<ActionEvent>
{
	private TextArea textArea;
	private TextField data;
	private GameMap gameMap;
	private GamePlayer gamePlayer;
	private Database database;

	/**Constructor
	 * This passes in all needed objects for the command handling. 
	 */
	public TextHandlerListener(TextArea area, TextField userEntry, GameMap map,
			GamePlayer player, Database database)
	{
		this.textArea = area;
		this.data = userEntry;
		this.gameMap = map;
		this.gamePlayer = player;
		this.database = database;
	}

	/**Method: clearCommandBox
	 * This method clears out the command text box from all text contents. 
	 */
	private void clearCommandBox()
	{
		this.data.setText("");
	}

	/**Method: movePlayer
	 * This method handles navigation commands identified by the first word
	 * being equal to "move". The player is moved up, down, left, or right. If the 
	 * user types "move" not followed by a valid direction, notification message is shown
	 * of invalid direction command. 
	 * @param commandValues
	 */
	private void movePlayer(String[] commandValues)
	{
		if (commandValues.length > 1)
		{
			if (commandValues[1].equalsIgnoreCase("up"))
			{
				//move up
				this.textArea.appendText("\nup");
			}
			else if (commandValues[1].equalsIgnoreCase("down"))
			{
				//move down
				this.textArea.appendText("\ndown");
			}
			else if (commandValues[1].equalsIgnoreCase("left"))
			{
				//move left
				this.textArea.appendText("\nleft");
			}
			else if (commandValues[1].equalsIgnoreCase("right"))
			{
				//move right
				this.textArea.appendText("\nright");
			}
		}
		else
		{
			throw new InvalidCommandException();
		}
	}

	/** Method: handleCommand
	 * This handles command input.
	 * This method will handle all user input to 
	 * direct what will simulate in the game. 
	 * @throws Exception if a valid command is not entered
	 */
	private void handleCommand(String[] commandValues) throws InvalidCommandException
	{
		if (commandValues[0].equalsIgnoreCase("move"))
		{
			this.movePlayer(commandValues);
		}
		else if (commandValues[0].equalsIgnoreCase("quit") && commandValues[1].equalsIgnoreCase("game"))
		{

		}
		else if (commandValues[0].equalsIgnoreCase("save") && commandValues[1].equalsIgnoreCase("game"))
		{
			//save game method
		}
		else
		{
			throw new InvalidCommandException();
		}
	}

	/**Method: handle
	 * @param event The event source that was found.
	 * This method handles the user pressing enter in the text box that 
	 * takes in text commands. 
	 */
	@Override
	public void handle(ActionEvent event) 
	{
		String[] commandValues = this.data.getText().toString().trim().split(" ");
		try
		{
			this.handleCommand(commandValues);
		}
		catch(InvalidCommandException ice)
		{
			this.textArea.appendText(ice.getMessage() + "\n");
		}
		this.clearCommandBox();
	}

}
