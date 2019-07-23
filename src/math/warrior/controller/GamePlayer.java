package math.warrior.controller;

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
	public static final int MAX_INVENTORY_SPACE = 5;
	private int id;

	/**Constructor
	 * @param name Name of the game player
	 * @param healthPoints Health Points of the player
	 * @param maxHealth Maximum Health of the player
	 * @param strength Strength of the player
	 * @param inventory Inventory of game items.
	 * @param score The player's current game score
	 * @param newID The id associated to the player in the db
	 */
	public GamePlayer(String name, String healthPoints, String maxHealth, String strength, GameItem[] inventory, String score, String newID)
	{
		super(name, healthPoints, strength);
		this.setInventory(inventory);
		this.setScore(score);
		this.setMaxHealth(maxHealth);
		this.setId(newID);
	}

	/**Method: getId
	 * Getter method for this instances's id
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}

	/**Method: setId
	 * Setter method for setting the id value.
	 * @param id the id to set
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**Method: setId
	 * Setter method for setting the id value.
	 * @param id the id to set
	 */
	public void setId(String id)
	{
		try
		{
			this.id = Integer.parseInt(id);
		}
		catch(NumberFormatException nfe)
		{
			this.id = 1;
		}
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
		return "The item is not armor or weapon and must be used, not equipped.";
	}

	/**Method: addItem
	 * This method will add the item to inventory. Then the item will be equipped to the player and change the values of 
	 * the player based on the type of item. The item must be ARMOR or WEAPON to be equipped. 
	 * @param item The item to be equipped to the character. 
	 * @return Display message detailing if the game item was added successfully. 
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
			//System.out.println("Game Item " + item + " was NOT added to inventory at slot " + index);
			return "\n" + item + " was NOT added to inventory. Maximum capacity is filled in inventory.\n";
		}
		else
		{
			//System.out.println("Game Item " + item + " was added to inventory because inventory is full.");
			return "\n" + item + " was added to inventory."+ "\n" + equippedMessage;
		}
	}

	/**Method: dropItem
	 * This method will loop through inventory and drop the requested item 
	 * if it is found in inventory. Will return a message depicting if the item was 
	 * dropped or not. 
	 * @param item The game item to be dropped
	 * @return The result of trying to drop the game item. 
	 */
	public String dropItem(String item)
	{
		boolean notDropped = true;
		int index = 0;
		try
		{
			for (index = 0; index < this.inventory.length && notDropped; index++)
			{
				if (this.inventory[index].getName().toLowerCase().contains(item.toLowerCase()))
				{
					this.inventory[index] = null;
					notDropped = false;
				}
			}
		}
		catch(NullPointerException npe)
		{
			//do nothing if null, boolean below handles return choice.
		}
		if (notDropped)
		{
			return "\n" + item.toUpperCase() + " could not be found in inventory and was not dropped.";
		}
		else
		{
			return "\n" + item.toUpperCase() + " was removed from inventory.";
		}
	}

	/**Method: useItem
	 * This method will use a game item on the player if it is type ITEM. This will allow the 
	 * player to recover health points by using the item.
	 * @param item The item to use.
	 * @return A display message depicting the outcome of the method call when using the item. 
	 */
	public String useItem(String item)
	{
		boolean notUsed = true;
		int index = 0;
		try
		{
			for (index = 0; index < this.inventory.length && notUsed; index++)
			{
				if (this.inventory[index].getName().toLowerCase().contains(item.toLowerCase()))
				{
					if (this.inventory[index].getType() == ItemType.ITEM)
					{
						this.healthPoints += this.inventory[index].getValue();
						if (this.healthPoints > this.maxHealth)
							this.healthPoints = this.maxHealth;
						this.inventory[index] = null;
						notUsed = false;
					}
				}
			}
		}
		catch(NullPointerException npe)
		{
			//do nothing if null, boolean below handles return choice.
		}
		if (notUsed)
		{
			return "\n" + item.toUpperCase() + " was NOT used. The item cannot be armor or a weapon and must be held in inventory.";
		}
		else
		{
			return "\n" + item.toUpperCase() + " was used. Remaining health points is now " + this.healthPoints;
		}
	}

	/**Method: fightMonster
	 * This method simulates the fight between the player and a monster.
	 * @param monster The game monster
	 * @return A display message showing the results of the battle. 
	 */
	public String fightMonster(GameMonster monster)
	{
		monster.setHealthPoints(monster.getHealthPoints() - this.strength);
		this.setHealthPoints(this.healthPoints - monster.getStrength());
		return "\n" + this.name + " attacked monster " + monster.getName() + ".\n "
		+ "The monsters remaining health points is " + monster.getHealthPoints() + ".\n" + 
		monster.getAttackDesciption() + ".\nThe player's remaining health points is " + this.healthPoints;
	}

	/**Method: toString
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		String inventoryInfo = "";
		for (GameItem element: this.inventory)
		{
			if (element != null)
				inventoryInfo += element.toString() + "\n";
		}
		return super.toString()
				+ "\nID:\t" + this.id + "\nMax Health:\t" + maxHealth + "\nScore\t" + score + "\nInventory:\n" + inventoryInfo;
	}
}
