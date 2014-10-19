package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import model.Encounter;
import model.Planet;
import model.Player;
import model.Ship;
import model.Universe;
import model.Enum.EncounterType;
import model.Enum.GoodType;

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
	private final double NPCEncounterRate = .4;
	private final double otherEncounterRate = .1;

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
	public List<Encounter> goToPlanet(Planet p) {
		Planet origin = player.getPlanet();
		player.setPlanet(p);
		Map<Planet, Integer> withinRange = getPlanetsWithinRange(universe,
				origin);
		ship.setFuel(ship.getFuel() - withinRange.get(p));
		return calculateEncounters(p);
	}

	/**
	 * Determines what happens at each possibility of an encounter. 40% are NPC,
	 * 10% are non NPC, and 50% are that nothing happens
	 * 
	 * @param p
	 *            the planet being traveled to
	 * @return The list of Strings to be shown to the player describing what
	 *         they encounter
	 */
	private List<Encounter> calculateEncounters(Planet p) {
		List<Encounter> encounters = new ArrayList<Encounter>();
		for (int i = 0; i < p.getChances(); i++) {
			double roll = Math.random();
			if (roll < NPCEncounterRate) {
				Encounter encounter = getNPCEncounter(p);
				if (encounter != null)
					encounters.add(encounter);
			} else if (roll < NPCEncounterRate + otherEncounterRate) {
				Encounter encounter = getNonNPCEncounter();
				if (encounter != null)
					encounters.add(encounter);
			}
		}
		return encounters;
	}

	/**
	 * Calculates who the player encounters, based on the EncounterRate of the
	 * planet for each faction
	 * 
	 * @param planet
	 *            The destination planet after the encounters are overs
	 * @return An encounter with an NPC based on the planet's EncounterRates
	 */
	private Encounter getNPCEncounter(Planet planet) {
		double pirateRate = planet.getPirateEncounterRate().getValue();
		double traderRate = planet.getTraderEncounterRate().getValue();
		double policeRate = planet.getPoliceEncounterRate().getValue();
		if (pirateRate + traderRate + policeRate == 0)
			return null;

		double rateSum = pirateRate + traderRate + policeRate;
		double pirateChance = pirateRate / rateSum;
		double traderChance = traderRate / rateSum + pirateChance;
		double roll = Math.random();
		if (roll < pirateChance) {
			return new Encounter(
					EncounterType.PIRATE,
					"You glance at the viewing monitor and see a ship approaching with pirate markings!");
		} else if (roll < traderChance) {
			return new Encounter(
					EncounterType.TRADER,
					"You fly towards the unidentified blip on your radar and discover a traveling trader!");
		} else {
			return new Encounter(
					EncounterType.POLICE,
					"A police ship hails you and flashes it's blue lights in an attempt to pull you over!");
		}
	}

	public boolean five(int o) {
		if (o == 1)
			return true;
		else if (o == 2)
			return false;
		else
			return false;
	}

	/**
	 * Randomly selects an encounter for the player. The options are to give
	 * credits(20%)/cargo(15%)/a weapon(5%)/fuel(15%), or to lose
	 * credits(5%)/lose cargo(15%)/lose fuel(15%), take shield-ship damage(10%),
	 * 
	 * @return The Encounter representing what happened
	 */
	private Encounter getNonNPCEncounter() {
		double roll = Math.random();
		// rate is just the cutoff for the random number falling into the given
		// bracket
		double gainCreditRate = .2;
		double gainCargoRate = .35;
		double weaponRate = .4;
		double gainFuelRate = .55;
		double loseCreditRate = .6;
		double losecargoRate = .75;
		double loseFuelRate = .9;

		if (roll < gainCreditRate) {
			// give credits
			int creditStandardDeviation = 300;
			int creditAverage = 1000;
			int amount = (int) (new Random().nextGaussian()
					* creditStandardDeviation + creditAverage);
			if (amount < 1)
				amount = 1;
			player.increaseCredits(amount);
			return new Encounter(EncounterType.OTHER, "You find " + amount
					+ " credits floating in space!");
		} else if (roll < gainCargoRate) {
			// give cargo
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
			return new Encounter(EncounterType.OTHER, "You find " + quantity
					+ " " + good.toString() + " floating in space!");
		} else if (roll < weaponRate) {
			// give weapon
			// check for free weapon slot, else return nothing
			// create an encounter to find a new weapon in space
			return new Encounter(EncounterType.OTHER, "IMPLEMENTATION NEEDED");
		} else if (roll < gainFuelRate) {
			// give fuel
			double percentOfFuelLost = .5;
			int currFuel = ship.getFuel();
			int maxFuel = ship.getShipType().getFuel();
			int amount = (int) (Math.random() * percentOfFuelLost * currFuel);
			if (amount + currFuel > maxFuel)
				amount = maxFuel - currFuel;
			ship.setFuel(currFuel + amount);
			return new Encounter(EncounterType.OTHER, "You find " + amount
					+ " fuel floating in space!");
		} else if (roll < loseCreditRate) {
			// lose credits
			double playerCred = player.getCredits();
			double percentOfMoneyLost = .4;
			int amount = (int) (Math.random() * percentOfMoneyLost * playerCred);
			player.decreaseCredits(amount);
			return new Encounter(
					EncounterType.OTHER,
					"You find a computer floating in a piece of debris. You boot it up and quickly realize it's a virus! It manages to siphon off "
							+ amount
							+ " of your credits before you jettison it out the trash chute.");
		} else if (roll < losecargoRate) {
			// lose cargo
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
			return new Encounter(EncounterType.OTHER,
					"An asteroid crashes into your cargo hold, and " + quantity
							+ " " + good.toString() + " slips out into space!");
		} else if (roll < loseFuelRate) {
			// lose fuel
			double percentOfFuelLost = 1 / 3.0;
			int currFuel = ship.getFuel();
			int amount = (int) Math.ceil(Math.random() * percentOfFuelLost
					* currFuel);
			ship.setFuel(currFuel - amount);
			return new Encounter(EncounterType.OTHER,
					"An asteroid crashes into your fuel tank, and " + amount
							+ " fuel leaks out into space!");
		} else {
			// take damage
			int maxHealthLost = 5;
			int healthLost = Math.min(ship.getCurrHP() - 1,
					(int) (Math.ceil(Math.random() * maxHealthLost)));
			ship.setCurrHP(ship.getCurrHP() - healthLost);
			return new Encounter(EncounterType.OTHER,
					"An asteroid glances off your ship's hull, doing "
							+ healthLost + " damage!");
		}
	}
}