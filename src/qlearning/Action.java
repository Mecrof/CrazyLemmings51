package qlearning;

public enum Action {
	WALK_RIGHT(0),
	WALK_LEFT(1),
	STAY(2),
	DIG_BELOW(3),
	DIG_RIGHT(4),
	DIG_LEFT(5);
	
	public final int id;
	public static final int totalActions = 6;
	
	Action(int i) { this.id = i; }
}
