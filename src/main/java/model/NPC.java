package model;

import model.enums.EncounterType;
import model.enums.GoodType;
import model.enums.LaserType;
import model.enums.ShieldType;
import model.enums.ShipType;

import java.util.ArrayList;

/**
 * Abstract class representing NPCs.
 * 
 * @author Eric Wan and Larry He
 * 
 */
public abstract class NPC {
    private int pilotSkill;
    private int fighterSkill;
    private int traderSkill;
    private int engineerSkill;
    private int investorSkill;
    private double credits;
    private Ship ship;

    /**
     * Returns the NPC's piloting skill
     * 
     * @return The NPC's piloting skill
     */
    public int getPilotSkill() {
        return pilotSkill;
    }

    /**
     * Returns the NPC's fighter skill
     * 
     * @return The NPC's fighter skill
     */
    public int getFighterSkill() {
        return fighterSkill;
    }

    /**
     * Returns the NPC's trader skill
     * 
     * @return The NPC's trader skill
     */
    public int getTraderSkill() {
        return traderSkill;
    }

    /**
     * Returns the NPC's Engineer skill
     * 
     * @return The NPC's engineer skill
     */
    public int getEngineerSkill() {
        return engineerSkill;
    }

    /**
     * Returns the NPC's investor skill
     * 
     * @return The NPC's investor skill
     */
    public int getInvestorSkill() {
        return investorSkill;
    }

    /**
     * Returns the NPC's credits
     * 
     * @return The NPC's credits
     */
    public double getCredits() {
        return credits;
    }

    /**
     * Sets the NPC's pilot skill points
     * 
     * @param points
     *            The NPC's new pilot skill points
     */
    public void setPilotSkill(int points) {
        pilotSkill = points;
    }

    /**
     * Sets the NPC's fighter skill points
     * 
     * @param points
     *            The NPC's new fighter skill points
     */
    public void setFighterSkill(int points) {
        fighterSkill = points;
    }

    /**
     * Sets the NPC's trader skill points
     * 
     * @param points
     *            The NPC's new trader skill points
     */
    public void setTraderSkill(int points) {
        traderSkill = points;
    }

    /**
     * Sets the NPC's engineer skill points
     * 
     * @param points
     *            The NPC's new engineer skill points
     */
    public void setEngineerSkill(int points) {
        engineerSkill = points;
    }

    /**
     * Sets the NPC's investor skill points
     * 
     * @param points
     *            The NPC's new investor skill points
     */
    public void setInvestorSkill(int points) {
        investorSkill = points;
    }

    /**
     * Randomly generates the ship the NPC is using given the player reputation
     * 
     * @param rep
     *            The reputation of the player
     * @param type
     *            The type of encounter the NPC is
     */
    protected void generateShip(int rep, EncounterType type) {
        Ship generatedShip;
        int indx;
        // generate a ship type
        ShipType[] shipTypes = ShipType.values();
        boolean shipTypeGenerated = false;
        while (!shipTypeGenerated) {
            indx = (int) (Math.random() * shipTypes.length);
            if (shipTypes[indx].getMinRep(type) <= rep && shipTypes[indx].getMaxRep(type) >= rep) {
                generatedShip = new Ship(shipTypes[indx]);
                shipTypeGenerated = true;
                setShip(generatedShip);
            }
        }

        // generate a weapon
        LaserType[] laserTypes = LaserType.values();
        int weaponCapacity = getShip().getShipType().getWeaponSlots();
        for (int i = 0; i < weaponCapacity; i++) {
            indx = (int) (Math.random() * laserTypes.length);
            if (laserTypes[indx].getMinRep(type) <= rep
                    && laserTypes[indx].getMaxRep(type) >= rep) {
                ship.addLaser(laserTypes[indx]);
            }
        }

        // generate shields
        ShieldType[] shieldTypes = ShieldType.values();
        int shieldCapacity = getShip().getShipType().getShieldSlots();
        for (int i = 0; i < shieldCapacity; i++) {
            indx = (int) (Math.random() * shieldTypes.length);
            if (shieldTypes[indx].getMinRep(type) <= rep
                    && shieldTypes[indx].getMaxRep(type) >= rep) {
                ship.addShield(shieldTypes[indx]);
            }
        }

        // generate cargo
        GoodType[] goodTypes = GoodType.values();
        int cargoCapacity = getShip().getShipType().getCargoSize();
        boolean addedAllowedGood = false;
        while (!addedAllowedGood) {
            indx = (int) (Math.random() * goodTypes.length);
            // -1 indicates that this type of NPC cannot have this kind of cargo on their ship
            if (goodTypes[indx].getNPCAmount(type) != -1) {
                int goodAmount = goodTypes[indx].getNPCAmount(type);
                if (goodAmount > cargoCapacity) {
                    goodAmount = cargoCapacity;
                }
                ship.addToCargo(goodTypes[indx], goodAmount);
                addedAllowedGood = true;
            }
        }

        // generate gadgets
        ArrayList<Gadget> gadgetTypes = new ArrayList<>(5);
        gadgetTypes.add(new AutoRepairSystem());
        gadgetTypes.add(new CloakingDevice());
        gadgetTypes.add(new FiveExtraCargo());
        gadgetTypes.add(new NavigatingSystem());
        gadgetTypes.add(new TargetingSystem());
        int gadgetCapacity = getShip().getShipType().getGadgetSlots();
        for (int i = 0; i < gadgetCapacity; i++) {
            indx = (int) (Math.random() * shieldTypes.length);
            ship.addGadget(gadgetTypes.get(indx));
        }
    }

    /**
     * Gets the ship of the NPC
     * 
     * @return Ship of the NPC
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Sets the NPCs credits
     * 
     * @param credits
     *            NPC's credits
     */
    public void setCredits(double credits) {
        this.credits = credits;
    }

    /**
     * Sets the NPCs ship
     * 
     * @param ship
     *            the ship
     */
    public void setShip(Ship ship) {
        this.ship = ship;
    }
}
