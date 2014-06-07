package environment.lemming;

import environment.Cell;

public class TypeCell extends Cell {
	
	private Type type;
	
	public TypeCell() 
	{
		super();
		type = Type.EMTPY;
	}
	
	public TypeCell(int x, int y)
	{
		super(x,y);
		this.type = Type.EMTPY;
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


}
