package model;

/**
 * Interface for determining whether or not a location is within the boundary
 * @author Jack Croft
 *
 */
public interface Boundary {
	public boolean isLocationInside(Location location);
	public Location getLocation();
}