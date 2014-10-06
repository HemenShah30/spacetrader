package model;

/**
 * Interface for determining whether or not a location is within the boundary
 * 
 * @author Jack Croft
 *
 */
public interface Boundary {
	/**
	 * Returns whether or not a given point is inside the Boundary
	 * 
	 * @param location
	 *            The location either inside or outside the boundary
	 * @return Whether or not the location is inside the boundary or not
	 */
	public boolean isLocationInside(Location location);

	/**
	 * Returns the location of the boundary
	 * 
	 * @return The location of the boundary
	 */
	public Location getLocation();
}