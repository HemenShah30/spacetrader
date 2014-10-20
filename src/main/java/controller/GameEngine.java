package controller;

import java.util.ArrayList;
import java.util.List;

import database.Database;
import model.Encounter;
import model.Location;
import model.MaxCapacityException;
import model.NPCEncounter;
import model.Planet;
import model.Player;
import model.Police;
import model.Ship;
import model.Universe;
import model.Enum.EncounterResult;
import model.Enum.GoodType;
import model.Enum.LaserType;
import model.Enum.ShipType;

/**
 * The main game coordinator that will connect the views and the controllers
 * 
 * @author Jack Croft
 *
 */
public class GameEngine {
	private static GameEngine gameEngine;
	private Database database;
	private TradeEngine tradeEngine;
	private FlightEngine flightEngine;
	private FightEngine fightEngine;
	private Universe universe;
	private Player player;

	/**
	 * Private constructor for the GameEngine preventing instantiation outside
	 * of the static method, also creates the universe
	 */
	private GameEngine() {
		universe = new Universe();
		universe.createPlanets();
	}

	/**
	 * Static method that returns the instance of the GameEngine singleton
	 * 
	 * @return The only instance of the GameEngine singleton
	 */
	public static GameEngine getGameEngine() {
		if (gameEngine == null)
			gameEngine = new GameEngine();
		return gameEngine;
	}

	/**
	 * Creates the database for the GameEngine, given the username
	 * 
	 * @param username
	 *            The username for the logged in player
	 */
	public void createDatabase(String username) {
		database = new Database(username);
	}

	/**
	 * Whether or not the user is in the database already
	 * 
	 * @return Whether or not the user exists
	 */
	public boolean userExists() {
		return database.userExists();
	}

	/**
	 * Loads an existing game from the database by populating the proper classes
	 */
	public void loadGame(String playerName) {
		Object[] gameData = database.loadGame(playerName);
		player = (Player) gameData[0];
		universe = (Universe) gameData[1];
		tradeEngine = new TradeEngine(player);
		flightEngine = new FlightEngine(player, universe);
		fightEngine = new FightEngine(player);
	}

	/**
	 * Saves all the current game data to the database
	 */
	public void saveGame() {
		database.saveGame(universe, player);
	}

	/**
	 * Gets the players associated with a given user
	 * 
	 * @return The players associated with a given user
	 */
	public List<String> getUserPlayers() {
		return database.getUserPlayers();
	}

	/**
	 * Setter for the player for the GameEngine taking in all the player info
	 * from the view
	 * 
	 * @param name
	 *            The name of the player
	 * @param pilotSkill
	 *            The pilot skill of the player
	 * @param fightingSkill
	 *            The fighting skill of the player
	 * @param traderSkill
	 *            The trader skill of the player
	 * @param engineerSkill
	 *            The engineer skill of the player
	 * @param investorSkill
	 *            The investor skill of the player
	 */
	public void setPlayer(String name, int pilotSkill, int fightingSkill,
			int traderSkill, int engineerSkill, int investorSkill) {
		Ship s = new Ship(ShipType.GNAT);
		try {
			s.addLaser(LaserType.PULSELASER);
		} catch (MaxCapacityException m) {
			m.printStackTrace();
		}
		player = new Player(name, pilotSkill, fightingSkill, traderSkill,
				engineerSkill, investorSkill, s);
		player.setPlanet(universe.getPlanets().get(0));
		tradeEngine = new TradeEngine(player);
		flightEngine = new FlightEngine(player, universe);
		fightEngine = new FightEngine(player);
	}

	/**
	 * Initiates a buy or sell transaction between the player and the
	 * marketplace using TradeEngine
	 * 
	 * @param good
	 *            The TradeGood being bought or sold
	 * @param quantity
	 *            The quantity of the good being bought or sold
	 * @param buyingGood
	 *            Whether or not a good is being bought
	 * @return The errors from the transaction, if any
	 */
	public List<String> marketplaceTrade(GoodType good, int quantity,
			boolean buyingGood) {
		player.getShip();
		if (buyingGood)
			return tradeEngine.buy(good, quantity, player.getPlanet()
					.getMarketplace());
		return tradeEngine.sell(good, quantity, player.getPlanet()
				.getMarketplace());
	}

