package jbdc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery4 {
	public static void main(String[] args) {
		try(Connection connection = DBConnector.connectToDb();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT firstName,"
				+ "email FROM contact WHERE firstName=\"Micheal\"")) {
			System.out.println("fName \tEmail");
			while (resultSet.next()) {
				System.out.println(resultSet.getString("firstName") + 
						"\t" + resultSet.getString("email"));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.exit(-1);
		}
	}
}
