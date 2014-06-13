package environment;

import java.awt.Point;

/**
 * 
 * behavior needed for a {@link Perceivable} object
 *
 */
public interface Perceivable {
	
	/**
	 * return the absolute position of the {@link Perceivable} object in the {@link Environment}
	 * @return
	 */
	public Point getPosition();
	
	/**
	 * return the relative position from the current position of the body who asked a perception
	 * @return
	 */
	public Point getDelta();

}
