package qlearning;

import environment.Sensor;
import environment.lemming.ActionInfluence;
import environment.lemming.Lemming;

public class RandomLemmingAgent extends Agent<Lemming> {
	
	private Lemming body;
	
	public RandomLemmingAgent(int posX, int posY, Sensor s) {
		this.body = new Lemming(posX, posY, true, Lemming.RIGHT, s);
	}

	@Override
	public Lemming createBody() {
		return body;
	}

	@Override
	public void live() {
		System.out.println("Reward obtained = "+body.getReward()+ " and position :"+body.getPosition());
		double r = Math.random();
		ActionInfluence action;
		if(r<0.5)
			action = new ActionInfluence(this, Action.WALK_FRONT);
		else
			action = new ActionInfluence(this, Action.WALK_BACK);
		this.body.influence(action);
	}

}
