package model.Enum;

/**
 * Enum representing the various types of trading goods
 * 
 * @author Larry He
 * 
 */
public enum GoodType {
    WATER       (0, 0, 2, 30, 3, 4, Condition.DROUGHT, SpecialResource.LOTSOFWATER, SpecialResource.DESERT, 30, 50, 5, 3, 7, 3, 10),
    FURS        (0, 0, 0, 250, 10, 10, Condition.COLD, SpecialResource.RICHFAUNA, SpecialResource.LIFELESS, 180, 230, -1, 4, 3, 2, 13),
    FOOD        (1, 0, 1, 100, 5, 5, Condition.CROPFAIL, SpecialResource.RICHSOIL, SpecialResource.POORSOIL, 90, 160, 10, 5, 7, 3, 15),
    ORE         (2, 2, 3, 350, 20, 10, Condition.WAR, SpecialResource.MINERALRICH, SpecialResource.MINERALPOOR, 350, 420, 10, 5, -1, 2, 9),
    GAMES       (3, 1, 6, 250, -10, 5, Condition.BOREDOM, SpecialResource.ARTISTIC, null, 160, 720, 10, 4, -1, 4, 15),
    FIREARMS    (3, 1, 5, 1250, -75, 100, Condition.WAR, SpecialResource.WARLIKE, null, 600, 1100, 15, 4, 20, 3, 25),
    MEDICINE    (4, 1, 6, 650, -20, 10, Condition.PLAGUE, SpecialResource.LOTSOFHERBS, null, 400, 700, 10, 5, 5, 4, 11),
    MACHINES    (4, 3, 5, 900, -30, 5, Condition.LACKOFWORKERS, null, null, 600, 800, 10, 4, -1, 2, 10),
    NARCOTICS   (5, 0, 5, 3500, -125, 150, Condition.BOREDOM, SpecialResource.WEIRDMUSHROOMS, null, 2000, 3000, 10, 4, 20, 2, 24),
    ROBOTS      (6, 4, 7, 5000, -150, 100, Condition.LACKOFWORKERS, null, null, 3500, 5000, 15, 4, -1, 1, 10);

    private int minTechLevelToProduce;
    private int minTechLevelToUse;
    private int biggestProducer;
    private int basePrice;
    private int priceIncPerTechLevel;
    private int variance;
    private Condition condition;
    private SpecialResource cheapResource;
    private SpecialResource expensiveResource;
    private int minTraderPrice;
    private int maxTraderPrice;
    private int baseQuantity;
    private int quantityIncPerTechLevel;
    private int policeAmount;
    private int pirateAmount;
    private int traderAmount;
    private int NPCMaxVariation = 2;

    /**
     * Constructor for the GoodType enum
     * 
     * @param minTechLevelToProduce
     *            Minimum Tech Level to produce this resource
     * @param minTechLevelToUse
     *            Minimum Tech Level to use this resource
     * @param biggestProducer
     *            Tech Level that produces the most of this item
     * @param basePrice
     *            Base Price of the good
     * @param priceIncPerTechLevel
     *            Price increase per Tech Level
     * @param variance
     *            Maximum difference the price can vary above or below the base
     * @param condition
     *            Radical price increase condition
     * @param cheapResource
     *            Cheap condition: When this condition is present, the resource is low
     * @param expensiveResource
     *            Expensive condition: When this condition is present, the resource is expensive
     * @param minTraderPrice
     *            Minimum price offered in space trade with random trader (not on a planet)
     * @param maxTraderPrice
     *            Maximum price offered in space trade with random trader (not on a planet)
     * @param baseQuantity
     *            Base quantity of good
     * @param quantityIncPerTechLevel
     *            Quantity increase per Tech Level
     * @param policeAmount
     *            Amount of a good a police ship can have in cargo
     * @param pirateAmount
     *            Amount of a good a pirate ship can have in cargo
     * @param traderAmount
     *            Amount of a good a trader ship can have in cargo
     */
    private GoodType(int minTechLevelToProduce, int minTechLevelToUse, int biggestProducer,
            int basePrice, int priceIncPerTechLevel, int variance, Condition condition,
            SpecialResource cheapResource, SpecialResource expensiveResource, int minTraderPrice,
            int maxTraderPrice, int baseQuantity, int quantityIncPerTechLevel, int policeAmount,
            int pirateAmount, int traderAmount) {
        this.minTechLevelToProduce = minTechLevelToProduce;
        this.minTechLevelToUse = minTechLevelToUse;
        this.biggestProducer = biggestProducer;
        this.basePrice = basePrice;
        this.priceIncPerTechLevel = priceIncPerTechLevel;
        this.variance = variance;
        this.condition = condition;
        this.cheapResource = cheapResource;
        this.expensiveResource = expensiveResource;
        this.minTraderPrice = minTraderPrice;
        this.maxTraderPrice = maxTraderPrice;
        this.baseQuantity = baseQuantity;
        this.quantityIncPerTechLevel = quantityIncPerTechLevel;
        this.policeAmount = policeAmount;
        this.pirateAmount = pirateAmount;
        this.traderAmount = traderAmount;
    }

