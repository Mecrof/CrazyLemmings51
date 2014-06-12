package qlearning;

import java.util.ArrayList;
import java.util.List;

import environment.lemming.Type;

@SuppressWarnings("rawtypes")
public class QState implements Comparable {
	
	private Type leftup;
	private Type leftdown;
	//private Type center;
	private Type rightup;
	private Type rightdown;
	private Type bottom1;
	private Type bottom2;
	
	private final int number;
	private final String description;
	
	public QState (Type lu, Type ld, Type ru, Type rd, Type b1, Type b2, int number, String description)
	{
		this.leftup = lu;
		this.leftdown = ld;
		this.rightup = ru;
		this.rightdown = rd;
		this.bottom1 = b1;
		this.bottom2 = b2;
		this.number = number;
		if(description == null || description.isEmpty())
		{
			this.description = "State_" + this.number;
		}
		else
		{
			this.description = description;
		}
	}
	
	public QState (Type b1)
	{
		this(null, null, null, null, b1, null, 0, null);
	}

	public Type getLeftup() {
		return leftup;
	}
	
	public Type getLeftdown() {
		return leftdown;
	}
	
	public Type getRightup() {
		return rightup;
	}
	
	public Type getRightdown() {
		return rightdown;
	}
	
	public Type getBottom1() {
		return bottom1;
	}
	
	public Type getBottom2() {
		return bottom2;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getNumber() {
		return number;
	}
	
	public List<QState> createallQState(){
		int number = 0;
		String description;
		List<QState> stateList = new ArrayList<QState>();
		for(int i = 0; i <= Type.values().length; i++)
		{
			for(int j = 0; j <= Type.values().length; j++)
			{
				for(int k = 0; k <= Type.values().length; k++)
				{	
					for(int l = 0; l <= Type.values().length; l++)
					{
						for(int m = 0; m <= Type.values().length; m++)
						{
							for(int n = 0; n <= Type.values().length; n++)
							{
								description = "" + Type.getLetter(Type.getType(i)) + Type.getLetter(Type.getType(2)) + Type.getLetter(Type.getType(j)) + Type.getLetter(Type.getType(k)) + Type.getLetter(Type.getType(l)) + Type.getLetter(Type.getType(m)) + Type.getLetter(Type.getType(n)); 
								QState tempstate = new QState(Type.getType(i), Type.getType(j), Type.getType(k), Type.getType(l), Type.getType(m), Type.getType(n), number, description);
								stateList.add(tempstate);
								number++;
							}
						}
					}
				}
			}
		}
		return stateList;
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		if( this == arg0) return 0;
		return 0;
	}
}
