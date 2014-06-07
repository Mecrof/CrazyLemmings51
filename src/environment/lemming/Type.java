package environment.lemming;

public enum Type {
	EMTPY(0),
	FLOOR(2),
	GROUND(1);
	
	public final int id;
	
	Type(int i) { this.id = i; }
	
	public static Type getType(int t)
	{
		switch(t)
		{
		case 0: return Type.EMTPY;
		case 2: return Type.FLOOR;
		case 1: return Type.GROUND;
		default: return null;
		}
	}
	public static char getLetter(Type t)
	{
		if (t == Type.EMTPY)
			return 'E';
		if (t == Type.FLOOR)
			return 'F';
		if (t == Type.GROUND)
			return 'G';
		return ' ';
	}
	
	public static boolean isTraversable(Type t)
	{
		if (t == Type.EMTPY
				|| t == Type.FLOOR)
			return true;
		else
			return false;
	}
	
}
