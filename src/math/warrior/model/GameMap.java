package math.warrior.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javafx.scene.control.Button;

/**Class: GameMap.java
 * @author: Brock Bearchell / Matt / Quan
 * @version 1.0
 * Date Written/Updated: Nov 6, 2014
 * Class Description: Game Map that will hold a two dimensional array of game rooms.
 * Defaulted to 50 rooms to be stored in the map. Also stores the x and y position of the 
 * player in regards to the location on the map. 
 */

public class GameMap
{
	//Attributes
	private ArrayList<GameRoom> rooms;
	private int xPostion;
	private int yPostion;

	/**
	 * Constructor
	 * @param rooms The game rooms for the map. The map will hold the rooms for the game. 
	 * @param xPos The x position of the player
	 * @param yPos The y position of the player
	 */
	public GameMap(ArrayList<GameRoom> rooms, String xPos, String yPos)
	{
		this.setxPostion(xPostion);
		this.setyPostion(yPostion);
		Collections.shuffle(rooms);
		this.rooms = rooms;
	}

	/**Method: getxPostion
	 * Getter method for this instances's xPostion
	 * @return the xPostion
	 */
	public int getxPostion()
	{
		return xPostion;
	}

	/**Method: setxPostion
	 * Setter method for setting the xPostion value.
	 * @param xPostion the xPostion to set
	 */
	public void setxPostion(int xPostion)
	{
		this.xPostion = xPostion;
	}

	/**Method: setxPostion
	 * Setter method for setting the xPostion value.
	 * @param xPostion the xPostion to set
	 */
	public void setxPostion(String xPostion)
	{
		try
		{
			this.xPostion = Integer.parseInt(xPostion.trim());
		}
		catch(NumberFormatException nfe)
		{
			this.xPostion = 0;
			System.out.println("X Postion not read in correctly.");
		}
	}

	/**Method: getyPostion
	 * Getter method for this instances's yPostion
	 * @return the yPostion
	 */
	public int getyPostion()
	{
		return yPostion;
	}

	/**Method: setyPostion
	 * Setter method for setting the yPostion value.
	 * @param yPostion the yPostion to set
	 */
	public void setyPostion(int yPostion)
	{
		this.yPostion = yPostion;
	}

	/**Method: setyPostion
	 * Setter method for setting the yPostion value.
	 * @param yPostion the yPostion to set
	 */
	public void setyPostion(String yPostion)
	{
		try
		{
			this.yPostion = Integer.parseInt(yPostion.trim());
		}
		catch(NumberFormatException nfe)
		{
			this.yPostion = 0;
			System.out.println("Y Postion not read in correctly.");
		}
	}

	/**Method: getRooms
	 * Getter method for this instances's rooms
	 * @return the rooms
	 */
	public ArrayList<GameRoom> getRooms()
	{
		return rooms;
	}

	/**Method: setRooms
	 * Setter method for setting the rooms value.
	 * @param rooms the rooms to set
	 */
	public void setRooms(ArrayList<GameRoom> rooms)
	{
		this.rooms = rooms;
	}

	public GameRoom[][] putListIntoGrid()
	{
		GameRoom[][] rooms = new GameRoom[10][5];
		for (int rows = 0; rows < 10; rows++)
		{
			for (int columns = 0; columns < 5; columns++)
			{
				try
				{
					rooms[rows][columns] = this.rooms.get(rows + columns);
				}
				catch(NullPointerException npe)
				{
					System.out.println("Grid not made correctly.");
				}
			}
		}
		return rooms;
	}

	/**Method: toString
	 * @see java.lang.Object#toString()
	 * Displays game map object in String format.
	 */
	@Override
	public String toString()
	{
		return "Game Map\nRooms:" + rooms + "\nPlayer Position:\t(" + this.xPostion + "," + this.yPostion + ")";
	}


}
