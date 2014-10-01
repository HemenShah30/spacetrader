package controller;

import java.util.ArrayList;
import java.util.List;
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
	 * Constructor for the flight engine, taking in a given ship
	 * 
	 * @param s
	 */
	public FlightEngine(Ship s, Player p, Universe universe) {
		ship = s;
		player = p;
		this.universe = universe;
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

	/**
	 * Sends player to a different planet, calculates and creates possible
	 * encounters, and sets fuel to correct future value
	 * 
	 * @param p
	 *            The planet being clicked on to travel to
	 */
	public void goClick(Planet p) {
		Planet origin = player.getPlanet();
		player.setPlanet(p);
		// encounters go here
//		Map<Planet, Integer> withinRange = getPlanetsWithinRange(universe,
//				origin);
//		ship.setFuel(ship.getFuel() - withinRange.get(p));

	}
}