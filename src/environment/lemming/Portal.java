package environment.lemming;

import java.awt.Point;

import environment.WorldObject;

/**
 * 
 * Is the goal of the lemmings
 *
 */
public class Portal extends WorldObject {

	public Portal() {
		super();
	}

	public Portal(int x, int y, boolean t) {
		super(x, y, t);
	}

	public Portal(Point p, boolean t) {
		super(p, t);
	}
	
	

}
