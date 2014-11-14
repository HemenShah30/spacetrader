package model;

import model.enums.GoodType;
import model.enums.LaserType;
import model.enums.ShieldType;
import model.enums.ShipType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class representing a player's ship
 * 
 * @author Eric Wan
 * 
 */
public class Ship {
    private ShipType shipType;
    private int currHP;
    private int fuel;
    private int cargoSize;
    private Map<ShieldType, Integer> shields;
    private List<LaserType> lasers;
    private List<Gadget> gadgets;
    private List<Mercenary> mercenaries;
    private Map<GoodType, Integer> cargo;

    /**
     * Constructor for a ship taking in the starting fuel and the ship type
     * 
     * @param sT
     *            The ShipType for the ship
     */
    public Ship(ShipType shiptype) {
        this(shiptype, shiptype.getTotalHP(), shiptype.getFuel());
    }

    /**
     * Ship constructor taking in values for more instance variables
     * 
     * @param sT
     *            The ShipType for the ship
     * @param hp
     *            The Hull Points for the ship
     * @param f
     *            The fuel for the ship
     */
    public Ship(ShipType shiptype, int hp, int fuel) {
        setShipType(shiptype);
        cargo = new HashMap<GoodType, Integer>();
        for (GoodType type : GoodType.values()) {
            cargo.put(type, 0);
        }
        this.fuel = fuel;
        currHP = hp;
        cargoSize = shiptype.getCargoSize();
        shields = new HashMap<ShieldType, Integer>();
        lasers = new ArrayList<LaserType>();
        gadgets = new ArrayList<Gadget>();
        mercenaries = new ArrayList<Mercenary>();
    }

    /**
     * The given amount of damage is dealt to the ship
     * 
     * @param damage
     *            the amount of damage to be done
     * @throws DeathException
     *             Thrown when the ship would have 0 or less health after taking damage
     */
    public void takeDamage(int damage) throws DeathException {
        for (ShieldType shield : shields.keySet()) {
            int shieldHP = shields.get(shield);
            int absorbed = Math.min(damage, shieldHP);
            shields.put(shield, shieldHP - absorbed);
            damage -= absorbed;
        }
        currHP -= damage;
        if (currHP <= 0) {
            throw new DeathException();
        }
    }

    /**
     * A given amount of shields is added to the ship
     * 
     * @param amount
     *            The amount the shields need to be increased by
     */
    public void addShieldHP(int amount) {
        for (ShieldType shield : shields.keySet()) {
            int newShieldHP = Math.min(amount + shields.get(shield), shield.getShieldHP());
            amount -= newShieldHP - shields.get(shield);
            shields.put(shield, newShieldHP);
        }
    }

    /**
     * Returns maximum hull points of ship
     * 
     * @return the max hp of the ship
     */
    public int getMaxHP() {
        return shipType.getTotalHP();
    }

    /**
     * Returns the current shield hp of the ship
     * 
     * @return The current shield hp of the ship
     */
    public int getCurrShieldHP() {
        int sum = 0;
        for (int shieldHP : shields.values()) {
            sum += shieldHP;
        }
        return sum;
    }

    /**
     * Returns the maximum shield health points for the ship
     * 
     * @return The maximum shield hp for the ship
     */
    public int getMaxShieldHP() {
        int shieldHP = 0;
        for (ShieldType shield : shields.keySet()) {
            shieldHP += shield.getShieldHP();
        }
        return shieldHP;
    }

    /**
     * Returns currentHP of ship
     * 
     * @return the currentHP of the ship
     */
    public int getCurrHP() {
        return currHP;
    }

    /**
     * Sets the current hull points for the ship
     * 
     * @param currHP
     *            The new hull points for the ship
     */
    public void setCurrHP(int currHP) {
        this.currHP = currHP;
    }

    /**
     * Returns the cargo contents of ship
     * 
     * @return the cargo contents of the ship
     */
    public Map<GoodType, Integer> getCargo() {
        return cargo;
    }

