package environment.lemming;

import qlearning.Action;
import qlearning.Agent;
import environment.AgentBody;
import environment.Influence;

public class ActionInfluence implements Influence {
	
	private final Agent<AgentBody> emitter;
	private Action action;
	
	public ActionInfluence(Agent<AgentBody> emitter, Action action) {
		this.emitter = emitter;
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
