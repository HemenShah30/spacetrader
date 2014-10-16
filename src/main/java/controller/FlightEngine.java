package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.GoodType;
import model.Planet;
import model.Player;
import model.Ship;
import model.ShipType;
import model.Universe;

/**
 * Controller for traveling in game
 * 
 * @author Jack Croft
 *
 */
public class FlightEngine {
	private Ship ship;
	private Player player;
	private Universe universe;

	/**
	 * Constructor for the FlightEngine
	 * 
	 * @param s
	 *            The ship that's traveling in space
	 * @param p
	 *            The player piloting the ship
	 * @param u
	 *            The universe the ship is moving in
	 */
	public FlightEngine(Player p, Universe u) {
		player = p;
		ship = player.getShip();
		universe = u;
	}

	/**
	 * Returns a list of all planets within range of the origin planet given the
	 * amount of fuel in the ship
	 * 
	 * @param universe
	 *            The universe the planet is in
	 * @param origin
	 *            The origin planet
	 * @return All planets within travel range of the origin planet
	 */
	public Map<Planet, Integer> getPlanetsWithinRange(Universe universe,
			Planet origin) {
		Map<Planet, Integer> withinRange = new HashMap<Planet, Integer>();
		for (Planet planet : universe.getPlanets()) {
			double xDifferenceSquared = Math.pow(planet.getLocation().getX()
					- origin.getLocation().getX(), 2);
			double yDifferenceSquared = Math.pow(planet.getLocation().getY()
					- origin.getLocation().getY(), 2);
			int distance = (int) Math.ceil(Math.sqrt(xDifferenceSquared
					+ yDifferenceSquared));
			if (distance <= ship.getFuel())
				withinRange.put(planet, distance);
		}
		return withinRange;
	}

	/**
	 * Gets the distance from the player's current planet to another
	 * 
	 * @param destination
	 *            The destination planet
	 * @return The distance to the destination planet
	 */
	public int getDistanceToPlanet(Planet destination) {
		Planet origin = player.getPlanet();
		double xDifferenceSquared = Math.pow(destination.getLocation().getX()
				- origin.getLocation().getX(), 2);
		double yDifferenceSquared = Math.pow(destination.getLocation().getY()
				- origin.getLocation().getY(), 2);
		return (int) Math.ceil(Math.sqrt(xDifferenceSquared
				+ yDifferenceSquared));
	}

	/**
	 * Sends player to a different planet, calculates and creates possible
	 * encounters, and sets fuel to correct future value
	 * 
	 * @param p
	 *            The planet being clicked on to travel to
	 */
	public List<String> goToPlanet(Planet p) {
		Planet origin = player.getPlanet();
		List<String> ret = calculateEncounters(p);
		player.setPlanet(p);
		// encounters go here
		Map<Planet, Integer> withinRange = getPlanetsWithinRange(universe,
				origin);
		ship.setFuel(ship.getFuel() - withinRange.get(p));
		return ret;
	}

	/**
	 * Determines what happens at each possibility of an encounter. 70% chance
	 * of NPC, scaled to respective reps, 22% of npc-less encounter, 8% of
	 * nothing
	 * 
	 * @param p
	 *            the planet being traveled to
	 */
	private List<String> calculateEncounters(Planet p) {
		double totalSkill = (double) (player.getTraderRep()
				+ player.getPirateRep() + player.getPoliceRep());
		double trader = (player.getTraderRep() / totalSkill) * 0.70;
		double pirate = (player.getPirateRep() / totalSkill) * 0.70 + trader;
		double police = 0.70;
		double random = 0.92;
		List<String> ret = new ArrayList<String>();
		for (int i = 0; i < p.getChances(); i++) {
			double roll = Math.random();
			if (roll < trader) {
				ret.add("You encounter a traveling trader in space!");
				// trader encounter method
			} else if (roll >= trader && roll < pirate) {
				ret.add("You encounter a pirate in space!");
				// pirate encounter method
			} else if (roll >= pirate && roll < police) {
				ret.add("You are pulled over, figuratively, by some police!");
				// police encounter method
			} else if (roll >= police && roll < random) {
				ret.add(notNPCEncounter());
			}
		}
		return ret;
	}

	/**
	 * Randomly selects to give credits(20)/cargo(15)/weapon(5)/fuel(15), lose
	 * credits(5)/cargo(15)/fuel(15), take shield-ship damage(10),
	 * 
	 * @return The String representing what happened, to be displayed to the
	 *         player
	 */
	private String notNPCEncounter() {
		double roll = Math.random();
		String ret = "";
		if (roll < 0.20) {
			// give credits
			double amount = (int)(Math.random() * 2000) + 50;
			player.increaseCredits(amount);
			ret = "You find some credits floating in space!";
		} else if (roll >= 0.20 && roll < 0.35) {
			// give cargo
			GoodType[] goodTypes = GoodType.values();
			int index = (int) (Math.random() * goodTypes.length);
			GoodType good = goodTypes[index];
			int cargoCap = ship.getCargoSize();
			int currCargo = ship.getCurrCargo();
			int quantity = (int) (Math.random() * 0.20 * cargoCap);
			if (currCargo + quantity > cargoCap) {
				quantity = cargoCap - currCargo;
			}
			ship.addToCargo(good, quantity);
			ret = "You find some " + good.toString() + " floating in space!";
		} else if (roll >= 0.35 && roll < 0.40) {
			// give weapon
			// ret = "You find a " + weapon.toString() + " floating in space!";
		} else if (roll >= 0.40 && roll < 0.55) {
			// give fuel
			int currFuel = ship.getFuel();
			int amount = (int) (Math.random() / 2 * currFuel);
			ship.setFuel(currFuel + amount);
			ret = "You find some fuel floating in space!";
		} else if (roll >= 0.55 && roll < 0.60) {
			// lose credits
			double playerCred = player.getCredits();
			double amount = Math.random() * 0.40 * playerCred;
			player.decreaseCredits(amount);
			ret = "You find a computer floating in a piece of debris. You boot it up and quickly realize it's a virus! It manages to siphon off some of your credits before you jettison it out the trash chute. ";
		} else if (roll >= 0.60 && roll < 0.75) {
			// lose cargo
			Map<GoodType, Integer> cargo = ship.getCargo();
			GoodType[] playerGoods = (GoodType[]) cargo.values().toArray();
			GoodType good = playerGoods[((int) (Math.random() * playerGoods.length))];
			int quantity = cargo.get(good) * (int) (Math.random() * 0.50);
			ship.removeFromCargo(good, quantity);
			ret = "An asteroid crashes into your cargo hold, and some cargo slips out into space!";
		} else if (roll >= 0.75 && roll < 0.90) {
			// lose fuel
			int currFuel = ship.getFuel();
			int amount = (int) (Math.random() * 0.33 * currFuel);
			ship.setFuel(currFuel - amount);
			ret = "An asteroid crashes into your fuel tank, and some fuel leaks out into space!";
		} else {
			// take damage
			int newHP = ship.getCurrHP() - (int) (Math.ceil(Math.random() * 5));
			if (newHP <= 0) {
				// end game
			}
			ship.setCurrHP(newHP);
			ret = "An asteroid glances off your ship's hull, doing some minor damage!";
		}
		return ret;
	}
}