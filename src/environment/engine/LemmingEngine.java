package environment.engine;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;

import qlearning.Action;
import qlearning.LearningLemmingAgent;
import qlearning.LemmingAgent;
import environment.Influence;
import environment.Reward;
import environment.WorldObject;
import environment.exceptions.CellNotFoundException;
import environment.lemming.ActionInfluence;
import environment.lemming.Lemming;
import environment.lemming.LemmingEnvironment;
import environment.lemming.Portal;
import environment.lemming.Type;
import environment.lemming.TypeCell;

public class LemmingEngine implements Engine {
	
	// rewards
	private final Reward r_Dig_In_Rock = new Reward(		Reward.YOU_STUPID); 	// -2
	private final Reward r_Dig_In_Air = new Reward(			Reward.YOU_STUPID); 	// -2
	private final Reward r_Walk_In_Solid_Type = new Reward(	Reward.VERY_BAD_ACTION); 	// -2
	private final Reward r_Go_Down_And_Die = new Reward(	Reward.YOU_STUPID); 		// -100
	private final Reward r_Closer_To_Portal = new Reward(	Reward.GOOD_ACTION); 		// +100
	private final Reward r_Further_To_Portal = new Reward(	Reward.BAD_ACTION);  		// -1
	private final Reward r_Stay = new Reward(				Reward.NOTHING_HAPPENED); 	// +0
	private final Reward r_Reached_Portal = new Reward(		Reward.VERY_GOOD_ACTION);	// +2
	private final Reward r_Go_Under_Portal = new Reward(	Reward.YOU_STUPID);         // -100
	
	private final Lock LOCK = new Lock();
	private final Object mutex = new Object();
	
	private final int HIGH_TO_DIE = 7;
	
	private static boolean ended;
	
	private int timePerFrame;
	private LemmingEnvironment environment;
	private LinkedList<Lemming> lemmings;
	private LinkedList<LemmingAgent> agents;	
	
	public LemmingEngine(LemmingEnvironment e, int tpf) {
		this.environment = e;
		this.timePerFrame = tpf;
		lemmings = new LinkedList<Lemming>();
		agents = new LinkedList<LemmingAgent>();
	}

	@Override
	public void initialize() {
		/*Iterator<LemmingAgent> it = agents.iterator();
		lemmings = new LinkedList<Lemming>();
		while(it.hasNext())
		{
			Lemming body = it.next().createBody();
			try {
				environment.addWorldObject(body);
			} catch (CellNotFoundException e) {
				System.out.println("ERROR::LemmingEngine::initialize::Lemming can not be added into the environment.");
				e.printStackTrace();
			}
			lemmings.add(body);
		}
		*/
		ended = false;
	}

