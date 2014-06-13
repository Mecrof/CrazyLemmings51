package environment.lemming;

import java.util.List;

import environment.AgentBody;
import environment.Influence;
import environment.Perceivable;
import environment.Reward;
import environment.Sensor;
import environment.WorldObject;

/**
 * Is an {@link AgentBody} and a {@link WorldObject} and seen as a lemming
 * 
 *
 */
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
	public void influence(Influence inf) {
		this.influence = inf;
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
	public Sensor<?> getSensor() {
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
