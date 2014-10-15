package model;

import javafx.scene.paint.Color;

/**
 * Class representing a planet
 * 
 * @author Larry He
 * 
 */
public class Planet implements Boundary {
	private String name;
	private TechLevel techLevel;
	private SpecialResource resource;
	private Government government;
	private Location location;
	private Marketplace marketplace;
	private Condition condition;
	private EncounterRate policeEncounterRate;
	private EncounterRate pirateEncounterRate;
	private EncounterRate traderEncounterRate;
	private int radius, chances;
	private Color color;
	public final static Color[] approvedColors = { Color.BLUE, Color.RED,
			Color.FORESTGREEN, Color.PERU };

	/**
	 * Basic Planet constructor taking a tech level and a resource
	 * 
	 * @param tech
	 *            The tech level for the planet
	 * @param r
	 *            The resource for the planet
	 * @param n
	 *            The name of the planet
	 * @param g
	 *            The government type of the planet
	 * @param l
	 *            The location of the planet in space
	 * @param c
	 *            The condition of the planet
	 */
	public Planet(String n, TechLevel tech, SpecialResource r, Government g,
			Location l, Condition c, EncounterRate policeRate,
			EncounterRate pirateRate, EncounterRate traderRate, int size,
			Color color) {
		setName(n);
		setTechLevel(tech);
		setResource(r);
		setGovernment(g);
		setLocation(l);
		setCondition(c);
		policeEncounterRate = policeRate;
		pirateEncounterRate = pirateRate;
		traderEncounterRate = traderRate;
		marketplace = new Marketplace(this);
		setRadius(size);
		setColor(color);
		setChances();
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
	private void setResource(SpecialResource r) {
		if (r == null)
			throw new IllegalArgumentException();
		resource = r;
	}

	/**
	 * Private setter for government type validation
	 * 
	 * @param g
	 *            The government type of the planet to be set
	 */
	private void setGovernment(Government g) {
		if (g == null)
			throw new IllegalArgumentException();
		government = g;
	}

	/**
	 * Private setter for Location type validation
	 * 
	 * @param l
	 *            The location of the planet to be set
	 */
	private void setLocation(Location l) {
		if (l == null)
			throw new IllegalArgumentException();
		location = l;
	}

	/**
	 * Private setter for Condition type validation
	 * 
	 * @param c
	 *            The condition of the planet to be set
	 */
	private void setCondition(Condition c) {
		if (c == null)
			throw new IllegalArgumentException();
		condition = c;
	}

	/**
	 * private setter for size validation sets to 3 if invalid
	 * 
	 * @param i
	 */
	private void setRadius(int i) {
		if (2 < i && i < 8) {
			radius = i;
		} else {
			radius = 3;
		}
	}

	/**
	 * private setter for color validation if color is not an approved color, it
	 * is set to green
	 * 
	 * @param c
	 */
	private void setColor(Color c) {
		boolean isSet = false;
		for (Color col : approvedColors) {
			if (col.equals(c)) {
				color = c;
				isSet = true;
				break;
			}
		}
		if (!isSet) {
			color = approvedColors[2];
		}
	}

	/**
	 * Sets the number of chances to have an encounter when traveling to the
	 * planet. Is random, 0-8
	 */
	private void setChances() {
		chances = (int) (Math.random() * 10);
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
	public SpecialResource getResource() {
		return resource;
	}

	/**
	 * Getter for the government type of the planet
	 * 
	 * @return The government of the planet
	 */
	public Government getGovernment() {
		return government;
	}

	@Override
	public Location getLocation() {
		return location;
	}

	/**
	 * Getter for the condition of the planet
	 * 
	 * @return The condition of the planet
	 */
	public Condition getCondition() {
		return condition;
	}

	/**
	 * Returns the marketplace for the planet
	 * 
	 * @return The marketplace for the planet
	 */
	public Marketplace getMarketplace() {
		return marketplace;
	}

	@Override
	public String toString() {
		return name + ", Location: " + location + ", Tech Level: " + techLevel
				+ ", Resource: " + resource + ", Goverment: " + government
				+ ", Condition: " + condition;
	}

	@Override
	public boolean isLocationInside(Location location) {
		// TODO: check corners of box here to get a more accurate location
		return true;
	}

	/**
	 * Getter for the amount of police in the planet area
	 * 
	 * @return The amount of police in the planet area
	 */
	public EncounterRate getPoliceEncounterRate() {
		return policeEncounterRate;
	}

	/**
	 * Getter for the amount of pirates in the planet area
	 * 
	 * @return The amount of pirates in the planet area
	 */
	public EncounterRate getPirateEncounterRate() {
		return pirateEncounterRate;
	}

	/**
	 * Getter for the amount of trader in the planet area
	 * 
	 * @return The amount of trader in the planet area
	 */
	public EncounterRate getTraderEncounterRate() {
		return traderEncounterRate;
	}

	/**
	 * returns planet size
	 * 
	 * @return planet size
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * returns planet color
	 * 
	 * @return planet color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * returns planet chances
	 * 
	 * @return planet chances
	 */
	public int getChances() {
		return chances;
	}
}