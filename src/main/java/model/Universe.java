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

	/**
	 * Simple universe constructor, just creating a blank planet array
	 */
	public Universe() {
		planets = new ArrayList<Planet>();
	}

	/**
	 * Method for creating all the planets for the universe, setting up their tech level and resources
	 */
	public void createPlanets() {
		Reader reader = new Reader();
		List<String> planetNames = reader
				.readPlanetNames(".\\src\\main\\resources\\PlanetNames.txt");

		for (int i = 0; i < planetNames.size(); i++) {
			TechLevel[] levels = TechLevel.values();
			SpecialResource[] resources = SpecialResource.values();
			Government[] governments = Government.values();
			int l = (int) (Math.random() * levels.length);
			int r = (int) (Math.random() * resources.length);
			int g = (int) (Math.random() * governments.length);
			Planet p = new Planet(planetNames.get(i), levels[l], resources[r], governments[g]);
			planets.add(p);
		}
		System.out.println(planets);
	}
}