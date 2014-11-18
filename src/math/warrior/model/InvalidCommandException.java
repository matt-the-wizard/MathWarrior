package math.warrior.model;

/**Class: InvalidCommandException.java
 * @author: Brock Bearchell
* @version 1.0
 * Date Written/Updated: Nov 17, 2014
 * Class Description:Exception class that throws invalid entered commands.
 */

public class InvalidCommandException extends RuntimeException
{
		/**
		 * Constructor
		 *Used to instantiate the exception with the correct error message.
		 */
	 	public InvalidCommandException()
	 	{
	 		super("Invalid Input. Please Enter A Valid Command.");
	 	}
	 	
	 	/**Used for testing.
	 	 * public static void main(String[] args)
		{
			InvalidCommandException tester = new InvalidCommandException();
			System.out.println(tester);
		}*/
	 
}
