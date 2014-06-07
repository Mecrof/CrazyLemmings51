package environment.lemming;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import qlearning.Action;
import environment.AgentBody;
import environment.Cell;
import environment.DefaultFrustrum;
import environment.Environment;
import environment.Influence;
import environment.Perceivable;
import environment.Reward;
import environment.Sensor;
import environment.WorldObject;
import environment.exceptions.CellNotFoundException;

public class Lemming extends WorldObject implements AgentBody {
	
	public static final int LEFT = -1;
	public static final int RIGHT = 1;
	
	private DefaultFrustrum frustrum;
	private Environment environment;
	
	private int direction;
	private boolean dead;
	private Reward reward;
	
	private Influence influence;
	
	public Lemming(Environment e, int x, int y, boolean t, int direction, DefaultFrustrum f) {
		super(x,y,t);
		frustrum = f;
		environment = e;
		this.setDirection(direction);
		this.dead = false;
		this.reward = new Reward(Reward.NOTHING_HAPPENED);
	}

	@Override
	public List<Perceivable> getPerception() {
		LinkedList<Perceivable> perceivedObjects = new LinkedList<Perceivable>();
		try 
		{
			Cell currentCell = this.getCurrentCell(environment);
			// gets cells perceived by the body, if currentCell is out of borders, CellNotFound is thrown
			List<Cell> cells = frustrum.getPerceivedCells(currentCell);
			// goes through the perceived cells
			Iterator<Cell> itCells = cells.iterator();
			while(itCells.hasNext())
			{
				Cell cell = itCells.next();
				Point delta = new Point();
				Point cellPosition = cell.getPosition();
				
				Iterator<WorldObject> itObjects = cell.getIterator();
				Type type = Type.EMTPY;
				// gets type of the cell
				while(itObjects.hasNext())
				{
					WorldObject obj = itObjects.next();
					if(obj instanceof GroundObject)
					{
						type = ((GroundObject) obj).getType();
						break;
					}
				}
				// if it is still an empty, let's check if it may be a floor
				if (type == Type.EMTPY)
				{
					try
					{
						Cell bellowCell = environment.getCellAt(cellPosition.x, cellPosition.y+1);
						itObjects = bellowCell.getIterator();
						while(itObjects.hasNext())
						{
							if(itObjects.next() instanceof GroundObject)
							{
								type = Type.FLOOR;
								break;
							}
						}
					}
					// exception if the cell is on bottom border
					catch (CellNotFoundException c)
					{
						type = Type.FLOOR;
					}
				}
				PerceivedType perceivedType = new PerceivedType(cellPosition.x,
																cellPosition.y, 
																cellPosition.x-currentCell.getPosition().x,
																cellPosition.y-currentCell.getPosition().y,
																type);
				perceivedObjects.add(perceivedType);
			}
		} catch (CellNotFoundException e) 
		{
			e.printStackTrace();
		}
		return perceivedObjects;
	}

	@Override
	public void influence(Influence inf) { // TODO thing about when influences are applied
		this.influence = inf;
		/*
		if (inf instanceof ActionInfluence)
		{
			ActionInfluence actionInfluence = (ActionInfluence) inf;
			actionInfluence.getAction();
			switch (actionInfluence.getAction()) {
			case WALK_FRONT:
				
				break;
			case WALK_BACK:
				
				break;
			case DIG_FRONT:
				
				break;
			case DIG_BACK:
	
				break;
			case DIG_BELOW:
	
				break;
			default:
				return new Reward(Reward.NOTHING_HAPPENED);
			}
		}
		return new Reward(Reward.NOTHING_HAPPENED);*/
	}
	
	public void setDead(boolean d)
	{
		this.dead = d;
	}

	@Override
	public boolean isDead() {
		return this.dead;
	}

	@Override
	public Sensor getSensor() {
		return this.frustrum;
	}
	
	@Override
	public Reward getReward() {
		return this.reward;
	}
	
	public int getDirection() {
		return this.direction;
	}

	public void setDirection(int direction) {
		if (direction >= 0)
			this.direction = RIGHT;
		else
			this.direction = LEFT;
	}

	public final Influence getInfluence()
	{
		return this.influence;
	}

}
