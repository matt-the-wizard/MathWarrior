package math.warrior.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import math.warrior.model.Database;
import math.warrior.model.GameItem;
import math.warrior.model.GameMap;
import math.warrior.model.GamePlayer;
import math.warrior.model.GameRoom;
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
	private GameRoom[][] roomGrid;

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
		this.roomGrid = this.gameMap.putListIntoGrid();
		//Sets up text in the text area when the game loads. 
		this.textArea.setText("Welcome " + player.getName() + "\n");
		displayRoomInfo(this.roomGrid[map.getxPostion()][map.getyPostion()]);
	}

	/**Method: displayRoomInfo
	 * Displays the room and all of its contents if it is not solved. Makes sure to check
	 * for valid content objects first, acts differently than a room toString.
	 * @param room The room to show in the text area. 
	 */
	public void displayRoomInfo(GameRoom room)
	{
		this.textArea.appendText("You are in " + room.getName() + "\n" + room.getDescription());
		if (room.isSolved())
			this.textArea.appendText("\n There is nothing of interest here.");
		else
		{
			if (room.getMonster() != null)
				this.textArea.appendText("The room has monster " + room.getMonster().getName() + "\n" + room.getMonster().getDesciption());
			else if (room.getPuzzle() != null)
				this.textArea.appendText(room.getPuzzle().getDescription());
			else if (room.getItem() != null)
				this.textArea.appendText("The room contains " + room.getItem().getName());
		}
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
			if (commandValues[1].equalsIgnoreCase("down"))
			{
				if (this.gameMap.getyPostion() == 4)
					this.gameMap.setyPostion(0);
				this.gameMap.setyPostion(this.gameMap.getyPostion() + 1);
				this.displayRoomInfo(this.roomGrid[this.gameMap.getxPostion()][gameMap.getyPostion()]);
			}
			else if (commandValues[1].equalsIgnoreCase("up"))
			{
				if (this.gameMap.getyPostion() == 0)
					this.gameMap.setyPostion(4);
				this.gameMap.setyPostion(this.gameMap.getyPostion() - 1);
				this.displayRoomInfo(this.roomGrid[this.gameMap.getxPostion()][gameMap.getyPostion()]);
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
		try
		{
			if (!(commandValues.length > 0)) throw new InvalidCommandException();
			if (commandValues[0].equalsIgnoreCase("move"))
			{
				this.movePlayer(commandValues);
			}
			else if (commandValues[0].equalsIgnoreCase("quit") && commandValues[1].equalsIgnoreCase("game"))
			{
				System.exit(0);
			}
			else if (commandValues[0].equalsIgnoreCase("save") && commandValues[1].equalsIgnoreCase("game"))
			{
				//save game method
			}
			else if (commandValues[0].equalsIgnoreCase("hint"))
			{
				GameRoom room = this.roomGrid[this.gameMap.getxPostion()][this.gameMap.getyPostion()];
				if (room.getPuzzle() != null)
					this.textArea.appendText("\n In this room, " + room.getPuzzle().getHint() + "\n");
				else
					this.textArea.appendText("\nThere is no puzzle in this room. Refer to the command menu for commands to enter.\n");
			}
			else if (commandValues[0].equalsIgnoreCase("use") && commandValues[1].equalsIgnoreCase("item"))
			{
				try
				{
					boolean used = false;
					for (GameItem item: this.gamePlayer.getInventory())
					{
						if (item.getName().equals(commandValues[2]))
						{
							this.textArea.appendText(this.gamePlayer.useItem(item));
							used = true;
						}
					} 
					if (!used) this.textArea.appendText("\nThe item selected was not found in inventory.\n");
				}
				catch(NullPointerException npe)
				{
					this.textArea.appendText("\nThe item selected was not found in inventory.\n");
				}
			}
			else
			{
				throw new InvalidCommandException();
			}
		}
		catch(ArrayIndexOutOfBoundsException exc)
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
