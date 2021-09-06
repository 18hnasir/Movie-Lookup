package application;

import java.io.*;
import java.sql.*;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.jdbc.driver.*;

public class Student {
	static Connection con;
	static Statement stmt;

	public static void main(String argv[]) {
		connectToDatabase();
	}

	public static void connectToDatabase() {
		String driverPrefixURL = "jdbc:oracle:thin:@";
		String username = null;
		String password = null;
		String dataSource = null;
		try {
			// Register Oracle driver
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (Exception e) {
			System.out.println("Failed to load JDBC/ODBC driver.");
			return;
		}
		try {
			// look for resource file 'odbc.datasource'
			dataSource = "artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
			username = "hnasir4";
			password = "rardyluj";
			
		} catch (Exception e) {
			System.out.println("Unable to read resource file to get data source");
			return;
		}

		try {
			System.out.println(driverPrefixURL + dataSource);
			con = DriverManager.getConnection(driverPrefixURL + dataSource, username, password);
			DatabaseMetaData dbmd = con.getMetaData();
			stmt = con.createStatement();

			System.out.println("Connected.");

			if (dbmd == null) {
				System.out.println("No database meta data");
			}

			else {
				System.out.println("Database Product Name: " + dbmd.getDatabaseProductName());
				System.out.println("Database Product Version: " + dbmd.getDatabaseProductVersion());
				System.out.println("Database Driver Name: " + dbmd.getDriverName());
				System.out.println("Database Driver Version: " + dbmd.getDriverVersion());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}// End of connectToDatabase()
	
	//Returns an observable list of all rows in database
	public static ObservableList<Movie> getAll(String query) throws Exception {
		try {
			connectToDatabase();
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			ObservableList<Movie> returnArray = FXCollections.observableArrayList();
			
			while(result.next()) {
				returnArray.add(new Movie(result.getString(1), result.getString(2), result.getString(3), result.getString(4),
						result.getString(5), result.getDate(6), result.getString(7), result.getString(8)));
			}
			return returnArray;
		}
		catch (Exception e) {
			System.out.println("getAll() FAILED");
		}
		return null;
	}
	
}
