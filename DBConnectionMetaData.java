package jbdc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

// Illustrate how to obtain metadata from Collection
// object and examine the metadata for using it
// in a program
public class DBConnectionMetaData {
	public static void main(String[] args) throws SQLException {
		Connection connection = DBConnector.connectToDb();
		DatabaseMetaData metaData = connection.getMetaData();
		System.out.println("Displaying some of the database"
				+ "metadata from the Connection object");
		System.out.println("Database is: " + metaData.getDatabaseProductName()
				+ " " + metaData.getDatabaseProductVersion());
		System.out.println("Driver is: " + metaData.getDriverName()
				+ metaData.getDriverVersion());
		System.out.println("The URL for this connection is "
				+ metaData.getURL());
		System.out.println("User name is: " + metaData.getUserName());
		System.out.println("Maximum no. of rows you can insert is " 
				+ metaData.getMaxRowSize());
	}
}
