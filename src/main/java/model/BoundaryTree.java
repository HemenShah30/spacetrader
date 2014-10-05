package model;

import java.util.List;

/**
 * 
 * @author Jack Croft
 *
 */
public class BoundaryTree {
	private BoundaryBox root;

	public BoundaryTree(int universeWidth, int universeHeight,
			List<Planet> planets) {
		BoundaryBox b = new BoundaryBox(new Location(0, 0), new Location(
				universeWidth, universeHeight), null, null, null, null);
		root = b;

		for (Planet p : planets) {
			// 3 is radius at the moment
			int topX = p.getLocation().getX() - 3;
			int topY = p.getLocation().getY() - 3;
			int bottomX = p.getLocation().getX() + 3;
			int bottomY = p.getLocation().getY() + 3;
			BoundaryBox planetBox = new BoundaryBox(new Location(topX, topY),
					new Location(bottomX, bottomY), p, null, null, null);
			insert(planetBox, root, p.getLocation());
		}
	}

	public Planet getPlanetAtLocation(Location location) {
		// null check?
		return (Planet) root.getBoundaryObject(location);
	}

	private void insert(BoundaryBox newBoundary, BoundaryBox parent,
			Location insertLocation) {
		// planets should never overlap, therefore planetBox will never contain
		// another point
		// IT DOESN'T MATTER WHICH REGION IS WHICH, MUAHAHAHAHHAHAH
		// base case is isLocationInside a given region and that region is null

		// do isInisde for each quadrant, if boundary is null then set value,
		// otherwise call boundary on that object

		// if something is null or it's within throw it in there, but if
		// everything's full, then subdivide and re-add

		// if something is null, then region will be just the new boundary
		if (parent.getTopLeft() == null) {
			parent.setTopLeft(newBoundary);
			return;
		} else if (parent.getTopRight() == null) {
			parent.setTopRight(newBoundary);
			return;
		} else if (parent.getBottomLeft() == null) {
			parent.setBottomLeft(newBoundary);
			return;
		} else if (parent.getBottomRight() == null) {
			parent.setBottomRight(newBoundary);
			return;
		}

		// if nothing is null, but something contains it, recurse there
		if (parent.getTopLeft().isLocationInside(insertLocation)) {
			insert(newBoundary, (BoundaryBox) parent.getTopLeft(),
					insertLocation);
			return;
		} else if (parent.getTopRight().isLocationInside(insertLocation)) {
			insert(newBoundary, (BoundaryBox) parent.getTopRight(),
					insertLocation);
			return;
		} else if (parent.getBottomLeft().isLocationInside(insertLocation)) {
			insert(newBoundary, (BoundaryBox) parent.getBottomLeft(),
					insertLocation);
			return;
		} else if (parent.getBottomRight().isLocationInside(insertLocation)) {
			insert(newBoundary, (BoundaryBox) parent.getBottomRight(),
					insertLocation);
			return;
		}

		// everything is not null, and point is not contained, therefore
		// subdivide then insert with same arguments
		subdivide(parent);
		insert(newBoundary, parent, insertLocation);

	}

	private void subdivide(BoundaryBox box) {
		Boundary topLeft = box.getTopLeft();
		Boundary topRight = box.getTopRight();
		Boundary bottomLeft = box.getBottomLeft();
		Boundary bottomRight = box.getBottomRight();

		int midpointX = (box.getBottomRightCorner().getX() + box
				.getTopLeftCorner().getX()) / 2;
		int midpointY = (box.getBottomRightCorner().getY() + box
				.getTopLeftCorner().getY()) / 2;
		int topYValue = box.getTopLeftCorner().getY();
		int rightXValue = box.getBottomRightCorner().getX();
		int leftXValue = box.getTopLeftCorner().getX();
		int bottomYValue = box.getBottomRightCorner().getY();

		BoundaryBox topLeftBox = new BoundaryBox(box.getTopLeftCorner(),
				new Location(midpointX, midpointY), null, null, null, null);
		BoundaryBox topRightBox = new BoundaryBox(new Location(midpointX,
				topYValue), new Location(rightXValue, midpointY), null, null,
				null, null);
		BoundaryBox bottomLeftBox = new BoundaryBox(new Location(leftXValue,
				midpointY), new Location(midpointX, bottomYValue), null, null,
				null, null);
		BoundaryBox bottomRightBox = new BoundaryBox(new Location(midpointX,
				midpointY), box.getBottomRightCorner(), null, null, null, null);

		box.setTopLeft(topLeftBox);
		box.setTopRight(topRightBox);
		box.setBottomLeft(bottomLeftBox);
		box.setBottomRight(bottomRightBox);

		// insert old values into new boxes, since we're never hitting planets
		// here, cast is safe
		insert((BoundaryBox) topLeft, box, topLeft.getLocation());
		insert((BoundaryBox) topRight, box, topRight.getLocation());
		insert((BoundaryBox) bottomLeft, box, bottomLeft.getLocation());
		insert((BoundaryBox) bottomRight, box, bottomRight.getLocation());
	}
}