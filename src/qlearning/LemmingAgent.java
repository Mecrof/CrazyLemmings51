package qlearning;

import environment.Sensor;
import environment.lemming.Lemming;

abstract public class LemmingAgent extends Agent<Lemming> {

	protected final Lemming body;
	
	public LemmingAgent(int posX, int posY, boolean traversable, int direction, Sensor s) {
		this.body = new Lemming(posX, posY, traversable, direction, s);
	}
	
	@Override
	public Lemming createBody() {
		return body;
	}

}
