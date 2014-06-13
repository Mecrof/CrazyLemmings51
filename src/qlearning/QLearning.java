package qlearning;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QLearning {

	private final QProblem problem;
	
	private float discountFactor = 0.5f;
	
	private float learningRate = 0.5f;
	
	/**
	 * Set the reward for each action of a state to 0.0f
	 * @param problem Give the list of all the set of QState
	 */
	public QLearning(QProblem problem)
	{
		this.problem = problem;
		for(QState state : problem.getAvailableStates())
		{
			for(Action action : Action.values())
			{
				state.setRewards(0.0f, action.id);
			}
		}
	}
	
	/**
	 * Compute the reward for the given state, action and feedbackValue
	 * @param state get the current state of the agent
	 * @param action get the last action 
	 * @param feedbackValue get the reward given by the body
	 */
	public void setReward(QState state, Action action, int feedbackValue)
	{
		float q = state.getRewards(action.id);
		
		q = (1 - learningRate)*state.getRewards(action.id) + learningRate*((float)feedbackValue + discountFactor/*(state.getRewards(getBestAction(nextState).id)) */- state.getRewards(action.id));
		
		state.setRewards(q, action.id);
	}
	
	/**
	 * Get every actions with the same reward and randomly pick one
	 * @param state give the state to get all the actions and their reward
	 * @return an randomly action pick between each action with the same reward
	 */
	public Action getBestAction(QState state)
	{
		Random rnd = new Random();
		List<Action> bestActions = new ArrayList<Action>();
		float bestScore = Float.NEGATIVE_INFINITY;
		
		for(int i = 0; i < Action.totalActions; i++)
		{
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
		return bestActions.get(rnd.nextInt(bestActions.size()));
	}
	
	public float getDiscountFactor() {
		return discountFactor;
	}
	
	public float getLearningRate() {
		return learningRate;
	}
	
	public QProblem getProblem() {
		return problem;
	}
	
}
