package environment.lemming;

import qlearning.Action;
import environment.AgentBody;
import environment.Influence;

public class ActionInfluence implements Influence {
	
	private final AgentBody emitter;
	private Action action;
	
	public ActionInfluence(AgentBody emitter, Action action) {
		this.emitter = emitter;
		this.action = action;
	}

	@Override
	public AgentBody getEmitter() {
		return emitter;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
	
	

}
