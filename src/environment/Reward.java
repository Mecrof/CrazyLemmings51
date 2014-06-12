package environment;

public class Reward {
	
	public static final int VERY_GOOD_ACTION = 100;
	public static final int GOOD_ACTION = 20;
	public static final int NOTHING_HAPPENED = -5;
	public static final int BAD_ACTION = -20;
	public static final int VERY_BAD_ACTION = -50;
	public static final int YOU_STUPID = -100;
	
	public final int reward;
	
	public Reward(int reward) {
		if (reward >= VERY_GOOD_ACTION)
		{
			this.reward = VERY_GOOD_ACTION;
		}
		else
		{
			if (reward >= GOOD_ACTION)
			{
				this.reward = GOOD_ACTION;
			}
			else
			{
				if (reward < GOOD_ACTION && reward > BAD_ACTION)
				{
					this.reward = NOTHING_HAPPENED;
				}
				else
				{
					if (reward <= VERY_BAD_ACTION)
					{
						this.reward = VERY_BAD_ACTION;
					}
					else
					{
						this.reward = BAD_ACTION;
					}
				}					
			}
		}
	}
	
	public int getReward() {
		return reward;
	}

	@Override
	public String toString() {
		return ""+reward;
	}

}
