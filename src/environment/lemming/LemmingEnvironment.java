package environment.lemming;

import java.util.Iterator;
import java.util.LinkedList;

import environment.Environment;
import environment.WorldObject;
import environment.exceptions.CellNotFoundException;
import gui.sprites.Sprite;

public class LemmingEnvironment extends Environment<TypeCell> {

	private Portal portal;
	private int deads;
	
	public LemmingEnvironment(int w, int h) {
		super(w, h);
		this.portal = null;
		this.deads = 0;
	}

	@Override
	protected void build() 
	{
		TypeCell c;
		cells = new LinkedList<TypeCell>();
		StageParser parser = new StageParser("../../stage/stage_1");
		parser.load();
		for (int i = 0; i < this.height; ++i)
		{
			for(int j = 0; j < this.width; ++j)
			{
				c = new TypeCell(j, i);
				parser.setCellOjbect(c);
				this.cells.add(c);
			}
		}
		parser.close();
	}

	@Override
	protected TypeCell createCell(int x, int y) {
		return new TypeCell(x, y);
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
				if (c.getType() == Type.EMTPY)
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
	
	public boolean setPortal(Portal p)
	{
		try {
			this.addWorldObject(p);
			this.portal = p;
			return true;
		} catch (CellNotFoundException e) {
			e.printStackTrace();
		}
		return false;
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

}