    /**
     * Returns the current fuel of ship
     * 
     * @return the current fuel of the ship
     */
    public int getFuel() {
        return fuel;
    }

    /**
     * Sets the fuel the ship currently has
     * 
     * @param f
     *            The new fuel for the ship
     */
    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    /**
     * Calculates and returns amount needed to refuel
     * 
     * @return cost to refuel ship
     */
    public double getRefuelCost() {
        return (shipType.getFuel() - fuel) * shipType.getFuelCost();
    }

    /**
     * Calculates and returns amount needed to repair
     * 
     * @return The cost to repair the ship
     */
    public double getRepairCost() {
        return (shipType.getTotalHP() - currHP) * shipType.getRepairCost();
    }

    /**
     * Refuels ship
     * 
     * @param credits
     *            used in refueling
     */
    public void refuel(double credits) {
        fuel += (credits / shipType.getFuelCost());
    }

    /**
     * Repairs ships
     * 
     * @param credits
     *            amount used in the repair
     */
    public void repair(double credits) {
        currHP += credits / shipType.getRepairCost();
    }

    /**
     * Returns the size of the cargo hold of the ship
     * 
     * @return the size of the cargo hold of the ship
     */
    public int getCargoSize() {
        return cargoSize + (gadgets.contains(new FiveExtraCargo()) ? 5 : 0);
    }

    /**
     * Sets the cargo size for the ship
     * 
     * @param cargoSize
     *            The new cargo size for the ship
     */
    public void setCargoSize(int cargoSize) {
        this.cargoSize = cargoSize;
    }

    /**
     * Returns the list of mercenaries
     *
     * @return the list of mercenaries
     */
    public List<Mercenary> getMercenaries() {
        return mercenaries;
    }

    /**
     * Returns the current cargo capacity of the ship
     * 
     * @return the current cargo capacity of the ship
     */
    public int getCurrCargo() {
        int currCargo = 0;
        for (GoodType good : cargo.keySet()) {
            currCargo += cargo.get(good);
        }
        return currCargo;
    }

    /**
     * Adds a good to the cargo of the ship
     * 
     * @param goodType
     *            The good type that is being added to the ship's cargo
     * @param quantity
     *            The number of this good being added
     */
    public void addToCargo(GoodType goodType, int quantity) {
        int currentGoodCargo = cargo.get(goodType);
        cargo.put(goodType, currentGoodCargo + quantity);
    }

    /**
     * Removes a good from the cargo of the ship
     * 
     * @param goodType
     *            The good type being removed from this ship's cargo
     * @param quantity
     *            The number of this good being removed
     */
    public void removeFromCargo(GoodType goodType, int quantity) {
        int currentGoodCargo = cargo.get(goodType);
        cargo.put(goodType, currentGoodCargo - quantity);
    }

    /**
     * Returns quantity of a certain good in cargo (will be 0 if doesn't exist)
     * 
     * @param goodType
     *            The GoodType being checked
     * @return The amount of the good type in cargo
     */
    public int amountInCargo(GoodType goodType) {
        if (!cargo.containsKey(goodType)) {
            return 0;
        }
        return cargo.get(goodType);
    }

    /**
     * Getter for the ship type for the ship
     * 
     * @return The ship type for the ship
     */
    public ShipType getShipType() {
        return shipType;
    }

    /**
     * Private setter for the ship type of the ship
     * 
     * @param sT
     *            The ship type the ship will have
     */
    private void setShipType(ShipType shiptype) {
        if (shiptype == null) {
            throw new IllegalArgumentException();
        }
        shipType = shiptype;
    }

    /**
     * Adds a shield to the ship
     * 
     * @param shield
     *            The shield to be added to the ship
     */
    public void addShield(ShieldType shield) {
        if (shipType.getShieldSlots() > shields.size()) {
            shields.put(shield, shield.getShieldHP());
            return;
        }
        throw new MaxCapacityException("Already has max shields");
    }