    /**
     * Getter for the minimum technology level to produce
     * 
     * @return Minimum technology level to produce
     */
    public int getMinTechLevelToProduce() {
        return minTechLevelToProduce;
    }

    /**
     * Getter for the minimum technology level to use
     * 
     * @return Minimum technology level to use
     */
    public int getMinTechLevelToUse() {
        return minTechLevelToUse;
    }

    /**
     * Getter for the top tech producer (i.e. indicates what tech level will produce the most of
     * this good)
     * 
     * @return Top tech producer
     */
    public int getBiggestProducer() {
        return biggestProducer;
    }

    /**
     * Getter for the base price of a good
     * 
     * @return Base price of a good
     */
    public int getBasePrice() {
        return basePrice;
    }

    /**
     * Getter for increase per level (i.e. higher tech level means higher cost)
     * 
     * @return Increase per level
     */
    public int getPriceIncPerTechLevel() {
        return priceIncPerTechLevel;
    }

    /**
     * Getter for the random variation of price
     * 
     * @return Random variation of price
     */
    public int getVariance() {
        return variance;
    }

    /**
     * Getter for increase condition (i.e. conditions that cause certain goods to be very expensive)
     * 
     * @return Increase condition of a planet
     */
    public Condition getCondition() {
        return condition;
    }

    /**
     * Getter for the special resource that causes certain goods to be cheaper
     * 
     * @return Special resource that makes things cheaper
     */
    public SpecialResource getCheapResource() {
        return cheapResource;
    }

    /**
     * Getter for the special resource that causes certain goods to be more expensive
     * 
     * @return Special resource that makes things more expensive
     */
    public SpecialResource getExpensiveResource() {
        return expensiveResource;
    }

    /**
     * Getter for lower bound of a good's price for a non-planet trader
     * 
     * @return Lower bound of a good's price for a non-planet trader
     */
    public int getMinTraderPrice() {
        return minTraderPrice;
    }

    /**
     * Getter for upper bound of a good's price for a non-planet trader
     * 
     * @return Upper bound of a good's price for a non-planet trader
     */
    public int getMaxTraderPrice() {
        return maxTraderPrice;
    }

    /**
     * Getter for base quantity of a good
     * 
     * @return Base quantity of good
     */
    public int getBaseQuantity() {
        return baseQuantity;
    }

    /**
     * Getter for base quantity of a good
     * 
     * @return Base quantity of good
     */
    public int getQuantityIncPerTechLevel() {
        return quantityIncPerTechLevel;
    }

    public int getNPCAmount(EncounterType type) {
        if (type == EncounterType.POLICE) {
            return (int) (policeAmount * Math.random() * NPCMaxVariation);
        } else if (type == EncounterType.PIRATE) {
            return (int) (pirateAmount * Math.random() * NPCMaxVariation);
        } else if (type == EncounterType.TRADER) {
            return (int) (traderAmount * Math.random() * NPCMaxVariation);
        }
        return 0;
    }

    @Override
    public String toString() {
        switch (this) {
        case WATER:
            return "Water";
        case FURS:
            return "Furs";
        case FOOD:
            return "Food";
        case ORE:
            return "Ore";
        case GAMES:
            return "Games";
        case MEDICINE:
            return "Medicine";
        case FIREARMS:
            return "Firearms";
        case MACHINES:
            return "Machines";
        case NARCOTICS:
            return "Narcotics";
        case ROBOTS:
            return "Robots";
        default:
            return "";
        }
    }
}