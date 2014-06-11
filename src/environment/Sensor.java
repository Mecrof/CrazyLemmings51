package environment;

import java.util.List;

public interface Sensor<C extends Cell> {
	
	public List<Perceivable> getPerception(WorldObject ob);
	
	public Environment<C> getEnvironment();

}
