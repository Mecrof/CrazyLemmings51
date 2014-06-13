package environment.lemming;

import java.awt.Point;

import environment.Perceivable;

/**
 * 
 * Is a {@link Perceivable} object. It contains the position of the {@link Type} found and the
 * relative position from the perceiver.
 *
 */
public class PerceivedType implements Perceivable {
	
	private final Point position;
	private final Point delta; // relative position from the perceiver
	private final Point portalDirection;
	
	private final Type type;
	
	public PerceivedType(int x, int y, int deltaX, int deltaY, int portalDirectionX, int portalDirectionY, Type type)
	{
		this.type = type;
		this.position = new Point(x, y);
		this.delta = new Point(deltaX, deltaY);
		this.portalDirection = new Point(portalDirectionX, portalDirectionY);
	}

	@Override
	public Point getPosition() {
		return position;
	}

	public Type getType() {
		return type;
	}

	public boolean isType(Type t) {
		return this.type == t;
	}
	
	public Point getPortalDirection() {
		return portalDirection;
	}

	@Override
	public Point getDelta() {
		return delta;
	}

	@Override
	public String toString() {
		return "[("+ position.x + "," + position.y + "), ("
				+delta.x+","+delta.y+"), ("
				+portalDirection.x+","+portalDirection.y+"), "
				+Type.getLetter(type)+"]";
	}

}
