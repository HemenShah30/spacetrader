package model;

import java.util.Random;

/**
 * Class representing Pirates.
 * 
 * @author Eric Wan and Larry He
 * 
 */
public class Pirate extends NPC {
	private Ship ship;
	private double credits, chanceOfAttack;
	private boolean isAttacking;

	public Pirate(int rep) {
		setPilot((int) (rep * .6));
		setFighter((int) (rep * .6));
		setTrader(1);
		setEngineer(3);
		setInvestor(1);
		generateShip(rep);
		generateChance(rep);
	}

	/**
	 * Generates Pirate's ship based off player rep
	 * 
	 * @param rep
	 *            notoriety amongst pirates of Player
	 */
	private void generateShip(int rep) {
		ShipType[] shiptypes = ShipType.values();
		int index = (rep - 1) / 2;
		ShipType shiptype = shiptypes[index];
		ship = new Ship(shiptype);
	}

	/**
	 * Generates whether the Pirate attacks or not based off player rep
	 * 
	 * @param rep
	 *            notoriety amongst pirates of player
	 */
	private void generateChance(int rep) {
		chanceOfAttack = (double) (Math.random() / 2 + (rep / 20));
		if (Math.random() > chanceOfAttack) {
			isAttacking = false;
		} else {
			isAttacking = true;
		}
	}
}
