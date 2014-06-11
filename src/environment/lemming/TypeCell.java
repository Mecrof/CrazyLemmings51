package environment.lemming;

import java.util.Iterator;

import environment.Cell;
import environment.WorldObject;

public class TypeCell extends Cell {
	
	private Type type;
	
	public TypeCell() 
	{
		super();
		type = Type.EMPTY;
	}
	
	public TypeCell(int x, int y)
	{
		super(x,y);
		this.type = Type.EMPTY;
	}
	
	public TypeCell(int x, int y, Type type)
	{
		super(x,y);
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public boolean containsPortal()
	{
		Iterator<WorldObject> it = this.getIterator();
		while (it.hasNext())
		{
			if (it.next() instanceof Portal)
				return true;
		}
		return false;
	}

}
