package jbdc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUpdate {
	public static void main(String[] args) throws SQLException {
		try (Connection connection = DBConnector.connectToDb();
				Statement statement = connection.createStatement(
						ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet resultSet = statement.executeQuery("SELECT * "
				+ "FROM contact WHERE firstName=\"Micheal\"")) {
			// first fetch the data and display it before
			// the update operation
			System.out.println("Before the update");
			System.out.println("ID \tfName \tlName \tEmail \t\tphoneNo");
			while(resultSet.next()) {
				System.out.println(resultSet.getInt("id") + "\t"
				+ resultSet.getString("firstName") + "\t"
				+ resultSet.getString("lastName") + "\t"
				+ resultSet.getString("email") + "\t" 
				+ resultSet.getString("phoneNo"));
			}
			
			// now update  the result set and display the modified data
			resultSet.absolute(1);
			resultSet.updateString("phoneNo", "+919976543210");
			// reflect the updated changes back to the databse 
			// by calling the updateRow() method
			resultSet.updateRow();
			System.out.println("After the update");
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
