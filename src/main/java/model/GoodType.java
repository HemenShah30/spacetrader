package model;

/**
 * Enum representing the type of good a good is. Info holder.
 * 
 * @author Larry He
 * 
 */
public enum GoodType {
	WATER		(0, 0, 2, 30, 3, 4, Condition.DROUGHT, SpecialResource.LOTSOFWATER, SpecialResource.DESERT, 30, 50),
	FURS 		(0, 0, 0, 250, 10, 10, Condition.COLD, SpecialResource.RICHFAUNA, SpecialResource.LIFELESS, 230, 180),
	FOOD 		(1, 0, 1, 100, 5, 5, Condition.CROPFAIL, SpecialResource.RICHSOIL, SpecialResource.POORSOIL, 90, 160),
	ORE 		(2, 2, 3, 350, 20, 10, Condition.WAR, SpecialResource.MINERALRICH, SpecialResource.MINERALPOOR, 350, 420),
	GAMES 		(3, 1, 6, 250, -10, 5, Condition.BOREDOM, SpecialResource.ARTISTIC, SpecialResource.NEVER, 160, 720),
	FIREARMS 	(3, 1, 5, 1250, -75, 100, Condition.WAR, SpecialResource.WARLIKE, SpecialResource.NEVER, 600, 1100),
	MEDICINE 	(4, 1, 6, 650, -20, 10, Condition.PLAGUE, SpecialResource.LOTSOFHERBS, SpecialResource.NEVER, 400, 700),
	MACHINES 	(4, 3, 5, 900, -30, 5, Condition.LACKOFWORKERS, SpecialResource.NEVER, SpecialResource.NEVER, 600, 800),
	NARCOTICS 	(5, 0, 5, 3500, -125, 150, Condition.BOREDOM, SpecialResource.WEIRDMUSHROOMS, SpecialResource.NEVER, 2000, 3000),
	ROBOTS 		(6, 4, 7, 5000, -150, 100, Condition.LACKOFWORKERS, SpecialResource.NEVER, SpecialResource.NEVER, 3500, 5000);
	
	private int MTLP; //Minimum Tech Level to produce this resource
	private int MTLU; //Maximum Tech Level to be use this resource
	private int TTP; //Tech Level that produces the most of this item
	private int basePrice;
	private int IPL; //Price increase per Tech Level
	private int Var; //Maximum percentage the price can vary above or below the base
	private Condition IE; //Radical price increase event
	private SpecialResource CR; //Cheap resource: When this condition is present, the resource is low
	private SpecialResource ER; //Expensive resource: When this condition is present, the resource is expensive
	private int MTL; //Min price offered in space trade with random trader (not on a planet)
	private int MTH; //Max price offered in space trade with random trader (not on a planet)
	
	private GoodType(int MTLP, int MTLU, int TTP, int basePrice, int IPL, 
			int Var, Condition IE, SpecialResource CR, SpecialResource ER, int MTL, int MTH) {
		this.MTLP = MTLP;
		this.MTLU = MTLU;
		this.TTP = TTP;
		this.basePrice = basePrice;
		this.IPL = IPL;
		this.Var = Var;
		this.IE = IE;
		this.CR = CR;
		this.ER = ER;
		this.MTL = MTL;
		this.MTH = MTH;
	}
	
	/**
	 * Getter for the minimum technology level to produce
	 * 
	 * @return Minimum technology level to produce
	 */
	public int getMTLP() {
		return MTLP;
	}
	
	/**
	 * Getter for the minimum technology level to use
	 * 
	 * @return Minimum technology level to use
	 */
	public int getMTLU() {
		return MTLU;
	}
	
	/**
	 * Getter for the top tech producer
	 * (i.e. indicates what tech level will produce the most of this good)
	 * 
	 * @return Top tech producer
	 */
	public int getTTP() {
		return TTP;
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
	 * Getter for increase per level
	 * (i.e. higher tech level means higher cost)
	 * 
	 * @return Increase per level
	 */
	public int getIPL() {
		return IPL;
	}
	
	/**
	 * Getter for the random variation of price
	 * 
	 * @return Random variation of price
	 */
	public int getVar() {
		return Var;
	}
	
	/**
	 * Getter for increase event
	 * (i.e. events that cause certain goods to be very expensive)
	 * 
	 * @return Increase event of a planet
	 */
	public Condition getIE() {
		return IE;
	}
	
	/**
	 * Getter for the special resource that causes certain goods to be cheaper
	 * 
	 * @return Special resource that makes things cheaper
	 */
	public SpecialResource getCR() {
		return CR;
	}
	
	/**
	 * Getter for the special resource that causes certain goods to be more expensive
	 * 
	 * @return Special resource that makes things more expensive
	 */
	public SpecialResource getER() {
		return ER;
	}
	
	/**
	 * Getter for lower bound of a good's price for a non-planet trader
	 * 
	 * @return Lower bound of a good's price for a non-planet trader
	 */
	public int getMTL() {
		return MTL;
	}
	
	/**
	 * Getter for upper bound of a good's price for a non-planet trader
	 * 
	 * @return Upper bound of a good's price for a non-planet trader
	 */
	public int getMTH() {
		return MTH;
	}
}
