package model.enums;

/**
 * Enum representing the various ship types
 * 
 * @author Jack Croft
 *
 */
public enum ShipType {
	FLEA		(20, 25, 10, 0, 0, 0, 1, 4, 1, 2000, 5, 2, -1, -1, 0, 1, 0, 0, 0, 0, 20, 20, 70),
	GNAT		(14, 100, 15, 1, 0, 1, 1, 5, 2, 10000, 50, 28, 0, 0, 0, 1, 1, 0, 0, 0, 30, 30, 70),
	FIREFLY		(17, 100, 20, 1, 1, 1, 1, 5, 3, 25000, 75, 20, 0, 0, 0, 1, 1, 0, 0, 0, 30, 40, 80),
	MOSQUITO	(13, 100, 15, 2, 1, 1, 1, 5, 5, 30000, 100, 20, 0, 1, 0, 1, 1, 0, 0, 0, 40, 50, 90),
	BUMBLEBEE	(15, 100, 25, 1, 2, 2, 2, 5, 7, 60000, 125, 15, 0, 1, 0, 1, 2, 0, 0, 0, 50, 60, 101),
	BEETLE		(14, 50, 50, 0, 1, 1, 3, 5, 7, 60000, 150, 12, 0, 0, 1, 2, 2, 10, 0, 0, 80, 101, 101),
	HORNET		(16, 150, 20, 3, 2, 1, 2, 6, 7, 90000, 200, 5, 0, 0, 0, 3, 2, 30, 20, 0, 101, 101, 101),
	GRASSHOPPER	(15, 150, 30, 2, 2, 3, 3, 6, 5, 100000, 500, 5, 0, 0, 0, 4, 3, 40, 30, 10, 101, 101, 101),
	TERMITE		(13, 200, 60, 1, 3, 2, 3, 6, 7, 120000, 200, 5, 0, 0, 0, 4, 3, 50, 40, 30, 101, 101, 101),
	WASP		(14, 200, 35, 3, 2, 2, 3, 7, 7, 160000, 1000, 5, 0, 0, 0, 5, 3, 50, 40, 60, 101, 101, 101),
	MANTIS		(16, 225, 10, 3, 3, 3, 3, 7, 7, 220000, 1000, 5, 0, 0, 0, 5, 3, 60, 50, 70, 101, 101, 101);

    private int fuel;
    private int totalHP;
    private int cargoSize;
    private int weaponSlots;
    private int shieldSlots;
    private int gadgetSlots;
    private int crewSpace;
    private int minTechLevel;
    private int fuelCost;
    private int price;
    private int bounty;
    private int occurrence;
    private int policeModifier;
    private int pirateModifier;
    private int traderModifier;
    private int repairCost;
    private int size;
    private int minPoliceRep;
    private int minPirateRep;
    private int minTraderRep;
    private int maxPoliceRep;
    private int maxPirateRep;
    private int maxTraderRep;

    /**
     * Constructor for the ShipType enum, taking in all the parameters related to the ship
     * 
     * @param f
     *            The fuel capacity for the ship
     * @param thp
     *            The Total Hull Points, or health, for the ship
     * @param cs
     *            The Cargo Size, or maximum cargo capacity of the ship
     * @param ws
     *            The number of weapon slots the ship has
     * @param ss
     *            The number of shield slots the ship has
     * @param gs
     *            The number of gadget slots the ship has
     * @param crewS
     *            The amount of crew space the ship has
     * @param mtl
     *            The minimum tech level required for a ship
     * @param fc
     *            The cost of fuel for the ship
     * @param p
     *            The price of the ship
     * @param b
     *            The bounty value for the ship
     * @param o
     *            The occurrence of the ship
     * @param pm
     *            The modifier for police encounters for the ship
     * @param pirateM
     *            The modifier for pirate encounters for the ship
     * @param tm
     *            The modifier for trader encounters for the ship
     * @param rc
     *            The repair cost for the ship
     * @param s
     *            The size of the ship
     * @param minPoliceRep
     *            The minimum Police rep needed for the police to have this ship
     * @param minPirateRep
     *            The minimum Pirate rep needed for the pirate to have this ship
     * @param minTraderRep
     *            The minimum Trader rep needed for the trader to have this ship
     * @param maxPoliceRep
     *            The maximum Police rep needed for the police to have this ship
     * @param maxPirateRep
     *            The maximum Pirate rep needed for the pirate to have this ship
     * @param maxTraderRep
     *            The maximum Trader rep needed for the trader to have this ship
     */
    private ShipType(int fuel, int thp, int cs, int ws, int ss, int gs, int crewS, int mtl, int fc,
            int price, int bounty, int occurrence, int pm, int pirateM, int tm, int rc, int size,
            int minPoliceRep, int minPirateRep, int minTraderRep, int maxPoliceRep, 
            int maxPirateRep, int maxTraderRep) {
        this.fuel = fuel;
        totalHP = thp;
        cargoSize = cs;
        weaponSlots = ws;
        shieldSlots = ss;
        gadgetSlots = gs;
        crewSpace = crewS;
        minTechLevel = mtl;
        fuelCost = fc;
        this.price = price;
        this.bounty = bounty;
        this.occurrence = occurrence;
        policeModifier = pm;
        pirateModifier = pirateM;
        traderModifier = tm;
        repairCost = rc;
        this.size = size;
        this.minPoliceRep = minPoliceRep;
        this.minPirateRep = minPirateRep;
        this.minTraderRep = minTraderRep;
        this.maxPoliceRep = maxPoliceRep;
        this.maxPirateRep = maxPirateRep;
        this.maxTraderRep = maxTraderRep;
    }

