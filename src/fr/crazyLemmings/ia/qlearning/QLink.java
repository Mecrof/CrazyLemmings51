package fr.crazyLemmings.ia.qlearning;

public class QLink {
	
	private QState from;
	private QState to;
	private int actionRewards[];
	
	public QLink(QState f, QState t)
	{
		this.from = f;
		this.to = t;
		this.actionRewards = new int[Action.totalActions];
		initialize();
	}
	
	private void initialize()
	{
		for (int i = 0; i < Action.totalActions; ++i)
			this.actionRewards[i] = 0;
	}
	
	public int getReward(Action a)
	{
		return this.actionRewards[a.id];
	}

	public QState getFrom() {
		return from;
	}

	public QState getTo() {
		return to;
	}
	
	

}
