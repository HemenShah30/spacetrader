package model;

import java.util.Random;

/**
 * Class representing Police.
 * 
 * @author Eric Wan and Larry He
 * 
 */
public class Police extends NPC {
	private boolean willCheckCargo;
	private double minCredBribe; // minimum bribe required to avoid a search
	private Ship ship;
	private double credits;

	public Police(int rep) {
		setPilot((int) (rep * .6));
		setFighter((int) (rep * .6));
		setTrader(1);
		setEngineer(3);
		setInvestor(1);
		generateShip(rep);

	}

	/**
	 * Creates the ratio needed to bribe the officer
	 */
	private void generateBribeRatio() {

	}
	
	private void generateShip(int rep) {
		
	}
}
