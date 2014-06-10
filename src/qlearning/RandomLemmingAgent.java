package qlearning;

import environment.Sensor;
import environment.lemming.ActionInfluence;
import environment.lemming.Lemming;

public class RandomLemmingAgent extends LemmingAgent {
	
	public RandomLemmingAgent(int posX, int posY, Sensor s) {
		super(posX, posY, true, Lemming.RIGHT, s);
	}
	
	@Override
	public void live() {
		//System.out.println("Reward obtained = "+body.getReward()+ " and position :"+body.getPosition());
		int r = (int)(Math.random()*5.0);
		ActionInfluence action;
		switch (r) {
		case 0:
			action = new ActionInfluence(this, Action.WALK_FRONT);
			break;
		case 1:
			action = new ActionInfluence(this, Action.WALK_BACK);
			break;
		case 2:
			action = new ActionInfluence(this, Action.DIG_FRONT);
			break;
		case 3:
			action = new ActionInfluence(this, Action.DIG_BACK);
			break;
		case 4:
			action = new ActionInfluence(this, Action.DIG_BELOW);
			break;

		default:
			action = new ActionInfluence(this, Action.STAY);
			break;
		}
		this.body.influence(action);
	}

}
