package qlearning;

import java.awt.Point;
import java.util.Iterator;
import java.util.List;

import environment.Perceivable;
import environment.Sensor;
import environment.lemming.ActionInfluence;
import environment.lemming.Lemming;
import environment.lemming.PerceivedType;
import environment.lemming.Type;

public class RandomLemmingAgent extends LemmingAgent {
	
	private String name;
	
	public RandomLemmingAgent(String name, int posX, int posY, Sensor s) {
		super(posX, posY, true, Lemming.RIGHT, s);
		this.name = name;
	}
	
	@Override
	public void live() {
		//System.out.println("Reward obtained = "+getBody().getReward());
		if (this.getBody().isDead())
		{
			killMe();
		}
		else
		{
			System.out.println("--------------------------------------");
			List<Perceivable> perceptions = this.getBody().getPerception();
			Iterator<Perceivable> it = perceptions.iterator();
			while(it.hasNext())
			{
				Perceivable perceivedObject = it.next();
				if (perceivedObject instanceof PerceivedType)
				{
					System.out.println(perceivedObject.toString());
					if (perceivedObject.getDelta().x == 0 && perceivedObject.getDelta().y ==0)
					{
						Point portalDirection = ((PerceivedType) perceivedObject).getPortalDirection();
						if (portalDirection.x == 0 && portalDirection.y == 0)
						{
							killMe();
							System.out.println(name+": I have just reached the portal !! :D");
							return;
						}
					}
				}
			}
			
			int r = (int)(Math.random()*5.0);
			ActionInfluence action;
			switch (r) {
			case 0:
				action = new ActionInfluence(this, Action.WALK_RIGHT);
				break;
			case 1:
				action = new ActionInfluence(this, Action.WALK_LEFT);
				break;
			case 2:
				action = new ActionInfluence(this, Action.DIG_RIGHT);
				break;
			case 3:
				action = new ActionInfluence(this, Action.DIG_LEFT);
				break;
			case 4:
				action = new ActionInfluence(this, Action.DIG_BELOW);
				break;
	
			default:
				action = new ActionInfluence(this, Action.STAY);
				break;
			}
			System.out.println("do:"+action.getAction());
			this.getBody().influence(action);
		}
	}

}
