package model;

import java.util.List;

/**
 * 
 * @author Jack Croft
 *
 */
public class BoundaryTree {
	private BoundaryBox root;
	
	public BoundaryTree(int universeWidth, int universeHeight, List<Planet> planets) {
		//create a boundary box for each planet and somehow pair them and tree them up
	}

	public Planet getClickedPlanet(Location location) {
		return recursiveGetClickedPlanet(root, location);
	}

	private Planet recursiveGetClickedPlanet(BoundaryBox box,
			Location location) {
		if (box.hasPlanet()) {
			// put in logic to make sure planet is hit, not just box corner
			return box.getPlanet();
		}
		// here we need to check if the point is in the left or right
		// sub-box or otherwise return null
		// do so by checking borders of left and right boxes
		return recursiveGetClickedPlanet(box.getLeft(), location);
	}
}