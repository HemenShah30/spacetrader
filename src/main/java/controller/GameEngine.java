package controller;

import database.Database;

import model.Encounter;
import model.Gadget;
import model.Location;
import model.MaxCapacityException;
import model.Mercenary;
import model.NPCEncounter;
import model.Planet;
import model.Player;
import model.Ship;
import model.ShipUpgrade;
import model.Trader;
import model.Universe;

import model.enums.EncounterResult;
import model.enums.GoodType;
import model.enums.LaserType;
import model.enums.ShieldType;
import model.enums.ShipType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private ShipyardEngine shipyardEngine;
    private MercenaryEngine mercenaryEngine;
    private Universe universe;
    private Player player;

    /**
     * Private constructor for the GameEngine preventing instantiation outside of the static method,
     * also creates the universe
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
        if (gameEngine == null) {
            gameEngine = new GameEngine();
        }
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
     * 
     * @param playerName
     *            The name of the player being loaded into the game
     */
    public void loadGame(String playerName) {
        Object[] gameData = database.loadGame(playerName);
        player = (Player) gameData[0];
        universe = (Universe) gameData[1];
        tradeEngine = new TradeEngine(player);
        flightEngine = new FlightEngine(player, universe);
        fightEngine = new FightEngine(player);
        shipyardEngine = new ShipyardEngine(player);
        mercenaryEngine = new MercenaryEngine(player);
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
     * Setter for the player for the GameEngine taking in all the player info from the view
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
    public void setPlayer(String name, int pilotSkill, int fightingSkill, int traderSkill,
            int engineerSkill, int investorSkill) {
        Ship ship = new Ship(ShipType.GNAT);
        try {
            ship.addLaser(LaserType.PULSELASER);
        } catch (MaxCapacityException m) {
            m.printStackTrace();
        }
        player = new Player(name, pilotSkill, fightingSkill, traderSkill, engineerSkill,
                investorSkill, ship);
        player.setPlanet(universe.getPlanets().get(0));
        tradeEngine = new TradeEngine(player);
        flightEngine = new FlightEngine(player, universe);
        fightEngine = new FightEngine(player);
        shipyardEngine = new ShipyardEngine(player);
        mercenaryEngine = new MercenaryEngine(player);
    }

    /**
     * Initiates a buy or sell transaction between the player and the marketplace using TradeEngine
     * 
     * @param good
     *            The TradeGood being bought or sold
     * @param quantity
     *            The quantity of the good being bought or sold
     * @param buyingGood
     *            Whether or not a good is being bought
     * @return The errors from the transaction, if any
     */
    public List<String> tradeWithMarketplace(GoodType good, int quantity, boolean buyingGood) {
        if (buyingGood) {
            return tradeEngine.buy(good, quantity, player.getPlanet().getMarketplace());
        }
        return tradeEngine.sell(good, quantity, player.getPlanet().getMarketplace());
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
     * Returns the maximum good amount that can be bought or sold by the player from a marketplace
     * for a given good
     * 
     * @param type
     *            The type of good being bought or sold
     * @param buying
     *            Whether or not the player is buying
     * @return The maximum amount that can be bought or sold by the player
     */
    public int getMaximumMarketplaceTradeAmount(GoodType type, boolean buying) {
        if (buying) {
            return tradeEngine.getMaximumBuyGoodAmount(type);
        }
        return tradeEngine.getMaximumSellGoodAmount(type);
    }

    /**
     * Executes a trade with a trader if possible, otherwise returns a list of error strings
     * 
     * @param npc
     *            The NPCEncounter that the player is involved in
     * @param quantity
     *            The quantity of the good the player is trying to trade
     * @return The list of error strings, if any, with the trade
     */
    public List<String> tradeWithTrader(NPCEncounter npc, int quantity) {
        return tradeEngine.tradeWithTrader((Trader) (npc.getNPC()), quantity);
    }

    /**
     * Returns the maximum good amount that can be traded with a trader in the given NPCEncounter
     * 
     * @param encounter
     *            The NPCEncounter that the player is having
     * @return The maximum amount of the good the player can buy or sell
     */
    public int getMaximumTraderTradeAmount(NPCEncounter encounter) {
        return tradeEngine.getMaximumTraderTradeAmount((Trader) encounter.getNPC());
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
     * @return The list of Encounters the player has along the trip to the planet
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
        return new ArrayList<Planet>(flightEngine.getPlanetsWithinRange(universe,
                player.getPlanet()).keySet());
    }

    /**
     * Method that represents a player attack during the given NPC encounter
     * 
     * @param npc
     *            The encounter that the player is involved in
     * @return The result of the given attack
     */
    public EncounterResult playerAttack(NPCEncounter npc) {
        return fightEngine.playerAttack(npc);
    }

    /**
     * Represents an attempt of the player to flee during an encounter
     * 
     * @param npc
     *            The encounter the player is attempting to flee from
     * @return The result of the flee attempt
     */
    public EncounterResult playerFlee(NPCEncounter npc) {
        return fightEngine.playerFlee(npc);
    }

    /**
     * Represents a player attempting to bribe a police officer
     * 
     * @param npc
     *            The NPCEncounter that the player is involved with
     * @param credits
     *            The credits the player is offering the police officer
     * @return Whether the bribe was successful
     */
    public boolean bribePolice(NPCEncounter npc, int credits) {
        return fightEngine.bribePolice(npc, credits);
    }

    /**
     * Represents a player surrendering in the given encounter
     * 
     * @param npc
     *            The encounter in which the player is surrendering
     */
    public void surrenderToNPC(NPCEncounter npc) {
        fightEngine.playerSurrender(npc);
    }

    /**
     * Represents a player consenting to a police search
     * 
     * @param npc
     *            The encounter in which the player is consenting
     * 
     * @return The success of the search in finding illegal goods, true if goods found, false
     *         otherwise
     */
    public boolean consentToSearch(NPCEncounter npc) {
        return fightEngine.consentToSearch(npc);
    }

    /**
     * Initiates a buy or sell transaction between the player and the shipyard using ShipyardEngine
     * 
     * @param type
     *            The type of ship being bought by the player
     * @param shipyard
     *            The shipyard involved in the transaction
     * @param isBuying
     *            whether player is buying or not (false: selling)
     * 
     * @return The errors from the transaction, if any
     */
    public List<String> tradeWithShipyard(ShipType type) {
        List<String> errors = new ArrayList<String>();
        double value = getPlayerAssetValue();
        errors = shipyardEngine.buy(type, player.getPlanet().getShipyard(), value);
        return errors;
    }

    /**
     * Calculates the value of the ship and all items on board
     * 
     * @return double value of ship/items
     */
    public double getPlayerAssetValue() {
        double value = player.getPlanet().getShipyard()
                .getSellPrice(player.getShip().getShipType());
        Map<GoodType, Integer> cargo = player.getShip().getCargo();
        for (GoodType each : cargo.keySet()) {
            if (each.getMinTechLevelToUse() <= player.getPlanet().getTechLevel().getValue()) {
                int quantity = cargo.get(each);
                for (int i = 0; i < quantity; i++) {
                    value += player.getPlanet().getMarketplace().getSellPrice(each);
                }
            }
        }
        // TODO: gadget/shield/weapon money
        return value;
    }

    /**
     * Initiates a ShipUpgrade transaction between a player and the shipyard
     * 
     * @param upgrade
     *            The ShipUpgrade being transacted
     * @param isBuying
     *            True for a buy, false for a sell
     * @return A list of errors, if any
     */
    public List<String> tradeShipUpgradeWithShipyard(ShipUpgrade upgrade, boolean isBuying) {
        if (isBuying) {
            return tradeEngine.buyShipUpgrade(upgrade, player.getPlanet().getShipyard());
        }
        return tradeEngine.sellShipUpgrade(upgrade, player.getPlanet().getShipyard());
    }

    /**
     * Returns the list of available ships to buy on the player's current planet
     * 
     * @return The list of available ships to buy on the player's current planet
     */
    public List<ShipType> getAvailableShips() {
        if (player.getPlanet().getShipyard() != null) {
            return shipyardEngine.getAvailableShips();
        }
        return null;
    }

    /**
     * Returns the list of available lasers to buy on the player's current planet
     * 
     * @return The list of available lasers to buy on the player's current planet
     */
    public List<LaserType> getAvailableLasers() {
        if (player.getPlanet().getShipyard() != null) {
            return shipyardEngine.getAvailableLasers();
        }
        return null;
    }

    /**
     * Returns the list of available shields to buy on the player's current planet
     * 
     * @return The list of available shields to buy on the player's current planet
     */
    public List<ShieldType> getAvailableShields() {
        if (player.getPlanet().getShipyard() != null) {
            return shipyardEngine.getAvailableShields();
        }
        return null;
    }

    /**
     * Returns the list of available gadgets to buy on the player's current planet
     * 
     * @return The list of available gadgets to buy on the player's current planet
     */
    public List<Gadget> getAvailableGadgets() {
        if (player.getPlanet().getShipyard() != null) {
            return shipyardEngine.getAvailableGadgets();
        }
        return null;
    }
    
    /**
     * Gets the planet's mercenaries
     * @return list of mercenaries
     */
    public List<Mercenary> getPlanetMercenaries() {
        if (player.getPlanet().getBar() != null) {
            return mercenaryEngine.getPlanetMercenaries();
        }
        return null;
    }

    /**
     * Hires a mercenary
     * @param merc Mercenary to be hired
     * @return errors List of errors that occurred
     */
    public List<String> hireMercenary(Mercenary merc) {
        return mercenaryEngine.hire(merc, player.getPlanet().getBar());
    }
    
    /**
     * Fires a mercenary
     * @param merc Mercenary to be fired
     */
    public void fireMercenary(Mercenary merc) {
        mercenaryEngine.fire(merc, player.getPlanet().getBar());
    }
    
    /**
     * Pays the mercenaries on the ship
     * @return payment The payment the player makes. Returns -1 if the player can't afford.
     */
    public int payMercenaries() {
        return mercenaryEngine.pay();
    }
}