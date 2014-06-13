package environment;

import java.awt.Point;
import java.util.LinkedList;

import listener.EnvironmentListener;
import environment.exceptions.CellNotFoundException;

/**
 * 
 * An {@link Environment} can be seen as a grid of {@link Cell}.
 *
 * @param <C> the environment contains {@link Cell} or king of {@link Cell}
 */
public abstract class Environment<C extends Cell> {
	
	protected int width;
	protected int height;
	protected LinkedList<C> cells;
	protected LinkedList<EnvironmentListener> listeners;
	
	public Environment(int w, int h)
	{
		this.width = w; this.height = h;
		this.listeners = new LinkedList<EnvironmentListener>();
	}
	
	/**
	 * this method build the {@link Cell} in the {@link Environment} and initialize
	 */
	abstract protected void build();
	
	/**
	 * get the {@link Cell} at the position given
	 * @param x position in x
	 * @param y position in y
	 * @return the {@link Cell} found
	 * @throws CellNotFoundException generated if the position is out of the {@link Environment}
	 */
	public C getCellAt(int x, int y) throws CellNotFoundException
	{
		if (x >= this.width || x < 0 || y >= this.height || y <0)
			throw new CellNotFoundException(x, y);
		return this.cells.get(this.width*y+x);
	}
	
	/**
	 * idem as getCellAt(x,y)
	 * @param p position searched
	 * @return the {@link Cell} found
	 * @throws CellNotFoundException generated if the position is out of the {@link Environment}
	 */
	public C getCellAt(Point p) throws CellNotFoundException
	{
		return this.getCellAt(p.x, p.y);
	}
	
	/**
	 * get position in the list of {@link Cell} of a {@link Cell} from
	 * its position in (x,y) in the "real world"
	 * @param x position in x
	 * @param y position in y
	 * @return position in the list of {@link Cell}
	 */
	protected int getPosition(int x, int y)
	{
		return y*this.width+x;
	}
	
	public void addWorldObject(WorldObject o) throws CellNotFoundException
	{
		this.getCellAt(o.getPosition()).addWorldObject(o);
	}
	
	public void detachWorldObject(WorldObject o) throws CellNotFoundException
	{
		this.getCellAt(o.getPosition()).detachWorldObject(o);
	}
	
	/**
	 * add listener in the {@link Environment} for fireChange()
	 * @param listener
	 * @return true if correctly added
	 */
	public boolean addListener(EnvironmentListener listener)
	{
		return this.listeners.add(listener);
	}
	
	/**
	 * remove listener from the {@link Environment} for fireChange()
	 * @param listener
	 * @return true if correctly removed
	 */
	public boolean removeListener(EnvironmentListener listener)
	{
		return this.listeners.remove(listener);
	}
	
	/**
	 * fires event to listeners
	 */
	public abstract void fireChange();
}
