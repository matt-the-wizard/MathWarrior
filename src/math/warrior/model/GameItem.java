package math.warrior.model;

/**Class: GameItem.java
 * @author: Matthew Berger
* @version 1.0
 * Date Written/Updated: Oct 16, 2014
 * Class Description:Game Item that has a name, value, description, and type. 
 * Business Class that sets up the data for a Game Item. References an enum in the 
 * package named ItemType as an attribute. 
 */
public class GameItem
{

	//Attributes
	private String name;
	private int value;
	private String description;
	private ItemType type;
	
	/**Constructor
	 * @param name Name of the game item
	 * @param value Value of the game item
	 * @param description Description of the game item
	 * @param type The item type, weapon, armor, or item
	 */
	public GameItem(String name, String value, String description, String type)
	{
		this.setName(name.trim());
		this.setValue(value.trim());
		this.setDescription(description.trim());
		this.setType(type.trim());
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

	/**Method: getValue
	 * Getter method for this instances's value
	 * @return the value
	 */
	public int getValue()
	{
		return value;
	}

	/**Method: setValue
	 * Setter method for setting the value value.
	 * @param value the value to set
	 */
	public void setValue(int value)
	{
		this.value = value;
	}
	
	/**Method: setValue
	 * Setter overloaded method to handle text being assigned to this game 
	 * items value. 
	 * @param value The value to set.
	 */
	public void setValue(String value)
	{
		try 
		{
			this.value = Integer.parseInt(value);
		}
		catch(NumberFormatException nfe)
		{
			this.value = 5;
			System.out.println("Error occured when creating game item " + this.name + " Invalid value for game item. Setting a default value.");
		}
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

	/**Method: getType
	 * Getter method for this instances's type
	 * @return the type
	 */
	public ItemType getType()
	{
		return type;
	}

	/**Method: setType
	 * Setter method for setting the type value.
	 * @param type the type to set
	 */
	public void setType(ItemType type)
	{
		this.type = type;
	}
	
	/**Method: setType
	 * Setter overloaded method for setting the type of the game item. 
	 * @param type The game item's type. 
	 */
	public void setType(String type)
	{
		try
		{
			this.type = ItemType.valueOf(type);
		} 
		catch (IllegalArgumentException iae)
		{
			this.type = ItemType.ITEM;
			System.out.println("Invalid item type, defaulting to type ITEM");
		}
	}

	/**Method: toString
	 * @see java.lang.Object#toString()
	 * Displays game item data in a text format. 
	 */
	@Override
	public String toString()
	{
		return "\nItem:\t" + name + "\nValue:\t" + value + "\nDescription:\t"
				+ description + "\nType:\t" + type + ".\n";
	}
	
	/**
	public static void main(String[] args)
	{
		GameItem item = new GameItem("Fire Sword", "5", "The sword of fire weilded by the archmage of ferelden. Uses immense fire damage.", "Sally");
		System.out.println(item);
	}**/
	
	
}
