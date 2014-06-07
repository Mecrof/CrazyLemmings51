package environment;

import java.awt.Point;

import environment.exceptions.CellNotFoundException;

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
	
	public Cell getCurrentCell(Environment e) throws CellNotFoundException
	{
		return e.getCellAt(position);
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Environment e, Point position) {
		try{
		Cell c = e.getCellAt(this.position);
		c.detachWorlObject(this);
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
