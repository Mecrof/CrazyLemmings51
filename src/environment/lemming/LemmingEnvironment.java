package environment.lemming;

import java.util.Iterator;
import java.util.LinkedList;

import environment.Environment;
import environment.WorldObject;
import environment.exceptions.CellNotFoundException;
import gui.sprites.Sprite;

public class LemmingEnvironment extends Environment<TypeCell> {

	public LemmingEnvironment(int w, int h) {
		super(w, h);
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
				c = new TypeCell(Sprite.WIDTH * j, Sprite.HEIGHT * i);
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
							res+="_";
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

}
