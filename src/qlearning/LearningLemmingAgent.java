package qlearning;

import java.awt.Point;
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
	
	/*
	 * rndAction is used to get a chance to have a random Action each time we call live() function
	 */
	private  float rndAction = 0.1f; 
	
	
	private QState oldState = null;
	
	private Action previousAction = null;
	
	@SuppressWarnings("rawtypes")
	public LearningLemmingAgent(int posX, int posY, Sensor s, boolean isLearning) {
		super(posX, posY, true, Lemming.RIGHT, s);
		this.qProblem = new QProblem();
		this.qLearning = new QLearning(this.qProblem);
		this.isLearning = isLearning;
	}
	
	@SuppressWarnings("rawtypes")
	public LearningLemmingAgent(int posX, int posY, boolean traversable,
			int direction, Sensor s) {
		super(posX, posY, traversable, direction, s);
		this.qProblem = new QProblem();
		this.qLearning = new QLearning(this.qProblem);
		this.isLearning = true;
	}
	
	/**
	 * Live function of the learning lemming agent. It's used to learn from lab session and use this knowledge
	 * on real stage.
	 */
	@Override
	public void live() {
		if (this.getBody().isDead())
		{
			System.out.print("!!!im dead");
			killMe();
		}
		else
		{
			List<Perceivable> perceptions = this.getBody().getPerception();
			
			Random rnd = new Random();
			
			Iterator<Perceivable> it = perceptions.iterator();
			while(it.hasNext())
			{
				Perceivable perceivedObject = it.next();
				if (perceivedObject instanceof PerceivedType)
				{
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
		
			/*
			 * If the Lemming is learning, this is used to set a reward to a state/action couple
			 */
			if(this.isLearning)
			{
				if(oldState!=null && previousAction !=null)
				{
					this.qLearning.setReward(oldState, previousAction, this.getBody().getReward().reward);
				}
			}
			
			this.qProblem.translateCurrentState(perceptions);
			
			QState previousState = this.qProblem.getCurrentState();
			oldState = this.qProblem.getCurrentState();		
			
			Action action = null;
			if (isLearning)
			{
				rndAction =0.9f;
			}
				if(rnd.nextFloat() < rndAction)
				{
					List<Action> actions = this.qProblem.getAvailableActionsFor(previousState);
					action = actions.get(rnd.nextInt(actions.size()));
				}
				else
				{
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
	
			ActionInfluence newAction = new ActionInfluence(this, action);
			
			this.getBody().influence(newAction);
			
			this.qProblem.translateCurrentState(perceptions);
			
			this.previousAction = action;
		}
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
