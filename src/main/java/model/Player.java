package model;

/**
 * Class representing a game player
 * 
 * @author Larry He
 * 
 */
public class Player {
    private String name;
    private int pilotSkill;
    private int fighterSkill;
    private int traderSkill;
    private int engineerSkill;
    private int investorSkill;
    private int traderRep;
    private int policeRep;
    private int pirateRep;
    private double credits;
    private Ship ship;
    private Planet planet;

    /**
     * Constructor for the player taking all the appropriate instance variables
     * 
     * @param name
     *            The name of the player
     * @param pilot
     *            The amount of pilot skill points
     * @param fighter
     *            The amount of fighter skill points
     * @param trader
     *            The amount of trader skill points
     * @param engineer
     *            The amount of engineer skill points
     * @param investor
     *            The amount of investor skill points
     * @param ship
     *            The ship the player is flying in
     */
    public Player(String name, int pilot, int fighter, int trader, int engineer, int investor,
            Ship ship) {
        setName(name);
        this.pilotSkill = pilot;
        this.fighterSkill = fighter;
        this.traderSkill = trader;
        this.engineerSkill = engineer;
        this.investorSkill = investor;
        this.ship = ship;
        credits = 1000;
        traderRep = 50;
        policeRep = 30;
        pirateRep = 1;
    }

    /**
     * Constructor for player for setting all the variables for the class
     * 
     * @param name
     *            The name of the player
     * @param pilot
     *            The amount of pilot skill points
     * @param fighter
     *            The amount of fighter skill points
     * @param trader
     *            The amount of trader skill points
     * @param engineer
     *            The amount of engineer skill points
     * @param investor
     *            The amount of investor skill points
     * @param s
     *            The ship the player is flying in
     * @param c
     *            The credits the player currently has
     * @param traderR
     *            The reputation the player has with traders
     * @param policeR
     *            The reputation the player has with police
     * @param pirateR
     *            The reputation the player has with pirates
     */
    public Player(String name, int pilot, int fighter, int trader, int engineer, int investor,
            Ship ship, double credits, int traderR, int policeR, int pirateR) {
        setName(name);
        pilotSkill = pilot;
        fighterSkill = fighter;
        traderSkill = trader;
        engineerSkill = engineer;
        investorSkill = investor;
        this.ship = ship;
        this.credits = credits;
        traderRep = traderR;
        policeRep = policeR;
        pirateRep = pirateR;
    }

    /**
     * Returns the name of the player
     * 
     * @return The name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the player's piloting skill
     * 
     * @return The player's piloting skill
     */
    public int getPilotSkill() {
        return Math.max(pilotSkill, ship.getMercenaryPilot());
    }

    /**
     * Returns the player's fighter skill
     * 
     * @return The player's fighter skill
     */
    public int getFighterSkill() {
        return Math.max(fighterSkill, ship.getMercenaryFighter());
    }

    /**
     * Returns the player's trader skill
     * 
     * @return The player's trader skill
     */
    public int getTraderSkill() {
        return Math.max(traderSkill, ship.getMercenaryTrader());
    }

    /**
     * Returns the player's Engineer skill
     * 
     * @return The player's engineer skill
     */
    public int getEngineerSkill() {
        return Math.max(engineerSkill, ship.getMercenaryEngineer());
    }

    /**
     * Returns the player's investor skill
     * 
     * @return The player's investor skill
     */
    public int getInvestorSkill() {
        return Math.max(investorSkill, ship.getMercenaryInvestor());
    }

    /**
     * Returns the player's credits
     * 
     * @return The player's credits
     */
    public double getCredits() {
        return credits;
    }

    /**
     * Setter for the name of the player
     * 
     * @param name
     *            The name of the player
     */
    public void setName(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
        this.name = name;
    }

    /**
     * Sets the player's pilot skill points
     * 
     * @param points
     *            The player's new pilot skill points
     */
    public void setPilot(int points) {
        pilotSkill = points;
    }

    /**
     * Sets the player's fighter skill points
     * 
     * @param points
     *            The player's new fighter skill points
     */
    public void setFighter(int points) {
        fighterSkill = points;
    }

    /**
     * Set's the player's trader skill points
     * 
     * @param points
     *            The player's new trader skill points
     */
    public void setTrader(int points) {
        traderSkill = points;
    }

    /**
     * Sets the player's engineer skill points
     * 
     * @param points
     *            The player's new engineer skill points
     */
    public void setEngineer(int points) {
        engineerSkill = points;
    }

    /**
     * Sets the player's investor skill points
     * 
     * @param points
     *            The player's new investor skill points
     */
    public void setInvestor(int points) {
        investorSkill = points;
    }

    /**
     * Increases the player's credits
     * 
     * @param increase
     *            The player's increase in credits
     */
    public void increaseCredits(double increase) {
        credits += increase;
    }

    /**
     * Decreases the player's credits
     * 
     * @param decrease
     *            The player's decrease in credits
     */
    public void decreaseCredits(double decrease) {
        credits -= decrease;
    }

    /**
     * Getter for the player's ship
     * 
     * @return The player's ship
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Removes credits and refuels ship if player has enough credits
     * 
     * @param amountBy
     *            credits used for payment
     * @return true if transaction works, false if fails
     */
    public boolean refuelShip(double amountBy) {
        if (credits >= amountBy) {
            this.decreaseCredits(amountBy);
            ship.refuel(amountBy);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Removes credits and repairs ship if player has enough credits
     * 
     * @param amountBy
     *            credits used for payment
     * @return true if transaction works, false if fails
     */
    public boolean repairShip(double amountBy) {
        if (credits >= amountBy) {
            this.decreaseCredits(amountBy);
            ship.repair(amountBy);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Setter for the player's ship
     * 
     * @param ship
     *            The new ship for the player
     */
    public void setShip(Ship ship) {
        this.ship = ship;
    }

    /**
     * Getter for the planet that the player is currently on
     * 
     * @return The planet the player is currently on
     */
    public Planet getPlanet() {
        return planet;
    }

    /**
     * Setter for the planet the player is currently on, if null then player is in space
     * 
     * @param planet
     *            The planet the player is currently on, if any
     */
    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    @Override
    public String toString() {
        return "Player: " + name + ", Pilot Skill: " + pilotSkill + ", Fighter Skill: "
                + fighterSkill + ", Trader Skill: " + traderSkill + ", Engineer Skill: "
                + engineerSkill + ", Investor Skill: " + investorSkill + ", Credits: " + credits;
    }

    /**
     * Setter for player traderRep
     * 
     * @param rep
     *            int 1-100 to set rep to
     */
    public void setTraderRep(int rep) {
        traderRep = rep;
    }

    /**
     * Setter for player policeRep
     * 
     * @param rep
     *            int 1-100 to set rep to
     */
    public void setPoliceRep(int rep) {
        policeRep = rep;
    }

    /**
     * Setter for player pirateRep
     * 
     * @param rep
     *            int 1-100 to set rep to
     */
    public void setPirateRep(int rep) {
        pirateRep = rep;
    }

    /**
     * Getter for player traderRep
     * 
     * @return player traderRep
     */
    public int getTraderRep() {
        return traderRep;
    }

    /**
     * Returns player policeRep
     * 
     * @return player policeRep
     */
    public int getPoliceRep() {
        return policeRep;
    }

    /**
     * Returns player pirateRep
     * 
     * @return player pirateRep
     */
    public int getPirateRep() {
        return pirateRep;
    }
}