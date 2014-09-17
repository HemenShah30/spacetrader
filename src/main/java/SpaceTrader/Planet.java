package SpaceTrader;

/**
 * Class representing a planet
 * 
 * @author Larry He
 * 
 */
public class Planet {
	private String name;
	private TechLevel techLevel;
	private Resource resource;

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
		setName(n);
		setTechLevel(tech);
		setResource(r);
	}

	/**
	 * Private setter for name validation
	 * 
	 * @param n
	 *            The name of the planet to be set
	 */
	private void setName(String n) {
		if (n == null || n.equals(""))
			throw new IllegalArgumentException();
		name = n;
	}

	/**
	 * Private setter for tech level validation
	 * 
	 * @param tl
	 *            The tech level of the planet to be set
	 */
	private void setTechLevel(TechLevel tl) {
		if (tl == null)
			throw new IllegalArgumentException();
		techLevel = tl;
	}

	/**
	 * Private setter for resource validation
	 * 
	 * @param r
	 *            The resource of the planet to be set
	 */
	private void setResource(Resource r) {
		if (r == null)
			throw new IllegalArgumentException();
		resource = r;
	}

	/**
	 * Getter for the name of the planet
	 * 
	 * @return The name of the planet
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for the tech level for the planet
	 * 
	 * @return The tech level of the planet
	 */
	public TechLevel getTechLevel() {
		return techLevel;
	}

	/**
	 * Getter for the resource of the planet
	 * 
	 * @return The resource of the planet
	 */
	public Resource getResource() {
		return resource;
	}

	@Override
	public String toString() {
		return name + ", Tech Level: " + techLevel + ", Resource: " + resource;
	}
}