    /**
     * Returns the fuel capacity for the ship
     * 
     * @return The fuel capacity for the ship
     */
    public int getFuel() {
        return fuel;
    }

    /**
     * Returns the total hull points for the ship
     * 
     * @return The total hull points for the ship
     */
    public int getTotalHP() {
        return totalHP;
    }

    /**
     * Returns the cargo size for the ship
     * 
     * @return The cargo size for the ship
     */
    public int getCargoSize() {
        return cargoSize;
    }

    /**
     * Returns the number of weapon slots for the ship
     * 
     * @return The number of weapon slots for the ship
     */
    public int getWeaponSlots() {
        return weaponSlots;
    }

    /**
     * Returns the number of shield slots for the ship
     * 
     * @return The number of shield slots for the ship
     */
    public int getShieldSlots() {
        return shieldSlots;
    }

    /**
     * Returns the number of gadget slots for the ship
     * 
     * @return The number of gadget slots for the ship
     */
    public int getGadgetSlots() {
        return gadgetSlots;
    }

    /**
     * Returns the amount of crew space for the ship
     * 
     * @return The amount of crew space for the ship
     */
    public int getCrewSpace() {
        return crewSpace;
    }

    /**
     * Gets the minimum tech level for the ship
     * 
     * @return The minimum tech level for the ship
     */
    public int getMinTechLevel() {
        return minTechLevel;
    }

    /**
     * Gets the cost of fuel for the ship
     * 
     * @return The cost of fuel for the ship
     */
    public int getFuelCost() {
        return fuelCost;
    }

    /**
     * Returns the price of the ship
     * 
     * @return The price of the ship
     */
    public int getPrice() {
        return price;
    }

    /**
     * Returns the bounty that gets put on a ship
     * 
     * @return The bounty that gets put on a ship
     */
    public int getBounty() {
        return bounty;
    }

    /**
     * Returns the occurrence of a ship
     * 
     * @return The occurrence of a ship
     */
    public int getOccurrence() {
        return occurrence;
    }

    /**
     * Returns the police modifier for encounters for the ship
     * 
     * @return The police modifier for encounters for the ship
     */
    public int getPoliceModifier() {
        return policeModifier;
    }

    /**
     * Returns the pirate modifier for encounters for the ship
     * 
     * @return The pirate modifier for encounters for the ship
     */
    public int getPirateModifier() {
        return pirateModifier;
    }

    /**
     * Returns the trader modifier for encounters for the ship
     * 
     * @return The trader modifier for encounters for the ship
     */
    public int getTraderModifier() {
        return traderModifier;
    }

    /**
     * Gets the repair cost for the ship
     * 
     * @return The repair cost for the ship
     */
    public int getRepairCost() {
        return repairCost;
    }

    /**
     * Returns the size of the ship
     * 
     * @return The size of the ship
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the minimum reputation for the corresponding EncounterType
     * 
     * @param type
     *            The type of NPCEncounter
     * @return The corresponding minimum reputation for this EncounterType
     */
    public int getMinRep(EncounterType type) {
        if (type == EncounterType.POLICE) {
            return minPoliceRep;
        } else if (type == EncounterType.PIRATE) {
            return minPirateRep;
        } else if (type == EncounterType.TRADER) {
            return minTraderRep;
        }
        return 0;
    }

    /**
     * Returns the maximum reputation for the corresponding EncounterType
     * 
     * @param type
     *            The type of NPCEncounter
     * @return The corresponding maximum reputation for this EncounterType
     */
    public int getMaxRep(EncounterType type) {
        if (type == EncounterType.POLICE) {
            return maxPoliceRep;
        } else if (type == EncounterType.PIRATE) {
            return maxPirateRep;
        } else if (type == EncounterType.TRADER) {
            return maxTraderRep;
        }
        return 0;
    }

    @Override
    public String toString() {
        switch (this) {
        case FLEA:
            return "Flea";
        case GNAT:
            return "Gnat";
        case FIREFLY:
            return "Firefly";
        case MOSQUITO:
            return "Mosquito";
        case BUMBLEBEE:
            return "Bumblebee";
        case BEETLE:
            return "Beetle";
        case HORNET:
            return "Hornet";
        case GRASSHOPPER:
            return "Grasshopper";
        case TERMITE:
            return "Termite";
        case WASP:
            return "Wasp";
        case MANTIS:
            return "Mantis";
        default:
            return "";
        }
    }
}