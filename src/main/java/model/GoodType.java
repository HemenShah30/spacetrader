package model;

/**
 * Class representing the type of good a good is. Info holder.
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
	
	public int getMTLP() {
		return MTLP;
	}
	
	public int getMTLU() {
		return MTLU;
	}
	
	public int getTTP() {
		return TTP;
	}
	
	public int getBasePrice() {
		return basePrice;
	}
	
	public Condition getIE() {
		return IE;
	}
	
	public SpecialResource getCR() {
		return CR;
	}
	
	public SpecialResource getER() {
		return ER;
	}
	
	public int getMTL() {
		return MTL;
	}
	
	public int getMTH() {
		return MTH;
	}
	
	@Override
	public String toString() {
		return basePrice + "";
	}
}
