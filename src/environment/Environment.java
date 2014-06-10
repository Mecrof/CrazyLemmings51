package environment;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;

import listener.EnvironmentEvent;
import listener.EnvironmentListener;
import environment.exceptions.CellNotFoundException;
import environment.lemming.Type;

public abstract class Environment<C extends Cell> {
	
	static public final int RANDOM = 0;
	
	protected int width;
	protected int height;
	protected LinkedList<C> cells;
	private LinkedList<EnvironmentListener> listeners;
	
	public Environment(int w, int h)
	{
		this.width = w; this.height = h;
		this.build();
		this.listeners = new LinkedList<EnvironmentListener>();
	}
	
	abstract protected void build();
	
	abstract protected C createCell(int x, int y);
	
	public C getCellAt(int x, int y) throws CellNotFoundException
	{
		if (x >= this.width || x < 0 || y >= this.height || y <0)
			throw new CellNotFoundException(x, y);
		return this.cells.get(this.width*y+x);
	}
	
	public C getCellAt(Point p) throws CellNotFoundException
	{
		return this.getCellAt(p.x, p.y);
	}
	
	public void generateWorld(int worldType)// TODO : NOT FONCTIONNAL YET!
	{
		switch(worldType)
		{
		case RANDOM:
			this.generateRandom();
		}
	}
	
	private void generateRandom() // TODO : NOT FONCTIONNAL YET!
	{
		for (int i = this.height-1; i >= 0; --i)
			for(int j = 0; j < this.width; ++j)
			{
				int tmp = (int)(Math.random() * 2);
				Type t = Type.getType(tmp);
				if (t == null) throw new Error("GenerateWorld in random mode : internal error. Generated Type is null.");
				C c = this.cells.get(this.getPosition(j, i));
			}
	}
	
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
	
	public boolean addListener(EnvironmentListener listener)
	{
		return this.listeners.add(listener);
	}
	
	public boolean removeListener(EnvironmentListener listener)
	{
		return this.listeners.remove(listener);
	}
	
	public void fireChange()
	{
		Iterator<EnvironmentListener> it = listeners.iterator();
		while(it.hasNext())
		{
			it.next().onEnvironmentChanged(new EnvironmentEvent(new EnvironmentState<C>(this.cells)));
		}
	}
}
