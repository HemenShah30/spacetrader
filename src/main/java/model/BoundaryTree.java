package model;

import java.util.List;

/**
 * Boundary tree holding and organizing Boundary objects into a QuadTree
 * 
 * @author Jack Croft
 *
 */
public class BoundaryTree {
    private BoundaryBox root;

    /**
     * Constructor for the BoundaryTree taking in the Universe dimensions and the planets in the
     * universe
     * 
     * @param universeWidth
     *            The width of the universe
     * @param universeHeight
     *            The height of the universe
     * @param planets
     *            The planets within the universe
     */
    public BoundaryTree(int universeWidth, int universeHeight, List<Planet> planets) {
        BoundaryBox box = new BoundaryBox(new Location(0, 0), new Location(universeWidth,
                universeHeight), null, null, null, null);
        root = box;
        int diam;
        for (Planet p : planets) {
            // TODO: p.getLocation.getX() should be a valid top x, but doesnt
            // work
            // soon
            diam = p.getDiameter();
            int topX = p.getLocation().getX() - diam;
            int topY = p.getLocation().getY() - diam;
            int bottomX = p.getLocation().getX() + diam;
            int bottomY = p.getLocation().getY() + diam;
            BoundaryBox planetBox = new BoundaryBox(new Location(topX, topY), new Location(bottomX,
                    bottomY), p, null, null, null);
            insert(planetBox, root, p.getLocation());
        }
    }

    /**
     * Gets a planet at the given location, or returns null if there's no planet there
     * 
     * @param location
     *            The location that the planet is either at or not
     * @return The Planet at the given location, or null if there's no planet
     */
    public Planet getPlanetAtLocation(Location location) {
        if (location == null || root == null) {
            return null;
        }
        return (Planet) root.getBoundaryObject(location);
    }

    // TODO: remove insertLocation and replace with the .getLocation() method
    /**
     * Inserts a new Boundary object into the tree structure
     * 
     * @param newBoundary
     *            The Boundary object to be put into the tree
     * @param parent
     *            The parent of the new Boundary object
     * @param insertLocation
     *            The location the new Boundary object is located at
     */
    private void insert(BoundaryBox newBoundary, BoundaryBox parent, Location insertLocation) {
        // first do a null check to see if the new boundary can just be added
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

        // if not, then check to see if the new boundary fits inside the
        // existing boundaries
        if (parent.getTopLeft().isLocationInside(insertLocation)) {
            insert(newBoundary, (BoundaryBox) parent.getTopLeft(), insertLocation);
            return;
        } else if (parent.getTopRight().isLocationInside(insertLocation)) {
            insert(newBoundary, (BoundaryBox) parent.getTopRight(), insertLocation);
            return;
        } else if (parent.getBottomLeft().isLocationInside(insertLocation)) {
            insert(newBoundary, (BoundaryBox) parent.getBottomLeft(), insertLocation);
            return;
        } else if (parent.getBottomRight().isLocationInside(insertLocation)) {
            insert(newBoundary, (BoundaryBox) parent.getBottomRight(), insertLocation);
            return;
        }

        // otherwise, divide up the parent boundary into equal regions and
        // re-add the new boundary
        subdivide(parent);
        insert(newBoundary, parent, insertLocation);
    }

    /**
     * Divides a given BoundaryBox into four equal parts and re-inserts the sub-regions into their
     * new regions
     * 
     * @param box
     *            The BoundaryBox being subdivided
     */
    private void subdivide(BoundaryBox box) {
        Boundary topLeft = box.getTopLeft();
        Boundary topRight = box.getTopRight();
        Boundary bottomLeft = box.getBottomLeft();
        Boundary bottomRight = box.getBottomRight();
        
        int midpointX = (box.getBottomRightCorner().getX() + box.getTopLeftCorner().getX()) / 2;
        int midpointY = (box.getBottomRightCorner().getY() + box.getTopLeftCorner().getY()) / 2;
        int topYValue = box.getTopLeftCorner().getY();
        int rightXValue = box.getBottomRightCorner().getX();
        int leftXValue = box.getTopLeftCorner().getX();
        int bottomYValue = box.getBottomRightCorner().getY();

        BoundaryBox topLeftBox = new BoundaryBox(box.getTopLeftCorner(), new Location(midpointX,
                midpointY), null, null, null, null);
        BoundaryBox topRightBox = new BoundaryBox(new Location(midpointX, topYValue), new Location(
                rightXValue, midpointY), null, null, null, null);
        BoundaryBox bottomLeftBox = new BoundaryBox(new Location(leftXValue, midpointY),
                new Location(midpointX, bottomYValue), null, null, null, null);
        BoundaryBox bottomRightBox = new BoundaryBox(new Location(midpointX, midpointY),
                box.getBottomRightCorner(), null, null, null, null);

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