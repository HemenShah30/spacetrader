package model;

/**
 * Exception for exceeding the maximum laser, shield, or gadget quantity in a
 * ship
 * 
 * @author Jack Croft
 *
 */
public class MaxCapacityException extends Exception {

	private static final long serialVersionUID = 3065061179052313223L;

	public MaxCapacityException(String msg) {
		super(msg);
	}
}