package jbdc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//How to query the database
public class DBQuery2 {
	public static void main(String[] args) {
		/* Get connection, execute query, get the result
		 * set and print the entries from the result set
		 */
		try (Connection connection = DBConnector.connectToDb();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM contact")) {
			System.out.println("ID \tfName \tlName \tEmail \t\tphoneNo");
			while(resultSet.next()) {
				// use column numbers instead of column names
				System.out.println(resultSet.getInt(1) + "\t"
				+ resultSet.getString(2) + "\t"
				+ resultSet.getString(3) + "\t"
				+ resultSet.getString(4) + "\t" 
				+ resultSet.getString(5));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.exit(-1);
		}
	}
}
