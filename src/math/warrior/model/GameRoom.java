/**Class: GameRoom.java
 * @author: Matthew Berger
* @version 1.0
 * Date Written/Updated: Nov 7, 2014
 * Class Description: Entity that resembles a game room. Holds data to be manipulated in the game logic.
 */
package math.warrior.model;

/**Types
 * @author Matt
 */
public class GameRoom
{
	//Attributes
	private String name;
	private String description;
	private boolean isSolved;
	private GameMonster monster;
	private GamePuzzle puzzle;
	private GameItem item;
	
	/**Constructor
	 * @param name Name of the room
	 * @param description Description of the room
	 * @param isSolved True or false, Whether the room is solved.
	 * @param monster Monster in the room
	 * @param puzzle Puzzle in the room
	 * @param item Item in the room
	 */
	public GameRoom(String name, String description, String isSolved,
			GameMonster monster, GamePuzzle puzzle, GameItem item)
	{
		super();
		this.name = name;
		this.description = description;
		this.setSolved(isSolved);
		this.monster = monster;
		this.puzzle = puzzle;
		this.item = item;
	}

	/**Method: getName
	 * Getter method for this instances's name
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**Method: setName
	 * Setter method for setting the name value.
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**Method: getDescription
	 * Getter method for this instances's description
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**Method: setDescription
	 * Setter method for setting the description value.
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**Method: isSolved
	 * Getter method for this instances's isSolved
	 * @return the isSolved
	 */
	public boolean isSolved()
	{
		return isSolved;
	}

	/**Method: setSolved
	 * Setter method for setting the isSolved value.
	 * @param isSolved the isSolved to set
	 */
	public void setSolved(boolean isSolved)
	{
		this.isSolved = isSolved;
	}
	
	/**Method: setSolved
	 * Setter method for setting the isSolved value.
	 * @param isSolved the isSolved to set
	 */
	public void setSolved(String isSolved)
	{
		this.isSolved = Boolean.parseBoolean(isSolved);
	}

	/**Method: getMonster
	 * Getter method for this instances's monster
	 * @return the monster
	 */
	public GameMonster getMonster()
	{
		return monster;
	}

	/**Method: setMonster
	 * Setter method for setting the monster value.
	 * @param monster the monster to set
	 */
	public void setMonster(GameMonster monster)
	{
		this.monster = monster;
	}

	/**Method: getPuzzle
	 * Getter method for this instances's puzzle
	 * @return the puzzle
	 */
	public GamePuzzle getPuzzle()
	{
		return puzzle;
	}

	/**Method: setPuzzle
	 * Setter method for setting the puzzle value.
	 * @param puzzle the puzzle to set
	 */
	public void setPuzzle(GamePuzzle puzzle)
	{
		this.puzzle = puzzle;
	}

	/**Method: getItem
	 * Getter method for this instances's item
	 * @return the item
	 */
	public GameItem getItem()
	{
		return item;
	}

	/**Method: setItem
	 * Setter method for setting the item value.
	 * @param item the item to set
	 */
	public void setItem(GameItem item)
	{
		this.item = item;
	}

	/**Method: toString
	 * @see java.lang.Object#toString()
	 * Prints out all assets of a Game Room
	 */
	@Override
	public String toString()
	{
		return "\nRoom:\t" + name + "\nDescription:\t" + description
				+ "\nSolved:\t" + isSolved + "\nMonster:\n" + monster
				+ "\nPuzzle:\n" + puzzle + "\nItem:\t" + item + "";
	}
	
	/*public static void main(String[] args)
	{
		GameRoom room = new GameRoom("Locker Room", "Smelly Socks", false, null, null, null);
		System.out.println(room);
	}*/
	
}
