package model;

/**
 * Class representing a mercenary
 * 
 * @author Larry He
 * 
 */
public class Mercenary {
	//Wages can vary from being 80% of the expected cost to 120%
	private static final double wageVariation = 0.2;
	private String name;
	private int pilotSkill, fighterSkill, traderSkill, engineerSkill,
	investorSkill;
	private double wage;
	private Planet homePlanet;
	
	/**
     * Constructor for the mercenary
     * 
     * @param name
     *            The name of the mercenary
    **/
	public Mercenary(String name, Planet planet) {
	    this.name = name;
	    pilotSkill = (int) (Math.random() * 10);
	    fighterSkill = (int) (Math.random() * 10);
	    traderSkill = (int) (Math.random() * 10);
	    engineerSkill = (int) (Math.random() * 10);
	    investorSkill = (int) (Math.random() * 10);
	    wage = generateWage();
	    homePlanet = planet;
	}
	
	/**
	 * Constructor for the mercenary taking all the appropriate instance variables
	 * 
	 * @param name
	 *            The name of the mercenary
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
	 * @param wage
	 *            The amount of credits the player must pay every turn
	 */
	public Mercenary(String name, int pilot, int fighter, int trader,
			int engineer, int investor) {
		this.name = name;
		this.pilotSkill = pilot;
		this.fighterSkill = fighter;
		this.traderSkill = trader;
		this.engineerSkill = engineer;
		this.investorSkill = investor;
		wage = generateWage();
	}
	
	/**
	 * Returns the name of the mercenary
	 * 
	 * @return The name of the mercenary
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the mercenary's piloting skill
	 * 
	 * @return The mercenary's piloting skill
	 */
	public int getPilotSkill() {
		return pilotSkill;
	}

	/**
	 * Returns the mercenary's fighter skill
	 * 
	 * @return The mercenary's fighter skill
	 */
	public int getFighterSkill() {
		return fighterSkill;
	}

	/**
	 * Returns the mercenary's trader skill
	 * 
	 * @return The mercenary's trader skill
	 */
	public int getTraderSkill() {
		return traderSkill;
	}

	/**
	 * Returns the mercenary's Engineer skill
	 * 
	 * @return The mercenary's engineer skill
	 */
	public int getEngineerSkill() {
		return engineerSkill;
	}

	/**
	 * Returns the mercenary's investor skill
	 * 
	 * @return The mercenary's investor skill
	 */
	public int getInvestorSkill() {
		return investorSkill;
	}

	/**
	 * Returns the mercenary's wage
	 * 
	 * @return The mercenary's wage
	 */
	public double getWage() {
		return wage;
	}
	
	/**
     * Returns the mercenary's home planet
     * 
     * @return The mercenary's home planet
     */
    public Planet getHomePlanet() {
        return homePlanet;
    }
	
	/**
	 * Calculates the mercenary's wage based on their skill level
	 * 
	 * @return The mercenary's wage
	 */
	private double generateWage() {
		double wage = pilotSkill + fighterSkill + traderSkill + engineerSkill + investorSkill;
		wage = wage * 2 * (Math.random() - .5) * wageVariation;
		return wage;
	}
	
	/**
     * Returns string giving all characteristics of this mercenary
     * 
     * @return String description
     */
	@Override
	public String toString() {
	    String s = "Name: " + name + ", pilot:" + pilotSkill + ", fighter:" + fighterSkill +
	            ", trader:" + traderSkill + ", engineer: " + engineerSkill + ", investor: " +
	            investorSkill + "\n";
	    return s;
	}
}
