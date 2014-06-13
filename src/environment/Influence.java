package environment;

import qlearning.Agent;

/**
 * 
 * behavior needed for an influence
 *
 */
public interface Influence {
	
	/**
	 * return the emitter of the influence
	 * @return
	 */
	public Agent<AgentBody> getEmitter();

}
