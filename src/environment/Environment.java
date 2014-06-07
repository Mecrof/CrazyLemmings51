package environment;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import listener.EnvironmentEvent;
import listener.EnvironmentListener;
import environment.exceptions.CellNotFoundException;
import environment.lemming.Type;
import gui.sprites.Sprite;

public class Environment {
	
	static public final int RANDOM = 0;
	
	private int width;
	private int height;
	private LinkedList<Cell> cells;
	private LinkedList<EnvironmentListener> listeners;
	
	public Environment(int w, int h)
	{
		this.width = w; this.height = h;
		this.build();
		this.listeners = new LinkedList<EnvironmentListener>();
	}
	
	private void build()
	{
		Cell c;
		cells = new LinkedList<Cell>();
		StageParser parser = new StageParser("../stage/stage_1");
		parser.load();
		for (int i = 0; i < this.height; ++i)
		{
			for(int j = 0; j < this.width; ++j)
			{
				c = new Cell(Sprite.WIDTH * j, Sprite.HEIGHT * i);
				parser.setCellOjbect(c);
				this.cells.add(c);
			}
		}
		parser.close();
	}
	
	public Cell getCellAt(int x, int y) throws CellNotFoundException
	{
		if (x >= this.width || x < 0 || y >= this.height || y <0)
			throw new CellNotFoundException(x, y);
		return this.cells.get(this.width*y+x);
	}
	
	public Cell getCellAt(Point p) throws CellNotFoundException
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
				Cell c = this.cells.get(this.getPosition(j, i));
			}
	}
	
	private	int getPosition(int x, int y)
	{
		return y*this.width+x;
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
			it.next().onEnvironmentChanged(new EnvironmentEvent(new EnvironmentState(this.cells)));
		}
	}
	
	public String toString()
	{
		String res = new String();
		for (int i = 0; i < this.height; ++i)
		{
			res += '\n';
			for(int j = 0; j < this.width; ++j)
			{
				res += "";//Type.getLetter(this.cells.get(this.getPosition(j, i)).getType());
			}
		}
		return res;
	}
}
