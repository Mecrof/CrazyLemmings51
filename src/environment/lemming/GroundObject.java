package environment.lemming;

import java.awt.Point;

import environment.WorldObject;

public class GroundObject extends WorldObject {
	
	private Type type;
	
	public GroundObject(Point p, Type type) {
		super(p.x, p.y,false);
		this.type = type;
	}
	
	public GroundObject(int x, int y, Type type) {
		super(x,y,false);
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
