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
				+ " join Monsters on Rooms.RoomID = Monsters.MID "
				+ " join Players on Rooms.PlayerID = Players.PlayerID "
				+ " where Players.PlayerID = " + playerID);
		try
		{
			while(set.next())
			{
				//System.out.println(set.getString("RoomName"));
				String roomName = set.getString("RoomName");
				String roomDescription = set.getString("RoomDescription");
				String solved = set.getString("Solved");
				String monsterName = set.getString("MonsterName");
				String monsterDescription = set.getString("MonsterDescription");
				String monsterStrength = set.getString("MonsterStrength");
				String monsterHealth = set.getString("MonsterHealth");
				String attackDescription = set.getString("MonsterAttackDescription");
				String password = set.getString("Password");
				String playerName = set.getString("PlayerName");
				GameMonster monster = new GameMonster(monsterName, monsterDescription, monsterHealth, monsterStrength, attackDescription);
				GameRoom room = new GameRoom(roomName, roomDescription, solved, monster, null, null);
				System.out.println(room + "\n");
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
