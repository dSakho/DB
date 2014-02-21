package jbdc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class DBSavePoint {
	public static void main(String[] args) throws SQLException {
		Connection connection = DBConnector.connectToDb();
		ResultSet resultSet = null;
		/* we're using explicit finally blocks
		 * instead of try-wht-resources in this code
		 */
		try {
			// for commit / rollback, we first need to set auto-commit to false
			connection.setAutoCommit(false);
			Statement statement = 
					connection.createStatement
					(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			resultSet = statement.executeQuery("SELECT * FROM familyGroup");
			
			System.out.println("Printing the contents of the table before inserting");
			while(resultSet.next()) {
				System.out.println(resultSet.getInt("id") + " "
						+ resultSet.getString("nickName"));
			}
			
			System.out.println("Starting to insert rows.");
			
			// first insert
			resultSet.moveToInsertRow();
			resultSet.updateString("nickName", "Tom");
			resultSet.insertRow();
			System.out.println("Inserted row for Tom");
			// our first savepoint is here....
			Savepoint firstSavepoint = connection.setSavepoint("SavepointForTom");
			
			// second insert
			resultSet.moveToInsertRow();
			resultSet.updateString("nickName", "Dick");
			resultSet.insertRow();
			System.out.println("Inserted row for Dick");
			// our second savepoint is here
			Savepoint secondSavepoint = connection.setSavepoint("SavepointForDick");
			
			// third insert
			resultSet.moveToInsertRow();
			resultSet.updateString("nickName", "Harry");
			resultSet.insertRow();
			System.out.println("Inserted row for Harry");
			// our first savepoint is here....
			Savepoint thirdSavepoint = connection.setSavepoint("SavepointForHarry");
			
			/* rollback to the state when Dick was inserted;
			 * so the insert for Harry will be lost
			 */
			System.out.println("Rolling back to the state where Tom and Dick where"
					+ "inserted");
			connection.rollback(secondSavepoint);
			// commit the changes now and see what happens to the contents of the table
			connection.commit();
			System.out.println("Printing the contents of the table after commit");
			resultSet = statement.executeQuery("SELECT * FROM familyGroup");
			while(resultSet.next()) {
				System.out.println(resultSet.getInt("id") + " "
						+ resultSet.getString("nickName"));
			}
		} catch (SQLException sqle) {
			System.out.println("Something gone wrong, couldn't add a "
					+ "contact in family group");
			// roll back all the changes in the transaction since something
			// has gone wrong
			connection.rollback();
			sqle.printStackTrace();
		} finally {
			if(connection != null) connection.close();
			if(resultSet != null) resultSet.close();
		}
	}
}
