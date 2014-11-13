package model;

/**
 * Factory for all the gadget classes
 * 
 * @author Jack Croft
 *
 */
public class GadgetFactory {

    /**
     * Creates a new Gadget given the name of the gadget
     * 
     * @param gadgetName
     *            The name of the gadget
     * @return The Gadget with the given name
     */
    public static Gadget createGadget(String gadgetName) {
        if (gadgetName.equals("Auto Repair System")) {
            return new AutoRepairSystem();
        } else if (gadgetName.equals("Navigating System")) {
            return new NavigatingSystem();
        } else if (gadgetName.equals("Five Extra Cargo Spaces")) {
            return new FiveExtraCargo();
        } else if (gadgetName.equals("Targeting System")) {
            return new TargetingSystem();
        } else {
            return new CloakingDevice();
        }
    }
}