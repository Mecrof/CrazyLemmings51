package environment;

import java.util.LinkedList;

public class EnvironmentState<C extends Cell> {

	private LinkedList<C> world;
	
	public EnvironmentState(LinkedList<C> cells)
	{
		this.world = cells;
	}
	
	public LinkedList<C> getCurrentState() 
	{
		return this.world;
	}

}
