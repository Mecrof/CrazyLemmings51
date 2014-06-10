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
	
	private Sensor frustrum;
	
	private int direction;
	private boolean digging;
	private boolean dead;
	private Reward reward;
	
	private Influence influence;
	
	public Lemming(int x, int y, boolean traversable, int direction, Sensor f) {
		super(x,y,traversable);
		frustrum = f;
		this.setDirection(direction);
		this.dead = false;
		this.digging = false;
		this.reward = new Reward(Reward.NOTHING_HAPPENED);
	}

	@Override
	public List<Perceivable> getPerception() {
		LinkedList<Perceivable> perceivedObjects = new LinkedList<Perceivable>();

		Cell currentCell;
		try {
			currentCell = this.getCurrentCell(this.frustrum.getEnvironment());
			
			// gets cells perceived by the body, if currentCell is out of borders, CellNotFound is thrown
			List<Cell> cells = (List<Cell>) frustrum.getPerceivedCells(currentCell);
			// goes through the perceived cells
			Iterator<Cell> itCells = cells.iterator();
			while(itCells.hasNext())
			{
				Cell cell = itCells.next();
				
				Type type;
				Point cellPosition = cell.getPosition();
				
				// gets type of the cell
				if (cell instanceof TypeCell)
					type = ((TypeCell) cell).getType();
				else
					type = Type.EMTPY;
				// if it is still an empty, let's check if it may be a floor
				if (type == Type.EMTPY)
				{
					try {
						Cell bottomCell = this.frustrum.getEnvironment().getCellAt(cellPosition.x, cellPosition.y+1);
						if (bottomCell instanceof TypeCell && !Type.isTraversable( ((TypeCell) bottomCell).getType() ) )
						{
							type = Type.FLOOR;
						}
					} catch (CellNotFoundException e) { // we are at the bottom of the world
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
		} catch (CellNotFoundException e1) {
			e1.printStackTrace();
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
	
	public void setReward(Reward r)
	{
		this.reward = r;
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

	public boolean isDigging() {
		return digging;
	}

	public void setDigging(boolean digging) {
		this.digging = digging;
	}

}
