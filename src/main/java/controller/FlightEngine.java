package controller;

import java.util.ArrayList;
import java.util.List;

import model.Planet;
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

	/**
	 * Constructor for the flight engine, taking in a given ship
	 * 
	 * @param s
	 */
	public FlightEngine(Ship s) {
		ship = s;
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
	public List<Planet> getPlanetsWithinRange(Universe universe, Planet origin) {
		List<Planet> withinRange = new ArrayList<Planet>();
		for (Planet planet : universe.getPlanets()) {
			double xDifferenceSquared = Math.pow(planet.getLocation().getX()
					- origin.getLocation().getX(), 2);
			double yDifferenceSquared = Math.pow(planet.getLocation().getY()
					- origin.getLocation().getY(), 2);
			int distance = (int) Math.ceil(Math.sqrt(xDifferenceSquared
					+ yDifferenceSquared));
			if (distance <= ship.getFuel())
				withinRange.add(planet);
		}
		return withinRange;
	}
}