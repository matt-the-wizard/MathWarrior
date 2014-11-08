package math.warrior.model;
/**Class: Monster.java
 * @author: Quan Tran
 * @version 1.0
 * Date Written/Updated: Nov 07, 2014
 * This is the basic information for a monster. Maps all attributes stored in database to the monster
 * entity and shares attributes from game character. 
 */

public class GameMonster extends GameCharacter 
{
	//Attributes
	private String desciption, attackDesciption;

	/**Constructor
	 * @param name:  name of the monster
	 * @param description:  description of the monster
	 * @param healthPoints :  HP of the monster
	 * @param strength:  strength of the monster
	 * @param attackDesciption: attack of the monster
	 */
	public GameMonster(String name, String description, String healthPoints, String strength, String attackDesciption)
	{
		super(name, healthPoints, strength);
		this.desciption=description;
		this.attackDesciption=attackDesciption;
	}


	/**Method: getDesciption
	 * Getter method for this instances's desciption
	 * @return the desciption
	 */
	public String getDesciption()
	{
		return desciption;
	}

	/**Method: setDesciption
	 * Setter method for this instances's desciption
	 * @param desciption the desciption to set
	 */
	public void setDesciption(String desciption) 
	{
		this.desciption = desciption;
	}


	/**Method: getAttackDesciption
	 * Getter method for this instances's attackDesciption
	 * @return the attackDesciption
	 */
	public String getAttackDesciption() 
	{
		return attackDesciption;
	}

	/**Method: setAttackDesciption
	 * Setter method for this instances's attackDesciption
	 * @param attackDesciption the attackDesciption to set
	 */

	public void setAttackDesciption(String attackDesciption) 
	{
		this.attackDesciption = attackDesciption;
	}

	/**Method: toString
	 * @see java.lang.Object#toString()
	 * Prints out Monster object.
	 */
	@Override
	public String toString()
	{
		return super.toString() + "\nDescription:\t" + desciption + "\nAttack Description:\t"
				+ attackDesciption;
	} 


}
