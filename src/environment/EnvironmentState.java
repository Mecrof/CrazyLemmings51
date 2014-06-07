package environment;

import java.util.LinkedList;

public class EnvironmentState {

	private LinkedList<Cell> world;
	
	public EnvironmentState(LinkedList<Cell> world)
	{
		this.world = world;
	}
	
	public LinkedList<Cell> getCurrentState() 
	{
		return this.world;
	}

}
