package model;

/**
 * Class representing the location of a planet
 * 
 * @author Larry He
 * 
 */
public class Location {
	private int x;
	private int y;
	
	/**
	 * Basic Location constructor taking in coordinate values
	 * 
	 * @param x
	 *            The x coordinate of the planet
	 * @param y
	 *            The y coordinate of the planet
	 */
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns the x coordinate of the planet
	 * 
	 * @return The x coordinate of the planet
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Returns the y coordinate of the planet
	 * 
	 * @return The y coordinate of the planet
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Checks if an Object is the same location
	 * 
	 * @return boolean
	 */
	@Override
	public boolean equals(Object other) {
		if (other == null) return false;
		Location that = (Location) other;
		return (getX()==that.getX() && getY()==that.getY());
	}
}
