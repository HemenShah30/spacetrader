package SpaceTrader;

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

	public Universe() {
		planets = new ArrayList<Planet>();
	}

	public void createPlanets() {
		Reader reader = new Reader();
		List<String> planetNames = reader
				.readPlanetNames(".\\src\\main\\resources\\PlanetNames.txt");

		for (int i = 0; i < planetNames.size(); i++) {
			TechLevel[] levels = TechLevel.values();
			Resource[] resources = Resource.values();
			int l = (int) (Math.random() * levels.length);
			int r = (int) (Math.random() * resources.length);
			Planet p = new Planet(planetNames.get(i), levels[l], resources[r]);
			planets.add(p);
		}
		System.out.println(planets);
	}
}