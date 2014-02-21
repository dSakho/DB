package jbdc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// This class attempts to acquire a connection with the database
public class DbConnect {
	public static void main(String[] args) {
		/* url points to jdbc protocol : mysql subprotocol; local host
		 * is the address of the server where we installed out DBMS
		 * (i.e. on local machine) and 3306 is the port on which we
		 * need to contact our DBMS
		 */
		String url = "jdbc:mysql://localhost:3306/";
		// We are connecting to the addressBook database we connected to earlier
		String database = "addressBook";
		// we login as "root" user with the password "maike93"
		String userName = "root";
		String password = "maike93";
		
		try (Connection connection = DriverManager.getConnection(
				url + database, userName, password)) {
			System.out.println("Database Connection: Succesful");
		} catch (SQLException sqle) {
			System.out.println("Database Connection: Failed");
			sqle.printStackTrace();
		}
	}
}
