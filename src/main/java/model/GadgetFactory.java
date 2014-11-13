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
        Gadget gadget;
        if (gadgetName.equals("Auto Repair System")) {
            gadget = new AutoRepairSystem();
        } else if (gadgetName.equals("Navigating System")) {
            gadget = new NavigatingSystem();
        } else if (gadgetName.equals("Five Extra Cargo Spaces")) {
            gadget =  new FiveExtraCargo();
        } else if (gadgetName.equals("Targeting System")) {
            gadget =  new TargetingSystem();
        } else {
            gadget =  new CloakingDevice();
        }
        return gadget;
    }
}