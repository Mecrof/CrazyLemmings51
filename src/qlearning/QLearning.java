package qlearning;

import java.awt.Point;
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
	
	private final float discountFactor = 0.5f;
	
	private final float learningRate = 0.5f;
	
	//private final List<QState, List<Action, Float>> qValues;
	private final Map<QState, Map<Action, Float>> qValues = new TreeMap<QState, Map<Action, Float>>();
	//private final List<Action, Float>
	
	//private final Arrays<QState, Arrays<Action, Float>> qValues = Arrays<QState, Arrays<Action, Float>>();
	
	public QLearning(QProblem problem)
	{
		int i = 0;
		this.problem = problem;
		for(QState state : problem.getAvailableStates())
		{
			for(Action action : Action.values())
			{
				state.setRewards(0.0f, action.id);
			}
			/*
			Map<Action, Float> m = new TreeMap<Action, Float>();
			this.qValues.put(state, m);
			for(Action action : Action.values())//problem.getAvailableActionsFor(state))
			{
				m.put(action, 0f);
			}
			*/
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
	
	
	public void setReward(QState state, Action action, int feedbackValue, QState nextState)
	{
		//Map<Action,Float> m = this.qValues.get(state);
		//this.qValues.put(state, m);
		float q = state.getRewards(action.id);
		
		q = (1 - learningRate)*state.getRewards(action.id) + learningRate*((float)feedbackValue + discountFactor/*(state.getRewards(getBestAction(nextState).id)) */- state.getRewards(action.id));
		//float q = getQ(state,action);
		
		//q = getQ(state, action) + learningRate*((float)feedbackValue + discountFactor - getQ(state, action));
		
		//m.put(action, (float) q);
		//putQ(state, action, q);
		//this.qValues.put(state, m);
		state.setRewards(q, action.id);
		//System.out.println(feedbackValue + " action="+action);
	}
	
	
	public Action getBestAction(QState state)
	{
		Random rnd = new Random();
		Map<Action, Float> m = this.qValues.get(state);
		List<Action> bestActions = new ArrayList<Action>();
		float bestScore = Float.NEGATIVE_INFINITY;
		
		for(int i = 0; i < Action.totalActions; i++)
		{
			//System.out.println("- " + Action.getAction(i).name() + " : " + state.getRewards(i) + ";");
			if(state.getRewards(i) > bestScore)
			{
				bestActions.clear();
				bestActions.add(Action.getAction(i));
				bestScore = state.getRewards(i);
			}
			if (state.getRewards(i) == bestScore)
			{
				bestActions.add(Action.getAction(i));
			}
		}
		
		
		/*
		for(Entry<Action, Float> entry : m.entrySet())
		{
			System.out.println(entry.getKey().name() + " : " + getQ(state, entry.getKey()));
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
		*/
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
	
	public float getDiscountFactor() {
		return discountFactor;
	}
	
	public float getLearningRate() {
		return learningRate;
	}
	
	public String getLearning(QState state)
	{
		float f;
		String temp = new String();
		for(Action action : problem.getAvailableActionsFor(state))
		{
			f = getQ(state, action);
			temp += "" + action.name() + ":" + f;
		}
		//float f = getQ(state, action);
		return temp;//new String();
	}
	
	public QProblem getProblem() {
		return problem;
	}
	
}
