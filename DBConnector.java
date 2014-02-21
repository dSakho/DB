package jbdc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// utility class with method connectToDb() 
public class DBConnector {
	public static Connection connectToDb() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/";
		String database = "addressBook";
		String userName = "root";
		String password = "maike93";
		return DriverManager.getConnection(url + database, userName, password);
	}
}
