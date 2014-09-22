package model;

/**
 * Class representing the type of good a good is
 * 
 * @author Larry He
 * 
 */
public enum GoodType {
	WATER		(0, 0, 2, 30, 3, 4, DROUGHT, LOTSOFWATER, DESERT, 30, 50),
	FURS 		(0, 0, 0, 250, 10, 10, COLD, RICHFAUNA, LIFELESS, 230, 180),
	FOOD 		(1, 0, 1, 100, 5, 5, CROPFAIL, RICHSOIL, POORSOIL, 90, 160),
	ORE 		(2, 2, 3, 350, 20, 10, WAR, MINERALRICH, MINERALPOOR, 350, 420),
	GAMES 		(3, 1, 6, 250, -10, 5, BOREDOM, ARTISTIC, NEVER, 160, 720),
	FIREARMS 	(3, 1, 5, 1250, -75, 100, WAR, WARLIKE, NEVER, 600, 1100),
	MEDICINE 	(4, 1, 6, 650, -20, 10, PLAGUE, LOTSOFHERBS, NEVER, 400, 700),
	MACHINES 	(4, 3, 5, 900, -30, 5, LACKOFWORKERS, NEVER, NEVER, 600, 800),
	NARCOTICS 	(5, 0, 5, 3500, -125, 150, BOREDOM, WEIRDMUSHROOMS, NEVER, 2000, 3000),
	ROBOTS 		(6, 4, 7, 5000, -150, 100, LACKOFWORKERS, NEVER, NEVER, 3500, 5000);
	
	private int MTLP; //Minimum Tech Level to produce this resource
	private int MTLU; //Maximum Tech Level to be use this resource
	private int TTP; //Tech Level that produces the most of this item
	private int basePrice;
	private int IPL; //Price increase per Tech Level
	private int Var; //Maximum percentage the price can vary above or below the base
	private int IE; //Radical price increase event
	private SpecialResource CR; //Cheap resource: When this condition is present, the resource is low
	private SpecialResource ER; //Expensive resource: When this condition is present, the resource is expensive
	private int MTL; //Min price offered in space trade with random trader (not on a planet)
	private int MTH; //Max price offered in space trade with random trader (not on a planet)
	
	private GoodType(int MTLP, int MTLU, int TTP, int basePrice, int IPL, 
			int Var, int IE, SpecialResource CR, SpecialResource ER, int MTL, int MTH) {
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
	
	@Override
	public String toString() {
		return basePrice + "";
	}
}
