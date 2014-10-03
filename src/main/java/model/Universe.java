package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Class representing the entire universe
 * 
 * @author Jack Croft
 *
 */
public class Universe {

	private List<Planet> planets;
	private final double universeLength = 100;
	private final double universeWidth = 100;
	private final double percentNoneCondition = 0.5;
	private final double percentNoSpecialResource = 0.5;

	/**
	 * Simple universe constructor, just creating a blank planet array
	 */
	public Universe() {
		planets = new ArrayList<Planet>();
	}

	/**
	 * Constructor for a universe taking in a list of planets to begin with
	 * 
	 * @param p
	 */
	public Universe(List<Planet> p) {
		planets = p;
	}

	/**
	 * Method for creating all the planets for the universe, setting up their
	 * tech level and resources
	 */
	public void createPlanets() {
		FileReader reader = new FileReader();
		List<String> planetNames = reader
				.readPlanetNames("model/PlanetNames.txt");
		Collections.shuffle(planetNames);
		Set<Location> uniqueLocations = new HashSet<Location>();
		Random rand = new Random();
		int x, y;
		// int uniSize = (int) Math.ceil(Math.sqrt(planetNames.size()));
		int uniSize = 10;
		for (int i = 0; i < planetNames.size(); i++) {
			TechLevel[] levels = TechLevel.values();
			SpecialResource[] resources = SpecialResource.values();
			Government[] governments = Government.values();
			Condition[] conditions = Condition.values();

			int t = (int) (Math.random() * levels.length);
			int r = (int) (Math.random() * resources.length);
			int g = (int) (Math.random() * governments.length);
			int c = (int) (Math.random() * conditions.length);
			// int x = (int) (Math.random() * universeLength);
			// int y = (int) (Math.random() * universeWidth);

			if (Math.random() < percentNoneCondition) {
				c = 0;
			}

			if (Math.random() < percentNoSpecialResource) {
				r = 0;
			}

			/*
			 * boolean uniqueLocation = false; Location l = null; while
			 * (!uniqueLocation) { l = new Location(x, y); if
			 * (!uniqueLocations.contains(l)) uniqueLocation = true; else { x =
			 * (int) (Math.random() * universeLength); y = (int) (Math.random()
			 * * universeWidth); } }
			 */

			if (i < 100) {
				x = i / uniSize;
				y = i % uniSize;
				x = 40 * x;
				y = 40 * y;
				x = x + rand.nextInt(25) + 5;
				y = y + rand.nextInt(25) + 5;
			} else {
				x = rand.nextInt(400);
				y = rand.nextInt(400);
			}
			Location l = new Location(x, y);
			Planet p = new Planet(planetNames.get(i), levels[t], resources[r],
					governments[g], l, conditions[c]);
			uniqueLocations.add(l);
			planets.add(p);
		}
		System.out.println(planetNames.size());
	}

	/**
	 * Gets the list of planets in the universe
	 * 
	 * @return The list of planets in the universe
	 */
	public List<Planet> getPlanets() {
		return planets;
	}
}