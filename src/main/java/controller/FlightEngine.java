package controller;

import java.util.HashMap;
import java.util.Map;

import model.Planet;
import model.Player;
import model.Ship;
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
	public void goToPlanet(Planet p) {
		Planet origin = player.getPlanet();
		player.setPlanet(p);
		// encounters go here
		Map<Planet, Integer> withinRange = getPlanetsWithinRange(universe,
				origin);
		ship.setFuel(ship.getFuel() - withinRange.get(p));

	}
}