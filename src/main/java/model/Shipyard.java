package model;

import java.util.HashMap;
import java.util.Map;

import model.Enum.ShipType;

/**
 * Class representing the shipyard of a planet
 * 
 * @author Larry He
 * 
 */
public class Shipyard {
    private static final double sellPricePenalty = 0.9;
    private Map<ShipType, Double> prices;
    private Planet planet;

    public Shipyard(Planet planet) {
        this.planet = planet;

        prices = new HashMap<ShipType, Double>();
        for (ShipType type : ShipType.values()) {
            double price = generateSellPrice(type);
            prices.put(type, price);
        }
    }

    /**
     * Generates the price of the ship to be sold by the player Note: This method was created in
     * case we make other factors impact the price
     * 
     * @param type
     *            The ship whose price is being determined
     * @return The price of the ship
     */
    private double generateSellPrice(ShipType type) {
        return type.getPrice();
    }

    /**
     * Returns the purchase price of a given ship in the shipyard to be bought by the player
     * 
     * @param type
     *            The type of ship in the shipyard
     * @return The purchase price of the ship in the shipyard
     */
    public double getBuyPrice(ShipType type) {
        if (type.getMinTechLevel() > planet.getTechLevel().getValue())
            return -1;
        return prices.get(type);
    }

    /**
     * Returns the sell price of a given ship in the shipyard to be sold by the player A penalty to
     * the amount of credits you get back is applied
     * 
     * @param type
     *            The type of ship in the shipyard
     * @return The sell price of the ship in the shipyard
     */
    public double getSellPrice(ShipType type) {
        return prices.get(type) * sellPricePenalty;
    }

    /**
     * Returns buy price of a given ShipUpgrade in the shipyard
     * 
     * @param upgrade
     *            the ShipUpgrade being purchased
     * @return the price in double
     */
    public double getShipUpgradeBuyPrice(ShipUpgrade upgrade) {
        if (upgrade.getMinTechLevel() > planet.getTechLevel().getValue()) {
            return -1;
        }
        return upgrade.getPrice();
    }

    /**
     * Returns the sell price of a given ShipUpgrade in the shipyard, with the penalty applied
     * 
     * @param upgrade
     *            the ShipUpgrade being sold
     * @return the sell price in double
     */
    public double getSellableSell(ShipUpgrade upgrade) {
        return upgrade.getPrice() * sellPricePenalty;
    }
}
