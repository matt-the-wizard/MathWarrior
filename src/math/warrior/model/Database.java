package math.warrior.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

/**Class: Database.java
 * @author: Matthew Berger
* @version 1.0
 * Date Written/Updated: Oct 28, 2014
 * Class Description: SQLite Database that stores all information for the game. 
 * The database establishes the connection and then allows update and execute 
 * queries to be sent to the already existing tables in the database:
 * Rooms, Players, Monsters, Puzzles, Items etc.
 * This will allow storing and saving data as well as letting the user change data in 
 * the database for the creation mode. Where this class is implemented and operations
 * are called is where the handling of returned data will occur. 
 */
public class Database
{
	//Attributes
	private Connection connection;
	private Statement statement;
	private final String DATABASE_NAME = "GameDatabase";
	private final String JDBC =  "jdbc:sqlite";
	private ArrayList<GameRoom> roomsAL; //List of rooms
	//List of rooms contents to keep track of rooms that hold a monster, item, or puzzle.
	//Used to determine empty rooms. Does this by keeping track of ID's in the database, 
	//this is not very effective at all but for now it is better than nothing. 
	private ArrayList<Integer> roomContentAL; 

	/**
	 * Constructor
	 * Establishes the connection to the SQLite Database by first finding the 
	 * Driver, then the package in the JAR, and then the Database file outside the 
	 * src directory. 
	 */
	public Database(int playerID)
	{
		//Checks JAR to make sure the driver class is found.
		String sDriver = "org.sqlite.JDBC";
		try
		{
			Class.forName(sDriver);
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println("Could not find JAR containing SQLite package and driver.");
		}
		String dbURL = JDBC + ":" + DATABASE_NAME;
		try
		{
			connection = DriverManager.getConnection(dbURL);
			statement = connection.createStatement();
		}
		catch(SQLException sqle)
		{
			System.out.println("Could not establish a good connection with the database. ");
		}
		this.roomsAL = new ArrayList<GameRoom>();
		this.roomContentAL = new ArrayList<Integer>();
	}

	/**Method: executeQuery
	 * This method allows SQL queries to be sent to the database and the results
	 * returned. The implementer will have to handle the returned data based on the 
	 * query they passed in. 
	 * @param query The data wanted retrieved from the database
	 * @return Result Set containing records of the requested data. 
	 */
	public ResultSet executeQuery(String query)
	{
		ResultSet result = null;
		try
		{
			result = this.statement.executeQuery(query);
			return result;
		} 
		catch (SQLException sqle)
		{
			System.out.println("Could not execute query to DBMS.");
			return result;
		}
	}
	
	/**Method: updateDatabase
	 * This method allows updates to be sent to the database using update table. 
	 * @param sql The sql statement to be sent.
	 * @return true or false depicting if the update was a success.
	 */
	public boolean updateDatabase(String sql)
	{
		boolean success = true;
		try
		{
			this.statement.executeUpdate(sql);
		} 
		catch (SQLException e)
		{
			System.out.println("Could not update the database with new information.");
			success = false;
		}
		return success;
	}
	
	/**Method: loadMonsterRooms
	 * This loads all rooms from the database that hold a monster. 
	 * @param playerID The id associated to the current player. 
	 */
	private void loadMonsterRooms(int playerID)
	{	
		ResultSet set = this.executeQuery(""
				+ "select  * from Rooms "
				+ " join Monsters on Rooms.RoomID = Monsters.MID "
				+ " join Players on Rooms.PlayerID = Players.PlayerID "
				+ " where Players.PlayerID = " + playerID);
		try
		{
			while(set.next())
			{
				int roomContent = Integer.parseInt(set.getString("RoomID"));
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
				this.roomsAL.add(room);
				this.roomContentAL.add(roomContent);
			}
		} 
		catch (SQLException e)
		{
			System.out.println("Could not create rooms with MONSTERS correctly.");
		}
	}

