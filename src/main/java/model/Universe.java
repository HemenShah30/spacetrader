package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the entire universe
 * 
 * @author Jack Croft
 *
 */
public class Universe {

	private List<Planet> planets;
	private static final double universeLength = 100;
	private static final double universeWidth = 150;

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
				.readPlanetNames(".\\src\\main\\resources\\PlanetNames.txt");

		for (int i = 0; i < planetNames.size(); i++) {
			TechLevel[] levels = TechLevel.values();
			SpecialResource[] resources = SpecialResource.values();
			Government[] governments = Government.values();
			
			int t = (int) (Math.random() * levels.length);
			int r = (int) (Math.random() * resources.length);
			int g = (int) (Math.random() * governments.length);
			int x = (int) (Math.random() * universeLength);
			int y = (int) (Math.random() * universeWidth);
			Location l = new Location(x, y);
			Planet p = new Planet(planetNames.get(i), levels[t], resources[r],
					governments[g], l);
			planets.add(p);
		}
		System.out.println(planets);
	}
}