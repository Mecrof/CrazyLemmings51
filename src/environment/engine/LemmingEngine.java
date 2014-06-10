package environment.engine;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;

import qlearning.Action;
import qlearning.Agent;
import environment.Environment;
import environment.Influence;
import environment.Reward;
import environment.WorldObject;
import environment.exceptions.CellNotFoundException;
import environment.lemming.ActionInfluence;
import environment.lemming.Lemming;
import environment.lemming.LemmingEnvironment;
import environment.lemming.Type;
import environment.lemming.TypeCell;

public class LemmingEngine implements Engine {
	
	private final int HIGH_TO_DIE = 7;
	
	private int timePerFrame;
	private LemmingEnvironment environment;
	private LinkedList<Lemming> lemmings;
	private LinkedList<Agent<Lemming>> agents;
	private boolean ended;
	
	
	public LemmingEngine(LemmingEnvironment e, int tpf) {
		this.environment = e;
		this.timePerFrame = tpf;
		lemmings = new LinkedList<Lemming>();
		agents = new LinkedList<Agent<Lemming>>();
		
	}

	@Override
	public void initialize() {
		Iterator<Agent<Lemming>> it = agents.iterator();
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
		this.ended = false;
	}

	@Override
	public void run() {
		while(!ended)
		{
			try {
				Thread.sleep(timePerFrame);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Iterator<Agent<Lemming>> it = agents.iterator();
			while(it.hasNext())
			{
				it.next().live();
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
			this.environment.fireChange();
			System.out.println(this.environment.toString());
		}
	}
	
	private Reward applyActionInfluence(ActionInfluence influence, Lemming lemming)
	{
		if (!lemming.isDead())
		{
			switch (influence.getAction()) {
			case WALK_FRONT:
				return walk(lemming, Action.WALK_FRONT);
			case WALK_BACK:
				return walk(lemming, Action.WALK_BACK);
			case DIG_FRONT:
				return dig(lemming, Action.DIG_FRONT);
			case DIG_BACK:
				return dig(lemming, Action.DIG_BACK);
			case DIG_BELOW:
				return dig(lemming, Action.DIG_BELOW);
			default:
				return new Reward(Reward.NOTHING_HAPPENED);
			}
		}
		return new Reward(Reward.NOTHING_HAPPENED);
	}
	
	private Reward dig(Lemming lemming, Action action)
	{
		lemming.setDigging(true);
		Reward reward = new Reward(Reward.VERY_BAD_ACTION);
		try {
			TypeCell targetCell = this.getTargetCell(lemming, action);
			Type targetType = targetCell.getType();
			if (targetType == Type.CLAY)
			{
				targetCell.setType(Type.EMTPY);
				routineForNewPosition(targetCell, lemming);
			}
		} catch (CellNotFoundException e) // the agent tries to go outside of the environment
		{}
		return reward;
	}
	
	private Reward walk(Lemming lemming, Action action)
	{
		Reward reward = new Reward(Reward.VERY_BAD_ACTION);
		try{
			TypeCell targetCell = this.getTargetCell(lemming, action);
			Type targetType = targetCell.getType();
			if (targetType == Type.CLAY || targetType == Type.ROCK)
			{
				reward = new Reward(Reward.VERY_BAD_ACTION); // very bad because too stupid by walking against a wall...
			}
			else if (targetType == Type.EMTPY)
			{
				routineForNewPosition(targetCell, lemming);
			}
		}
		catch (CellNotFoundException ex) // the agent tries to go outside of the environment
		{}
		return reward;
	}
	
	private Reward routineForNewPosition(TypeCell targetCell, Lemming lemming)
	{
		Reward reward = new Reward(Reward.VERY_BAD_ACTION);
		Point currentPosition = lemming.getPosition();
		try{
			TypeCell bottomTargetCell = environment.getCellAt(targetCell.getPosition().x, targetCell.getPosition().y+1);
			if (bottomTargetCell.getType() == Type.EMTPY)
			{
				if(this.goDown(targetCell, lemming))
				{
					if (isCloserToPortal(currentPosition, lemming.getPosition()))
						reward = new Reward(Reward.GOOD_ACTION);
					else
						reward = new Reward(Reward.BAD_ACTION);
				}
			}
			else
			{
				if (isCloserToPortal(currentPosition, targetCell.getPosition()))
					reward = new Reward(Reward.GOOD_ACTION);
				else
					reward = new Reward(Reward.BAD_ACTION);
				lemming.setPosition(environment, targetCell.getPosition());
			}
		}catch(CellNotFoundException ex)
		{
			if (isCloserToPortal(currentPosition, targetCell.getPosition()))
				reward = new Reward(Reward.GOOD_ACTION);
			else
				reward = new Reward(Reward.BAD_ACTION);
			lemming.setPosition(environment, targetCell.getPosition());
		}
		return reward;
	}
	
	private boolean isCloserToPortal(Point oldPos, Point newPos)
	{
		Point posPortal = environment.getPortal().getPosition();
		if (posPortal.distance(oldPos) > posPortal.distance(newPos))
			return true;
		return false;
	}
	
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
			if (lemming.getDirection() == Lemming.RIGHT)
			{
				if (action == Action.WALK_FRONT || action == Action.DIG_FRONT)
				{
					targetCell = environment.getCellAt(position.x+1, position.y);
				}
				else
				{
					targetCell = environment.getCellAt(position.x-1, position.y);
					lemming.setDirection(Lemming.LEFT);
				}
			}
			else
			{
				if (action == Action.WALK_FRONT || action == Action.DIG_FRONT)
				{
					targetCell = environment.getCellAt(position.x-1, position.y);
				}
				else
				{
					targetCell = environment.getCellAt(position.x+1, position.y);
					lemming.setDirection(Lemming.RIGHT);
				}
			}
		}
		return targetCell;
	}
	
	private boolean goDown(TypeCell from, Lemming lemming)
	{
		TypeCell tmpCell = from;
		int high = 0;
		try{
		while (tmpCell.getType() == Type.EMTPY)
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
			return false;
		}
		return true;
	}
	
	public boolean enableAgent(Agent<Lemming> ag)
	{
		if (agents.add(ag))
		{
			lemmings.add(ag.createBody());
			return true;
		}
		return false;
	}

}
