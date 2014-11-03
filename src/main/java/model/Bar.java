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
	
	public void hireMercenary(Mercenary m) {
		mercenaries.remove(m);
	}
	
	public void fireMercenary(Mercenary m) {
		mercenaries.add(m);
	}
}
