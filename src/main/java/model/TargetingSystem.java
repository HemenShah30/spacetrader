package model;

/**
 * A TargetingSystem class that improves upon the player's fighting skill
 * 
 * @author Caroline Zhang
 *
 */
public class TargetingSystem extends Gadget {

    public static final int PRICE = 25000;
    public static final int MIN_TECH_LEVEL = 5;

    /**
     * Constructor for the TargetingSystem that passes the price and TechLevel to the Gadget
     * superclass
     */
    public TargetingSystem() {
        super(PRICE, MIN_TECH_LEVEL);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof TargetingSystem;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return "Targeting System";
    }
}