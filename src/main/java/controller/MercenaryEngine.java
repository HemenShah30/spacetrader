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

    public List<String> hire(Mercenary merc, Bar bar) {
        List<String> errors = validateHire();
        if (errors.isEmpty()) {
            ship.addToCrew(merc);
            bar.removeMercenary(merc);
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

    public void fire(Mercenary merc, Bar bar) {
        ship.removeFromCrew(merc);
        bar.addMercenary(merc);
    }

    public List<Mercenary> getPlanetMercenaries() {
        if (player.getPlanet().getBar() != null) {
            return player.getPlanet().getBar().getMercenaries();
        }
        return null;
    }
}