	/**Method: loadItemRooms
	 * This loads all rooms from the database that hold items. 
	 * @param playerID The id associated to the current player playing the game. 
	 */
	private void loadItemRooms(int playerID)
	{
		ResultSet set = this.executeQuery(""
				+ "select  * from Rooms "
				+ " join Items on Rooms.RoomID = Items.ItemID "
				+ " join Players on Rooms.PlayerID = Players.PlayerID "
				+ " where Players.PlayerID = " + playerID);
		try
		{
			while(set.next())
			{
				int roomContent = Integer.parseInt(set.getString("RoomID"));
				String roomName = set.getString("RoomName");
				String roomDescription = set.getString("RoomDescription");
				String solved = set.getString("Solved");
				String itemName = set.getString("ItemName");
				String type = set.getString("Type");
				String description = set.getString("ItemDescription");
				String value = set.getString("Value");
				String password = set.getString("Password");
				String playerName = set.getString("PlayerName");
				GameItem item = new GameItem(itemName, value, description, type);
				GameRoom room = new GameRoom(roomName, roomDescription, solved, null, null, item);
				System.out.println(room + "\n");
				this.roomsAL.add(room);
				this.roomContentAL.add(roomContent);
			}
		} 
		catch (SQLException e)
		{
			System.out.println("Could not create rooms with ITEMS correctly.");
		}
	}

	/**Method: loadPuzzleRooms
	 * This method loads all rooms from the database that contain puzzles. 
	 * @param playerID The id associated to the current player. 
	 */
	private void loadPuzzleRooms(int playerID)
	{	
		ResultSet set = this.executeQuery(""
				+ "select  * from Rooms "
				+ " join Puzzles on Rooms.RoomID = Puzzles.PID "
				+ " join Players on Rooms.PlayerID = Players.PlayerID "
				+ " where Players.PlayerID = " + playerID);
		try
		{
			while(set.next())
			{
				int roomContent = Integer.parseInt(set.getString("RoomID"));
				String roomName = set.getString("RoomName");
				String roomDescription = set.getString("RoomDescription");
				String solved = set.getString("Solved");
				String puzzleDescription = set.getString("PuzzleDescription");
				String hint = set.getString("PuzzleHint");
				String terminator = set.getString("PuzzleTerminator");
				String solvedMessage = set.getString("PuzzleSolvedMessage");
				String password = set.getString("Password");
				String playerName = set.getString("PlayerName");
				GamePuzzle puzzle = new GamePuzzle(puzzleDescription, terminator, hint, solvedMessage);
				GameRoom room = new GameRoom(roomName, roomDescription, solved, null, puzzle, null);
				System.out.println(room + "\n");
				this.roomsAL.add(room);
				this.roomContentAL.add(roomContent);
			}
		} 
		catch (SQLException e)
		{
			System.out.println("Could not create rooms with PUZZLES correctly.");
		}
	}

	/**Method: loadExtraRooms
	 * This loads all rooms from the database that do not contain items or monsters. 
	 * @param playerID The id associated to the current player playing the game. 
	 */
	private void loadExtraRooms(int playerID)
	{	
		ResultSet roomSet = this.executeQuery(""
				+ "select  * from Rooms "
				+ " join Players on Rooms.PlayerID = Players.PlayerID "
				+ " where Players.PlayerID = " + playerID 
				+ ";");
		try
		{
			Collections.sort(this.roomContentAL); 
			while(roomSet.next())
			{
				Integer roomID = Integer.parseInt(roomSet.getString("RoomID"));
				if (this.roomContentAL.contains(roomID))
				{
					//do nothing, rooms is not added because we need empty rooms. 
				}
				else
				{
					String roomName = roomSet.getString("RoomName");
					String roomDescription = roomSet.getString("RoomDescription");
					String solved = roomSet.getString("Solved");
					GameRoom room = new GameRoom(roomName, roomDescription, solved, null, null, null);
					System.out.println(room + "\n");
					this.roomsAL.add(room);
				}
			}
		} 
		catch (SQLException e)
		{
			System.out.println("Could not create rooms with NO CONTENTS correctly.");
		}
	}
	
	/**Method: loadRooms
	 * This method gets the ArrayList of Game Rooms loaded from the database.
	 * @param playerID The player id that belongs to the current player playing the game.
	 * @return roomsAL The Array List of rooms used for the game map. 
	 */
	public ArrayList<GameRoom> loadRooms(int playerID)
	{
		this.roomsAL.clear();
		this.roomContentAL.clear();
		this.loadMonsterRooms(playerID);
		this.loadPuzzleRooms(playerID);
		this.loadItemRooms(playerID);
		this.loadExtraRooms(playerID);
		return this.roomsAL;
	}
}
