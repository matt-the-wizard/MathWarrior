/**Class: InvalidCommandException.java
 * @author: Brock Bearchell
* @version 1.0
 * Date Written/Updated: Nov 17, 2014
 * Class Description:Exception class that throws invalid entered commands.
 */

public class InvalidCommandException extends RuntimeException
{
	
	 	public InvalidCommandException()
	 	{
	 		super("Invalid Input. Please Enter A Valid Command.");
	 	}
	 	
	 	public static void main(String[] args)
		{
			InvalidCommandException tester = new InvalidCommandException();
			System.out.println(tester);
		}
	 
}
