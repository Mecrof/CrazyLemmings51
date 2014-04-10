package fr.crazyLemmings.ia.qlearning;

public enum Action {
	WALK_FRONT(0),
	WALK_BACK(1),
	STAY(2),
	DIG_BELOW(3),
	DIG_FRONT(4),
	DIG_BACK(5);
	
	public final int id;
	public static final int totalActions = 6;
	
	Action(int i) { this.id = i; }
}
