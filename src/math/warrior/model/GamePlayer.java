package math.warrior.model;

/**Class: GamePlayer.java
 * @author: Matthew Berger
 * @version 1.0
 * Date Written/Updated: Oct 17, 2014
 * Class Description:
 */
public class GamePlayer extends GameCharacter
{
	//Attributes
	private GameItem[] inventory;
	private int score;
	private static boolean gameOver;
	private static final int MAX_INVENTORY_SPACE = 5;

	/**Constructor
	 * @param name Name of the game player
	 * @param healthPoints Health Points of the player
	 * @param strength Strength of the player
	 */
	public GamePlayer(String name, String healthPoints, String strength, GameItem[] inventory, String score)
	{
		super(name, healthPoints, strength);
		this.setInventory(inventory);
		this.setScore(score);
	}

	/**Method: getInventory
	 * Getter method for this instances's inventory
	 * @return the inventory
	 */
	public GameItem[] getInventory()
	{
		return inventory;
	}

	/**Method: setInventory
	 * Setter method for setting the inventory value.
	 * @param inventory the inventory to set
	 */
	public void setInventory(GameItem[] inventory)
	{
		this.inventory = inventory;
	}

	/**Method: getScore
	 * Getter method for this instances's score
	 * @return the score
	 */
	public int getScore()
	{
		return score;
	}

	/**Method: setScore
	 * Setter method for setting the score value.
	 * @param score the score to set
	 */
	public void setScore(int score)
	{
		this.score = score;
	}

	/**Method: setScore
	 * Setter method overloaded for setting the score of the player.
	 * @param score the score to set
	 */
	public void setScore(String score)
	{
		try
		{
			this.score = Integer.parseInt(score);
		}
		catch(NumberFormatException nfe)
		{
			this.score = 0;
			System.out.println("Score data is not valid. Defaulted score to 0.");
		}
	}
	
	//add equip item
	//add drop item
	//add use item
	//add simulateBattle
	//add toString
	//any other methods

}
