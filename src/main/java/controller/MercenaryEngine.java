package controller;

import model.Bar;
import model.Mercenary;
import model.Player;
import model.Ship;

import java.util.ArrayList;
import java.util.List;

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
    public MercenaryEngine(Player player) {
        this.player = player;
        ship = player.getShip();
    }
    
    /**
     * Hires a mercenary from the given bar
     * @param merc Mercenary to be hired
     * @param bar Bar where the mercenary is
     * @return errors List of errors that occurred
     */
    public List<String> hire(Mercenary merc, Bar bar) {
        List<String> errors = validateHire();
        if (errors.isEmpty()) {
            ship.addToCrew(merc);
            bar.removeMercenary(merc);
        }
        return errors;
    }

    /**
     * Checks if an attempted hire is valid
     * @return errors List of errors that occurred
     */
    private List<String> validateHire() {
        List<String> errors = new ArrayList<String>();

        if (ship.getCrewSpace() - ship.getCrewCurrentSize() < 1) {
            errors.add("Not enough space in your ship");
        }
        return errors;
    }

    /**
     * Fires a mercenary to the Bar
     * @param merc Mercenary to be fired
     * @param bar Bar where the mercenary will go
     */
    public void fire(Mercenary merc, Bar bar) {
        ship.removeFromCrew(merc);
        bar.addMercenary(merc);
    }
    
    /**
     * Fires all mercenaries on the ship due to lack of payment
     */
    private void fireAll() {
        int mercNum = ship.getMercenaries().size();
        for (int i = 0; i < mercNum; i++) {
            fire(ship.getMercenaries().get(0), 
                    ship.getMercenaries().get(0).getHomePlanet().getBar());
        }
    }
    
    /**
     * Pays the mercenaries on the ship
     * @return payment The payment the player makes. Returns -1 if the player can't afford.
     */
    public int pay() {
        int payment = ship.getMercenaryPay();
        double credits = player.getCredits();
        if (player.getCredits() < payment) {
            fireAll();
            player.decreaseCredits(credits);
            return -1;
        } else {
            player.decreaseCredits(payment);
            return payment;
        }
    }
    
    /**
     * Gets the planet's mercenaries
     * @return list of mercenaries
     */
    public List<Mercenary> getPlanetMercenaries() {
        if (player.getPlanet().getBar() != null) {
            return player.getPlanet().getBar().getMercenaries();
        }
        return null;
    }
}
