package fr.crazyLemmings.ia.qlearning;

public enum Type {
	EMTPY(0),
	FLOOR(1),
	GROUND(2);
	
	public final int id;
	
	Type(int i) { this.id = i; }
	
}