	@Override
	public void run() {
		while(!ended)
		{
			if(LOCK.isLocked())
				LOCK.waitLock();
				
			try {
				Thread.sleep(timePerFrame);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			runAgents();
			this.environment.fireChange();
			//System.out.println(this.environment.toString());
		}
	}
	
	public void runAgents()
	{
		synchronized(this.mutex)
		{
			Iterator<LemmingAgent> it = agents.iterator();
			while(it.hasNext())
			{
				LemmingAgent ag = it.next();
				if (ag.isKilled())
				{
					int index = agents.indexOf(ag);
					it.remove();
					Lemming lem = lemmings.remove(index);
					try {
						environment.detachWorldObject(lem);
					} catch (CellNotFoundException e) {
						e.printStackTrace();
					}
				}
				else
				{
					ag.live();
				}
			}
			Iterator<Lemming> itLem = lemmings.iterator();
			while(itLem.hasNext())
			{
				Lemming lemming = itLem.next();
				lemming.setDigging(false);
				Influence influence = lemming.getInfluence();
				Reward reward;
				if (influence instanceof ActionInfluence)
				{
					reward = this.applyActionInfluence((ActionInfluence) influence, lemming);
				}
				else
				{
					reward = new Reward(Reward.NOTHING_HAPPENED);
				}
				lemming.setReward(reward);
			}
		}
	}
	
	private Reward applyActionInfluence(ActionInfluence influence, Lemming lemming)
	{
		if (!lemming.isDead())
		{
			switch (influence.getAction()) {
			case WALK_RIGHT:
				return walk(lemming, Action.WALK_RIGHT);
			case WALK_LEFT:
				return walk(lemming, Action.WALK_LEFT);
			case DIG_RIGHT:
				return dig(lemming, Action.DIG_RIGHT);
			case DIG_LEFT:
				return dig(lemming, Action.DIG_LEFT);
			case DIG_BELOW:
				return dig(lemming, Action.DIG_BELOW);
			default:
				return r_Stay;
			}
		}
		return new Reward(Reward.NOTHING_HAPPENED);
	}
	
	private Reward dig(Lemming lemming, Action action)
	{
		lemming.setDigging(true);
		Reward reward = r_Dig_In_Rock;
		try {
			TypeCell targetCell = this.getTargetCell(lemming, action);
			Type targetType = targetCell.getType();
			if (targetType == Type.CLAY)
			{
				targetCell.setType(Type.EMPTY);
				return routineForNewPosition(targetCell, lemming);
			}
			else if(targetType == Type.EMPTY)
			{
				return r_Dig_In_Air;
			}
		} catch (CellNotFoundException e) // the agent tries to go outside of the environment
		{}
		return reward;
	}
	
	private Reward walk(Lemming lemming, Action action)
	{
		Reward reward = r_Dig_In_Rock;
		try{
			TypeCell targetCell = this.getTargetCell(lemming, action);
			Type targetType = targetCell.getType();
			if (targetType == Type.CLAY || targetType == Type.ROCK)
			{
				reward = r_Walk_In_Solid_Type; // very bad because too stupid by walking against a wall...
			}
			else if (targetType == Type.EMPTY)
			{
				return routineForNewPosition(targetCell, lemming);
			}
		}
		catch (CellNotFoundException ex) // the agent tries to go outside of the environment
		{}
		return reward;
	}
	
	private Reward routineForNewPosition(TypeCell targetCell, Lemming lemming)
	{
		Reward reward = r_Go_Down_And_Die;
		Point currentPosition = lemming.getPosition();
		try{
			TypeCell bottomTargetCell = environment.getCellAt(targetCell.getPosition().x, targetCell.getPosition().y+1);
			if (bottomTargetCell.getType() == Type.EMPTY)
			{
				if(this.goDown(targetCell, lemming))
				{
					TypeCell newCell = environment.getCellAt(lemming.getPosition());
					if (newCell.containsPortal())
					{
						return r_Reached_Portal;
					}
					else
					{
						return r_Closer_To_Portal;//isCloserToPortal(currentPosition, lemming.getPosition());
					}
				}
			}
			else
			{
				lemming.setPosition(environment, targetCell.getPosition());
				if(targetCell.containsPortal())
				{
					return r_Reached_Portal;
				}
				else
				{
					return r_Closer_To_Portal;//isCloserToPortal(currentPosition, targetCell.getPosition());
				}
			}
		}catch(CellNotFoundException ex)
		{
			lemming.setPosition(environment, targetCell.getPosition());
			if(targetCell.containsPortal())
			{
				return r_Reached_Portal;
			}
			else
			{
				return r_Closer_To_Portal;//isCloserToPortal(currentPosition, targetCell.getPosition());
			}
		}
		return reward;
	}
	
	/*private boolean isCloserToPortal(Point oldPos, Point newPos)
	{
		Point posPortal = environment.getPortal().getPosition();
		if (posPortal.distance(oldPos) > posPortal.distance(newPos))
			return true;
		return false;
	}*/
	
	private TypeCell getTargetCell(Lemming lemming, Action action) throws CellNotFoundException
	{
		TypeCell targetCell;
		Point position = lemming.getPosition();
		if (action == Action.DIG_BELOW)
		{
			targetCell = environment.getCellAt(position.x, position.y+1);
		}
		else
		{
			if (action == Action.WALK_RIGHT || action == Action.DIG_RIGHT)
			{
				targetCell = environment.getCellAt(position.x+1, position.y);
				lemming.setDirection(Lemming.RIGHT);
			}
			else
			{
				targetCell = environment.getCellAt(position.x-1, position.y);
				lemming.setDirection(Lemming.LEFT);
			}
		}
		return targetCell;
	}
	
	private boolean goDown(TypeCell from, Lemming lemming)
	{
		TypeCell tmpCell = from;
		int high = 0;
		try{
		while (tmpCell.getType() == Type.EMPTY)
		{
			high++;
			tmpCell = environment.getCellAt(tmpCell.getPosition().x, tmpCell.getPosition().y+1);
		}
		}catch(CellNotFoundException ex){} // caught if the lemming is going down on the bottom border
		Point newPosition = new Point(from.getPosition().x, from.getPosition().y + high - 1);
		lemming.setPosition(environment, newPosition);
		if ( high > this.HIGH_TO_DIE )
		{
			lemming.setDead(true);
			this.environment.addNewDeadOne();
			return false;
		}
		return true;
	}

	public boolean enableAgent(LemmingAgent ag)
	{
		if (agents.add(ag))
		{
			lemmings.add(ag.createBody());
			return true;
		}
		return false;
	}
	
	public Lock getLock()
	{
		return this.LOCK;
	}

	public static void exit()
	{
		ended = true;
	}

	public void reset()
	{
		lemmings = new LinkedList<Lemming>();
		agents = new LinkedList<LemmingAgent>();
	}
	
	public void setEnvironment(LemmingEnvironment environment) {
		this.environment = environment;
	}

	public Object getMutex() {
		return this.mutex;
	}
}
