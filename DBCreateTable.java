package jbdc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

// create a table in the database programatically
public class DBCreateTable {
	public static void main(String[] args) {
		try(Connection connection = DBConnector.connectToDb();
				Statement statement = connection.createStatement()) {
			// use CREATE TABLE SQL statement to create table familyGroup
			int result = statement.executeUpdate("CREATE TABLE familyGroup(id int not"
					+ " null auto_increment, nickName varchar(30) not null, "
					+ "primary key(id));");
			System.out.println("Table created successfully!");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.exit(-1);
		}
	}
}
