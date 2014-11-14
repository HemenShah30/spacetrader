package model;

import model.Enum.Condition;
import model.Enum.EncounterRate;
import model.Enum.Government;
import model.Enum.SpecialResource;
import model.Enum.TechLevel;

import javafx.scene.paint.Color;

/**
 * Class representing a planet in the Universe
 * 
 * @author Larry He
 * 
 */
public class Planet implements Boundary {
    private String name;
    private TechLevel techLevel;
    private SpecialResource resource;
    private Government government;
    private Location location;
    private Marketplace marketplace;
    private Shipyard shipyard;
    private Bar bar;
    private Condition condition;
    private EncounterRate policeEncounterRate;
    private EncounterRate pirateEncounterRate;
    private EncounterRate traderEncounterRate;
    private int diameter;
    private int chances;
    private Color color;
    public static final Color[] approvedColors = { Color.DARKCYAN, Color.MEDIUMVIOLETRED,
            Color.AQUAMARINE, Color.SPRINGGREEN, Color.YELLOWGREEN };

    /**
     * Planet constructor taking in all the instance variable values
     * 
     * @param n
     *            The name of the planet
     * @param tech
     *            The TechLevel for the planet
     * @param r
     *            The SpecialResource for the planet
     * @param g
     *            The Government of the planet
     * @param l
     *            The Location of the planet in space
     * @param c
     *            The Condition afflicting the planet
     * @param policeRate
     *            The rate at which Police are encountered around the planet
     * @param pirateRate
     *            The rate at which Pirates are encountered around the planet
     * @param traderRate
     *            The rate at which Traders are encountered around the planet
     * @param size
     *            The size of the planet in space
     * @param color
     *            The color of the planet
     */
    public Planet(String name, TechLevel tech, SpecialResource resource, 
            Government government, Location location, Condition condition, 
            EncounterRate policeRate, EncounterRate pirateRate,
            EncounterRate traderRate, int size, Color color) {
        setName(name);
        setTechLevel(tech);
        setResource(resource);
        setGovernment(government);
        setLocation(location);
        setCondition(condition);
        policeEncounterRate = policeRate;
        pirateEncounterRate = pirateRate;
        traderEncounterRate = traderRate;
        marketplace = new Marketplace(this);
        if (tech.getValue() < 4) {
            shipyard = null;
        } else {
            shipyard = new Shipyard(this);
        }
        bar = new Bar();
        setDiameter(size);
        setColor(color);
        setChances();
    }

    /**
     * Private setter for name validation
     * 
     * @param name
     *            The name of the planet to be set
     */
    private void setName(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    /**
     * Private setter for tech level validation
     * 
     * @param tl
     *            The tech level of the planet to be set
     */
    private void setTechLevel(TechLevel tl) {
        if (tl == null) {
            throw new IllegalArgumentException();
        }
        techLevel = tl;
    }

    /**
     * Private setter for resource validation
     * 
     * @param resource
     *            The resource of the planet to be set
     */
    private void setResource(SpecialResource resource) {
        if (resource == null) {
            throw new IllegalArgumentException();
        }
        this.resource = resource;
    }

    /**
     * Private setter for government type validation
     * 
     * @param g
     *            The government type of the planet to be set
     */
    private void setGovernment(Government government) {
        if (government == null) {
            throw new IllegalArgumentException();
        }
        this.government = government;
    }

    /**
     * Private setter for Location type validation
     * 
     * @param l
     *            The location of the planet to be set
     */
    private void setLocation(Location location) {
        if (location == null) {
            throw new IllegalArgumentException();
        }
        this.location = location;
    }

    /**
     * Private setter for Condition type validation
     * 
     * @param c
     *            The condition of the planet to be set
     */
    private void setCondition(Condition condition) {
        if (condition == null) {
            throw new IllegalArgumentException();
        }
        this.condition = condition;
    }

    /**
     * private setter for size validation sets to 3 if invalid
     * 
     * @param i
     */
    private void setDiameter(int length) {
        if (2 < length && length < 9) {
            diameter = length;
        } else {
            diameter = 5;
        }
    }

    /**
     * Private setter for color validation if color is not an approved color, it is set to green
     * 
     * @param c
     *            The color of the planet
     */
    private void setColor(Color co) {
        boolean isSet = false;
        for (Color col : approvedColors) {
            if (col.equals(co)) {
                color = co;
                isSet = true;
                break;
            }
        }
        if (!isSet) {
            color = approvedColors[2];
        }
    }

    /**
     * Sets the number of chances to have an encounter when traveling to the planet. Is random, 3-12
     */
    private void setChances() {
        chances = (int) (Math.random() * 10) + 3;
    }

    /**
     * Getter for the name of the planet
     * 
     * @return The name of the planet
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the tech level for the planet
     * 
     * @return The tech level of the planet
     */
    public TechLevel getTechLevel() {
        return techLevel;
    }

    /**
     * Getter for the resource of the planet
     * 
     * @return The resource of the planet
     */
    public SpecialResource getResource() {
        return resource;
    }

    /**
     * Getter for the government type of the planet
     * 
     * @return The government of the planet
     */
    public Government getGovernment() {
        return government;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    /**
     * Getter for the condition of the planet
     * 
     * @return The condition of the planet
     */
    public Condition getCondition() {
        return condition;
    }

    /**
     * Returns the marketplace for the planet
     * 
     * @return The marketplace for the planet
     */
    public Marketplace getMarketplace() {
        return marketplace;
    }

    /**
     * Returns the shipyard for the planet
     * 
     * @return The shipyard for the planet
     */
    public Shipyard getShipyard() {
        return shipyard;
    }

    /**
     * Returns the bar for the planet
     * 
     * @return The bar for the planet
     */
    public Bar getBar() {
        return bar;
    }

    /**
     * Setter for the bar for the planet
     * 
     * @param bar
     *            The bar for the planet
     */
    public void setBar(Bar bar) {
        this.bar = bar;
    }

    @Override
    public String toString() {
        return name + ", Location: " + location + ", Tech Level: " + techLevel + ", Resource: "
                + resource + ", Goverment: " + government + ", Condition: " + condition
                + ", Mercenaries: " + bar;
    }

    @Override
    public boolean isLocationInside(Location location) {
        // TODO: check corners of box here to get a more accurate location
        return true;
    }

    /**
     * Getter for the amount of police in the planet area
     * 
     * @return The amount of police in the planet area
     */
    public EncounterRate getPoliceEncounterRate() {
        return policeEncounterRate;
    }

    /**
     * Getter for the amount of pirates in the planet area
     * 
     * @return The amount of pirates in the planet area
     */
    public EncounterRate getPirateEncounterRate() {
        return pirateEncounterRate;
    }

    /**
     * Getter for the amount of trader in the planet area
     * 
     * @return The amount of trader in the planet area
     */
    public EncounterRate getTraderEncounterRate() {
        return traderEncounterRate;
    }

    /**
     * returns planet size
     * 
     * @return planet size
     */
    public int getDiameter() {
        return diameter;
    }

    /**
     * returns planet color
     * 
     * @return planet color
     */
    public Color getColor() {
        return color;
    }

    /**
     * returns planet chances
     * 
     * @return planet chances
     */
    public int getChances() {
        return chances;
    }
}