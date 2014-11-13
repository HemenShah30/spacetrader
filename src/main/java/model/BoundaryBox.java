package model;

/**
 * Class defining a box in the universe, for easier location of planets in the universe
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

    /**
     * Constructor for a BoundaryBox taking in the bounding locations and its sub-regions
     * 
     * @param topLeftLoc
     *            The top left corner of the BoundaryBox
     * @param bottomRightLoc
     *            The bottom right corner of the BoundaryBox
     * @param topLeftBox
     *            The top left sub-region of the BoundaryBox
     * @param topRightBox
     *            The top right sub-region of the BoundaryBox
     * @param bottomLeftBox
     *            The bottom left sub-region of the BoundaryBox
     * @param bottomRightBox
     *            The bottom right sub-region of the BoundaryBox
     */
    public BoundaryBox(Location topLeftLoc, Location bottomRightLoc, Boundary topLeftBox,
            Boundary topRightBox, Boundary bottomLeftBox, Boundary bottomRightBox) {
        topLeftCorner = topLeftLoc;
        bottomRightCorner = bottomRightLoc;
        topLeft = topLeftBox;
    }

    /**
     * Returns the top left sub-region of the BoundaryBox
     * 
     * @return the top left sub-region of the BoundaryBox
     */
    public Boundary getTopLeft() {
        return topLeft;
    }

    /**
     * Returns the top right sub-region of the BoundaryBox
     * 
     * @return the top right sub-region of the BoundaryBox
     */
    public Boundary getTopRight() {
        return topRight;
    }

    /**
     * Returns the bottom left sub-region of the BoundaryBox
     * 
     * @return the bottom left sub-region of the BoundaryBox
     */
    public Boundary getBottomLeft() {
        return bottomLeft;
    }

    /**
     * Returns the bottom right sub-region of the BoundaryBox
     * 
     * @return the bottom right sub-region of the BoundaryBox
     */
    public Boundary getBottomRight() {
        return bottomRight;
    }

    /**
     * Sets the top left sub-region for the BoundaryBox
     * 
     * @param tl
     *            The new top left sub-region for the BoundaryBox
     */
    public void setTopLeft(Boundary tl) {
        topLeft = tl;
    }

    /**
     * Sets the top right sub-region for the BoundaryBox
     * 
     * @param tr
     *            The new top right sub-region for the BoundaryBox
     */
    public void setTopRight(Boundary tr) {
        topRight = tr;
    }

    /**
     * Sets the bottom left sub-region for the BoundaryBox
     * 
     * @param bl
     *            The new bottom left sub-region for the BoundaryBox
     */
    public void setBottomLeft(Boundary bl) {
        bottomLeft = bl;
    }

    /**
     * Sets the bottom right sub-region for the BoundaryBox
     * 
     * @param br
     *            The new bottom right sub-region for the BoundaryBox
     */
    public void setBottomRight(Boundary br) {
        bottomRight = br;
    }

    /**
     * Gets the top left corner of this boundary box
     * 
     * @return The top left corner of the boundary box
     */
    public Location getTopLeftCorner() {
        return topLeftCorner;
    }

    /**
     * Returns the bottom right corner of the boundary box
     * 
     * @return The bottom right corner of the boundary box
     */
    public Location getBottomRightCorner() {
        return bottomRightCorner;
    }

    /**
     * Returns the Boundary at the given location
     * 
     * @param location
     *            The location that is being queried
     * @return The Boundary at the given location, or null if there's no boundary object
     */
    public Boundary getBoundaryObject(Location location) {
        return recursiveGetBoundaryObject(location, this);
    }

    /**
     * Recursive method for getting the boundary object at a given location
     * 
     * @param location
     *            The location which is being queried
     * @param parent
     *            The parent Boundary that is being recursed down
     * @return The Boundary object at the given location, or null if there's no object there
     */
    private Boundary recursiveGetBoundaryObject(Location location, Boundary parent) {
        if (parent instanceof Planet) {
            if (parent.isLocationInside(location)) {
                return parent;
            }
            return null;
        }

        BoundaryBox box = (BoundaryBox) parent;
        if (box.getTopLeft() != null && box.getTopLeft().isLocationInside(location)) {
            return recursiveGetBoundaryObject(location, box.getTopLeft());
        } else if (box.getTopRight() != null && box.getTopRight().isLocationInside(location)) {
            return recursiveGetBoundaryObject(location, box.getTopRight());
        } else if (box.getBottomLeft() != null && box.getBottomLeft().isLocationInside(location)) {
            return recursiveGetBoundaryObject(location, box.getBottomLeft());
        } else if (box.getBottomRight() != null 
                && box.getBottomRight().isLocationInside(location)) {
            return recursiveGetBoundaryObject(location, box.getBottomRight());
        }
        return null;
    }

    @Override
    public boolean isLocationInside(Location location) {
        int xPosition = location.getX();
        int yPosition = location.getY();
        return xPosition >= topLeftCorner.getX() && xPosition <= bottomRightCorner.getX()
                && yPosition <= bottomRightCorner.getY() && yPosition >= topLeftCorner.getY();
    }

    @Override
    public Location getLocation() {
        return new Location(((bottomRightCorner.getX() + topLeftCorner.getX()) / 2),
                (bottomRightCorner.getY() + topLeftCorner.getY()) / 2);
    }
}