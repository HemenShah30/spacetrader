package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	private final double universeWidth = 150;

	/**
	 * Simple universe constructor, just creating a blank planet array
	 */
	public Universe() {
		planets = new ArrayList<Planet>();
	}

	/**
	 * Method for creating all the planets for the universe, setting up their
	 * tech level and resources
	 */
	public void createPlanets() {
		Reader reader = new Reader();
		List<String> planetNames = reader
				.readPlanetNames("model/PlanetNames.txt");

		Set<Location> uniqueLocations = new HashSet<Location>();
		for (int i = 0; i < planetNames.size(); i++) {
			TechLevel[] levels = TechLevel.values();
			SpecialResource[] resources = SpecialResource.values();
			Government[] governments = Government.values();

			int t = (int) (Math.random() * levels.length);
			int r = (int) (Math.random() * resources.length);
			int g = (int) (Math.random() * governments.length);
			int x = (int) (Math.random() * universeLength);
			int y = (int) (Math.random() * universeWidth);

			boolean uniqueLocation = false;
			Location l = null;
			while (!uniqueLocation) {
				l = new Location(x, y);
				if (!uniqueLocations.contains(l))
					uniqueLocation = true;
				else {
					x = (int) (Math.random() * universeLength);
					y = (int) (Math.random() * universeWidth);
				}
			}

			Planet p = new Planet(planetNames.get(i), levels[t], resources[r],
					governments[g], l);
			uniqueLocations.add(l);
			planets.add(p);
		}
		for (int i = 0; i < planets.size(); i++)
			System.out.println(planets.get(i) + "\n");
	}
}