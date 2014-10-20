package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Encounter;
import model.NPCEncounter;
import model.Pirate;
import model.Planet;
import model.Player;
import model.Police;
import model.Ship;
import model.Trader;
import model.Universe;
import model.Enum.EncounterType;

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
			return new NPCEncounter(EncounterType.PIRATE, new Pirate(
					player.getPirateRep()));
		} else if (roll < traderChance) {
			return new NPCEncounter(EncounterType.TRADER, new Trader(
					player.getTraderRep()));
		} else {
			return new NPCEncounter(EncounterType.POLICE, new Police(
					player.getPoliceRep(), player.getCredits()));
		}
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
			return new Encounter(EncounterType.GAINCREDITS);
		} else if (roll < gainCargoRate) {
			return new Encounter(EncounterType.GAINCARGO);
		} else if (roll < weaponRate) {
			return new Encounter(EncounterType.GAINWEAPON);
		} else if (roll < gainFuelRate) {
			return new Encounter(EncounterType.GAINFUEL);
		} else if (roll < loseCreditRate) {
			return new Encounter(EncounterType.LOSECREDITS);
		} else if (roll < losecargoRate) {
			return new Encounter(EncounterType.LOSECARGO);
		} else if (roll < loseFuelRate) {
			return new Encounter(EncounterType.LOSEFUEL);
		} else {
			return new Encounter(EncounterType.LOSEHEALTH);
		}
	}
}