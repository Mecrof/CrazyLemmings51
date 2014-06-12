package qlearning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

import environment.Reward;

public class QLearning {

	private final QProblem problem;
	
	//private final List<QState, List<Action, Float>> qValues;
	private final Map<QState, Map<Action, Float>> qValues = new TreeMap<QState, Map<Action, Float>>();
	
	//private final Arrays<QState, Arrays<Action, Float>> qValues = Arrays<QState, Arrays<Action, Float>>();
	
	public QLearning(QProblem problem)
	{
		int i = 0;
		this.problem = problem;
		for(QState state : problem.getAvailableStates())
		{
			Map<Action, Float> m = new TreeMap<Action, Float>();
			this.qValues.put(state, m);
			for(Action action : problem.getAvailableActionsFor(state))
			{
				m.put(action, 0f);
			}
		}
	}
	
	public void learn(int numberIteration)
	{
		List<Action> actions;
		QState state;
		Action action;
		Reward result = null;
		
		Random rnd = new Random();
		
		state = this.problem.getCurrentState();
	
		for(int i = 0; i <= numberIteration; i++)
		{
			if (rnd.nextFloat() < this.problem.getRho())
			{
				state = this.problem.getRandomState();
			}
			if (rnd.nextFloat() < this.problem.getNu())
			{
				actions = this.problem.getAvailableActionsFor(state);
				action = actions.get(rnd.nextInt(actions.size()));
			}
			else
			{
				action = getBestAction(state);
		
			}
			//this.setReward(state, action, i);
			
			//state = this.problem.getRandomState();
		}
		
		//result.getReward();
		
		//state;
	}
	
	
	public void setReward(QState state, Action action, int feedbackValue)
	{
		Map<Action,Float> m = this.qValues.get(state);
		this.qValues.put(state, m);
		float q = getQ(state,action);
		
		
		m.put(action, (float) feedbackValue);
		//System.out.println(feedbackValue);
	}
	
	
	public Action getBestAction(QState state)
	{
		Random rnd = new Random();
		Map<Action, Float> m = this.qValues.get(state);
		List<Action> bestActions = new ArrayList<Action>();
		float bestScore = Float.NEGATIVE_INFINITY;
		for(Entry<Action, Float> entry : m.entrySet())
		{
			//System.out.println(entry.getKey().name() + " : " + getQ(state, entry.getKey()));
			//System.out.println(entry.getValue());
			if (entry.getValue() > bestScore)
			{
				bestActions.clear();
				bestActions.add(entry.getKey());
				bestScore = entry.getValue();
			}
			else if (entry.getValue() == bestScore)
			{
				bestActions.add(entry.getKey());
			}
		}
		return bestActions.get(rnd.nextInt(bestActions.size()));
	}
	
	private float getQ(QState state, Action action) {
		Map<Action,Float> m = this.qValues.get(state);
		Float q = m.get(action);
		if (q==null) 
		{
			return 0f;
		}
		return q.floatValue();
	}

	private void putQ(QState state, Action action, float q) {
		Map<Action,Float> m = this.qValues.get(state);
		m.put(action, q);
	}
	
}
