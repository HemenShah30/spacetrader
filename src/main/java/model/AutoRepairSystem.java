package model;

public class AutoRepairSystem extends Gadget {

	public static final int PRICE = 25000;
	public static final int MIN_TECH_LEVEL = 5;
	
	public AutoRepairSystem() {
		super(PRICE, MIN_TECH_LEVEL);
	}
}