package model;

/**
 * Class representing the location of a planet
 * 
 * @author Larry He
 * 
 */
public class Location {
    private int xpos;
    private int ypos;

    /**
     * Basic Location constructor taking in coordinate values
     * 
     * @param xpos
     *            The x coordinate of the planet
     * @param ypos
     *            The y coordinate of the planet
     */
    public Location(int xpos, int ypos) {
        setX(xpos);
        setY(ypos);
    }

    /**
     * Private setter for x coordinate
     * 
     * @param xpos
     *            The x coordinate of the planet
     */
    private void setX(int xpos) {
        this.xpos = xpos;
    }

    /**
     * Private setter for y coordinate
     * 
     * @param ypos
     *            The y coordinate of the planet
     */
    private void setY(int ypos) {
        this.ypos = ypos;
    }

    /**
     * Returns the x coordinate of the planet
     * 
     * @return The x coordinate of the planet
     */
    public int getX() {
        return xpos;
    }

    /**
     * Returns the y coordinate of the planet
     * 
     * @return The y coordinate of the planet
     */
    public int getY() {
        return ypos;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Location)) {
            return false;
        }
        Location that = (Location) other;
        return (getX() == that.getX() && getY() == that.getY());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return "(" + xpos + ", " + ypos + ")";
    }
}