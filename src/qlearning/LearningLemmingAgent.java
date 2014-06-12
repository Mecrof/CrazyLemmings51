package qlearning;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import environment.Perceivable;
import environment.Sensor;
import environment.lemming.ActionInfluence;
import environment.lemming.Lemming;
import environment.lemming.PerceivedType;
import environment.lemming.Type;

public class LearningLemmingAgent extends LemmingAgent{

	private QProblem qProblem;
	
	private final QLearning qLearning;
	
	private boolean isLearning = true;
	
	private final int Number_iterations = 5;
	
	private  float rndAction = 0.1f;
	
	private QState oldState = null;
	
	private Action previousAction = null;
	
	public LearningLemmingAgent(int posX, int posY, Sensor s, boolean isLearning) {
		super(posX, posY, true, Lemming.RIGHT, s);
		this.qProblem = new QProblem();
		this.qLearning = new QLearning(this.qProblem);
		this.isLearning = isLearning;
	}
	
	public LearningLemmingAgent(int posX, int posY, boolean traversable,
			int direction, Sensor s) {
		super(posX, posY, traversable, direction, s);
		this.qProblem = new QProblem();
		this.qLearning = new QLearning(this.qProblem);
		this.isLearning = true;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void live() {
		if (this.getBody().isDead())
		{
			System.out.print("!!!im dead");
			killMe();
		}
		else
		{
		
		// TODO Auto-generated method stub
		//System.out.println("--------------------------------------");
		List<Perceivable> perceptions = this.getBody().getPerception();
		
		Random rnd = new Random();
		
		QState currentState = this.qProblem.getCurrentState();
		Iterator<Perceivable> it = perceptions.iterator();
		while(it.hasNext())
		{
			Perceivable perceivedObject = it.next();
			if (perceivedObject instanceof PerceivedType)
			{
				//System.out.println(perceivedObject.toString());
					Point portalDirection = ((PerceivedType) perceivedObject).getPortalDirection();
					if (portalDirection.x == 0 && portalDirection.y == 0)
					{
						killMe();
						System.out.println(": I have just reached the portal !! :D");
						this.win();
						return;
					}
			}
		}
		
		if(this.isLearning)
		{
			if(oldState!=null && previousAction !=null)
			{
				//System.out.println("test");
				this.qLearning.setReward(oldState, previousAction, this.getBody().getReward().reward, currentState);
			}
		}
		
		this.qProblem.translateCurrentState(perceptions);
		
		QState previousState = this.qProblem.getCurrentState();
		//System.out.println(previousState.getDescription());
		oldState = this.qProblem.getCurrentState();
		//this.qLearning.learn(Number_iterations);
		
		
		
		Action action = null;
		//if (isLearning)
		//{
		if (isLearning)
		{
			rndAction =0.9f;
		}
			if(rnd.nextFloat() < rndAction)
			{
				//System.out.println("RANDOM !!!!");
				List<Action> actions = this.qProblem.getAvailableActionsFor(previousState);
				action = actions.get(rnd.nextInt(actions.size()));
			}
			else
			{
				//System.out.println("Best action");
				if (isLearning)
				{
					action = this.qLearning.getBestAction(this.qProblem.getCurrentState());
				}
				else
				{
					QState state = this.qProblem.getCurrentState();
					if (state.getLeftup()==Type.FLOOR && state.getRightup()==Type.FLOOR)
					{
						if (this.getBody().getDirection() == Lemming.LEFT)
						{
							action = Action.WALK_LEFT;
						}
						else
						{
							action = Action.WALK_RIGHT;
						}
					}
					else
					{
						action = this.qLearning.getBestAction(state);
					}
				}
			}
		/*}
		else
		{
			action = this.qLearning.getBestAction(this.qProblem.getCurrentState());
		}*/
		//Action action = this.qLearning.getBestAction(this.qProblem.getCurrentState());
		
		ActionInfluence newAction = new ActionInfluence(this, action);
		//System.out.println(newAction.getAction().name());
		
		this.getBody().influence(newAction);
		
		this.qProblem.translateCurrentState(perceptions);
		
		this.previousAction = action;
		//String temp = new String();
		
		//QState tempstate = this.qProblem.getRandomState();
		//String temp = tempstate.getDescription() + "  " + this.qLearning.getLearning(tempstate);
		//System.out.println(temp);
		
		
		//QState currentState = this.qProblem.getCurrentState();
		
		
		/*
		if(this.isLearning)
		{
			//TODO : Lab test
			this.qLearning.setReward(previousState, action, this.getBody().getReward().reward, currentState);
		}
		*/
		//this.qLearning.setReward(previousState, action, this.getBody().getReward().reward);
		}
	}
	
	public void getMemory(QState emptyMemory, QState loadedMemory)
	{
		for(int i = 0; i < Action.totalActions; i++)
		{
			emptyMemory.setRewards(loadedMemory.getRewards(i), i);
		}
	}
	
	public void getMemory(QProblem emptyMemory, QProblem loadedMemory)
	{
		emptyMemory = loadedMemory;
	}
	
	public QProblem getqProblem() {
		return qProblem;
	}
	
	public void setqProblem(QProblem qProblem) {
		this.qProblem = qProblem;
	}
	
	public boolean isLearning() {
		return isLearning;
	}
	
	public void setLearning(boolean isLearning) {
		this.isLearning = isLearning;
	}

}
