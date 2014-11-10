package model;

public interface ShipUpgrade {
	
	/**
	 * 
	 * @return
	 */
	public double getPrice();

	/**
	 * 
	 * @param ship
	 * @return
	 */
	public boolean canBuy(Ship ship);

	/**
	 * 
	 * @param ship
	 * @return
	 */
	public boolean canSell(Ship ship);

	/**
	 * 
	 * @return
	 */
	public int getMinTechLevel();
	
	/**
	 * 
	 * @return
	 */
	public int getType();
}
