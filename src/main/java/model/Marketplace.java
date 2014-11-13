package model;

import java.util.HashMap;
import java.util.Map;

import model.enums.GoodType;
import model.enums.TechLevel;

/**
 * Class representing the marketplace of a planet
 * 
 * @author Larry He
 * 
 */
public class Marketplace {
    private Map<GoodType, Double> prices;
    private Map<GoodType, Integer> quantities;
    private Planet planet;

    /**
     * Constructor for Marketplace that takes in the planet that it is on
     * 
     * @param planet
     *            The location of the marketplace
     */
    public Marketplace(Planet planet) {
        this.planet = planet;

        prices = new HashMap<GoodType, Double>();
        quantities = new HashMap<GoodType, Integer>();
        for (GoodType type : GoodType.values()) {
            int quantity = generateQuantity(type);
            quantities.put(type, quantity);
            double price = generateSellPrice(type);
            prices.put(type, price);
        }
    }

    /**
     * Generates the price of the good to be sold by the player at random based on various GoodType
     * parameters
     * 
     * @param good
     *            The TradeGood whose price is being determined
     * @return The price of the TradeGood
     */
    private double generateSellPrice(GoodType type) {
        TechLevel techlevel = planet.getTechLevel();
        double price = type.getBasePrice() + type.getPriceIncPerTechLevel()
                * (techlevel.getValue() - type.getMinTechLevelToProduce());
        price = price + (2 * Math.random() * type.getVariance() - type.getVariance());
        // price is decreased if a planet is the biggest producer of that good
        if (techlevel.getValue() == type.getBiggestProducer()) {
            price = price * (0.2 * Math.random() + 0.6);
        }
        // price is increased if condition affecting that good occurs
        if (planet.getCondition() == type.getCondition()) {
            price = price * (0.2 * Math.random() + 1.2);
        }
        // price is decreased if special resource affecting that good occurs
        if (planet.getResource() == type.getCheapResource()) {
            price = price * (0.2 * Math.random() + 0.6);
        }
        // price is increased if special resource affecting that good occurs
        if (planet.getResource() == type.getCheapResource()) {
            price = price * (0.2 * Math.random() + 1.2);
        }
        return (int) price;
    }

    /**
     * Generates the quantity of the good at random based on various GoodType parameters
     * 
     * @param good
     *            The TradeGood whose quantity is being determined
     * @return The quantity of the TradeGood
     */
    private int generateQuantity(GoodType type) {
        TechLevel techlevel = planet.getTechLevel();
        if (techlevel.getValue() < type.getMinTechLevelToProduce()) {
            return 0;
        }
        int quantity = type.getBaseQuantity() + type.getQuantityIncPerTechLevel()
                * (techlevel.getValue() - type.getMinTechLevelToProduce());
        quantity = quantity + (int) (10 * Math.random());
        // quantity is increased if a planet is the biggest producer of that
        if (techlevel.getValue() == type.getBiggestProducer()) {
            quantity = (int) (quantity * (0.5 * Math.random() + 1.5));
        }
        return quantity;
    }

    /**
     * Returns the quantity of a given good in the marketplace
     * 
     * @param good
     *            The good whose quantity is being asked for
     * @return The quantity of the given good
     */
    public int getQuantity(GoodType good) {
        return quantities.get(good);
    }

    /**
     * Returns the price of a given good in the marketplace to be sold by the player
     * 
     * @param good
     *            The good in the marketplace
     * @return The price of the good in the marketplace
     */
    public double getSellPrice(GoodType good) {
        if (good.getMinTechLevelToUse() > planet.getTechLevel().getValue()) {
            return -1;
        }
        return prices.get(good);
    }

    /**
     * Returns the price of a given good in the marketplace to be bought by the player. Changed by
     * the value of the player's trading skill
     * 
     * @param good
     *            The good to be bought
     * @param player
     *            The player buying the good
     * @return The price of the good
     */
    public double getBuyPrice(GoodType good, Player player) {
        if (good.getMinTechLevelToProduce() > planet.getTechLevel().getValue()) {
            return -1;
        }
        // Takes into account player's trade skill
        int buyPrice = (int) ((1 + (.025 * (10 - player.getTraderSkill()))) * prices.get(good));
        return buyPrice;
    }

    /**
     * Sets the quantity for the good type
     * 
     * @param good
     *            The good type to have its quantity changed
     * @param quantity
     *            The new quantity of the good type
     */
    public void setQuantity(GoodType good, int quantity) {
        quantities.put(good, quantity);
    }
}