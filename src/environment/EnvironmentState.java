package environment;

import java.util.LinkedList;

public class EnvironmentState<C extends Cell> {

	private LinkedList<C> world;
	private int deads;
	
	public EnvironmentState(LinkedList<C> cells, int deads)
	{
		this.world = cells;
		this.deads = deads;
	}
	
	public LinkedList<C> getCurrentState() 
	{
		return this.world;
	}
	
	public int getDeadNubmer()
	{
		return this.deads;
	}

}
