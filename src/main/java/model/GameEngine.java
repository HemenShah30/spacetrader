package model;

import java.util.List;

/**
 * The main game coordinator that will connect the views and the controllers
 * 
 * @author Jack Croft
 *
 */
public class GameEngine {
	private static GameEngine gameEngine;
	private TradeEngine tradeEngine;
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
	 * Setter for the player for the game engine
	 */
	public void setPlayer(String name, int pilotSkill, int fightingSkill,
			int traderSkill, int engineerSkill, int investorSkill) {
		Ship s = new Ship(ShipType.GNAT);
		player = new Player(name, pilotSkill, fightingSkill, traderSkill,
				engineerSkill, investorSkill, s);
		tradeEngine = new TradeEngine(player);
	}

	/**
	 * Initiates a buy or sell transaction between the player and the
	 * marketplace using TradeEngine
	 * 
	 * @param good
	 *            The TradeGood being bought or sold
	 * @param quantity
	 *            The quantity of the good being bought or sold
	 * @param market
	 *            The marketplace involved in the transaction
	 * @param buyingGood
	 *            Whether or not a good is being bought
	 * @return The errors from the transaction, if any
	 */
	public List<String> marketplaceTrade(TradeGood good, int quantity,
			Marketplace market, boolean buyingGood) {
		if (buyingGood)
			return tradeEngine.buy(good, quantity, market);
		return tradeEngine.sell(good, quantity, market);
	}
}