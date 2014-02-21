package jbdc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// illustrate how to insert a row in a ResultSet 
// and in the database
public class DBInsert {
	public static void main(String[] args) throws SQLException {
		try (Connection connection = DBConnector.connectToDb();
			// Create a statement from which the created ResultSets
			// are "scroll sensitive" as well as "updatable"
			Statement statement = connection.createStatement(
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = statement.executeQuery("SELECT * FROM contact")) {
			// first fetch the data and display it before
			// the update operation
			System.out.println("Before insert");
			System.out.println("ID \tfName \tlName \tEmail \t\tphoneNo");	
			while(resultSet.next()) {
				System.out.println(resultSet.getInt("id") + "\t"
				+ resultSet.getString("firstName") + "\t"
				+ resultSet.getString("lastName") + "\t"
				+ resultSet.getString("email") + "\t" 
				+ resultSet.getString("phoneNo"));
			}
			resultSet.moveToInsertRow();
			resultSet.updateString("firstName", "John");
			resultSet.updateString("lastName", "K.");
			resultSet.updateString("email", "john@abc.com");
			resultSet.updateString("phoneNo", "+19753186420");
			resultSet.insertRow();
			System.out.println("\nAfter the insert");
			System.out.println("ID \tfName \tlName \tEmail \t\tphoneNo");
			resultSet.beforeFirst();
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
