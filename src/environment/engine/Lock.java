package environment.engine;

/**
 * 
 * An object that permit to lock an other object execution
 * 
 */
public class Lock {

	private boolean locked;
	
	public Lock()
	{
		this.locked = false;
	}
	
	/**
	 * Set the state of the object
	 * 
	 * @param locked thet state of the object
	 */
	public void setLocked(boolean locked)
	{
		this.locked = locked;
	}
	
	/**
	 * 
	 * @return the state of the lock
	 */
	public boolean isLocked()
	{
		return this.locked;
	}
	
	/**
	 * Wait until the lock is unlocked
	 */
	public void waitLock()
	{
		synchronized(this)
		{
			while(this.locked)
			{
				try 
				{
					this.wait();
				} 
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Notify that the lock is unlocked
	 */
	public void notifyUnlocked()
	{
		synchronized(this)
		{
			this.notify();
		}
	}
	
}
