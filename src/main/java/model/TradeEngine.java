package model;

import java.util.ArrayList;

/**
 * @author Eric Wan
 *
 * Class providing methods for trade interactions
 */
public class TradeEngine {
	
	private ArrayList<String> errors;
	private Player p;
	private Ship ship;
//	private Planet planet;
	
	public TradeEngine(Player p) {
		this.p = p;
		ship = p.getShip();
		errors = new ArrayList<String>();
	}

	public ArrayList<String> buy(TradeGood goodType, int quantity, MarketPlace market) {
		errors.clear();
		double cost = market.generatePrice(goodType) * quantity;
		validateBuy(cost, quantity);
		if (errors.isEmpty()) {
			ship.addToCargo(goodType, quantity);
			p.decreaseCredits(cost);
		}
		return errors;
	}
	
	private void validateBuy(double cost, int quantity) {
		if (p.getCredits() < cost) {
			errors.add("Not enough credits");
		}
		if (ship.getCurrCargo() + quantity > ship.getCargoSize()) {
			errors.add("Not enough space");
		}
	}
	
	public ArrayList<String> sell(TradeGood goodType, int quantity, MarketPlace market) {
		errors.clear();
		double cost = market.generatePrice(goodType) * quantity;
		validateSell(goodType, quantity);
		if (errors.isEmpty()) {
			ship.removeFromCargo(goodType, quantity);
			p.increaseCredits(cost);
		}
		return errors;
	}
	
	private void validateSell(TradeGood goodType, int quantity) {
		if (ship.amountInCargo(goodType) == 0) {
			errors.add("You have no " + goodType.toString());
		} else if (ship.amountInCargo(goodType) - quantity < 0) {
			errors.add("You do not have that many of " + goodType.toString());
		}
	}
}
