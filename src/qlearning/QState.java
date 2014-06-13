package qlearning;

import environment.lemming.Type;
import qlearning.Action;

/**
 * This class create a QState and a array of float to store rewards from each action
 */
public class QState{
	
	private Type leftup;
	private Type leftdown;
	private Type rightup;
	private Type rightdown;
	private Type bottom1;
	private Type bottom2;
	
	private final int number;
	private final String description;
	/*
	 * Each QState has a float array of actions rewards
	 */
	private float rewards[] = new float[Action.totalActions];
	
	/**
	 * 
	 * @param lu Type of the left up cell
	 * @param ld Type of the left down cell
	 * @param ru Type of the right up cell
	 * @param rd Type of the right down cell
	 * @param b1 Type of the bottom cell
	 * @param b2 Type of the bottom cell at the bottom cell
	 * @param number
	 * @param description
	 */
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
		for(Action action : Action.values())
		{
			rewards[action.id] = 0.0f;
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
	
	public float[] getRewards() {
		return rewards;
	}
	
	public float getRewards(int idAction)
	{
		return rewards[idAction];
	}
	
	public void setRewards(float[] rewards) {
		this.rewards = rewards;
	}
	
	public void setRewards(float rewards, int idAction)
	{
		this.rewards[idAction] = rewards;
	}
}
