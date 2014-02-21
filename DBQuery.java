package jbdc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// How to query the database
public class DBQuery {
	public static void main(String[] args) {
		/* Get connection, execute query, get the result
		 * set and print the entries from the result set
		 */
		try (Connection connection = DBConnector.connectToDb();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM contact")) {
			System.out.println("ID \tfName \tlName \tEmail \t\tphoneNo");
			while(resultSet.next()) {
				System.out.println(resultSet.getInt("id") + "\t"
				+ resultSet.getString("firstName") + "\t"
				+ resultSet.getString("lastName") + "\t"
				+ resultSet.getString("email") + "\t" 
				+ resultSet.getString("phoneNo"));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.exit(-1);
		}
	}
}
