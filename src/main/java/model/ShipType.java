package model;

/**
 * Enum representing the various ship types
 * 
 * @author Jack Croft
 *
 */
public enum ShipType {
	FLEA, GNAT, FIREFLY, BUMBLEBEE, MOSQUITO;
	
	private int fuel;
	private int totalHP;
	private int cargoSize;
	private int weaponSlots;
	private int shieldSlots;
	private int gadgetSlots;
	private int crewSpace;
	private int minTechLevel;
	private int fuelCost;
	private int price;
	private int bounty;
	private int occurence;
	private int policeModifier;
	private int pirateModifier;
	private int traderModifier;
	private int repairCost;
	private int size;
}