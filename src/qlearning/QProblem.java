package qlearning;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import environment.Perceivable;
import environment.lemming.PerceivedType;
import environment.lemming.Type;

/**
 * This class get all the set of QState possible and used it to read perceptions into a QState
 */
public class QProblem {

	private QState currentState = null;
	
	private int numberState = (int)Math.pow((double) Type.values().length, (double)Action.totalActions);
	
	private final QState[] states = new QState[numberState];
	
	/**
	 * Create every set of QState
	 */
	public QProblem()
	{
		int number = 0;
		String description;
		int totalType = Type.values().length;
		for(int i = 0; i < totalType; i++)
		{
			for(int j = 0; j < totalType; j++)
			{
				for(int k = 0; k < totalType; k++)
				{	
					for(int l = 0; l < totalType; l++)
					{
						for(int m = 0; m < totalType; m++)
						{
							for(int n = 0; n < totalType; n++)
							{
								description = "" + Type.getLetter(Type.getType(i)) + Type.getLetter(Type.getType(j)) + Type.getLetter(Type.getType(k)) + Type.getLetter(Type.getType(l)) + Type.getLetter(Type.getType(m)) + Type.getLetter(Type.getType(n)); 
								this.states[number] = new QState(Type.getType(i), Type.getType(j), Type.getType(k), Type.getType(l), Type.getType(m), Type.getType(n), number, description);
								number++;
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Get all the actions available for the given state
	 * @param state
	 * @return all the actions for the state
	 */
	public List<Action> getAvailableActionsFor(QState state)
	{
		List<Action> actions = new ArrayList<Action>();
		for(Action type : Action.values())
		{			
			actions.add(type);
		}
		return actions;
	}
	
	/**
	 * Give every state available
	 * @return a list of every QState
	 */
	public List<QState> getAvailableStates()
	{
		return Arrays.asList(this.states);
	}
	
	public QState getCurrentState() 
	{
		return currentState;
	}
	
	/**
	 * Find the state equivalent to the given perceptions
	 * @param perceptions get by the agent, used to find the equivalent state
	 */
	public void translateCurrentState(List<Perceivable> perceptions)
	{
		String temp = new String();
		Iterator<Perceivable> it = perceptions.iterator();
		while(it.hasNext())
		{
			Perceivable perceivedObject = it.next();
			if (perceivedObject instanceof PerceivedType)
			{
				temp += "" + Type.getLetter(((PerceivedType) perceivedObject).getType());
			}
		}
		for(int i = 0; i < this.states.length; i++)
		{
			if(temp.equalsIgnoreCase(this.states[i].getDescription()))
			{
				this.currentState = this.states[i];
			}
		}
	}
}
