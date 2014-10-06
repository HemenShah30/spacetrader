package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import model.Player;
import model.Universe;

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
		}

		System.out.println("PostgreSQL JDBC Driver Registered!");

		try {
			connection = DriverManager
					.getConnection(
							"jdbc:postgresql://spacetraders.cucctpybeipt.us-west-2.rds.amazonaws.com:5432/SpaceTraders",
							"meep366", "chromium");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
	}
	

	//Things to save:
	//Planet - TechLevel, SpecialResource, Government, Location, Condition, PoliceEncounterRate, PirateEnconuterRate, Marketplace
	//Marketplace - prices, quantities, planet, radius, color
	//Player - name, pilotSkill, fighterSkill, traderSkill, engineerSkill, investorSkill, traderRep, policeRep, pirateRep, credits, planet, Ship
	//Ship - shipType, currHP, fuel, cargoSize, cargo(GoodType/quantity), weapons, shields, upgrades, mercenaries, insurance
	//Mercenary - dailyCost, fighterSkill, pilotSkill, engineerSkill
	public void saveGame(Universe universe, Player p) {
		
	}
}