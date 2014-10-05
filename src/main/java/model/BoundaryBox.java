package model;

/**
 * Class defining a box in the universe, for easier location of planets in the
 * universe
 * 
 * @author Jack Croft
 *
 */
public class BoundaryBox implements Boundary {
	private Boundary topLeft;
	private Boundary topRight;
	private Boundary bottomRight;
	private Boundary bottomLeft;
	private Location topLeftCorner;
	private Location bottomRightCorner;

	public BoundaryBox(Location topLeftLoc, Location bottomRightLoc,
			Boundary tLeftBox, Boundary tRightBox, Boundary bLeftBox,
			Boundary bRightBox) {
		topLeftCorner = topLeftLoc;
		bottomRightCorner = bottomRightLoc;
		topLeft = tLeftBox;

	}

	public Boundary getTopLeft() {
		return topLeft;
	}

	public Boundary getTopRight() {
		return topRight;
	}

	public Boundary getBottomLeft() {
		return bottomLeft;
	}

	public Boundary getBottomRight() {
		return bottomRight;
	}

	public void setTopLeft(Boundary tl) {
		topLeft = tl;
	}

	public void setTopRight(Boundary tr) {
		topRight = tr;
	}

	public void setBottomLeft(Boundary bl) {
		bottomLeft = bl;
	}

	public void setBottomRight(Boundary br) {
		bottomRight = br;
	}

	public Location getTopLeftCorner() {
		return topLeftCorner;
	}

	public Location getBottomRightCorner() {
		return bottomRightCorner;
	}

	public Boundary getBoundaryObject(Location location) {
		return recursiveGetBoundaryObject(location, this);
	}

	public Boundary recursiveGetBoundaryObject(Location location,
			Boundary parent) {
		if (parent instanceof Planet) {
			if (parent.isLocationInside(location))
				return parent;
			return null;
		}

		BoundaryBox box = (BoundaryBox) parent;
		if (box.getTopLeft() != null
				&& box.getTopLeft().isLocationInside(location))
			return recursiveGetBoundaryObject(location, box.getTopLeft());
		else if (box.getTopRight() != null
				&& box.getTopRight().isLocationInside(location))
			return recursiveGetBoundaryObject(location, box.getTopRight());
		else if (box.getBottomLeft() != null
				&& box.getBottomLeft().isLocationInside(location))
			return recursiveGetBoundaryObject(location, box.getBottomLeft());
		else if (box.getBottomRight() != null
				&& box.getBottomRight().isLocationInside(location))
			return recursiveGetBoundaryObject(location, box.getBottomRight());

		return null;
	}

	public boolean isLocationInside(Location location) {
		int x = location.getX();
		int y = location.getY();
		return x >= topLeftCorner.getX() && x <= bottomRightCorner.getX()
				&& y <= bottomRightCorner.getY() && y >= topLeftCorner.getY();
	}

	public Location getLocation() {
		return new Location(
				((bottomRightCorner.getX() + topLeftCorner.getX()) / 2),
				(bottomRightCorner.getY() + topLeftCorner.getY()) / 2);
	}
}