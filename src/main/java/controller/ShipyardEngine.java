package controller;

import model.Gadget;
import model.Player;
import model.Ship;
import model.Shipyard;

import model.enums.LaserType;
import model.enums.ShieldType;
import model.enums.ShipType;

import java.util.ArrayList;
import java.util.List;

/**
 * Class providing methods for shipyard interactions
 * 
 * @author Larry He
 * 
 */

public class ShipyardEngine {

    private Player player;
    private Ship ship;

    /**
     * Constructor for the ShipyardEngine, taking in the main game player
     * 
     * @param p
     *            The player that will be trading with the shipyard
     */
    public ShipyardEngine(Player player) {
        this.player = player;
        ship = player.getShip();
    }

    /**
     * Attempts a buy transaction between a shipyard and the player
     * 
     * @param type
     *            The type of ship being bought by the player
     * @param shipyard
     *            The shipyard involved in the transaction
     * @return The errors from the transaction, if any
     */
    public List<String> buy(ShipType type, Shipyard shipyard, double playerAssetValue) {
        double cost = shipyard.getBuyPrice(type);
        List<String> errors = validateBuy(cost, playerAssetValue);
        if (errors.isEmpty()) {
            cost -= playerAssetValue;
            ship = new Ship(type);
            player.setShip(ship);
            player.decreaseCredits(cost);
        }
        return errors;
    }

    /**
     * Internal method for validating a buy transaction
     * 
     * @param cost
     *            The cost of the ship
     * @param playerAssetValue
     *            the value of the player's current assets
     * @return Any errors from the buy, if any
     */
    private List<String> validateBuy(double cost, double playerAssetValue) {
        List<String> errors = new ArrayList<String>();

        if (player.getCredits() < cost - playerAssetValue) {
            errors.add("Not enough credits");
        }
        if (cost == -1) {
            errors.add("Planet cannot sell this ship");
        }
        return errors;
    }

    /**
     * Returns the list of available ships to buy on the player's current planet
     * 
     * @return The list of available ships to buy on the player's current planet
     */
    public List<ShipType> getAvailableShips() {
        List<ShipType> ships = new ArrayList<ShipType>();
        int techLevel = player.getPlanet().getTechLevel().getValue();
        for (ShipType ship : ShipType.values()) {
            if (ship.getMinTechLevel() <= techLevel) {
                ships.add(ship);
            }
        }
        return ships;
    }

    /**
     * Returns the list of available lasers to buy on the player's current planet
     * 
     * @return The list of available lasers to buy on the player's current planet
     */
    public List<LaserType> getAvailableLasers() {
        List<LaserType> lasers = new ArrayList<LaserType>();
        int techLevel = player.getPlanet().getTechLevel().getValue();
        for (LaserType laser : LaserType.values()) {
            if (laser.getMinTechLevel() <= techLevel) {
                lasers.add(laser);
            }
        }
        return lasers;
    }

    /**
     * Returns the list of available shields to buy on the player's current planet
     * 
     * @return The list of available shields to buy on the player's current planet
     */
    public List<ShieldType> getAvailableShields() {
        List<ShieldType> shields = new ArrayList<ShieldType>();
        int techLevel = player.getPlanet().getTechLevel().getValue();
        for (ShieldType shield : ShieldType.values()) {
            if (shield.getMinTechLevel() <= techLevel) {
                shields.add(shield);
            }
        }
        return shields;
    }

    /**
     * Returns the list of available gadgets to buy on the player's current planet
     * 
     * @return The list of available gadgets to buy on the player's current planet
     */
    public List<Gadget> getAvailableGadgets() {
        List<Gadget> gadgets = Gadget.getAllGadgets();
        int techLevel = player.getPlanet().getTechLevel().getValue();
        for (int i = 0; i < gadgets.size(); i++) {
            if (techLevel < gadgets.get(i).getMinTechLevel()) {
                gadgets.remove(i);
                i--;
            }
        }
        return gadgets;
    }
}