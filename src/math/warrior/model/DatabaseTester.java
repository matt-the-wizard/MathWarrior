package math.warrior.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;

public class DatabaseTester
{
	private Database database;

	private void loadMonsterRooms(int playerID)
	{	
		ResultSet set = database.executeQuery(""
				+ "select  * from Rooms "
				+ " join Monsters on Rooms.RoomID = Monsters.ID "
				+ " join Players on Rooms.PlayerID = Players.PlayerID "
				+ " where Players.PlayerID = " + playerID);
		try
		{
			while(set.next())
			{
				String roomName = set.getString("RoomName");
				System.out.println(roomName);
				String roomDescription = set.getString("RoomDescription");
				System.out.println(roomDescription);
				//String solved = set.getString("Solved");
				//String monsterName = set.getString("Name");
				//System.out.println(monsterName);
				//String monsterDescription = set.getString("Description");
				//System.out.println(monsterDescription);
				//String monsterStrength = set.getString("Strength");
				//System.out.println(monsterStrength);
				//String monsterHealth = set.getString("Health");
				//String monsterTerminator = set.getString("Terminator");
				//String attackDescription = set.getString("AttackDescription");
				System.out.println(set.getString("Password"));
				System.out.println(set.getString("Monsters.Name"));
				//create monster
				//create room
				//add room to map
			}
		} 
		catch (SQLException e)
		{
			System.out.println("Could not create rooms with monsters correctly.");
		}
	}

	public DatabaseTester()
	{
		this.database = new Database();
	}

	public static void main(String[] args)
	{
		DatabaseTester driver = new DatabaseTester();
		driver.loadMonsterRooms(1);
	}

}
