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
	//Planet - TechLevel, SpecialResource, Government, Location, Condition, PoliceEncounterRate, PirateEnconuterRate, Marketplace, radius, color
	//Marketplace - prices, quantities, planet
	//Player - name, pilotSkill, fighterSkill, traderSkill, engineerSkill, investorSkill, traderRep, policeRep, pirateRep, credits, planet, Ship
	//Ship - shipType, currHP, fuel, cargoSize, cargo(GoodType/quantity), weapons, shields, upgrades, mercenaries, insurance
	//Mercenary - dailyCost, fighterSkill, pilotSkill, engineerSkill
	//Bank - interestRate, outstandingDebt
	//StockExchange - allStocks, allBonds, playerStocks, playerBonds
	//Stock: price, company
	//Bond: price, interestRate
	
	//Tables:
	//Planet - PlanetId, TechLevelId, SpecialResourceId, GovernemntId, LocationX, LocationY, ConditionId, PoliceEncounterRateId, PirateEncounterRateId, MarketplaceId
	//Government - GovernmentId, Name
	//TechLevel - TechLevelId, Name, Value
	//SpecialResource - SpecialResourceId, Name
	//Government - GovernmentId, Name
	//Condition - ConditionId, Name
	//EncounterRate - EncounterRateId, Name
	//GoodType - GoodTypeId, Name, MinTechLevelToProduct, MinTechLevelToUse, BiggestProducer, BasePrice, PriceIncreasePerTechLevel, PriceVariance, NegativeCondition, CheapSpecialResource, ExpensiveSpecialResource, MinTraderPrice, MaxTraderPrice, BaseQuantity, QuantityIncreasePerTechLevel
	//Marketplace - MarketplaceId, PlanetId
	//MarketplaceGoods - MarketplaceId, GoodTypeId, GoodTypeQuantity, GoodTypePrice
	//MarketplacePrice
	//Player - PlayerId, Name, PilotSkill, FighterSkill, TraderSkill, EngineerSkill, InvestorSkill, TraderReputation, PoliceReputation, PirateReputation, Credits, ShipId, PlanetId
	//Ship - ShipId, ShipTypeId, CurrentHullPoints, Fuel, CargoSize, Insurance
	//ShipWeapons - ShipId, WeaponId
	//ShipShields - ShipId, ShieldId
	//ShipUpgrades - ShipId, UpgradeId
	//ShipMercenaries - ShipId, MercenaryId
	//ShipCargo - ShipId, GoodTypeId, GoodTypeQuantity
	//Mercenary - MercenaryId, DailyCost, FighterSkill, PilotSkill, EngineerSkill
	//Bank - BankId, PlayerId, InterestRate, OutstandingPlayerDebt
	//StockExchange - StockExchangeId, PlayerId
	//Stock - StockId, StockExchangeId, CompanyId, Value
	//Bond - BondId, StockExchangeId, InterestRate, Value
	public void saveGame(Universe universe, Player p) {
		
	}
}