package environment;

import java.awt.Point;

import environment.exceptions.CellNotFoundException;

/**
 * 
 * WorldObject is an object used to be in {@link Cell} of an {@link Environment}.
 * It may be traversable or not.
 *
 */
public abstract class WorldObject {
	
	private Point position;
	
	private boolean traversable;
	
	protected WorldObject()
	{
		this.position = new Point(0,0);
		this.traversable = true;
	}
	
	protected WorldObject(int x, int y, boolean t)
	{
		this.position = new Point(x,y);
		this.traversable = t;
	}

	protected WorldObject(Point p, boolean t)
	{
		this.position = new Point(p.x, p.y);
		this.traversable = t;
	}
	
	/**
	 * return the {@link Cell} where the {@link WorldObject} is contained in the {@link Environment} given
	 * @param e
	 * @return
	 * @throws CellNotFoundException generated if cell not found (if the position of the {@link WorldObject} is
	 * out of bound of the {@link Environment} given)
	 */
	public Cell getCurrentCell(Environment<?> e) throws CellNotFoundException
	{
		return e.getCellAt(position);
	}

	public Point getPosition() {
		return position;
	}

	/**
	 * Move the {@link WorldObject} in the {@link Environment} given to the new position given.
	 * @param e
	 * @param position
	 */
	public void setPosition(Environment<?> e, Point position) {
		try{
			Cell c = e.getCellAt(this.position);
			c.detachWorldObject(this);
			c = e.getCellAt(position);
			c.addWorldObject(this);
			this.position = position;
		}
		catch (CellNotFoundException ex){}
	}

	public boolean isTraversable() {
		return traversable;
	}

	public void setTraversable(boolean traversable) {
		this.traversable = traversable;
	}
	
	
	
}
