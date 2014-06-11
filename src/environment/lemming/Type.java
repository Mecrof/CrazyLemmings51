package environment.lemming;

public enum Type {
	EMTPY(0),
	FLOOR(2),
	CLAY(1),
	ROCK(3),
	PORTAL(4);
	
	public final int id;
	
	Type(int i) { this.id = i; }
	
	public static Type getType(int t)
	{
		switch(t)
		{
		case 0: return Type.EMTPY;
		case 2: return Type.FLOOR;
		case 1: return Type.CLAY;
		case 3: return Type.ROCK;
		case 4: return Type.PORTAL;
		default: return null;
		}
	}
	public static char getLetter(Type t)
	{
		if (t == Type.EMTPY)
			return 'E';
		if (t == Type.FLOOR)
			return 'F';
		if (t == Type.CLAY)
			return 'C';
		if (t == Type.ROCK)
			return 'R';
		if (t == Type.PORTAL)
			return '$';
		return ' ';
	}
	
	public static boolean isTraversable(Type t)
	{
		if (t == Type.EMTPY
				|| t == Type.FLOOR
				|| t == Type.PORTAL)
			return true;
		else
			return false;
	}
	
}
