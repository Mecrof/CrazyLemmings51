package environment;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;


public class Cell {
	
	private Point position;
	private LinkedList<WorldObject> objects;
	
	public Cell()
	{
		objects = new LinkedList<WorldObject>();
	}
	
	public Cell (Point p)
	{
		this();
		this.position = new Point(p.x,p.y);
	}
	
	public Cell(int x, int y)
	{
		this();
		this.position = new Point(x,y);
	}
	
	public Iterator<WorldObject> getIterator()
	{
		// grant access to the list through an Iterator (safer)
		return this.objects.iterator();
	}
	
	public void addWorldObject(WorldObject o)
	{
		if (!this.objects.contains(o))
			this.objects.add(o);
	}
	
	public void detachWorlObject(WorldObject o)
	{
		if (this.objects.contains(o))
			this.objects.remove(o);
	}

	public Point getPosition() {
		return position;
	}

}
