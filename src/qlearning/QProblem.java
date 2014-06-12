package qlearning;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import environment.Perceivable;
import environment.Reward;
import environment.lemming.PerceivedType;
import environment.lemming.Type;

public class QProblem {

	private QState currentState = null;
	
	//states contains every possible state
	private int numberState = (int)Math.pow((double) Type.values().length, (double)Action.totalActions);
	
	private final QState[] states = new QState[numberState];
	
	public QProblem()
	{
		int number = 0;
		String description;
		int totalType = Type.values().length;
		//for(int index = 0; index < states.length; index++)
		//{
			//List<QState> stateList = new ArrayList<QState>();
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
									//System.out.println(description);
									this.states[number] = new QState(Type.getType(i), Type.getType(j), Type.getType(k), Type.getType(l), Type.getType(m), Type.getType(n), number, description);
									number++;
								}
							}
						}
					}
				}
			}
		//}
		//System.out.println(number);
	}
	
	public List<Action> getAvailableActionsFor(QState state)
	{
		List<Action> actions = new ArrayList<Action>();
		for(Action type : Action.values())
		{
			/*
			//If left block is clay
			if(state.getLeftup() == Type.getType(1))
			{
				actions.add(Action.DIG_BACK);
			}
			//If left block is empty or floor
			if(state.getLeftup() == Type.getType(0) || state.getLeftup() == Type.getType(2))
			{
				actions.add(Action.WALK_BACK);
			}
			
			//If right block is clay
			if(state.getRightup() == Type.getType(1))
			{
				actions.add(Action.DIG_FRONT);
			}
			//If right block is empty or floor
			if(state.getRightup() == Type.getType(0) || state.getRightup() == Type.getType(2))
			{
				actions.add(Action.WALK_FRONT);
			}
			*/
			
			
			actions.add(type);
		}
		return actions;
	}
	
	public List<QState> getAvailableStates()
	{
		return Arrays.asList(this.states);
	}
	
	public QState getCurrentState() 
	{
		return currentState;
	}
	
	public QState getRandomState()
	{
		Random rnd = new Random();
		int index = rnd.nextInt(this.states.length);
		return this.states[index];
	}
	
	public void translateCurrentState(List<Perceivable> perceptions)
	{
		String temp = new String();
		Iterator<Perceivable> it = perceptions.iterator();
		while(it.hasNext())
		{
			Perceivable perceivedObject = it.next();
			if (perceivedObject instanceof PerceivedType)
			{
				//System.out.println(perceivedObject.toString());
				temp += "" + Type.getLetter(((PerceivedType) perceivedObject).getType());
			}
		}
		//System.out.println(temp);
		for(int i = 0; i < this.states.length; i++)
		{
			//System.out.println(this.states[i].getDescription());
			if(temp.equalsIgnoreCase(this.states[i].getDescription()))
			{
				this.currentState = this.states[i];
				//System.out.println("Success");
			}
		}
	}
	
	public float getAlpha()
	{
		return .5f;
	}
	
	public float getGamma() {
		return .5f;
	}

	public float getNu() {
		return .5f;
	}

	public float getRho() {
		return .5f;
	}
	/*
	public int takeAction(QState state, Action action)
	{
		switch(state.getLeftup().id)
		{
		case 0 : //If left block is empty
			switch(action.id)
			{
			case 0 : return -5;
			case 4 : return -5;
			default :
			}
			break;
		}
		return 0;
	}
	*/
}
