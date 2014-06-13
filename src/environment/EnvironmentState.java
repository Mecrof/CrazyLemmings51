package environment;

import java.util.LinkedList;

/**
 * Object containing the current state of the environment
 *
 * @param <C> the type of cell
 */
public class EnvironmentState<C extends Cell> {

	private LinkedList<C> world;
	private int deads;
	private int winners;
	
	public EnvironmentState(LinkedList<C> cells, int deads, int winners)
	{
		this.world = cells;
		this.deads = deads;
		this.winners = winners;
	}
	
	public LinkedList<C> getCurrentState() 
	{
		return this.world;
	}

	public int getDeadNubmer()
	{
		return this.deads;
	}

	public int getWinnerNubmer()
	{
		return this.winners;
	}

}
