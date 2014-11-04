package math.warrior.model;

import java.util.Arrays;
import java.util.Random;

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
	private int maxHealth;
	private int score;
	public static boolean gameOver;
	public static final int MAX_INVENTORY_SPACE = 5;

	/**Constructor
	 * @param name Name of the game player
	 * @param healthPoints Health Points of the player
	 * @param strength Strength of the player
	 */
	public GamePlayer(String name, String healthPoints, String maxHealth, String strength, GameItem[] inventory, String score)
	{
		super(name, healthPoints, strength);
		this.setInventory(inventory);
		this.setScore(score);
		this.setMaxHealth(maxHealth);
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

	/**Method: getMaxHealth
	 * Getter method for this instances's maxHealth
	 * @return the maxHealth
	 */
	public int getMaxHealth()
	{
		return maxHealth;
	}

	/**Method: setMaxHealth
	 * Setter method for setting the maxHealth value.
	 * @param maxHealth the maxHealth to set
	 */
	public void setMaxHealth(int maxHealth)
	{
		this.maxHealth = maxHealth;
	}

	/**Method: setMaxHealth
	 * Overloaded Setter method for setting the maxHealth value.
	 * @param maxHealth the maxHealth to set
	 */
	public void setMaxHealth(String maxHealth)
	{
		try 
		{
			this.maxHealth = Integer.parseInt(maxHealth);
		}
		catch(NumberFormatException nfe)
		{
			this.maxHealth = 100;
			System.out.println("Maximum health data is not valid. Defaulting to 100.");
		}
	}

	/**Method: equipItem
	 * This method handles how the game item affects the players stats after it is added to inventory. 
	 * @param item the game item to equip.
	 * @return The message displaying how the players stats were changed based on the equipped item.
	 */
	private String equipItem(GameItem item)
	{
		if (item.getType() == ItemType.WEAPON) //Weapon type adds to the strengh of your character.
		{
			this.strength += item.getValue();
			return this.name + " strength has increased to " + this.strength;
		}
		else if (item.getType() == ItemType.ARMOR) //Armor type allows maximum health allowed to be increased. 
		{
			this.maxHealth += item.getValue();
			return this.name + " maximum health limit has increased to " + this.maxHealth;
		}
		else //Type is Item, so add health points back but do not exceed max health limit allowed.
		{
			this.healthPoints += item.getValue();
			if (this.healthPoints > this.maxHealth)
			{
				this.healthPoints = this.maxHealth;
				return "Health points restored. Max health limit exceeded, restoring health to maximum limit of " + this.healthPoints;
			}
			else
			{
				return "Health points restored. Number of health points left is " + this.healthPoints;
			}
		}
	}

	/**Method: equipItem
	 * This method will add the passed in GameItem if there is an empty slot found in inventory. 
	 * When the item is added, based on the item type, it will be equipped and change the stats of the player.
	 * @param item The item to be equipped to the character. 
	 * @return Display message detailing if the game item was added successcully. 
	 */
	public String addItem(GameItem item)
	{
		boolean notAdded = true;
		int index = 0;
		String equippedMessage = "";
		for (index = 0; index < this.inventory.length && notAdded; index++)
		{
			if (this.inventory[index] == null)
			{
				this.inventory[index] = item;
				equippedMessage = this.equipItem(item);
				notAdded = false;
			}
		}
		if (notAdded)
		{
			System.out.println("Game Item " + item + " was added to inventory at slot " + index);
			return "Game Item " + item + " was added to inventory at slot " + index + "\n" + equippedMessage;
		}
		else
		{
			System.out.println("Game Item " + item + " was NOT added to inventory because inventory is full.");
			return "Game Item " + item + " was NOT added to inventory because inventory is full."+ "\n" + equippedMessage;
		}
	}

	/**Method: dropItem
	 * This method will loop through inventory and drop the requested item 
	 * if it is found in inventory. Will return a message depicting if the item was 
	 * dropped or not. 
	 * @param item The game item to be dropped
	 * @return The result of trying to drop the game item. 
	 */
	public String dropItem(GameItem item)
	{
		boolean notDropped = true;
		int index = 0;
		String equippedMessage = "";
		for (index = 0; index < this.inventory.length && notDropped; index++)
		{
			if (this.inventory[index].getName().equals(item.getName()))
			{
				this.inventory[index] = null;
				notDropped = false;
			}
		}
		if (notDropped)
		{
			return "The item " + item + " could not be found in inventory and was not dropped.";
		}
		else
		{
			return "The item " + item + " was removed from inventory.";
		}
	}

	/**Method: toString
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		String itemStats = "";
		for (GameItem item: inventory)
		{
			itemStats += item.toString() + "\n";
		}
		return super.toString() + "\n"
				+ "Max Health:\t" + maxHealth + "\nScore\t" + score + "\nInventory:\n" + itemStats;
	}
	
	/**public static void main(String[] args)
	{
		GameItem item = new GameItem("Fire Sword", "100", "Sword on fire.", "WEAPON");
		GameItem[] items = {item};
		GamePlayer player = new GamePlayer("Matt", "100", "150", "5", items, "0");
		player.addItem(item);
		System.out.println(player);
	}*/
	
	

}