	/**
	 * Returns the main game player
	 * 
	 * @return The main game player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Returns the maximum good amount that can be bought or sold by the player
	 * for a given good
	 * 
	 * @param type
	 *            The type of good being bought or sold
	 * @param buying
	 *            Whether or not the player is buying
	 * @return The maximum amount that can be bought or sold by the player
	 */
	public int getMaximumGood(GoodType type, boolean buying) {
		if (buying)
			return tradeEngine.getMaximumBuyGoodAmount(type);
		return tradeEngine.getMaximumSellGoodAmount(type);
	}

	/**
	 * Returns the universe that the player is currently in
	 * 
	 * @return The universe that the player is currently in
	 */
	public Universe getUniverse() {
		return universe;
	}

	/**
	 * Returns the distance from the player's planet to the destination planet
	 * 
	 * @param destination
	 *            The planet the player wishes to travel to
	 * @return The distance to the given planet
	 */
	public int getDistanceToPlanet(Planet destination) {
		return flightEngine.getDistanceToPlanet(destination);
	}

	/**
	 * Player flies to a new planet
	 * 
	 * @param planet
	 *            The planet the player is going to
	 */
	public List<Encounter> goToPlanet(Planet planet) {
		return flightEngine.goToPlanet(planet);
	}

	/**
	 * Gets a planet at the given location
	 * 
	 * @param location
	 *            The location to find the planet
	 * @return The planet at the location, or null if no planet
	 */
	public Planet getPlanetAtLocation(Location location) {
		return universe.getPlanetAtLocation(location);
	}

	/**
	 * Gets all the planets within range of the player
	 * 
	 * @return All planets within range of the player's ship
	 */
	public List<Planet> getPlanetsWithinRange() {
		return new ArrayList<Planet>(flightEngine.getPlanetsWithinRange(
				universe, player.getPlanet()).keySet());
	}

	/**
	 * Method that represents a player attack during the given NPC encounter
	 * 
	 * @param encounter
	 *            The encounter that the player is involved in
	 * @return The result of the given attack
	 */
	public EncounterResult playerAttack(NPCEncounter npc) {
		return fightEngine.playerAttack(npc.getNPC());
	}

	/**
	 * Represents an attempt of the player to flee during an encounter
	 * 
	 * @param npc
	 *            The encounter the player is attempting to flee from
	 * @return The result of the flee attempt
	 */
	public EncounterResult playerFlee(NPCEncounter npc) {
		return fightEngine.playerFlee(npc.getNPC());
	}

	/**
	 * Represents a player attempting to bribe a police officer
	 * 
	 * @param encounter
	 *            The NPCEncounter that the player is involved with
	 * @param credits
	 *            The credits the player is offering the police officer
	 * @return Whether the bribe was successful
	 */
	public boolean bribePolice(NPCEncounter npc, int credits) {
		return fightEngine.bribePolice((Police) npc.getNPC(), credits);
	}

	/**
	 * Represents a player surrendering in the given encounter
	 * 
	 * @param npc
	 *            The encounter in which the player is surrendering
	 */
	public void surrenderToNPC(NPCEncounter npc) {
		fightEngine.playerSurrender(npc.getNPC());
	}

	/**
	 * Represents a player consenting to a police search
	 * 
	 * @param npc
	 *            The encounter in which the player is consenting
	 * 
	 * @return The success of the search in finding illegal goods, true if goods
	 *         found, false otherwise
	 */
	public boolean consentToSearch(NPCEncounter npc) {
		return fightEngine.consentToSearch((Police) (npc.getNPC()));
	}
	
	/**
	 * Represents a player wanting to trade with the trader
	 * 
	 * @param npc
	 *            The encounter in which the player is trading
	 */
	public void tradeWithTrader(NPCEncounter npc) {
		//tradeEngine.tradeWithTrader
	}
	
	/**
	 * Calculates and returns repair cost for player's ship
	 * @return repair cost
	 */
	public double getRepairCost() {
		return player.getCostRepair();
	}
	
	/**
	 * Calculates and returns refuel cost for player's ship
	 * @return refuel cost
	 */
	public double getRefuelCost() {
		return player.getCostRefuel();
	}
	
	/**
	 * Removes credits and refuels ship if player has enough credits
	 * 
	 * @param amountBy
	 *            credits used for payment
	 * @return true if transaction works, false if fails
	 */
	public boolean refuelShip(double credits) {
		return player.refuelShip(credits);
	}
	
	/**
	 * Removes credits and repairs ship if player has enough credits
	 * 
	 * @param amountBy
	 *            credits used for payment
	 * @return true if transaction works, false if fails
	 */
	public boolean repairShip(double credits) {
		return player.repairShip(credits);
	}
}