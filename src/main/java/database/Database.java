package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.postgresql.util.PGobject;

import model.GoodType;
import model.Planet;
import model.Player;
import model.Ship;
import model.ShipType;
import model.Universe;

//Things to save(Not a complete list):
//Planet - TechLevel, SpecialResource, Government, Location, Condition, PoliceEncounterRate, PirateEnconuterRate, Marketplace, radius, color
//Marketplace - prices, quantities, planet
//Player - name, pilotSkill, fighterSkill, traderSkill, engineerSkill, investorSkill, traderRep, policeRep, pirateRep, credits, planet, Ship
//Ship - shipType, currHP, fuel, cargoSize, cargo(GoodType/quantity), weapons, shields, upgrades, mercenaries, insurance
//Mercenary - dailyCost, fighterSkill, pilotSkill, engineerSkill
//Bank - interestRate, outstandingDebt
//StockExchange - allStocks, allBonds, playerStocks, playerBonds
//Stock: price, company
//Bond: price, interestRate

public class Database {
	private Connection connection;
	private String username;
	private Map<Integer, String> techLevelValues;
	private Map<String, String> specialResourceValues;
	private Map<String, String> governmentValues;
	private Map<String, String> encounterRateValues;
	private Map<String, String> conditionValues;
	private Map<String, String> goodTypeValues;
	private Map<String, String> shipTypeValues;

	/**
	 * Creates a new Database class and attempts to connect to the database
	 */
	public Database(String u) {
		username = u;
		techLevelValues = new HashMap<Integer, String>();
		specialResourceValues = new HashMap<String, String>();
		governmentValues = new HashMap<String, String>();
		encounterRateValues = new HashMap<String, String>();
		conditionValues = new HashMap<String, String>();
		goodTypeValues = new HashMap<String, String>();
		shipTypeValues = new HashMap<String, String>();
		connect();
		createMaps();
	}

