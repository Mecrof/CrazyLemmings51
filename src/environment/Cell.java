package environment;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 
 * Cell of an {@link Environment}
 * Contained a list of {@link WorldObject}
 */
public abstract class Cell {
	
	private Point position;
	private LinkedList<WorldObject> objects;
	
	public Cell()
	{
		objects = new LinkedList<WorldObject>();
	}
	
	/**
	 * 
	 * @param p position of the cell
	 */
	public Cell (Point p)
	{
		this(p.x, p.y);
	}
	
	/**
	 * 
	 * @param x position of the cell in x
	 * @param y position of the cell in y
	 */
	public Cell(int x, int y)
	{
		this();
		this.position = new Point(x,y);
	}
	
	/**
	 * gets iterator of the {@link WorldObject} in the cell
	 * @return the iterator generated
	 */
	public Iterator<WorldObject> getIterator()
	{
		// grant access to the list through an Iterator (safer)
		return this.objects.iterator();
	}
	
	/**
	 * adds an {@link WorldObject} in the cell
	 * @param o object to add
	 */
	public void addWorldObject(WorldObject o)
	{
		if (!this.objects.contains(o))
			this.objects.add(o);
	}
	
	/**
	 * detaches an {@link WorldObject} from the cell
	 * @param o
	 */
	public void detachWorldObject(WorldObject o)
	{
		if (this.objects.contains(o))
			this.objects.remove(o);
	}

	public Point getPosition() {
		return position;
	}

}
