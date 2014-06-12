package qlearning;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import environment.Perceivable;
import environment.Sensor;
import environment.lemming.ActionInfluence;
import environment.lemming.Lemming;
import environment.lemming.PerceivedType;

public class LearningLemmingAgent extends LemmingAgent{

	private final QProblem qProblem;
	
	private final QLearning qLearning;
	
	private boolean isLearning = true;
	
	private final int Number_iterations = 5;
	
	private final float rndAction = 0.2f;
	
	public LearningLemmingAgent(int posX, int posY, Sensor s) {
		super(posX, posY, true, Lemming.RIGHT, s);
		this.qProblem = new QProblem();
		this.qLearning = new QLearning(this.qProblem);
	}
	
	public LearningLemmingAgent(int posX, int posY, boolean traversable,
			int direction, Sensor s) {
		super(posX, posY, traversable, direction, s);
		this.qProblem = new QProblem();
		this.qLearning = new QLearning(this.qProblem);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void live() {
		// TODO Auto-generated method stub
		System.out.println("--------------------------------------");
		List<Perceivable> perceptions = this.getBody().getPerception();
		
		Random rnd = new Random();
		
		this.qProblem.translateCurrentState(perceptions);
		
		QState previousState = this.qProblem.getCurrentState();
		
		//this.qLearning.learn(Number_iterations);
		
		if(this.isLearning)
		{
			//TODO : Lab test
		}
		
		Action action = null;
		
		if(rnd.nextFloat() < rndAction)
		{
			System.out.println("Random action");
			List<Action> actions = this.qProblem.getAvailableActionsFor(previousState);
			action = actions.get(rnd.nextInt(actions.size()));
		}
		else
		{
			System.out.println("Best action");
			action = this.qLearning.getBestAction(this.qProblem.getCurrentState());
		}
		//Action action = this.qLearning.getBestAction(this.qProblem.getCurrentState());
		
		ActionInfluence newAction = new ActionInfluence(this, action);
		System.out.println(newAction.getAction().name());
		
		this.getBody().influence(newAction);
		
		this.qProblem.translateCurrentState(perceptions);
		
		//QState currentState = this.qProblem.getCurrentState();
		
		this.qLearning.setReward(previousState, action, this.getBody().getReward().reward);
		
	}

}
