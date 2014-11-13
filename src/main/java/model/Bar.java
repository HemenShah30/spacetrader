package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the bar of a planet where mercenaries can be hired
 * 
 * @author Larry He
 * 
 */
public class Bar {
    private List<Mercenary> mercenaries;

    /**
     * Constructor for bar of a planet
     * 
     */
    public Bar() {
        mercenaries = new ArrayList<Mercenary>();
    }

    public Bar(List<Mercenary> mercenaries) {
        this.mercenaries = mercenaries;
    }

    public List<Mercenary> getMercenaries() {
        return mercenaries;
    }

    public List<Double> getWages() {
        List<Double> wages = new ArrayList<Double>();
        for (Mercenary m : mercenaries) {
            wages.add(m.getWage());
        }
        return wages;
    }

    public void removeMercenary(Mercenary merc) {
        mercenaries.remove(merc);
    }

    public void addMercenary(Mercenary merc) {
        mercenaries.add(merc);
    }

    @Override
    public String toString() {
        String string = "\n";
        for (Mercenary m : mercenaries) {
            string += m.toString();
        }
        return string;
    }
}