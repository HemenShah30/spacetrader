package controller;

import java.util.ArrayList;
import java.util.List;

import model.Marketplace;
import model.Player;
import model.ShipUpgrade;
import model.Ship;
import model.Shipyard;
import model.Trader;
import model.Enum.GoodType;

/**
 * Class providing methods for trade interactions
 * 
 * @author Eric Wan
 * 
 */
public class TradeEngine {

    private Player player;

    /**
     * Constructor for the TradeEngine, taking in the main game player
     * 
     * @param p
     *            The player that will be trading with the marketplace
     */
    public TradeEngine(Player player) {
        this.player = player;
    }

    /**
     * Attempts a buy transaction between a marketplace and the player
     * 
     * @param tradeGood
     *            The good being bought by the player
     * @param quantity
     *            The quantity of the good being bought
     * @param market
     *            The marketplace involved in the transaction
     * @return The errors from the transaction, if any
     */
    public List<String> buy(GoodType tradeGood, int quantity, Marketplace market) {
        Ship ship = player.getShip();
        double cost = market.getBuyPrice(tradeGood, player) * quantity;
        List<String> errors = validateBuy(cost, quantity);
        if (errors.isEmpty()) {
            ship.addToCargo(tradeGood, quantity);
            player.decreaseCredits(cost);
            market.setQuantity(tradeGood, market.getQuantity(tradeGood)
                    - quantity);
        }
        return errors;
    }

    /**
     * Internal method for validating a buy transaction
     * 
     * @param cost
     *            The cost of the good
     * @param quantity
     *            The quantity of the good
     * @return Any errors from the buy, if any
     */
    private List<String> validateBuy(double cost, int quantity) {
        Ship ship = player.getShip();
        List<String> errors = new ArrayList<String>();

        if (player.getCredits() < cost) {
            errors.add("Not enough credits");
        }
        if (ship.getCurrCargo() + quantity > ship.getCargoSize()) {
            errors.add("Not enough space in your ship");
        }
        if (cost == -1)
            errors.add("Planet cannot sell this good");
        return errors;
    }

    /**
     * Attempts a sell transaction between the player and the marketplace
     * 
     * @param tradeGood
     *            The good being sold by the player
     * @param quantity
     *            The quantity of the good being sold
     * @param market
     *            the marketplace involved in the transaction
     * @return The errors from the sell, if any
     */
    public List<String> sell(GoodType tradeGood, int quantity,
            Marketplace market) {
        Ship ship = player.getShip();
        double cost = market.getSellPrice(tradeGood) * quantity;
        List<String> errors = validateSell(tradeGood, quantity, market);
        if (errors.isEmpty()) {
            // if clause makes sure that goods dont get added to marketPlace
            // if it cant sell,
            // it still gets removed from player
            if (market.getBuyPrice(tradeGood, player) != -1) {
                market.setQuantity(tradeGood, market.getQuantity(tradeGood)
                        + quantity);
            }
            ship.removeFromCargo(tradeGood, quantity);
            player.increaseCredits(cost);
        }
        return errors;
    }

    /**
     * Validates a sell transaction
     * 
     * @param tradeGood
     *            The good being sold by the user
     * @param quantity
     *            The quantity of the good being sold
     * @return The errors from the sell, if any
     */
    private List<String> validateSell(GoodType tradeGood, int quantity,
            Marketplace market) {
        Ship ship = player.getShip();
        List<String> errors = new ArrayList<String>();

        if (ship.amountInCargo(tradeGood) == 0) {
            errors.add("You have no " + tradeGood + " to sell");
        } else if (ship.amountInCargo(tradeGood) - quantity < 0) {
            errors.add("You do not have that many of " + tradeGood);
        }
        if (market.getSellPrice(tradeGood) == -1)
            errors.add("Planet cannot purchase this good");

        return errors;
    }

    /**
     * Returns the maximum amount of a good a player can buy from the
     * marketplace
     * 
     * @param good
     *            The good being bought
     * @return The amount of the good the user can buy
     */
    public int getMaximumBuyGoodAmount(GoodType good) {
        Ship ship = player.getShip();
        Marketplace market = player.getPlanet().getMarketplace();
        if (market.getBuyPrice(good, player) == -1)
            return 0;
        return Math.min(Math.min(ship.getCargoSize() - ship.getCurrCargo(),
                market.getQuantity(good)),
                ((int) player.getCredits() / (int) market.getBuyPrice(good,
                        player)));
    }

    /**
     * Returns the maximum amount of a good that a player can sell to a
     * marketplace
     * 
     * @param good
     *            The good being sold
     * @return The amount of a good that a user can sell
     */
    public int getMaximumSellGoodAmount(GoodType good) {
        Ship ship = player.getShip();
        Marketplace market = player.getPlanet().getMarketplace();
        if (market.getSellPrice(good) == -1)
            return 0;
        return ship.amountInCargo(good);
    }

