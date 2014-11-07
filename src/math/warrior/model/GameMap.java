/**Class: GameMap.java
 * @author: Brock Bearchell
* @version 1.0
 * Date Written/Updated: Nov 6, 2014
 * Class Description:Game Map that as of right now is Super bare bones.
 */


public class GameMap
{
	public static void main(String[] args)
	{
		//creates the 2d array of rooms
		String[][] mathRooms = new String[10][5];
		mathRooms[0][0] = "Monster 1";
		mathRooms[9][4] = "Puzzle 1";
		for (int i = 0; i < mathRooms.length; i++) 
		{

		    // Loop and display sub-arrays.
		    String[] sub = mathRooms[i];
		    for (int x = 0; x < sub.length; x++) {
			System.out.print(sub[x] + " ");
		    }
		    System.out.println();
		}
	}
	
	
}
