package controller;

import java.util.ArrayList;
import java.util.List;

import model.Bar;
import model.Mercenary;
import model.Player;
import model.Ship;

/**
 * Class providing methods for mercenary interactions
 * 
 * @author Larry He
 * 
 */
public class MercenaryEngine {
	
	private Player player;
	private Ship ship;
	
	/**
	 * Constructor for the MercenaryEngine, taking in the main game player
	 * 
	 * @param p
	 *            The player that will be hiring mercenaries
	 */
	public MercenaryEngine(Player p) {
		player = p;
		ship = p.getShip();
	}
	
	public List<String> hire(Mercenary m, Bar bar) {
	    List<String> errors = validateHire();
	    if (errors.isEmpty()) {
	        ship.addToCrew(m);
	        bar.removeMercenary(m);
	    }
	    return errors;
	}
	
	private List<String> validateHire() {
	    List<String> errors = new ArrayList<String>();
	    
	    if (ship.getCrewSpace() - ship.getCrewCurrentSize() < 1) {
	        errors.add("Not enough space in your ship");
	    }
	    return errors;
	}
	
	public void fire(Mercenary m, Bar bar) {
	    ship.removeFromCrew(m);
	    bar.addMercenary(m);
	}
}