	/**
	 * Attempts a connection to the database
	 */
	private void connect() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your PostgreSQL JDBC Driver?"
					+ "Include in your library path!");
			e.printStackTrace();
		}

	//	System.out.println("PostgreSQL JDBC Driver Registered!");

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

	/**
	 * Creates the maps of enum values to UUIDs to be referenced in saving and
	 * loading later
	 */
	private void createMaps() {
		try {
			Statement s = connection.createStatement();
			ResultSet techLevels = s
					.executeQuery("SELECT \"TechLevelId\", \"TechLevelValue\" FROM \"TechLevel\"");
			while (techLevels.next()) {
				techLevelValues.put(techLevels.getInt("TechLevelValue"),
						techLevels.getString("TechLevelId"));
			}
			
			ResultSet specialResources = s
					.executeQuery("SELECT \"SpecialResourceId\", \"SpecialResourceName\" FROM \"SpecialResource\"");
			while (specialResources.next()) {
				specialResourceValues.put(
						specialResources.getString("SpecialResourceName"),
						specialResources.getString("SpecialResourceId"));
			}
			
			ResultSet governments = s
					.executeQuery("SELECT \"GovernmentId\", \"GovernmentName\" FROM \"Government\"");
			while (governments.next()) {
				governmentValues.put(governments.getString("GovernmentName"),
						governments.getString("GovernmentId"));
			}
			
			ResultSet conditions = s
					.executeQuery("SELECT \"ConditionId\", \"ConditionName\" FROM \"Condition\"");
			while (conditions.next()) {
				conditionValues.put(conditions.getString("ConditionName"),
						conditions.getString("ConditionId"));
			}
			
			ResultSet encounterRates = s
					.executeQuery("SELECT \"EncounterRateId\", \"EncounterRateName\" FROM \"EncounterRate\"");
			while (encounterRates.next()) {
				encounterRateValues.put(
						encounterRates.getString("EncounterRateName"),
						encounterRates.getString("EncounterRateId"));
			}
			
			ResultSet goodTypes = s
					.executeQuery("SELECT \"GoodTypeId\", \"GoodTypeName\" FROM \"GoodType\"");
			while (goodTypes.next()) {
				goodTypeValues.put(
						goodTypes.getString("GoodTypeName"),
						goodTypes.getString("GoodTypeId"));
			}
			
			ResultSet shipTypes = s
					.executeQuery("SELECT \"ShipTypeId\", \"ShipName\" FROM \"ShipType\"");
			while (shipTypes.next()) {
				shipTypeValues.put(
						shipTypes.getString("ShipName"),
						shipTypes.getString("ShipTypeId"));
			}
			
			
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	/**
	 * Closes a connection with the database
	 */
	public void close() {
		System.out.println("Closing Connection");
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Determines whether or not the given user exists in the database or not
	 * 
	 * @return Whether or not the user exists in the database already
	 */
	public boolean userExists() {
		try {
			Statement s = connection.createStatement();
			ResultSet r = s
					.executeQuery("SELECT \"UserId\" FROM \"User\" WHERE \"Username\"='"
							+ username + "'");
			return r.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Tables:
	// Planet - PlanetId, TechLevelId, SpecialResourceId, GovernemntId, LocationX, LocationY, ConditionId, PoliceEncounterRateId, PirateEncounterRateId, MarketplaceId
	// Government - GovernmentId, GovernmentName
	// TechLevel - TechLevelId, TechLevelName, TechLevelValue
	// SpecialResource - SpecialResourceId, SpecialResourceName
	// Condition - ConditionId, ConditionName
	// EncounterRate - EncounterRateId, EncounterRateName
	// GoodType - GoodTypeId, GoodTypeName, MinTechLevelToProduce, MinTechLevelToUse, BiggestProducer, BaseGoodTypePrice, PriceIncreasePerTechLevel, PriceVariance, Condition, CheapSpecialResource, ExpensiveSpecialResource, MinTraderPrice, MaxTraderPrice, BaseQuantity, QuantityIncreasePerTechLevel
	// Marketplace - MarketplaceId, PlanetId
	// MarketplaceGoods - MarketplaceId, GoodTypeId, GoodTypeQuantity, GoodTypePrice
	// Player - PlayerId, PlayerName, PilotSkill, FighterSkill, TraderSkill, EngineerSkill, InvestorSkill, TraderReputation, PoliceReputation, PirateReputation, Credits, ShipId, PlanetId
	// Ship - ShipId, ShipTypeId, CurrentHullPoints, CurrentFuel, Insurance
	// ShipType - ShipTypeId, ShipName, MaxFuel, TotalHullPoints, CargoSize, WeaponSlots, ShieldSlots, GadgetSlots, CrewSpace, ShipMinTechLevel, FuelCost, ShipPrice, ShipBounty, ShipOccurrence, PoliceModifier, PirateModifier, TraderModifier, RepairCost, Size
	// Laser - WeaponId, Name, BaseDamage, MinTechLevel, Price
	// Shield - ShieldId, Name, MaxStrength
	// Gadget - GadgetId, Name, Price, MinTechLevel, Ability
	// ShipLasers - ShipId, WeaponId
	// ShipShields - ShipId, ShieldId
	// ShipGadgets - ShipId, GadgetId
	// ShipMercenaries - ShipId, MercenaryId
	// ShipCargo - ShipId, GoodTypeId, GoodTypeQuantity
	// Mercenary - MercenaryId, DailyCost, FighterSkill, PilotSkill, EngineerSkill
	
	
	// Bank - BankId, PlayerId, InterestRate, OutstandingPlayerDebt
	// StockExchange - StockExchangeId, PlayerId
	// Stock - StockId, StockExchangeId, CompanyId, Value
	// Bond - BondId, StockExchangeId, InterestRate, Value
	public void saveGame(Universe universe, Player player) {
		long startTime = System.nanoTime();
		if (userExists()) {
			System.out.println("User exists, implementing update here");
		} else {
			try {
				Ship ship = player.getShip();
				PreparedStatement shipInsertStatement = connection
						.prepareStatement("INSERT INTO \"Ship\" VALUES(?, ?, ?, ?)");
				UUID shipUUID = UUID.randomUUID();
				PGobject shipUUIDObject = new PGobject();
				shipUUIDObject.setType("uuid");
				shipUUIDObject.setValue(shipUUID.toString());
				shipInsertStatement.setObject(1, shipUUIDObject);

				PGobject shipTypeUUIDObject = new PGobject();
				shipTypeUUIDObject.setType("uuid");
				shipTypeUUIDObject.setValue(shipTypeValues.get(ship
						.getShipType().toString()));
				shipInsertStatement.setObject(2, shipTypeUUIDObject);

				shipInsertStatement.setInt(3, ship.getCurrHP());
				shipInsertStatement.setInt(4, ship.getFuel());
				shipInsertStatement.execute();

				for (GoodType g : GoodType.values()) {
					PreparedStatement shipCargoInsertStatement = connection
							.prepareStatement("INSERT INTO \"ShipCargo\" VALUES(?, ?, ?)");
					shipCargoInsertStatement.setObject(1, shipUUIDObject);
					PGobject shipGoodTypeUUIDObject = new PGobject();
					shipGoodTypeUUIDObject.setType("uuid");
					shipGoodTypeUUIDObject.setValue(goodTypeValues.get(g
							.toString()));
					shipCargoInsertStatement.setObject(2,
							shipGoodTypeUUIDObject);
					shipCargoInsertStatement.setInt(3, ship.amountInCargo(g));
					shipCargoInsertStatement.execute();
				}

				PreparedStatement playerInsertStatement = connection
						.prepareStatement("INSERT INTO \"Player\" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

				UUID playerUUID = UUID.randomUUID();
				PGobject playerUUIDObject = new PGobject();
				playerUUIDObject.setType("uuid");
				playerUUIDObject.setValue(playerUUID.toString());
				playerInsertStatement.setObject(1, playerUUIDObject);

				playerInsertStatement.setInt(2, player.getPilotSkill());
				playerInsertStatement.setInt(3, player.getFighterSkill());
				playerInsertStatement.setInt(4, player.getTraderSkill());
				playerInsertStatement.setInt(5, player.getEngineerSkill());
				playerInsertStatement.setInt(6, player.getInvestorSkill());
				playerInsertStatement.setInt(7, player.getTraderRep());
				playerInsertStatement.setInt(8, player.getPoliceRep());
				playerInsertStatement.setInt(9, player.getPirateRep());

				PGobject playerCreditsUUIDObject = new PGobject();
				playerCreditsUUIDObject.setType("numeric");
				playerCreditsUUIDObject.setValue(Double.toString(player
						.getCredits()));
				playerInsertStatement.setObject(10, playerCreditsUUIDObject);
				playerInsertStatement.setObject(11, shipUUIDObject);
				playerInsertStatement.setNull(12, Types.NULL);
				playerInsertStatement.setString(13, player.getName());
				playerInsertStatement.execute();

				PreparedStatement userInsertStatement = connection
						.prepareStatement("INSERT INTO \"User\" VALUES(?, ?, ?)");
				UUID userUUID = UUID.randomUUID();
				PGobject userUUIDObject = new PGobject();
				userUUIDObject.setType("uuid");
				userUUIDObject.setValue(userUUID.toString());
				userInsertStatement.setObject(1, userUUIDObject);
				userInsertStatement.setString(2, username);
				userInsertStatement.setString(3, "password");
				userInsertStatement.execute();

				PreparedStatement userPlayersInsertStatement = connection
						.prepareStatement("INSERT INTO \"UserPlayers\" VALUES(?, ?)");
				userPlayersInsertStatement.setObject(1, userUUIDObject);
				userPlayersInsertStatement.setObject(2, playerUUIDObject);
				userPlayersInsertStatement.execute();
				
				PreparedStatement planetInsertStatement = connection
						.prepareStatement("INSERT INTO \"Planet\" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				PreparedStatement playerPlanetsInsertStatement = connection
						.prepareStatement("INSERT INTO \"PlayerPlanets\" VALUES(?, ?)");
				List<Planet> planets = universe.getPlanets();
				for (Planet p : planets) {
					UUID planetUUID = UUID.randomUUID();
					PGobject planetUUIDObject = new PGobject();
					planetUUIDObject.setType("uuid");
					planetUUIDObject.setValue(planetUUID.toString());
					planetInsertStatement.setObject(1, planetUUIDObject);

					PGobject techLevelUUIDObject = new PGobject();
					techLevelUUIDObject.setType("uuid");
					techLevelUUIDObject.setValue(techLevelValues.get(p
							.getTechLevel().getValue()));
					planetInsertStatement.setObject(2, techLevelUUIDObject);

					PGobject resourceUUIDObject = new PGobject();
					resourceUUIDObject.setType("uuid");
					resourceUUIDObject.setValue(specialResourceValues.get(p
							.getResource().toString()));
					planetInsertStatement.setObject(3, resourceUUIDObject);

					PGobject governmentUUIDObject = new PGobject();
					governmentUUIDObject.setType("uuid");
					governmentUUIDObject.setValue(governmentValues.get(p
							.getGovernment().toString()));
					planetInsertStatement.setObject(4, governmentUUIDObject);

					planetInsertStatement.setInt(5, p.getLocation().getX());
					planetInsertStatement.setInt(6, p.getLocation().getY());

					PGobject conditionUUIDObject = new PGobject();
					conditionUUIDObject.setType("uuid");
					conditionUUIDObject.setValue(conditionValues.get(p
							.getCondition().toString()));
					planetInsertStatement.setObject(7, conditionUUIDObject);

					PGobject policeEncounterUUIDObject = new PGobject();
					policeEncounterUUIDObject.setType("uuid");
					policeEncounterUUIDObject.setValue(encounterRateValues
							.get(p.getPoliceEncounterRate().toString()));
					planetInsertStatement.setObject(8,
							policeEncounterUUIDObject);

					PGobject pirateEncounterUUIDObject = new PGobject();
					pirateEncounterUUIDObject.setType("uuid");
					pirateEncounterUUIDObject.setValue(encounterRateValues
							.get(p.getPirateEncounterRate().toString()));
					planetInsertStatement.setObject(9,
							pirateEncounterUUIDObject);

					planetInsertStatement.setString(10, p.getName());
					planetInsertStatement.execute();
					
					
					playerPlanetsInsertStatement.setObject(1, playerUUIDObject);
					playerPlanetsInsertStatement.setObject(2, planetUUIDObject);
					playerPlanetsInsertStatement.execute();

					// PreparedStatement marketplaceInsertStatement =
					// connection.prepareStatement("INSERT INTO \"Marketplace\" VALUES(?, ?)");
					// UUID marketplaceUUID = UUID.randomUUID();
					// PGobject marketplaceUUIDObject = new PGobject();
					// marketplaceUUIDObject.setType("uuid");
					// marketplaceUUIDObject.setValue(marketplaceUUID.toString());
					// marketplaceInsertStatement.setObject(1,
					// marketplaceUUIDObject);
					//
					// marketplaceInsertStatement.setObject(2,
					// planetUUIDObject);
					// marketplaceInsertStatement.execute();
					//
					//
					//
					// Marketplace m = p.getMarketplace();
					// for(GoodType g: GoodType.values()) {
					// PreparedStatement marketplaceGoodsInsertStatement =
					// connection.prepareStatement("INSERT INTO \"MarketplaceGoods\" VALUES(?, ?, ?, ?)");
					// marketplaceGoodsInsertStatement.setObject(1,
					// marketplaceUUIDObject);
					// PGobject marketplaceGoodTypeUUIDObject = new PGobject();
					// marketplaceGoodTypeUUIDObject.setType("uuid");
					// marketplaceGoodTypeUUIDObject.setValue(goodTypeValues.get(g.toString()));
					// marketplaceGoodsInsertStatement.setObject(2,
					// marketplaceGoodTypeUUIDObject);
					// marketplaceGoodsInsertStatement.setInt(3,
					// m.getQuantity(g));
					// marketplaceGoodsInsertStatement.setInt(4, (int)
					// m.getSellPrice(g));
					// marketplaceGoodsInsertStatement.execute();
					// }

				}
				
				Statement s = connection.createStatement();
				ResultSet planet = s
						.executeQuery("SELECT \"PlanetId\" FROM \"Planet\" WHERE \"PlanetName\"='"
								+ player.getPlanet().getName() + "'");
				planet.next();
				String planetUUID = planet.getString("PlanetId");
				s.execute("UPDATE \"Player\" SET \"PlanetId\"='"+planetUUID+"' WHERE \"PlayerId\"='"+playerUUID+"'");

			} catch (SQLException s) {
				s.printStackTrace();
			}
		}

		System.out.println("Save Time: " + (System.nanoTime() - startTime)
				/ 1000000000.);
	}

	/**
	 * Loads the users game from the database
	 * 
	 * @return An object[] of the universe and planets
	 */
	public Object[] loadGame() {
		try {
			Statement s = connection.createStatement();
			ResultSet user = s
					.executeQuery("SELECT \"UserId\" FROM \"User\" WHERE \"Username\"='"
							+ username + "'");
			user.next();
			String userId = user.getString("UserId");
			
			ResultSet player = s
					.executeQuery("SELECT \"PlayerId\" FROM \"UserPlayers\" WHERE \"UserId\"='"
							+ userId + "'");
			user.next();
			String playerId = user.getString("UserId");
			
		} catch (SQLException s) {
			s.printStackTrace();
		}
		
		Universe u = new Universe();
		u.createPlanets();
		Player p = new Player("Jack",1,10,7,1,1,new Ship(ShipType.FIREFLY));
		p.setPlanet(u.getPlanets().get(0));
		return new Object[] {p,u};
	}

	// public void createEnum() {
	// Map<Integer, String> techLevelMap = new HashMap<Integer, String>();
	// techLevelMap.put(3, "346ab4de-3a9e-4edd-81ca-2dae7c498910");
	// techLevelMap.put(5, "5f0da0ba-35c1-4274-8f37-a9e74549ff7c");
	// techLevelMap.put(0, "69c54f7a-53a4-4216-9ae2-4609d38b132b");
	// techLevelMap.put(4, "735ca420-eb54-4157-8f96-acfc55fe35e9");
	// techLevelMap.put(1, "8501754a-7adb-4c38-8172-e204cf091bb5");
	// techLevelMap.put(2, "947effab-d08f-43ef-8735-a9d7866d1fc0");
	// techLevelMap.put(6, "a78330f1-a681-4140-99ba-63eee79bad02");
	// techLevelMap.put(7, "ba6271de-4b67-40c2-a99c-7130627ba0fa");
	//
	// try {
	// for (ShieldType c : ShieldType.values()) {
	// Statement s = connection.createStatement();
	// UUID uuid = UUID.randomUUID();
	// s.execute("INSERT INTO \"Shield\" VALUES('" + uuid + "', "
	// + c.getShieldHP() + ", '"
	// + techLevelMap.get(c.getMinTechLevel()) + "', "
	// + c.getPrice() + ", '" + c.toString() + "')");
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// public void createShipType() {
	// Map<Integer, String> techLevelMap = new HashMap<Integer, String>();
	// techLevelMap.put(3, "346ab4de-3a9e-4edd-81ca-2dae7c498910");
	// techLevelMap.put(5, "5f0da0ba-35c1-4274-8f37-a9e74549ff7c");
	// techLevelMap.put(0, "69c54f7a-53a4-4216-9ae2-4609d38b132b");
	// techLevelMap.put(4, "735ca420-eb54-4157-8f96-acfc55fe35e9");
	// techLevelMap.put(1, "8501754a-7adb-4c38-8172-e204cf091bb5");
	// techLevelMap.put(2, "947effab-d08f-43ef-8735-a9d7866d1fc0");
	// techLevelMap.put(6, "a78330f1-a681-4140-99ba-63eee79bad02");
	// techLevelMap.put(7, "ba6271de-4b67-40c2-a99c-7130627ba0fa");
	//
	// try {
	// for (ShipType g : ShipType.values()) {
	// Statement s = connection.createStatement();
	// UUID uuid = UUID.randomUUID();
	// String execStatement = "'" + uuid + "', '";
	// execStatement += g.toString() + "', ";
	// execStatement += g.getFuel() + ", ";
	// execStatement += g.getTotalHP() + ", ";
	// execStatement += g.getCargoSize() + ", ";
	// execStatement += g.getWeaponSlots() + ", ";
	// execStatement += g.getShieldSlots() + ", ";
	// execStatement += g.getGadgetSlots() + ", ";
	// execStatement += g.getCrewSpace() + ", '";
	// execStatement += techLevelMap.get(g.getMinTechLevel()) + "', ";
	// execStatement += g.getFuelCost() + ", ";
	// execStatement += g.getPrice() + ", ";
	// execStatement += g.getBounty() + ", ";
	// execStatement += g.getOccurrence() + ", ";
	// execStatement += g.getPoliceModifier() + ", ";
	// execStatement += g.getPirateModifier() + ", ";
	// execStatement += g.getTraderModifier() + ", ";
	// execStatement += g.getRepairCost() + ", ";
	// execStatement += g.getSize();
	// System.out.println("INSERT INTO \"ShipType\" VALUES("
	// + execStatement + ")");
	//
	// s.execute("INSERT INTO \"ShipType\" VALUES(" + execStatement
	// + ")");
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// public void createGoodType() {
	// Map<Integer, String> techLevelMap = new HashMap<Integer, String>();
	// techLevelMap.put(3, "346ab4de-3a9e-4edd-81ca-2dae7c498910");
	// techLevelMap.put(5, "5f0da0ba-35c1-4274-8f37-a9e74549ff7c");
	// techLevelMap.put(0, "69c54f7a-53a4-4216-9ae2-4609d38b132b");
	// techLevelMap.put(4, "735ca420-eb54-4157-8f96-acfc55fe35e9");
	// techLevelMap.put(1, "8501754a-7adb-4c38-8172-e204cf091bb5");
	// techLevelMap.put(2, "947effab-d08f-43ef-8735-a9d7866d1fc0");
	// techLevelMap.put(6, "a78330f1-a681-4140-99ba-63eee79bad02");
	// techLevelMap.put(7, "ba6271de-4b67-40c2-a99c-7130627ba0fa");
	//
	// Map<Condition, String> conditionMap = new HashMap<Condition, String>();
	// conditionMap.put(Condition.DROUGHT,
	// "0815cec6-99c7-443f-b9fd-7cf59924cad8");
	// conditionMap
	// .put(Condition.NONE, "1b45245b-0a91-4048-9331-9cb5ce360bbe");
	// conditionMap.put(Condition.CROPFAIL,
	// "3674ea27-a03d-48a1-88b6-5ac4389644d3");
	// conditionMap.put(Condition.BOREDOM,
	// "3dfffaa5-6c19-481b-bde2-7c17aa7e0181");
	// conditionMap
	// .put(Condition.COLD, "4f8c8ba3-5cb5-470f-a477-8f983adb1d52");
	// conditionMap.put(Condition.WAR, "9cddf2c4-d68d-4d20-b3df-cd3a7dcf4354");
	// conditionMap.put(Condition.LACKOFWORKERS,
	// "a1ff4493-40b4-4db8-ab71-14119c1cb207");
	// conditionMap.put(Condition.PLAGUE,
	// "d19a56f7-773b-4e96-93ce-554b393c6dc2");
	//
	// Map<SpecialResource, String> resourceMap = new HashMap<SpecialResource,
	// String>();
	// resourceMap.put(SpecialResource.DESERT,
	// "0d3455c8-112a-4e7b-ad62-aa29151ed5e8");
	// resourceMap.put(SpecialResource.NOSPECIALRESOURCES,
	// "0d65f5ed-9d90-4d73-a8b2-075f785b1bc9");
	// resourceMap.put(SpecialResource.WARLIKE,
	// "15d11d22-8e76-493c-a842-53494ea67bd0");
	// resourceMap.put(SpecialResource.RICHFAUNA,
	// "2663547b-3dac-4ddc-9a6b-fda2bc6715a4");
	// resourceMap.put(SpecialResource.MINERALRICH,
	// "9666f206-c6e3-433d-a4e4-471805ab0c1e");
	// resourceMap.put(SpecialResource.POORSOIL,
	// "aa211fff-983c-4c9b-b70f-8c2b528b7d43");
	// resourceMap.put(SpecialResource.LOTSOFWATER,
	// "adc14902-a84d-49a2-a84a-fcf29cd558f8");
	// resourceMap.put(SpecialResource.LIFELESS,
	// "c6d6d1ff-6237-4102-9491-d2cf35c2cbe4");
	// resourceMap.put(SpecialResource.WEIRDMUSHROOMS,
	// "c7bb252c-2247-4d91-9f13-865645fd2bf3");
	// resourceMap.put(SpecialResource.ARTISTIC,
	// "cc5241b7-7e6f-4cf5-98f3-19855b240b85");
	// resourceMap.put(SpecialResource.MINERALPOOR,
	// "e301d026-1383-4748-8e96-6141d0ccf3be");
	// resourceMap.put(SpecialResource.RICHSOIL,
	// "f545a5ff-10da-4c2f-ae8d-e8968b9acd9c");
	// resourceMap.put(SpecialResource.LOTSOFHERBS,
	// "f5918ab6-212d-4fc5-addd-fb9a30bbc731");
	//
	// try {
	// for (GoodType g : GoodType.values()) {
	// Statement s = connection.createStatement();
	// UUID uuid = UUID.randomUUID();
	// String execStatement = "'" + uuid + "', '";
	// execStatement += g.toString() + "', '";
	// execStatement += techLevelMap.get(g.getMinTechLevelToProduce())
	// + "', '";
	// execStatement += techLevelMap.get(g.getMinTechLevelToUse())
	// + "', '";
	// execStatement += techLevelMap.get(g.getBiggestProducer())
	// + "', ";
	// execStatement += g.getBasePrice() + ", ";
	// execStatement += g.getPriceIncPerTechLevel() + ", ";
	// execStatement += g.getVariance() + ", '";
	// execStatement += conditionMap.get(g.getCondition()) + "', ";
	// if (g.getCheapResource() != null)
	// execStatement += "'"
	// + resourceMap.get(g.getCheapResource()) + "', ";
	// else
	// execStatement += "'',";
	// if (g.getExpensiveResource() != null)
	// execStatement += "'"
	// + resourceMap.get(g.getExpensiveResource()) + "', ";
	// else
	// execStatement += "'',";
	// execStatement += g.getMinTraderPrice() + ", ";
	// execStatement += g.getMaxTraderPrice() + ", ";
	// execStatement += g.getBaseQuantity() + ", ";
	// execStatement += g.getQuantityIncPerTechLevel();
	// System.out.println("INSERT INTO \"GoodType\" VALUES("
	// + execStatement + ")");
	// if (g.getCheapResource() != null
	// && g.getExpensiveResource() != null)
	// s.execute("INSERT INTO \"GoodType\" VALUES("
	// + execStatement + ")");
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }
}