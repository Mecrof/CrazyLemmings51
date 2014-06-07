package listener;

import java.util.EventObject;

import environment.EnvironmentState;

public class EnvironmentEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	public EnvironmentEvent(EnvironmentState source)
	{
		super(source);
	}
	
	public EnvironmentState getEnvironmentState() 
	{
		return (EnvironmentState) this.getSource();
	}
}
