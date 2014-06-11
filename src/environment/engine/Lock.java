package environment.engine;

public class Lock {

	private boolean locked;
	
	public Lock()
	{
		this.locked = false;
	}
	
	public void setLocked(boolean locked)
	{
		this.locked = locked;
	}
	
	public boolean isLocked()
	{
		return this.locked;
	}
	
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

	public void notifyUnlocked()
	{
		synchronized(this)
		{
			this.notify();
		}
	}
	
}
