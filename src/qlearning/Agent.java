package qlearning;

import environment.AgentBody;

public abstract class Agent<B extends AgentBody> {

	abstract public B createBody();
	abstract public void live();
	abstract protected void killMe();
}
