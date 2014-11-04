package math.warrior.model;

/**Class: GameCharacter.java
 * @author: Matthew Berger
* @version 1.0
 * Date Written/Updated: Oct 17, 2014
 * Class Description: This class is an abstract class that will parent the game
 * player and game monster classes as they can share attributs and operations. 
 * This class sets up the base model of a game character focusing on general 
 * data and use. 
 */
public abstract class GameCharacter
{
	//Attributes
	protected String name;
	protected int healthPoints;
	protected int strength;
	
	/**Constructor
	 * @param name Name of the game character
	 * @param healthPoints Health Points the character has
	 * @param strength The strength points the character has
	 */
	public GameCharacter(String name, String healthPoints, String strength)
	{
		this.setName(name);
		this.setHealthPoints(healthPoints);
		this.setStrength(strength);
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

	/**Method: getHealthPoints
	 * Getter method for this instances's healthPoints
	 * @return the healthPoints
	 */
	public int getHealthPoints()
	{
		return healthPoints;
	}

	/**Method: setHealthPoints
	 * Setter method for setting the healthPoints value.
	 * @param healthPoints the healthPoints to set
	 */
	public void setHealthPoints(int healthPoints)
	{
		this.healthPoints = healthPoints;
	}
	
	/**Method: setHealthPoints
	 * Setter overloaded method for setting the healthPoints value.
	 * @param healthPoints the healthPoints to set
	 */
	public void setHealthPoints(String healthPoints)
	{
		try
		{
			this.healthPoints = Integer.parseInt(healthPoints);
		}
		catch(NumberFormatException nfe)
		{
			this.healthPoints = 50;
			System.out.println("Could not read in health points correctly. Setting a default value.");
		}
	}

	/**Method: getStrength
	 * Getter method for this instances's strength
	 * @return the strength
	 */
	public int getStrength()
	{
		return strength;
	}

	/**Method: setStrength
	 * Setter method for setting the strength value.
	 * @param strength the strength to set
	 */
	public void setStrength(int strength)
	{
		this.strength = strength;
	}
	
	/**Method: setStrength
	 * Setter method for setting the strength value.
	 * @param strength the strength to set
	 */
	public void setStrength(String strength)
	{
		try
		{
			this.strength = Integer.parseInt(strength);
		}
		catch(NumberFormatException nfe)
		{
			this.strength = 5;
			System.out.println("Could not read in strength points correctly. Setting a default value.");
		}
	}

	/**Method: toString
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "\nName:\t" + name + "\nHealth Points:\t" + healthPoints
				+ "\nStrength:\t" + strength + "";
	}
	
}
