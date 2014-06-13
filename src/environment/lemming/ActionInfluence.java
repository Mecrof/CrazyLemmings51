package environment.lemming;

import qlearning.Action;
import qlearning.Agent;
import environment.AgentBody;
import environment.Influence;

/**
 * 
 * Is an {@link Influence} specialize in {@link Action}
 *
 */
public class ActionInfluence implements Influence {
	
	private final Agent<AgentBody> emitter;
	private Action action;
	
	@SuppressWarnings("unchecked")
	public ActionInfluence(Agent<?> emitter, Action action) {
		this.emitter = (Agent<AgentBody>) emitter;
		this.action = action;
	}

	@Override
	public Agent<AgentBody> getEmitter() {
		return emitter;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
	
	

}
