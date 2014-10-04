package model;

/**
 * Class defining a box in the universe, for easier location of planets in the
 * universe
 * 
 * @author Jack Croft
 *
 */
public class BoundaryBox {
	private BoundaryBox left;
	private BoundaryBox right;
	private Location topLeftCorner;
	private Location bottomRightCorner;
	private Planet planet;

	public BoundaryBox(Location topLeft, Location bottomRight, Planet p,
			BoundaryBox leftBox, BoundaryBox rightBox) {
		topLeftCorner = topLeft;
		bottomRightCorner = bottomRight;
		planet = p;
		left = leftBox;
		right = rightBox;
	}

	public BoundaryBox getLeft() {
		return left;
	}

	public BoundaryBox getRight() {
		return right;
	}

	public boolean hasPlanet() {
		return planet != null;
	}

	public Planet getPlanet() {
		return planet;
	}

	public Location getTopLeftCorner() {
		return topLeftCorner;
	}

	public Location getBottomRightCorner() {
		return bottomRightCorner;
	}

	public boolean isPointInPlanet() {
		// this needs to check for hitting the circle versus the entire box
		return true;
	}
}