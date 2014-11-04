package math.warrior.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
//TO DO: Populate Database to test game source code
public class Database
{
	//Attributes
	private Connection connection;
	private Statement statement;
	private final String DATABASE_NAME = "GameDatabase";
	private final String JDBC =  "jdbc:sqlite";

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
			statement = connection.createStatement();
		}
		catch(SQLException sqle)
		{
			System.out.println("Could not establish a good connection with the database. ");
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
			System.out.println("Could not execute query to DBMS.");
			return result;
		}
	}
	
	/**Method: 
	 * 
	 * @param sql
	 * @return
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
}
