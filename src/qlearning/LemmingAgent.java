package qlearning;

import environment.AgentBody;
import environment.Sensor;
import environment.lemming.Lemming;

abstract public class LemmingAgent extends Agent<Lemming> {

	private final Lemming body;
	
	public LemmingAgent(int posX, int posY, boolean traversable, int direction, Sensor s) {
		this.body = new Lemming(posX, posY, traversable, direction, s);
	}
	
	@Override
	public Lemming createBody() {
		return body;
	}
	
	public final AgentBody getBody()
	{
		return body;
	}

}
