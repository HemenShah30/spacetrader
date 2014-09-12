package SpaceTrader;

public class Player {
	private String name;
	private int pilot, fighter, trader, engineer, investor;
	
	public Player(String name, int pilot, int fighter, int trader, int engineer, int investor) {
		this.name = name;
		this.pilot = pilot;
		this.fighter = fighter;
		this.trader = trader;
		this.engineer = engineer;
		this.investor = investor;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPilot() {
		return pilot;
	}
	
	public int getFighter() {
		return fighter;
	}
	
	public int getTrader() {
		return trader;
	}
	
	public int getEngineer() {
		return engineer;
	}
	
	public int getInvestor() {
		return investor;
	}
	
	public void setPilot(int points) {
		pilot = points;
	}
	
	public void setFighter(int points) {
		fighter = points;
	}
	
	public void setTrader(int points) {
		trader = points;
	}
	
	public void setEngineer(int points) {
		engineer = points;
	}
	
	public void setInvestor(int points) {
		investor = points;
	}
}
