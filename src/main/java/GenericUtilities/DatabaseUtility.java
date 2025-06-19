package GenericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUtility {
	Connection con;

	public Connection getDatabaseConnection(String url, String username, String password) throws SQLException {

		Driver dobj = new Driver();
		DriverManager.registerDriver(dobj);
		con = DriverManager.getConnection(url, username, password);
		return con;
	}

	public ResultSet executeSelectQuery(String query, int index) throws SQLException {

		Statement stat = con.createStatement();
		ResultSet result = stat.executeQuery(query);

		return result;

	}

	public int executeUpdateQuery(String updatequery) throws SQLException {
		Statement stat = con.createStatement();
		int res = stat.executeUpdate(updatequery);
		return res;
	}

	public void closeDatabaseConnection() throws SQLException {
		con.close();
	}
	
	public Connection getDatabaseConnection() throws SQLException {

		Driver dobj = new Driver();
		DriverManager.registerDriver(dobj);
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/advproject", "root", "root");
		return con;
	}

}
