package environment;

import java.util.List;

/**
 * 
 * Behavior needed for a sensor
 *
 * @param <C>
 */
public interface Sensor<C extends Cell> {
	
	/**
	 * return a list of {@link Perceivable} object perceived thanks to the sensor from the position of the {@link WorldObject} given
	 * @param ob
	 * @return
	 */
	public List<Perceivable> getPerception(WorldObject ob);
	
	public Environment<C> getEnvironment();

}
