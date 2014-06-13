package listener;

import java.util.EventObject;

import environment.EnvironmentState;

/**
 * En event fired from the environment
 *
 */
public class EnvironmentEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	public EnvironmentEvent(EnvironmentState<?> source)
	{
		super(source);
	}
	
	/**
	 * 
	 * @return the state of the environment
	 */
	public EnvironmentState<?> getEnvironmentState() 
	{
		return (EnvironmentState<?>) this.getSource();
	}
}
