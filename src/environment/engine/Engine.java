package environment.engine;

/**
 * behavior needed for an {@link Engine}
 *
 */
public interface Engine {
	
	/**
	 * initialize the engine
	 */
	public void initialize();
	
	/**
	 * run the engine
	 */
	public void run();

}