    /**
     * Adds a laser to the ship
     * 
     * @param laser
     *            The laser to be added to the ship
     */
    public void addLaser(LaserType laser) {
        if (shipType.getWeaponSlots() > lasers.size()) {
            lasers.add(laser);
            return;
        }
        throw new MaxCapacityException("Already has max lasers");
    }

    /**
     * Adds a gadget to the ship
     * 
     * @param g
     *            The gadget to be added to the ship
     */
    public void addGadget(Gadget gadget) {
        if (shipType.getGadgetSlots() > gadgets.size()) {
            gadgets.add(gadget);
            return;
        }
        throw new MaxCapacityException("Already has max gadgets");
    }

    /**
     * Returns all lasers currently equipped by ship
     * 
     * @return The lasers equipped by the ship
     */
    public List<LaserType> getLasers() {
        return lasers;
    }

    /**
     * Returns the shields equipped by the ship
     * 
     * @return The shields equipped by the ship
     */
    public List<ShieldType> getShields() {
        return new ArrayList<ShieldType>(shields.keySet());
    }

    /**
     * Returns the gadgets equipped by the ship
     * 
     * @return The gadgets equipped by the ship
     */
    public List<Gadget> getGadgets() {
        return gadgets;
    }

    /**
     * Returns the number of lasers equipped
     * 
     * @return the number of lasers
     */
    public int getNumLasers() {
        return lasers.size();
    }

    /**
     * Returns the number of shields equipped
     * 
     * @return the number of shields
     */
    public int getNumShields() {
        return shields.size();
    }

    /**
     * Returns the number of gadgets equipped
     * 
     * @return the number of gadgets
     */
    public int getNumGadgets() {
        return gadgets.size();
    }

    /**
     * Gets the navigation bonus for the ship
     * 
     * @return The navigation bonus for the ship
     */
    public int getPilotBonus() {
        return gadgets.contains(new NavigatingSystem()) ? 5 : 0;
    }

    /**
     * Gets the fighting bonus for the ship
     * 
     * @return The fighting bonus for the ship
     */
    public int getFightingBonus() {
        return gadgets.contains(new TargetingSystem()) ? 5 : 0;
    }

    /**
     * Gets the engineer bonus for the ship
     * 
     * @return The engineer bonus for the ship
     */
    public int getEngineerBonus() {
        return gadgets.contains(new AutoRepairSystem()) ? 5 : 0;
    }

    /**
     * Returns whether or not the ship has a cloaking device
     * 
     * @return Whether or not the ship has a cloaking device
     */
    public boolean hasCloakingDevice() {
        return gadgets.contains(new CloakingDevice());
    }

    /**
     * Adds the ShipUpgrade to the ship
     * 
     * @param sell
     *            The ShipUpgrade being added
     */
    public void addShipUpgrade(ShipUpgrade upgrade) {
        switch (upgrade.getType()) {
        case 0:
            ShieldType shield = (ShieldType) upgrade;
            addShield(shield);
            break;
        case 1:
            LaserType laser = (LaserType) upgrade;
            addLaser(laser);
            break;
        case 2:
            Gadget gadget = (Gadget) upgrade;
            addGadget(gadget);
            break;
        default:
            break;
        }
    }

    /**
     * Removes the ShipUpgrade from the ship
     * 
     * @param sell
     *            The ShipUpgrade being removed
     */
    public void removeShipUpgrade(ShipUpgrade upgrade) {
        switch (upgrade.getType()) {
        case 0:
            ShieldType shield = (ShieldType) upgrade;
            shields.remove(shield);
            break;
        case 1:
            LaserType laser = (LaserType) upgrade;
            lasers.remove(laser);
            break;
        case 2:
            Gadget gadget = (Gadget) upgrade;
            gadgets.remove(gadget);
            break;
        default:
            break;
        }
    }

    /**
     * Checks to see if the specified laser exists in the ship
     * 
     * @param l
     *            The specified laser
     * @return boolean result
     */
    public boolean hasLaser(LaserType laser) {
        return lasers.contains(laser);
    }

