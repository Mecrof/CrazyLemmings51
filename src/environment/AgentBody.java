package environment;

import java.util.List;


/**
 * 
 * Behavior needed for a body. The agent can only use this methods to access to his body.
 *
 */
public interface AgentBody {
	
	/**
	 * gets perceptions around of the body
	 * @return the list of perceived object has {@link Perceivable}
	 */
	public List<Perceivable> getPerception();
	
	/**
	 * stores the influence to apply
	 * @param inf {@link Influence} to apply
	 */
	public void influence(Influence inf);
	
	/**
	 * allows to know is the body is dead
	 * @return true is yes, it is. false it is not.
	 */
	public boolean isDead();
	
	/**
	 * gets the sensor of the body (may be {@link DefaultFrustrum}, etc)
	 * @return {@link Sensor} of the body
	 */
	public Sensor<?> getSensor();
	
	/**
	 * gets the reward obtained with the previous influence applied
	 * @return {@link Reward} obtained
	 */
	public Reward getReward();
	
	/**
	 * gets the actual direction of the body
	 * @return the direction has an integer (-1 to left, 1 to right)
	 */
	public int getDirection();

}
