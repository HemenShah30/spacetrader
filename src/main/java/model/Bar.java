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
	
	public void removeMercenary(Mercenary m) {
		mercenaries.remove(m);
	}
	
	public void addMercenary(Mercenary m) {
		mercenaries.add(m);
	}
}