    /**
     * Checks to see if the specified shield exists in the ship
     * 
     * @param l
     *            The specified shield
     * @return boolean result
     */
    public boolean hasShield(ShieldType shield) {
        if (!shields.containsKey(shield)) {
            return false;
        }
        return true;
    }

    /**
     * Checks to see if the specificed gadget exists in the ship
     * 
     * @param l
     *            The specified gadget
     * @return boolean result
     */
    public boolean hasGadget(Gadget gadget) {
        return gadgets.contains(gadget);
    }

    /**
     * Returns the largest number of crew this ship can house
     * 
     * @return The largest number of crew this ship can house
     */
    public int getCrewSpace() {
        return shipType.getCrewSpace();
    }

    /**
     * Returns the current number of crew this ship has
     * 
     * @return The current number of crew this ship has
     */
    public int getCrewCurrentSize() {
        return mercenaries.size();
    }

    /**
     * Adds a mercenary to the ship
     * 
     * @param m
     *            Mercenary to be added
     */
    public void addToCrew(Mercenary merc) {
        mercenaries.add(merc);
    }

    /**
     * Removes a mercenary to the ship
     * 
     * @param m
     *            Mercenary to be removed
     */
    public void removeFromCrew(Mercenary merc) {
        mercenaries.remove(merc);
    }

    /**
     * Returns the highest pilot skill among all mercenaries
     * 
     * @return highest Highest pilot skill among all mercenaries
     */
    public int getMercenaryPilot() {
        int highest = 0;
        if (mercenaries.isEmpty()) {
            return highest;
        }
        for (Mercenary m : mercenaries) {
            if (highest < m.getPilotSkill()) {
                highest = m.getPilotSkill();
            }
        }
        return highest;
    }

    /**
     * Returns the highest engineer skill among all mercenaries
     * 
     * @return highest Highest engineer skill among all mercenaries
     */
    public int getMercenaryEngineer() {
        int highest = 0;
        if (mercenaries.isEmpty()) {
            return highest;
        }
        for (Mercenary m : mercenaries) {
            if (highest < m.getEngineerSkill()) {
                highest = m.getEngineerSkill();
            }
        }
        return highest;
    }

    /**
     * Returns the highest trader skill among all mercenaries
     * 
     * @return highest Highest trader skill among all mercenaries
     */
    public int getMercenaryTrader() {
        int highest = 0;
        if (mercenaries.isEmpty()) {
            return highest;
        }
        for (Mercenary m : mercenaries) {
            if (highest < m.getTraderSkill()) {
                highest = m.getTraderSkill();
            }
        }
        return highest;
    }

    /**
     * Returns the highest fighter skill among all mercenaries
     * 
     * @return highest Highest fighter skill among all mercenaries
     */
    public int getMercenaryFighter() {
        int highest = 0;
        if (mercenaries.isEmpty()) {
            return highest;
        }
        for (Mercenary m : mercenaries) {
            if (highest < m.getFighterSkill()) {
                highest = m.getFighterSkill();
            }
        }
        return highest;
    }

    /**
     * Returns the highest investor skill among all mercenaries
     * 
     * @return highest Highest investor skill among all mercenaries
     */
    public int getMercenaryInvestor() {
        int highest = 0;
        if (mercenaries.isEmpty()) {
            return highest;
        }
        for (Mercenary m : mercenaries) {
            if (highest < m.getInvestorSkill()) {
                highest = m.getInvestorSkill();
            }
        }
        return highest;
    }

    @Override
    public String toString() {
        String msg = "";
        msg += "Shiptype: " + shipType;
        msg += "\nCargo size: " + cargoSize;
        msg += "\nCargo contents: " + cargo.toString();
        msg += "\nWeapon slots: " + shipType.getWeaponSlots();
        msg += "\nWeapons: " + lasers.toString();
        msg += "\nShield slots: " + shipType.getShieldSlots();
        msg += "\nShields: " + shields.toString();
        msg += "\nGadget slots: " + shipType.getGadgetSlots();
        msg += "\nGadgets: " + gadgets.toString();
        msg += "\nMercenaries: " + mercenaries.toString();
        return msg;
    }
}