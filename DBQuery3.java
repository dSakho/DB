package jbdc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery3 {

	public static void main(String[] args) {
		/* Get connection, execute query, get the result
		 * set and print the entries from the result set
		 */
		try (Connection connection = DBConnector.connectToDb();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM contact")) {
			System.out.println("ID \tfName \tlName \tEmail \t\tphoneNo");
			// retrieve the number of columns from the database
			int numOfColumns = resultSet.getMetaData().getColumnCount();
			while(resultSet.next()) {			
				// remember that the column index starts from 1, not 0
				// and ends at N, not N-1
				for (int i = 1; i <= numOfColumns; i++) {
					// since we do not know the data type of the column,
					// we use getObject()
					System.out.print(resultSet.getObject(i) + "\t");
				}
				System.out.println();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.exit(-1);
		}
	}
}