package model;

import java.util.ArrayList;

import model.Enum.EncounterType;
import model.Enum.GoodType;
import model.Enum.LaserType;
import model.Enum.ShieldType;
import model.Enum.ShipType;

/**
 * Abstract class representing NPCs.
 * 
 * @author Eric Wan and Larry He
 * 
 */
public abstract class NPC {
	private int threatLevel; // represents how dangerous the ship is.
								// ex. if player has bad reputation, the
								// threatlevel of police ships will be higher,
								// resulting in a tougher enemy
	private int pilotSkill, fighterSkill, traderSkill, engineerSkill,
			investorSkill;
	private double credits;
	private Ship ship;

	/**
	 * Returns the NPC's piloting skill
	 * 
	 * @return The NPC's piloting skill
	 */
	public int getPilotSkill() {
		return pilotSkill;
	}

	/**
	 * Returns the NPC's fighter skill
	 * 
	 * @return The NPC's fighter skill
	 */
	public int getFighterSkill() {
		return fighterSkill;
	}

	/**
	 * Returns the NPC's trader skill
	 * 
	 * @return The NPC's trader skill
	 */
	public int getTraderSkill() {
		return traderSkill;
	}

	/**
	 * Returns the NPC's Engineer skill
	 * 
	 * @return The NPC's engineer skill
	 */
	public int getEngineerSkill() {
		return engineerSkill;
	}

	/**
	 * Returns the NPC's investor skill
	 * 
	 * @return The NPC's investor skill
	 */
	public int getInvestorSkill() {
		return investorSkill;
	}

	/**
	 * Returns the NPC's credits
	 * 
	 * @return The NPC's credits
	 */
	public double getCredits() {
		return credits;
	}

	/**
	 * Sets the NPC's pilot skill points
	 * 
	 * @param points
	 *            The NPC's new pilot skill points
	 */
	public void setPilotSkill(int points) {
		pilotSkill = points;
	}

	/**
	 * Sets the NPC's fighter skill points
	 * 
	 * @param points
	 *            The NPC's new fighter skill points
	 */
	public void setFighterSkill(int points) {
		fighterSkill = points;
	}

	/**
	 * Sets the NPC's trader skill points
	 * 
	 * @param points
	 *            The NPC's new trader skill points
	 */
	public void setTraderSkill(int points) {
		traderSkill = points;
	}

	/**
	 * Sets the NPC's engineer skill points
	 * 
	 * @param points
	 *            The NPC's new engineer skill points
	 */
	public void setEngineerSkill(int points) {
		engineerSkill = points;
	}

	/**
	 * Sets the NPC's investor skill points
	 * 
	 * @param points
	 *            The NPC's new investor skill points
	 */
	public void setInvestorSkill(int points) {
		investorSkill = points;
	}

	/**
	 * Randomly generates the ship the NPC is using given the player reputation
	 * 
	 * @param rep
	 *            The reputation of the player
	 * @param type
	 *            The type of encounter the NPC is
	 */
	protected void generateShip(int rep, EncounterType type) {
		Ship s;
		int index;
		// generate a ship type
		ShipType[] shipTypes = ShipType.values();
		boolean shipTypeGenerated = false;
		while (!shipTypeGenerated) {
			index = (int) (Math.random() * shipTypes.length);
			if (shipTypes[index].getMinRep(type) <= rep
					&& shipTypes[index].getMaxRep(type) >= rep) {
				s = new Ship(shipTypes[index]);
				shipTypeGenerated = true;
				setShip(s);
			}
		}

		// generate a weapon
		LaserType[] laserTypes = LaserType.values();
		int weaponCapacity = getShip().getShipType().getWeaponSlots();
		for (int i = 0; i < weaponCapacity; i++) {
			index = (int) (Math.random() * laserTypes.length);
			if (laserTypes[index].getMinRep(type) <= rep
					&& laserTypes[index].getMaxRep(type) >= rep) {
				try {
					ship.addLaser(laserTypes[index]);
				} catch (MaxCapacityException m) {
					System.out.println("Over the max capacity for lasers!");
					m.printStackTrace();
				}
			}
		}

		// generate shields
		ShieldType[] shieldTypes = ShieldType.values();
		int shieldCapacity = getShip().getShipType().getShieldSlots();
		for (int i = 0; i < shieldCapacity; i++) {
			index = (int) (Math.random() * shieldTypes.length);
			if (shieldTypes[index].getMinRep(type) <= rep
					&& shieldTypes[index].getMaxRep(type) >= rep) {
				try {
					ship.addShield(shieldTypes[index]);
				} catch (MaxCapacityException m) {
					System.out.println("Over the max capacity for shields!");
					m.printStackTrace();
				}
			}
		}

		// generate cargo
		GoodType[] goodTypes = GoodType.values();
		int cargoCapacity = getShip().getShipType().getCargoSize();
		boolean addedAllowedGood = false;
		while(!addedAllowedGood) {
			index = (int) (Math.random() * goodTypes.length);
			if (goodTypes[index].getNPCAmount(type) != -1) {
				int goodAmount = goodTypes[index].getNPCAmount(type);
				if (goodAmount > cargoCapacity) {
					goodAmount = cargoCapacity;
				}
				ship.addToCargo(goodTypes[index], goodAmount);
				addedAllowedGood = true;
			}
		}
		
		// generate gadgets
		ArrayList<Gadget> gadgetTypes = new ArrayList<>(5);
		gadgetTypes.add(new AutoRepairSystem());
		gadgetTypes.add(new CloakingDevice());
		gadgetTypes.add(new FiveExtraCargo());
		gadgetTypes.add(new NavigatingSystem());
		gadgetTypes.add(new TargetingSystem());
		int gadgetCapacity = getShip().getShipType().getGadgetSlots();
		for (int i = 0; i < gadgetCapacity; i++) {
			index = (int) (Math.random() * shieldTypes.length);
			try {
				ship.addGadget(gadgetTypes.get(index));
			} catch (MaxCapacityException m) {
				System.out.println("Over the max capacity for gadgets!");
				m.printStackTrace();
			}
		}
		System.out.println("Encounter type: " + type);
		System.out.println("Ship description\n" + ship.toString());
	}

	/**
	 * Gets the ship of the NPC
	 * 
	 * @return Ship of the NPC
	 */
	public Ship getShip() {
		return ship;
	}

	/**
	 * Sets the NPCs credits
	 * 
	 * @param credits
	 *            NPC's credits
	 */
	public void setCredits(double credits) {
		this.credits = credits;
	}

	/**
	 * Sets the NPCs ship
	 * 
	 * @param ship
	 *            the ship
	 */
	public void setShip(Ship ship) {
		this.ship = ship;
	}
}
