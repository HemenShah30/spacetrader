package model;

public interface Sellable {
	double getPrice();

	boolean canBuy(Ship ship);

	boolean canSell(Ship ship);

	int getMinTechLevel();
	
	int getType();
}
