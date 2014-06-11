package qlearning;

import environment.AgentBody;
import environment.Sensor;
import environment.lemming.Lemming;

abstract public class LemmingAgent extends Agent<Lemming> {

	private final Lemming body;
	private boolean killed;
	
	public LemmingAgent(int posX, int posY, boolean traversable, int direction, Sensor s) {
		this.body = new Lemming(posX, posY, traversable, direction, s);
		this.killed = false;
	}
	
	@Override
	public Lemming createBody() {
		return body;
	}
	
	@Override
	protected void killMe() {
		this.killed = true;
	}
	
	public final AgentBody getBody()
	{
		return body;
	}

	public boolean isKilled() {
		return killed;
	}

}
