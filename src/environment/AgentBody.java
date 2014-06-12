package environment;

import java.util.List;

public interface AgentBody {
	
	public List<Perceivable> getPerception();
	
	public void influence(Influence inf);
	
	public boolean isDead();
	
	public Sensor getSensor();
	
	public Reward getReward();
	
	public int getDirection();

}
