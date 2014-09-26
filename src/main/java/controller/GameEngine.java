package controller;

import java.util.List;

import model.GoodType;
import model.Player;
import model.Ship;
import model.ShipType;
import model.Universe;

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
		player = new Player(name, pilotSkill, fightingSkill, traderSkill,
				engineerSkill, investorSkill, s);
		player.setPlanet(universe.getPlanets().get(0));
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
		return 40000;
	}
}