package jbdc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBDelete {
	public static void main(String[] args) throws SQLException {
		try (Connection connection = DBConnector.connectToDb();
				Statement statement = connection.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet resultSet1 = statement.executeQuery("SELECT * FROM contact"
						+ " WHERE firstName=\"John\"")) {
			if(resultSet1.next()) {
				// delete the first row
				resultSet1.deleteRow();
			}
			resultSet1.close();
			
			// now fetch again from the database 
			try (ResultSet resultSet2 = 
					statement.executeQuery("SELECT * FROM contact")) {
				System.out.println("\nAfter the deletion");
				while(resultSet2.next()) {
					System.out.println(resultSet2.getInt("id") + "\t"
					+ resultSet2.getString("firstName") + "\t"
					+ resultSet2.getString("lastName") + "\t"
					+ resultSet2.getString("email") + "\t" 
					+ resultSet2.getString("phoneNo"));
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.exit(-1);
		}
	}
}
