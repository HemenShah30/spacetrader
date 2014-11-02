package model;

/**
 * Exception for exceeding the maximum laser, shield, or gadget quantity in a
 * ship
 * 
 * @author Jack Croft
 *
 */
public class MaxCapacityException extends RuntimeException {

	private static final long serialVersionUID = 3065061179052313223L;

	/**
	 * Constructor for a MaxCapacityException that takes in a string description
	 * of the exception
	 * 
	 * @param msg
	 *            A description of the exception
	 */
	public MaxCapacityException(String msg) {
		super(msg);
	}
}