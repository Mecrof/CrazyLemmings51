package fr.crazyLemmings.ia.qlearning;

public class QState {
	
	private Type left;
	private Type center;
	private Type right;
	private Type bottom;
	
	public QState (Type l, Type c, Type r, Type b)
	{
		this.left = l;
		this.center = c;
		this.right = r;
		this.bottom = b;
	}
	
	public QState (Type c)
	{
		this(null, c, null, null);
	}

	public Type getLeft() {
		return left;
	}

	public Type getCenter() {
		return center;
	}

	public Type getRight() {
		return right;
	}

	public Type getBottom() {
		return bottom;
	}
	
	

}
