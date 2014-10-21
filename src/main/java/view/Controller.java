package view;

/**
 * Generic controller interface for referencing UI controllers in a general way
 * 
 * @author Jack Croft
 *
 */
public interface Controller {
	/**
	 * Causes the controller to update its given UI page
	 */
	public void updatePage();
}