package math.warrior.view.fx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import math.warrior.controller.GameItem;
import math.warrior.controller.GameMap;
import math.warrior.controller.GameMonster;
import math.warrior.controller.GamePlayer;
import math.warrior.controller.GameRoom;
import math.warrior.controller.InvalidCommandException;
import math.warrior.model.Database;

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
		this.textArea.setText("Welcome Math Warrior " + player.getName() + "\n");
		displayRoomInfo(this.roomGrid[map.getxPostion()][map.getyPostion()]);
	}

	/**Method: displayRoomInfo
	 * Displays the room and all of its contents if it is not solved. Makes sure to check
	 * for valid content objects first, acts differently than a room toString.
	 * @param room The room to show in the text area. 
	 */
	public void displayRoomInfo(GameRoom room)
	{
		if (room.isSolved())
		{
			this.textArea.appendText("\nYou are in " + room.getName() + "\n" + room.getDescription());
			this.textArea.appendText("\n There is nothing of interest here.");
		}
		else
		{
			this.textArea.appendText("\nYou are in " + room.getName() + "\n" + room.getDescription());
			if (room.getMonster() != null)
				this.textArea.appendText("\nThe room has monster " + room.getMonster().getName() + "\n" + room.getMonster().getDesciption());
			else if (room.getPuzzle() != null)
				this.textArea.appendText(room.getPuzzle().getDescription());
			else if (room.getItem() != null)
				this.textArea.appendText("\nThe room contains " + room.getItem().getName());
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
				else
					this.gameMap.setyPostion(this.gameMap.getyPostion() + 1);
				solveEmptyRoom(this.roomGrid[this.gameMap.getxPostion()][gameMap.getyPostion()]);
				this.displayRoomInfo(this.roomGrid[this.gameMap.getxPostion()][gameMap.getyPostion()]);
			}
			else if (commandValues[1].equalsIgnoreCase("up"))
			{
				if (this.gameMap.getyPostion() == 0)
					this.gameMap.setyPostion(4);
				else
					this.gameMap.setyPostion(this.gameMap.getyPostion() - 1);
				solveEmptyRoom(this.roomGrid[this.gameMap.getxPostion()][gameMap.getyPostion()]);
				this.displayRoomInfo(this.roomGrid[this.gameMap.getxPostion()][gameMap.getyPostion()]);
			}
			else if (commandValues[1].equalsIgnoreCase("left"))
			{
				if (this.gameMap.getxPostion() == 0)
					this.gameMap.setxPostion(9);
				else
					this.gameMap.setxPostion(this.gameMap.getxPostion() - 1);
				solveEmptyRoom(this.roomGrid[this.gameMap.getxPostion()][gameMap.getyPostion()]);
				this.displayRoomInfo(this.roomGrid[this.gameMap.getxPostion()][gameMap.getyPostion()]);
			}
			else if (commandValues[1].equalsIgnoreCase("right"))
			{
				if (this.gameMap.getxPostion() == 9)
					this.gameMap.setxPostion(0);
				else
					this.gameMap.setxPostion(this.gameMap.getxPostion() + 1);
				solveEmptyRoom(this.roomGrid[this.gameMap.getxPostion()][gameMap.getyPostion()]);
				this.displayRoomInfo(this.roomGrid[this.gameMap.getxPostion()][gameMap.getyPostion()]);
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
			else if (commandValues[0].equalsIgnoreCase("exit") && commandValues[1].equalsIgnoreCase("game"))
			{
				System.exit(0);
			}
			else if (commandValues[0].equalsIgnoreCase("save") && commandValues[1].equalsIgnoreCase("game"))
			{
				this.database.saveInventory(this.gamePlayer.getInventory(), this.gamePlayer.getId());
				this.database.savePlayer(this.gamePlayer);
				this.database.saveLocation(this.gamePlayer.getId(), this.gameMap.getxPostion(), this.gameMap.getyPostion());
				this.database.saveRooms(this.gameMap.gridIntoList(this.roomGrid), this.gamePlayer.getId());
			}
			else if (commandValues[0].equalsIgnoreCase("show") && commandValues[1].equalsIgnoreCase("location"))
			{
				this.textArea.appendText("\nLocation is (" + this.gameMap.getxPostion() + "," + this.gameMap.getyPostion() + ").");
			}
			else if (commandValues[0].equalsIgnoreCase("display") && commandValues[1].equalsIgnoreCase("stats"))
			{
				this.textArea.appendText("\n" + this.gamePlayer.toString());
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
				this.textArea.appendText(this.gamePlayer.useItem(commandValues[2]));
			}
			else if (commandValues[0].equalsIgnoreCase("drop") && commandValues[1].equalsIgnoreCase("item"))
			{
				this.textArea.appendText(this.gamePlayer.dropItem(commandValues[2]));
			}
			else if (commandValues[0].equalsIgnoreCase("add") && commandValues[1].equalsIgnoreCase("item"))
			{
				GameRoom room = this.roomGrid[this.gameMap.getxPostion()][this.gameMap.getyPostion()];
				if (room.getItem() != null && room.getItem().getName().toLowerCase().contains(commandValues[2].toLowerCase()))
				{
					GameItem item = room.getItem();
					this.textArea.appendText(this.gamePlayer.addItem(item));
					this.roomGrid[this.gameMap.getxPostion()][this.gameMap.getyPostion()].setSolved(true);
					this.roomGrid[this.gameMap.getxPostion()][this.gameMap.getyPostion()].setItem(null);
				}
			}
			else if (commandValues[0].equalsIgnoreCase("use") && commandValues[1].equalsIgnoreCase("weapon"))
			{
				GameRoom room = this.roomGrid[this.gameMap.getxPostion()][this.gameMap.getyPostion()];
				if (room.getMonster() != null)
				{
					this.textArea.appendText(this.gamePlayer.fightMonster(this.roomGrid[this.gameMap.getxPostion()][this.gameMap.getyPostion()].getMonster()));
					if (this.roomGrid[this.gameMap.getxPostion()][this.gameMap.getyPostion()].getMonster().getHealthPoints() <= 0)
					{
						this.roomGrid[this.gameMap.getxPostion()][this.gameMap.getyPostion()].setSolved(true);
						this.roomGrid[this.gameMap.getxPostion()][this.gameMap.getyPostion()].setMonster(null);
						this.gamePlayer.setScore(this.gamePlayer.getScore() + 10);
						if (!this.winGame(this.gamePlayer.getScore()))
							this.textArea.appendText("\nYou have defeated the monster in the room, congratulations! Score increased by 10 points.");
						if (!this.loseGame(this.gamePlayer.getHealthPoints()))
							this.textArea.appendText("\nPlayer's remaining health points is " + this.gamePlayer.getHealthPoints());
					}
				}
			}
			else
			{
				GameRoom room = this.roomGrid[this.gameMap.getxPostion()][this.gameMap.getyPostion()];
				if (room.getPuzzle() != null)
				{
					String entry = "";
					for (String element: commandValues)
						entry += element + " ";
					if ((entry.trim()).equalsIgnoreCase(room.getPuzzle().getTerminator()))
					{
						this.roomGrid[this.gameMap.getxPostion()][this.gameMap.getyPostion()].setSolved(true);
						this.textArea.appendText(room.getPuzzle().getSolvedMessage());
						this.roomGrid[this.gameMap.getxPostion()][this.gameMap.getyPostion()].setPuzzle(null);
						this.gamePlayer.setScore(this.gamePlayer.getScore() + 10);
						if (this.winGame(this.gamePlayer.getScore()))
							this.textArea.appendText("\nYou have solved the puzzle in the room, congratulations! Score increased by 10 points.");
					}
					else
						this.textArea.appendText("\nIn this room, " + room.getPuzzle().getHint() + "\n");
				}
				else throw new InvalidCommandException();
			}
		}
		catch(ArrayIndexOutOfBoundsException exc)
		{
			throw new InvalidCommandException();
		}
	}

	/**Method: solveEmptyRoom
	 * This solves rooms with no contents by defaulting the to solved.
	 * @param room A game room
	 */
	private void solveEmptyRoom(GameRoom room)
	{
		if (room.getItem() == null && room.getPuzzle() == null && room.getMonster() == null)
			room.setSolved(true);
	}

	/**Method: loseGame
	 * This method handles a game over if the player's health falls below 0.
	 * @param healthPoints The health points belonging to the player.
	 * @return If the game was lost.
	 */
	public boolean loseGame(int healthPoints)
	{
		if (healthPoints <= 0)
		{
			Stage stage = new Stage();
			stage.setTitle("GAME OVER!");
			FlowPane fPane = new FlowPane();
			fPane.getChildren().add(new Label("You have fallen to the minions of the haunted high school. GAME OVER!"));
			Scene scene = new Scene(fPane);
			stage.setScene(scene);
			stage.setFullScreen(true);
			stage.show();
			return true;
		}
		return false;
	}

	/**Method: winGame
	 * This exits the game when the player wins by achieving a score over 100.
	 * @param score players score.
	 * @return If the game was won or not.
	 */
	public boolean winGame(int score)
	{
		if (score >= 100)
		{
			Stage stage = new Stage();
			stage.setTitle("You Won The Game!");
			FlowPane fPane = new FlowPane();
			fPane.getChildren().add(new Label("CONGRATULATIONS! YOUR SCORE IS OVER 100 POINTS AND YOU WIN THE GAME!"));
			Scene scene = new Scene(fPane);
			stage.setScene(scene);
			stage.setFullScreen(true);
			stage.show();
			return true;
		}
		return false;
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
			this.textArea.appendText("\n" + ice.getMessage());
		}
		this.clearCommandBox();
	}

}
