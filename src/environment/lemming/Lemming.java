package environment.lemming;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import qlearning.Action;
import environment.AgentBody;
import environment.Cell;
import environment.DefaultFrustrum;
import environment.Environment;
import environment.Influence;
import environment.Perceivable;
import environment.Reward;
import environment.Sensor;
import environment.WorldObject;
import environment.exceptions.CellNotFoundException;

public class Lemming extends WorldObject implements AgentBody {
	
	public static final int LEFT = -1;
	public static final int RIGHT = 1;
	
	private Sensor<TypeCell> frustrum;
	
	private int direction;
	private boolean digging;
	private boolean dead;
	private Reward reward;
	
	private Influence influence;
	
	public Lemming(int x, int y, boolean traversable, int direction, Sensor<TypeCell> f) {
		super(x,y,traversable);
		frustrum = f;
		this.setDirection(direction);
		this.dead = false;
		this.digging = false;
		this.reward = new Reward(Reward.NOTHING_HAPPENED);
	}

	@Override
	public List<Perceivable> getPerception() {
		return this.frustrum.getPerception(this);
	}

	@Override
	public void influence(Influence inf) { // TODO thing about when influences are applied
		this.influence = inf;
		/*
		if (inf instanceof ActionInfluence)
		{
			ActionInfluence actionInfluence = (ActionInfluence) inf;
			actionInfluence.getAction();
			switch (actionInfluence.getAction()) {
			case WALK_FRONT:
				
				break;
			case WALK_BACK:
				
				break;
			case DIG_FRONT:
				
				break;
			case DIG_BACK:
	
				break;
			case DIG_BELOW:
	
				break;
			default:
				return new Reward(Reward.NOTHING_HAPPENED);
			}
		}
		return new Reward(Reward.NOTHING_HAPPENED);*/
	}
	
	public void setDead(boolean d)
	{
		this.dead = d;
	}

	@Override
	public boolean isDead() {
		return this.dead;
	}

	@Override
	public Sensor getSensor() {
		return this.frustrum;
	}
	
	@Override
	public Reward getReward() {
		return this.reward;
	}
	
	public void setReward(Reward r)
	{
		this.reward = r;
	}
	
	public int getDirection() {
		return this.direction;
	}

	public void setDirection(int direction) {
		if (direction >= 0)
			this.direction = RIGHT;
		else
			this.direction = LEFT;
	}

	public final Influence getInfluence()
	{
		return this.influence;
	}

	public boolean isDigging() {
		return digging;
	}

	public void setDigging(boolean digging) {
		this.digging = digging;
	}

}