    /**
     * Returns the maximum amount of good the given trader can buy or sell with
     * the player
     * 
     * @param trader
     *            The trader trading with the player
     * @return The maximum transaction amount with the player
     */
    public int getMaximumTraderTradeAmount(Trader trader) {
        Ship ship = player.getShip();
        if (trader.isBuying()) {
            return Math.min(ship.amountInCargo(trader.getGoodOfInterest()),
                    trader.getQuantity());
        } else {
            return Math.min(
                    Math.min(ship.getCargoSize() - ship.getCurrCargo(),
                            trader.getQuantity()),
                    (int) (player.getCredits() / trader.getPrice()));
        }
    }

    /**
     * Executes a trade with a trader if possible, or returns a list of error
     * strings
     * 
     * @param trader
     *            The trader who the player is trading with
     * @param quantity
     *            The quantity of the good the player is trying to trade
     * @return The list of errors, if any, with the trade
     */
    public List<String> tradeWithTrader(Trader trader, int quantity) {
        Ship ship = player.getShip();   
        List<String> errors = new ArrayList<String>();
        if (quantity > trader.getQuantity())
            errors.add("Trader will not "
                    + (trader.isBuying() ? "buy" : "sell") + " this much "
                    + trader.getGoodOfInterest());
        if (trader.isBuying()) {
            if (ship.amountInCargo(trader.getGoodOfInterest()) < quantity) {
                errors.add("You do not have " + quantity + " "
                        + trader.getGoodOfInterest() + " to sell");
            }
        } else {
            if (player.getCredits() < quantity * trader.getPrice())
                errors.add("You do not have enough credits to buy " + quantity
                        + " " + trader.getGoodOfInterest());
            if (ship.getCargoSize() - ship.getCurrCargo() < quantity)
                errors.add("Your ship cannot hold " + quantity + " more "
                        + trader.getGoodOfInterest());
        }

        if (errors.size() == 0) {
            if (trader.isBuying()) {
                ship.removeFromCargo(trader.getGoodOfInterest(), quantity);
                player.increaseCredits(quantity * trader.getPrice());
            } else {
                ship.addToCargo(trader.getGoodOfInterest(), quantity);
                player.decreaseCredits(quantity * trader.getPrice());
            }
        }

        return errors;
    }

    /**
     * Attempts a ShipUpgrade buy transaction between a shipyard and the player
     * 
     * @param upgrade
     *            The upgrade being bought by the player
     * @param shipyard
     *            The shipyard involved in the transaction
     * @return The errors from the transaction, if any
     */
    public List<String> buyShipUpgrade(ShipUpgrade upgrade, Shipyard shipyard) {
        Ship ship = player.getShip();
        double cost = shipyard.getShipUpgradeBuyPrice(upgrade);
        List<String> errors = validateShipUpgradeBuy(cost, upgrade);
        if (errors.isEmpty()) {
            ship.addShipUpgrade(upgrade);
            player.decreaseCredits(cost);
        }
        return errors;
    }

    /**
     * Returns the list of errors generated by attempting a ShipUpgrade buy
     * transaction
     * 
     * @param cost
     *            Cost in double of ShipUpgrade
     * @param upgrade
     *            The ShipUpgrade being bought
     * @return List<String> of errors
     */
    private List<String> validateShipUpgradeBuy(double cost, ShipUpgrade upgrade) {
        Ship ship = player.getShip();
        List<String> errors = new ArrayList<String>();

        if (player.getCredits() < cost) {
            errors.add("Not enough credits");
        }
        if (!upgrade.canBuy(ship)) {
            errors.add("Not enough space in your ship");
        }
        if (cost == -1)
            errors.add("Planet cannot sell this upgrade");
        return errors;
    }

    /**
     * Attempts a ShipUpgrade sell transaction between the player and the
     * shipyard
     * 
     * @param upgrade
     *            The upgrade being sold by the player
     * @param market
     *            the marketplace involved in the transaction
     * @return The errors from the sell, if any
     */
    public List<String> sellShipUpgrade(ShipUpgrade upgrade, Shipyard shipyard) {
        Ship ship = player.getShip();
        double cost = shipyard.getSellableSell(upgrade);
        List<String> errors = validateShipUpgradeSell(cost, upgrade);
        if (errors.isEmpty()) {
            ship.removeShipUpgrade(upgrade);
            player.increaseCredits(cost);
        }
        return errors;
    }

    /**
     * Validates a ShipUpgrade sell transaction
     * 
     * @param cost
     *            The cost of the ShipUpgrade
     * @param upgrade
     *            The ShipUpgrade being sold
     * @return The errors from the sell, if any
     */
    private List<String> validateShipUpgradeSell(double cost, ShipUpgrade upgrade) {
        Ship ship = player.getShip();
        List<String> errors = new ArrayList<String>();

        if (!upgrade.canSell(ship)) {
            errors.add("You do not have any to sell");
        }
        if (cost == -1)
            errors.add("Planet cannot purchase this upgrade");

        return errors;
    }
}