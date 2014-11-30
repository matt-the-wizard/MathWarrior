package math.warrior.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import math.warrior.controller.GameItem;
import math.warrior.controller.GameMap;
import math.warrior.controller.GameMonster;
import math.warrior.controller.GamePlayer;
import math.warrior.controller.GamePuzzle;
import math.warrior.controller.GameRoom;

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
	public Database()
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
			this.connection.setAutoCommit(true);
			statement = connection.createStatement();
		}
		catch(SQLException sqle)
		{
			System.out.println("Could not establish a good connection with the database. ");
		}
		this.roomsAL = new ArrayList<GameRoom>();
		this.roomContentAL = new ArrayList<Integer>();
	}

	/**Method: commitConnection
	 * This method commits the connection, making sure any pending queries are sent
	 * to the database. Should be called after every execute or update query.
	 */
	public void commitConnection()
	{
		try
		{
			this.connection.commit();
		}
		catch(SQLException sqle)
		{
			//System.out.println("Could not commit the connection to the database.");
		}
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
			//System.out.println("Could not execute query to DBMS.");
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
			//System.out.println("In update \n" + sql);
			this.statement.executeUpdate(sql);
			//System.out.println("In update \n" + sql);
		} 
		catch (SQLException e)
		{
			//System.out.println("Could not update the database with new information.");
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
				+ " where Players.PlayerID = " + playerID + " and Monsters.PlayerID = " + playerID);
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
				String roomID = set.getString("RID");
				GameMonster monster = new GameMonster(monsterName, monsterDescription, monsterHealth, monsterStrength, attackDescription);
				GameRoom room = new GameRoom(roomName, roomDescription, solved, monster, null, null, roomID);
				//System.out.println(room + "\n");
				this.roomsAL.add(room);
				this.roomContentAL.add(roomContent);
			}
			set.close(); //Make sure to close set every time.
			this.commitConnection();
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
				+ " where Players.PlayerID = " + playerID  + " and Items.PlayerID = " + playerID);
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
				String roomID = set.getString("RID");
				String itemID = set.getString("ItemID");
				GameItem item = new GameItem(itemName, value, description, type, itemID);
				GameRoom room = new GameRoom(roomName, roomDescription, solved, null, null, item, roomID);
				//System.out.println(room + "\n");
				this.roomsAL.add(room);
				this.roomContentAL.add(roomContent);
			}
			set.close();
			this.commitConnection();
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
				+ " where Players.PlayerID = " + playerID  + " and Puzzles.PlayerID = " + playerID);
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
				String roomID = set.getString("RID");
				GamePuzzle puzzle = new GamePuzzle(puzzleDescription, terminator, hint, solvedMessage);
				GameRoom room = new GameRoom(roomName, roomDescription, solved, null, puzzle, null, roomID);
				//System.out.println(room + "\n");
				this.roomsAL.add(room);
				this.roomContentAL.add(roomContent);
			}
			set.close();
			this.commitConnection();
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
					String ID = roomSet.getString("RID");
					GameRoom room = new GameRoom(roomName, roomDescription, solved, null, null, null, ID);
					//System.out.println(room + "\n");
					this.roomsAL.add(room);
				}
			}
			roomSet.close();
			this.commitConnection();
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

	/**Method: loadInventory
	 * This method loads the game items from the inventory table in the database, so the 
	 * player can load stored items.
	 * @param playerID Id associated to the current player.
	 * @return array of GameItem objects. 
	 */
	public GameItem[] loadInventory(int playerID)
	{
		GameItem[] inventory = new GameItem[GamePlayer.MAX_INVENTORY_SPACE];
		ResultSet set = this.executeQuery("select * from Inventory " + 
				"where Inventory.PlayerID = " + playerID);
		try
		{
			int count = 0;
			while(set.next())
			{
				String itemName = set.getString("ItemName");
				String type = set.getString("Type");
				String description = set.getString("ItemDescription");
				String value = set.getString("Value");
				String itemID = set.getString("InventoryID");
				inventory[count] = new GameItem(itemName, value, description, type, itemID);
				count++;
			}
			set.close();
			this.commitConnection();
			return inventory;
		} 
		catch (SQLException e)
		{
			System.out.println("Item inventory could not be loaded correctly.");
		}
		return inventory;
	}

	/**Method: loadPlayerId
	 * This obtains the players id based on their password. 
	 * @param password The password belonging to the player.
	 * @return The player id.
	 */
	public int loadPlayerId(String password)
	{
		int id = 0;
		ResultSet set = this.executeQuery("select * from Players where Players.Password like '" + password + "'");
		try
		{
			if (set.next())
			{
				id = Integer.parseInt(set.getString("PlayerID"));
				return id;
			}
			set.close();
		} 
		catch (SQLException e)
		{
			System.out.println("Player could not be found.");
		}
		return id;
	}

	/**Method: loadGamePlayer
	 * This loads the player from the database based on the player id.
	 * Then uses the data read in to create a GamePlayer object. 
	 * @param playerID The id associated to the player.
	 * @return the player as a GamePlayer object
	 */
	public GamePlayer loadGamePlayer(int playerID)
	{
		GameItem[] inventory = this.loadInventory(playerID);
		GamePlayer player = null;
		ResultSet set = this.executeQuery("select * from Players where PlayerID = " + playerID);
		this.commitConnection();
		try
		{
			if (set.next())
			{
				String name = set.getString("PlayerName");
				String maxHealth = set.getString("MaximumHealth");
				String healthPoints = set.getString("HealthPoints");
				String strength = set.getString("Strength");
				String score = set.getString("Score");
				String id = set.getString("PlayerID");
				player = new GamePlayer(name, healthPoints, maxHealth, strength, inventory, score, id);
				//System.out.println("returning player");
				set.close();
				return player;
			}
		} 
		catch (SQLException e)
		{
			System.out.println("Player could not be created from the database.");
		}
		return player;
	}

	/**Method: loadGameMap
	 * This loads the rooms from the database as well as the location of the player.
	 * The data read is used to make a GameMap object.
	 * @param playerID The id associated to the player.
	 * @return The game map.
	 */
	public GameMap loadGameMap(int playerID)
	{
		GameMap map = null;
		ResultSet set = this.executeQuery("select * from Location" + " join Players on Location.LocationID = Players.PlayerID  "
				+ "where Players.PlayerID = " + playerID);	
		String xPos = "";
		String yPos = "";
		try
		{
			if (set.next())
			{
				xPos = set.getString("XPos");
				yPos = set.getString("YPos");
			}

			//System.out.println("XPos = " + xPos + " YPos = " + yPos);
			set.close();
		} 
		catch (SQLException e)
		{
			System.out.println("Game Map could not be created from the database.");
		}
		ArrayList<GameRoom> rooms = this.loadRooms(playerID);
		map = new GameMap(rooms, xPos, yPos);
		//System.out.println("Map location in gameMapLoad "+ map);
		return map;
	}

	/**Method: genRoomRecordsForNewPlayer
	 * This method generates rooms in the game database for a new player.
	 * It simply executes a query with a bunch of insert into statements to put records in the Rooms table.
	 * @param playerID The id associated to the player.
	 */
	public void genRoomRecordsForNewPlayer(int playerID)
	{
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (1, \'Gymnasium\', \'The school gym. You notice cracked concreate floors and spiderwebs covering the windows.\', " + playerID + ", \'false\') ");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (2, \'Cafeteria\', \'Cafeteria where shattered plates of fried chicken and green goo decorate the floor and walls.\', " + playerID + ", \'false\');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (3, 'Principles Office', 'Office of the school principle where documents are scattered everywhere.', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (4, 'Locker Room', 'The stench of football equipment fills the air as you enter the school locker room. Who knows what lies buried in lockers.', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (5, 'Attendance Office', 'Office where attendance slips were filed. Everything is out of place and file cabinets are turned over.', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (6, 'Hallway', 'A hallway filled with cobwebs and shadows flickering in the distance. Green goo covers the ceiling and spiders crawl on the ground.', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (7, 'Hallway', 'A hallway filled with cobwebs and shadows flickering in the distance. Green goo covers the ceiling and spiders crawl on the ground.', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (8, 'Hallway', 'A hallway dimly lit with a chill wind blowing through it. You hear clanking of metal instruments in the distance.', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (9, 'History Classroom', 'Classroom where history lessons were taught. A giant skeletion head has replaced where the globe stood on the teachers desk.', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (10, 'Counseling Office', ' A skeleton lies on the floor of the room unmoving. Besides that, the room looks pretty normal. Oh wait, a witches cauldron is on the desk, never mind.', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (11, 'Bleachers', 'Rusty old metal benches by the football field. ', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (12, 'Janitors Closet', 'The closet that houses cleaning supplies in the school. Mysterious sounds were always heard coming from this room.', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (13, 'Bathroom', 'The school bathroom, flooded with nasty water. All the mirrors are broken and an air vent is torn down from the ceiling.', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (14, 'Hallway', 'A hallway filled with cobwebs and shadows flickering in the distance. Green goo covers the ceiling and spiders crawl on the ground.', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (15, 'English Classroom', 'A classroom where lessons were taught. All the books looked as if they were piled against the door, to keep something from coming in.', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (16, 'Hallway', 'A wide, large hallway decorated with cobblestones. Claw marks etched into the stone lead the way down the center. ', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (17, 'Parking Lot', 'Lot where all the vehicles were parked. Only one school bus remains and it is covered in help signs written in black spray paint. ', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (18, 'Hallway', 'A hallway dimly lit with a chill wind blowing through it. You hear clanking of metal instruments in the distance.', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (19, 'Stairs', 'These stairs lead to other floors and rooms in the school. Watch your step, they are slimy and crawling with bugs.', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (20, 'Hallway', 'A wide, large hallway decorated with cobblestones. Claw marks etched into the stone lead the way down the center. ', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (21, 'Hallway', 'A hallway filled with cobwebs and shadows flickering in the distance. Green goo covers the ceiling and spiders crawl on the ground.', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (22, 'Softball Field', 'Field covered in treaded grass from football cleats. The ground looks ripped up as if lots of people were running on it. ', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (23, 'Baseball Field', 'Field covered in treaded grass from football cleats. The ground looks ripped up as if lots of people were running on it. ', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (24, 'Hallway', 'A hallway dimly lit with a chill wind blowing through it. You hear clanking of metal instruments in the distance.', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (25, 'Theatre', 'The school theatre with a huge amount of props lying about. ', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (26, 'Football Field', 'Field covered in treaded grass from cleats. The ground looks ripped up as if lots of people were running on it. ', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (27, 'Soccer Field', 'Field covered in treaded grass from cleats. The ground looks ripped up as if lots of people were running on it. ', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (28, 'Chemistry Classroom', 'A classroom where lessons were taught. All the books looked as if they were piled against the door, to keep something from coming in.', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (29, 'Physics Classroom', 'A classroom where lessons were taught. All the books looked as if they were piled against the door, to keep something from coming in.', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (30, 'Math Classroom', 'A classroom where lessons were taught. All the books looked as if they were piled against the door, to keep something from coming in.', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (31, 'Langauage Room', 'A classroom where lessons were taught. All the books looked as if they were piled against the door, to keep something from coming in.', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (32, 'Band Room', 'Music room full of instruments, however most appear to be broken. Large indents and claw marks have destroyed these. ', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (33, 'Orchestra Room', 'Music room full of instruments, however most appear to be broken. Large indents and claw marks have destroyed these. ', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (34, 'Vice Principals Office', 'Office of the school vice principle where documents are scattered everywhere.', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (35, 'School Store', 'The retail store of the school where clothes were sold. Piles of clothes lay scattered.', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (36, 'Grand Hall', 'The grand hall where ceremonies are held. Floating lit candles cover the room and a cauldron of wiches brew lies alone on the podium.', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (37, 'Garden ', 'A once peaceful garden, this school greenhouse is sickly. All the plants are dying and fungus is growing from their remains. Gravel covers the floor of this cold place.', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (38, 'Forest ', 'The forest behind the school said to be haunted by ghosts. There is a burial graveyard not far from hear, and a mist densely covers the air. ', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (39, 'Track', 'The track surrounding the school. It is littered with trash and green goo puddles from zombie tracks. It looks very ominous. ', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (40, 'Stairs', 'These stairs lead to other floors and rooms in the school. Watch your step, they are slimy and crawling with bugs.', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (41, 'Stairs', 'These stairs lead to other floors and rooms in the school. Watch your step, they are slimy and crawling with bugs.', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (42, 'Hallway', 'A hallway filled with cobwebs and shadows flickering in the distance. Green goo covers the ceiling and spiders crawl on the ground.', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (43, 'Basement', 'The basement, where none have ever ventured. Barely any light lights the way, and cobwebs are everywhere. ', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (44, 'Hallway', 'A hallway dimly lit with a chill wind blowing through it. You hear clanking of metal instruments in the distance.', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (45, 'Hallway', 'A hallway dimly lit with a chill wind blowing through it. You hear clanking of metal instruments in the distance.', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (46, 'Faculty Office', 'The faculty offices of the school with lesson plans neatly piled on the desks. This area seems mostly untouched from disaster, very strange. ', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (47, 'Staff Office', 'A regular office room. The window is broken and a rope was used for someone to climb out the window. ', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (48, 'Tutoring Center', 'The tutoring center of the school, left in ruins. The brick walls are caving in and green vines have taken over much of what is left. ', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (49, 'Recreation Center', 'Swimming area where the swimming pool is used. The water is as dark as night, and something looks like it is moving in the water, something VERY BIG. ', " + playerID + ", 'false');");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Rooms] ([RoomID], [RoomName], [RoomDescription], [PlayerID], [Solved]) VALUES (50, 'Tech Hub', 'The game area where all the IT nerds gathered after school. Mountain dw and pizza are left on the table, they looked as if they left in a hurry. ', " + playerID + ", 'false');");
	}

	/**Method: genMonsterRecordsForNewPlayer
	 * This method will insert records into the monster table for a new player.
	 * @param playerID The id associated to the player.
	 */
	public void genMonsterRecordsForNewPlayer(int playerID)
	{
		this.updateDatabase("INSERT INTO [Monsters] ([MID], [MonsterName], [MonsterDescription], [MonsterStrength], [MonsterHealth], [MonsterAttackDescription]) VALUES (1, 'Cheerleader Ghost Captain', 'The captain of the cheerleading squad. Beware her ferocious pom poms chained with metal spikes. ', 8, 10, 'The spiked pom poms strike you hard against the head. ', " + playerID + ");");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Monsters] ([MID], [MonsterName], [MonsterDescription], [MonsterStrength], [MonsterHealth], [MonsterAttackDescription], [PlayerID]) VALUES (2, 'Principle Goo Monster ', 'He ozzes with green goo and slimes everything he touches. Beware his toxicity!', 10, 15, 'Green goo is thrown at you hitting you in the face!', " + playerID + ");");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Monsters] ([MID], [MonsterName], [MonsterDescription], [MonsterStrength], [MonsterHealth], [MonsterAttackDescription], [PlayerID]) VALUES (3, 'Sassy Substitute Teacher Zombie', 'She wears high heels and laughs hysterically like a mad woman. Green goo covers her once beautiful red dress. ', 9, 11, 'High heels strike you in the rear!', " + playerID + ");");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Monsters] ([MID], [MonsterName], [MonsterDescription], [MonsterStrength], [MonsterHealth], [MonsterAttackDescription], [PlayerID]) VALUES (4, 'Gym Coatch Frankenstein', 'The large menacing frankenstein bores down at you with red glowing eyes and huge monstrous green hands.', 12, 12, 'Large green hands grab you and squeeze the breath out of you. ', " + playerID + ");");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Monsters] ([MID], [MonsterName], [MonsterDescription], [MonsterStrength], [MonsterHealth], [MonsterAttackDescription], [PlayerID]) VALUES (5, 'Cafeteria Pumpkin Monster', 'A large pumpkin monster mutated from zombie goo in the cafeteria. He opens his mouth and you see little pumpkin minions dancing around inside his mouth.', 6, 7,'A mini pumpkin is thrown at you at a very fast speed. ', " + playerID + ");");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Monsters] ([MID], [MonsterName], [MonsterDescription], [MonsterStrength], [MonsterHealth], [MonsterAttackDescription], [PlayerID]) VALUES (6, 'Zombie Nurse', 'The school nurse turned into a moaning zombie! Beware her medecinal needles full of green goo!', 5, 8, 'The nurse injects green goo into your arm!', " + playerID + ");");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Monsters] ([MID], [MonsterName], [MonsterDescription], [MonsterStrength], [MonsterHealth], [MonsterAttackDescription], [PlayerID]) VALUES (7, 'Ghost Bully', 'The school bully turned into a ghost! He hallows at you in a wailing voice!', 4, 10,  'The ghost hallows a deadly wind that sweeps you off your feet. ', " + playerID + ");");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Monsters] ([MID], [MonsterName], [MonsterDescription], [MonsterStrength], [MonsterHealth], [MonsterAttackDescription], [PlayerID]) VALUES (8, 'Mad Science Teacher', 'The chemistry professor has turned into a mad scientist! His hair has frizzed out everywhere and he holds flasks of green goo boiling and producing an odd odor. ', 6, 5, 'The scientist hurls a flask at you exploding green goo all over your body. ', " + playerID + ");");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Monsters] ([MID], [MonsterName], [MonsterDescription], [MonsterStrength], [MonsterHealth], [MonsterAttackDescription], [PlayerID]) VALUES (9, 'Rampaging Math Tutor', 'The math tutor has gone out of control and is running around the room destroying everything with his textbook! Beware his giant Calculus book!', 9, 8,  'The math tutor swings his Calculus book at you dealing a deadly blow!', " + playerID + ");");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Monsters] ([MID], [MonsterName], [MonsterDescription], [MonsterStrength], [MonsterHealth], [MonsterAttackDescription], [PlayerID]) VALUES (10, 'Dead Lunch Ladies', 'The lunch ladies of the cafeteria are zombified and spray green goo from their mouths. ', 8, 13,  'The lunch ladies spray green goo at you. ', " + playerID + ");");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Monsters] ([MID], [MonsterName], [MonsterDescription], [MonsterStrength], [MonsterHealth], [MonsterAttackDescription], [PlayerID]) VALUES (11, 'Pumpkin Witch', 'A witch riding a flying pumpkin. She is cloaked in black with a large pointy hat. Her hand holds a green flame, beware! ', 7, 10, 'The witch hurls a ball of green flame at you burning your skin.', " + playerID + ");");
		this.commitConnection();
	}

	/**Method: genItemRecordsForNewPlayer
	 * This method adds records to the item table associated to a new player.
	 * @param playerID The id associated to the player.
	 */
	public void genItemRecordsForNewPlayer(int playerID)
	{
		this.updateDatabase("INSERT INTO [Items] ([ItemID], [ItemName], [Type], [ItemDescription], [Value], [PlayerID]) VALUES (20, 'Heavy Textbook', 'WEAPON', 'Equip this item to increase your strenth in battle.', 2, " + playerID + ");");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Items] ([ItemID], [ItemName], [Type], [ItemDescription], [Value], [PlayerID]) VALUES (21, 'Mountain Dew', 'ITEM', 'Use this item to heal your character''s current health points.', 25, " + playerID + ");");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Items] ([ItemID], [ItemName], [Type], [ItemDescription], [Value], [PlayerID]) VALUES (22, 'Trash Can Lid', 'ARMOR', 'Equip this item to increase your maximum hp amount to endure more hits in combat. ', 10, " + playerID + ");");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Items] ([ItemID], [ItemName], [Type], [ItemDescription], [Value], [PlayerID]) VALUES (23, 'Coffee', 'ITEM', 'Use this item to heal your character''s current health points.', 14, " + playerID + ");");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Items] ([ItemID], [ItemName], [Type], [ItemDescription], [Value], [PlayerID]) VALUES (24, 'Lunch Tray', 'WEAPON', 'Equip this item to increase your strenth in battle.', 3, " + playerID + ");");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Items] ([ItemID], [ItemName], [Type], [ItemDescription], [Value], [PlayerID]) VALUES (25, 'Beer', 'ITEM', 'Use this item to heal your character''s current health points.', 12, " + playerID + ");");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Items] ([ItemID], [ItemName], [Type], [ItemDescription], [Value], [PlayerID]) VALUES (26, 'Football Shoulder Pads', 'ARMOR', 'Equip this item to increase your maximum hp amount to endure more hits in combat. ', 8, " + playerID + ");");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Items] ([ItemID], [ItemName], [Type], [ItemDescription], [Value], [PlayerID]) VALUES (27, 'Skateboard', 'WEAPON', 'Equip this item to increase your strenth in battle.', 2, " + playerID + ");");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Items] ([ItemID], [ItemName], [Type], [ItemDescription], [Value], [PlayerID]) VALUES (28, 'Work Boots', 'ARMOR', 'Equip this item to increase your maximum hp amount to endure more hits in combat. ', 12, " + playerID + ");");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Items] ([ItemID], [ItemName], [Type], [ItemDescription], [Value], [PlayerID]) VALUES (29, 'Coke', 'ITEM', 'Use this item to heal your character''s current health points.', 20, " + playerID + ");");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Items] ([ItemID], [ItemName], [Type], [ItemDescription], [Value], [PlayerID]) VALUES (30, 'Red Bull', 'ITEM', 'Use this item to heal your character''s current health points.', 20, " + playerID + ");");
		this.commitConnection();
	}

	/**Method: genPuzzleRecordsForNewPlayer
	 * This method adds records to the puzzle table when a new player is created.
	 * @param playerID Id assocaited to the player.
	 */
	public void genPuzzleRecordsForNewPlayer(int playerID)
	{
		this.updateDatabase("INSERT INTO [Puzzles] ([PID], [PuzzleDescription], [PuzzleHint], [PuzzleTerminator], [PuzzleSolvedMessage], [PlayerID]) VALUES (35, 'There is no light and you cannot see. There is a lantern lying on the ground with matches next too it covered in goo. Maybe the matches can be lit in the lantnern.', 'Try using \"light\" with what you are trying to light.', 'light matches', 'The matches light the lantern and you can now see the way!', " + playerID + ");");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Puzzles] ([PID], [PuzzleDescription], [PuzzleHint], [PuzzleTerminator], [PuzzleSolvedMessage], [PlayerID]) VALUES (36, 'OH NO! Your foot triggered a rope trap and are snagged up into the air. Scissors are laying on the ground below and you think you can reach them with your fingertips. ', 'Try using \"grab\" with what you are trying to grab.', 'grab scissors', 'You grabbed the scissors and cut the rope. You fall with a steady landing but seem ok. The room is now cleared!', " + playerID + ");");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Puzzles] ([PID], [PuzzleDescription], [PuzzleHint], [PuzzleTerminator], [PuzzleSolvedMessage], [PlayerID]) VALUES (37, 'As you journey further into the room, a poisonous gas is released and you are losing your breadth. A gas mask is lying on a table, if only you could reach the gas mask. ', 'Try using \"reach\" with what you are trying to reach.', 'reach mask', 'You reached the gas mask and were able to breath. The fumes dissipated and now the room is clear.', " + playerID + ");");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Puzzles] ([PID], [PuzzleDescription], [PuzzleHint], [PuzzleTerminator], [PuzzleSolvedMessage], [PlayerID]) VALUES (38, 'The room has a blockade and you must climb over it to pass. A ladder is set aside, maybe if you could use the ladder. ', 'Try using \"use\" with what you are trying to use.', 'use ladder', 'You put the ladder against the rubbage and climbed over the blockade. You can now journey forward.', " + playerID + ");");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Puzzles] ([PID], [PuzzleDescription], [PuzzleHint], [PuzzleTerminator], [PuzzleSolvedMessage], [PlayerID]) VALUES (39, 'You come across a massive puddle of green goo with a plank sticking out of it. You need to pass, but how? Try using the plank to cross. ', 'Try using \"use\" with what you are trying to use.', 'use plank', 'You maneuver the plank out of the puddle and place it just right to fit on each side of the puddle. You can now pass!', " + playerID + ");");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Puzzles] ([PID], [PuzzleDescription], [PuzzleHint], [PuzzleTerminator], [PuzzleSolvedMessage], [PlayerID]) VALUES (40, 'As you journey further, you stumble and fall. Your leg is cut, and you need bandages. Try tearing your shirt to use as a bandage. ', 'Try using \"tear\" with what you are trying to tear.', 'tear shirt', 'You tear your shirt and use it as a bandage for your cut. You can now walk and venture on.', " + playerID + ");");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Puzzles] ([PID], [PuzzleDescription], [PuzzleHint], [PuzzleTerminator], [PuzzleSolvedMessage], [PlayerID]) VALUES (41, 'A dark spell is triggered, and you are paralyzed by fear. You cannot move and must answer the riddle to break the spell. Riddle: 30 white horses on a red hill. First they chump, then they stump, then they stand still.', 'What do you use to eat your food?', 'teeth', 'You solve the riddle and the dark spell is lifted! You can now venture forward. ', " + playerID + ");");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Puzzles] ([PID], [PuzzleDescription], [PuzzleHint], [PuzzleTerminator], [PuzzleSolvedMessage], [PlayerID]) VALUES (42, 'A dark spell is triggered, and you are paralyzed by fear. You cannot move and must answer the riddle to break the spell. Riddle: I have oceanless seas, sandless deserts, heightless mountains, and grassless lands. What am I?', 'What do you use to look at geography?', 'map', 'You solve the riddle and the dark spell is lifted! You can now venture forward. ', " + playerID + ");");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Puzzles] ([PID], [PuzzleDescription], [PuzzleHint], [PuzzleTerminator], [PuzzleSolvedMessage], [PlayerID]) VALUES (43, 'A dark spell is triggered, and you are paralyzed by fear. You cannot move and must answer the riddle to break the spell. Riddle: This thing all things devours. Birds, beasts, trees, flowers. Gnaws iron, bites steel. Grinds hard stones to meal. Slays king, ruins town, and beats mountain down.', 'What never stops?', 'time', 'You solve the riddle and the dark spell is lifted! You can now venture forward. ', " + playerID + ");");
		this.commitConnection();
		this.updateDatabase("INSERT INTO [Puzzles] ([PID], [PuzzleDescription], [PuzzleHint], [PuzzleTerminator], [PuzzleSolvedMessage], [PlayerID]) VALUES (44, 'A dark spell is triggered, and you are paralyzed by fear. You cannot move and must answer the riddle to break the spell. Riddle: It cannot be seen, cannot be felt,Cannot be heard, cannot be smelt.It lies behind stars and under hills,And empty holes it fills.It comes out first and follows after, Ends life, kills laughter. ', 'Opposite of light', 'dark', 'You solve the riddle and the dark spell is lifted! You can now venture forward. ', " + playerID + ");");
		this.commitConnection();
	}

	/**Method: genLocationForNewPlayer
	 * This method adds a record to the Location table to assign the default location of the new player in the database.
	 * @param playerID The id assocaited to the player. 
	 */
	public void genLocationForNewPlayer(int playerID)
	{
		this.updateDatabase("INSERT INTO [Location]  ([LocationID], [Xpos], [YPos])VALUES (" + playerID + ", 0, 0);");
		this.commitConnection();
	}

	/**Method:genNewPlayer
	 * This puts the data for the new player in the database.
	 * @param userName The players name
	 * @param password The players password
	 */
	public void genNewPlayer(String userName, String password)
	{
		this.executeQuery("INSERT INTO [Players] ([PlayerName], [MaximumHealth], [HealthPoints], [Strength], "
				+ "[Score], [Password]) VALUES ('" + userName + "', 100, 100, 5, 0, '"+ password + "');");
		this.commitConnection();
	}

	/**Method: genNewInventory
	 * This generates the inventory data for a new player corresponding to the players id.
	 * @param playerID The id belonging to the player.
	 */
	public void genNewInventory(int playerID)
	{
		this.executeQuery("INSERT INTO [Inventory] ([InventoryID], [ItemName], [Type], [ItemDescription], [Value], [PlayerID]) VALUES (1, 'Heavy Textbook', 'WEAPON', 'Equip this item to increase your strenth in battle.', 2, " + playerID + ");");
		this.commitConnection();
	}

	/**Method: saveLocation
	 * This method saves the player location in the Location table.
	 * Does by determining the players location record based on player id.
	 * @param playerID The player id associated to the player. 
	 * @param xPos The x coordinate on the map.
	 * @param yPos The y coordinate on the map.
	 */
	public void saveLocation(int playerID, int xPos, int yPos)
	{
		this.updateDatabase("update Location "
				+ "set XPos = " + xPos + ", YPos = " + yPos + " "
				+ "where Location.LocationID = " +  playerID + ";");
		this.commitConnection();
	}

	/**Method: saveRooms
	 * This method saves the rooms belonging to the player.
	 * @param rooms The list of game rooms
	 * @param playerID The id belonging to the player.
	 */
	public void saveRooms(ArrayList<GameRoom> rooms, int playerID)
	{
		for (GameRoom element: rooms)
		{
			this.updateDatabase("update Rooms set Solved = " + element.isSolved() +
					"where Rooms.PlayerID = " +  playerID + " and Rooms.ID = " + element.getID() + ";");
			this.commitConnection();
		}
	}

	/**Method: savePlayer
	 * This saves the players data back into the database.
	 * @param player The game player.
	 */
	public void savePlayer(GamePlayer player)
	{
		this.updateDatabase("update Players set MaximumHealth = " + player.getMaxHealth() +
				" , HealthPoints = " + player.getHealthPoints() +
				" , Strength = " + player.getStrength() +
				" , Score = " + player.getScore() +
				" where PlayerID = " +  player.getId() + ";");
		this.commitConnection();
	}

	/**Method: saveInventory
	 * This saves the players inventory to the database.
	 * @param inventory The players inventory of items.
	 * @param playerID The id associated to this players inventory.
	 */
	public void saveInventory(GameItem[] inventory, int playerID)
	{
		this.updateDatabase("delete from Inventory where Inventory.PlayerID = " + playerID +";");
		this.commitConnection();
		for (GameItem element: inventory)
		{
			try
			{
				this.executeQuery("INSERT INTO [Inventory] ([InventoryID], [ItemName], [Type], [ItemDescription], [Value], [PlayerID]) "
						+ "VALUES (" + element.getId() + ", '" + element.getName() + "', '" + element.getType() + "', '" + element.getDescription() + "', '" + element.getValue() + "', " + playerID + ");");
				this.commitConnection();
			}
			catch(NullPointerException npe)
			{
				//no item in inventory, so do nothing.
			}
		}
	}

}

