package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private Connection connection;

	public Database() {
		connect();
	}

	private void connect() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your PostgreSQL JDBC Driver?"
					+ "Include in your library path!");
			e.printStackTrace();
			return;
		}

		System.out.println("PostgreSQL JDBC Driver Registered!");

		try {
			connection = DriverManager
					.getConnection(
							"jdbc:postgresql:spacetraders.cucctpybeipt.us-west-2.rds.amazonaws.com:5432/spacetraders",
							"meep366", "chromium");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
	}
}