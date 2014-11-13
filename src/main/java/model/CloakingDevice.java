package model;

/**
 * A cloaking device gadget which allows the player to avoid other ships and aids in combat
 * 
 * @author Caroline Zhang
 *
 */
public class CloakingDevice extends Gadget {

    public static final int PRICE = 40000;
    public static final int MIN_TECH_LEVEL = 6;

    /**
     * A constructor for the CloakingDevice which passes the price and minimum TechLevel to the
     * Gadget superclass
     */
    public CloakingDevice() {
        super(PRICE, MIN_TECH_LEVEL);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof CloakingDevice;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return "Cloaking Device";
    }
}