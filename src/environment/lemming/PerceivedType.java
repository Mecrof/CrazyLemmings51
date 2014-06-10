package environment.lemming;

import java.awt.Point;

import environment.Perceivable;

public class PerceivedType implements Perceivable {
	
	private final Point position;
	private final Point delta; // relative position from the perceiver
	
	private final Type type;
	
	public PerceivedType(int x, int y, int deltaX, int deltaY, Type type) {
		this.type = type;
		this.position = new Point(x, y);
		this.delta = new Point(deltaX, deltaY);
	}

	@Override
	public Point getPosition() {
		return position;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public boolean isType(Type t) {
		return this.type == t;
	}
	
	@Override
	public String toString() {
		return "[("+ position.x + "," + position.y + "), ("
				+delta.x+","+delta.y+"), "
				+Type.getLetter(type)+"]";
	}

}
