package environment.lemming;

import environment.WorldObject;

public class GroundObject extends WorldObject {
	
	private Type type;
	
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
