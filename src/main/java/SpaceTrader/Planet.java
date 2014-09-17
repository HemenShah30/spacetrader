package SpaceTrader;

/**
 * Class representing a planet
 * 
 * @author Larry He
 * 
 */
public class Planet {
	String name;
	TechLevel techLevel;
	Resource resource;

	/**
	 * Basic Planet constructor taking a tech level and a resource
	 * 
	 * @param tech
	 *            The tech level for the planet
	 * @param r
	 *            The resource for the planet
	 * @param n
	 *            The name of the planet
	 */
	public Planet(String n, TechLevel tech, Resource r) {
		name = n;
		techLevel = tech;
		resource = r;
	}

	@Override
	public String toString() {
		return name + ", Tech Level: " + techLevel + ", Resource: "
				+ resource;
	}
}