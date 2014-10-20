package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import controller.GameEngine;
import model.Enum.EncounterType;
import model.Enum.GoodType;

/**
 * Class representing an Encounter with an NPC or other
 * 
 * @author Jack Croft
 *
 */
public class Encounter {
	protected EncounterType encounterType;

	/**
	 * Constructor for an Encounter taking a type and message
	 * 
	 * @param t
	 *            The type of encounter
	 */
	public Encounter(EncounterType t) {
		encounterType = t;
	}

	/**
	 * Returns the type of encounter
	 * 
	 * @return The type of encounter
	 */
	public EncounterType getEncounterType() {
		return encounterType;
	}

	/**
	 * Does the encounter logic and then returns the string describing the
	 * events of the encounter
	 * 
	 * @return The encounter event description
	 */
	public String doEncounter() {
		Player player = GameEngine.getGameEngine().getPlayer();
		Ship ship = player.getShip();
		switch (encounterType) {
		case GAINCREDITS: {
			int creditStandardDeviation = 300;
			int creditAverage = 1000;
			int amount = (int) (new Random().nextGaussian()
					* creditStandardDeviation + creditAverage);
			if (amount < 1)
				amount = 1;
			player.increaseCredits(amount);
			return "You find " + amount + " credits floating in space!";
		}
		case GAINCARGO: {
			GoodType[] goodTypes = GoodType.values();
			int index = (int) (Math.random() * goodTypes.length);
			GoodType good = goodTypes[index];
			double percentOfCargoFilled = .2;
			int cargoCapacity = ship.getCargoSize();
			int currCargo = ship.getCurrCargo();
			int quantity = (int) (Math.random() * percentOfCargoFilled * cargoCapacity);
			if (currCargo + quantity > cargoCapacity) {
				quantity = cargoCapacity - currCargo;
			}
			ship.addToCargo(good, quantity);
			return "You find " + quantity + " " + good.toString()
					+ " floating in space!";
		}
		case GAINWEAPON: {
			// check for free weapon slot, else return nothing
			// if free slot, then create a new weapon and add to the ship
			return "IMPLEMENTATION NEEDED";
		}
		case GAINFUEL: {
			double percentOfFuelLost = .5;
			int currFuel = ship.getFuel();
			int maxFuel = ship.getShipType().getFuel();
			int amount = (int) (Math.random() * percentOfFuelLost * currFuel);
			if (amount + currFuel > maxFuel)
				amount = maxFuel - currFuel;
			ship.setFuel(currFuel + amount);
			return "You find " + amount + " fuel floating in space!";
		}
		case LOSECREDITS: {
			double playerCred = player.getCredits();
			double percentOfMoneyLost = .4;
			int amount = (int) (Math.random() * percentOfMoneyLost * playerCred);
			player.decreaseCredits(amount);
			return "You find a computer floating in a piece of debris. You boot it up and quickly realize it's a virus! It manages to siphon off "
					+ amount
					+ " of your credits before you jettison it out the trash chute.";
		}
		case LOSECARGO: {
			double percentOfCargoLost = .5;
			List<GoodType> cargo = new ArrayList<GoodType>();
			for (GoodType g : GoodType.values()) {
				if (ship.amountInCargo(g) > 0)
					cargo.add(g);
			}
			if (cargo.size() == 0)
				return null;

			GoodType good = cargo.get((int) Math.random() * cargo.size());
			int quantity = (int) Math.ceil(ship.amountInCargo(good)
					* Math.random() * percentOfCargoLost);
			ship.removeFromCargo(good, quantity);
			return "An asteroid crashes into your cargo hold, and " + quantity
					+ " " + good.toString() + " slips out into space!";
		}
		case LOSEFUEL: {
			double percentOfFuelLost = 1 / 3.0;
			int currFuel = ship.getFuel();
			int amount = (int) Math.ceil(Math.random() * percentOfFuelLost
					* currFuel);
			ship.setFuel(currFuel - amount);
			return "An asteroid crashes into your fuel tank, and " + amount
					+ " fuel leaks out into space!";
		}
		case LOSEHEALTH: {
			int maxHealthLost = 5;
			int healthLost = Math.min(ship.getCurrHP() - 1,
					(int) (Math.ceil(Math.random() * maxHealthLost)));
			ship.setCurrHP(ship.getCurrHP() - healthLost);
			return "An asteroid glances off your ship's hull, doing "
					+ healthLost + " damage!";
		}
		default:
			return null;
		}
	}

	@Override
	public String toString() {
		return encounterType.toString();
	}
}