package model;

import java.util.Random;

/**
 * Class representing a trade good.
 * 
 * @author Larry He
 * 
 */
public class TradeGood {
	private GoodType type;
	private int price;
	private int quantity;
	
	public TradeGood(GoodType type) {
		this.type = type;
	}
	
	public int generatePrice(Planet planet) {
		price = type.getBasePrice();
		return price;
	}
	
	public int getPrice() {
		return price;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "Type: " + type + ", Price: " + price;
	}
}
