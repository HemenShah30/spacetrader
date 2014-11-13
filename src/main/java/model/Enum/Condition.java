package model.Enum;

/**
 * Class representing the condition of a planet
 * 
 * @author Larry He
 * 
 */
public enum Condition {
    NONE, DROUGHT, COLD, CROPFAIL, WAR, BOREDOM, PLAGUE, LACKOFWORKERS;

    @Override
    public String toString() {
        switch (this) {
        case NONE:
            return "None";
        case DROUGHT:
            return "Drought";
        case COLD:
            return "Cold";
        case CROPFAIL:
            return "Crop Failure";
        case WAR:
            return "War";
        case BOREDOM:
            return "Boredom";
        case PLAGUE:
            return "Plague";
        case LACKOFWORKERS:
            return "Lack of Workers";
        default:
            return "";
        }
    }

    /**
     * Returns the enum given the toString() of the enum
     * 
     * @param value
     *            The enum being looked for
     * @return The enum represented by the value string
     */
    public static Condition getEnum(String value) {
        value = value.toLowerCase();
        switch (value) {
        case "none":
            return Condition.NONE;
        case "drought":
            return Condition.DROUGHT;
        case "cold":
            return Condition.COLD;
        case "crop failure":
            return Condition.CROPFAIL;
        case "war":
            return Condition.WAR;
        case "boredom":
            return Condition.BOREDOM;
        case "plague":
            return Condition.PLAGUE;
        case "lack of workers":
            return Condition.LACKOFWORKERS;
        default:
            return null;
        }
    }
}