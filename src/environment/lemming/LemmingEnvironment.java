package environment.lemming;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;

import listener.EnvironmentEvent;
import listener.EnvironmentListener;
import environment.Cell;
import environment.Environment;
import environment.EnvironmentState;
import environment.WorldObject;
import environment.exceptions.CellNotFoundException;

/**
 * 
 * Specialization of an {@link Environment} to contain {@link TypeCell} as {@link Cell}
 *
 */
public class LemmingEnvironment extends Environment<TypeCell> {

	private Portal portal;
	private int deads;
	private int winners;
	private Point startPosition;
	private String stageFile;
	
	public LemmingEnvironment(int w, int h, String stage) {
		super(w, h);
		this.deads = 0;
		this.winners = 0;
		this.stageFile = stage;
		this.build();
	}

	@Override
	protected void build() 
	{
		TypeCell c;
		cells = new LinkedList<TypeCell>();
		StageParser parser = new StageParser(this.stageFile);
		parser.load();
		for (int i = 0; i < this.height; ++i)
		{
			for(int j = 0; j < this.width; ++j)
			{
				c = new TypeCell(j, i);
				parser.setCellOjbect(c,this);
				this.cells.add(c);
			}
		}
		parser.close();
	}
	
	public String toString()
	{
		String res = new String();
		for (int i = 0; i < this.height; ++i)
		{
			res += '\n';
			for(int j = 0; j < this.width; ++j)
			{
				TypeCell c = this.cells.get(this.getPosition(j, i));
				if (c.getType() == Type.EMPTY)
				{
					try {
						if (!Type.isTraversable(this.getCellAt(j, i+1).getType()))
						{
							Iterator<WorldObject> it = c.getIterator();
							String content = "_";
							while(it.hasNext())
							{
								WorldObject ob = it.next();
								
								if (ob instanceof Portal)
								{
									content = "$";
								}
								else if(ob instanceof Lemming)
								{
									if (((Lemming) ob).isDead())
										content = "t";
									else if (((Lemming) ob).getDirection() == Lemming.RIGHT)
										content = "E";
									else
										content = "3";
								}
							}
							res+=content;
						}							
						else
							res+=" ";
					} catch (CellNotFoundException e) {
						res+="_";
					}
				}
				else
					res += Type.getLetter(c.getType());
			}
		}
		return res;
	}
	
	public void setPortal(Portal p)
	{
		this.portal = p;
	}

	public final Portal getPortal() {
		return portal;
	}

	public int getDeads() {
		return deads;
	}
	
	public void addNewDeadOne()
	{
		this.deads++;
	}
	
	public void addNewWinner()
	{
		this.winners++;
	}
	
	public int getWinners()
	{
		return winners;
	}

	@Override
	public void fireChange() 
	{
		Iterator<EnvironmentListener> it = this.listeners.iterator();
		while(it.hasNext())
		{
			it.next().onEnvironmentChanged(new EnvironmentEvent(new EnvironmentState<TypeCell>(this.cells, this.deads, this.winners)));
		}
	}

	public Point getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(Point startPosition) {
		this.startPosition = startPosition;
	}

}
