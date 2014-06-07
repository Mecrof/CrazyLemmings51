package environment;

import java.awt.Point;

import environment.lemming.Type;

public interface Perceivable {
	
	public Point getPosition();
	
	public Type getType();
	
	public boolean isType(Type t);

}
