package environment;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import environment.exceptions.CellNotFoundException;
import environment.lemming.LemmingEnvironment;
import environment.lemming.PerceivedType;
import environment.lemming.Type;
import environment.lemming.TypeCell;

public class DefaultFrustrum implements Sensor<TypeCell> {

	/*------------------------
	    DEFAULT FRUSTRUM
	         SHAPE
	--------------------------
	      ___ ___ ___
         |   | x |   |
         |___|___|___|
         |   |   |   |
         |___|___|___|
             |   |
             |___|
             	
	x = currentPosition
	
	--------------------------*/
	private LemmingEnvironment environment;
	
	public DefaultFrustrum(LemmingEnvironment e) {
		this.environment = e;
	}

	public List<TypeCell> getPerceivedCells(TypeCell currentCell) {
		LinkedList<TypeCell> cells = new LinkedList<TypeCell>();
		Point currentPosition = currentCell.getPosition();
		
		// adding cell at the up left
		cells.add(this.getCellAt(currentPosition.x-1, currentPosition.y));
		// adding cell at the bottom left
		cells.add(this.getCellAt(currentPosition.x-1, currentPosition.y+1));
		
		// adding currentCell
		//cells.add(currentCell);
		
		// adding cell at the up right
		cells.add(this.getCellAt(currentPosition.x+1, currentPosition.y));
		// adding cell at the bottom right
		cells.add(this.getCellAt(currentPosition.x+1, currentPosition.y+1));
		
		// adding cell at the bottom
		cells.add(this.getCellAt(currentPosition.x, currentPosition.y+1));
		// adding cell at the bottom of the bottom cell
		cells.add(this.getCellAt(currentPosition.x, currentPosition.y+2));
		
		return cells;
	}
	
	@Override
	public List<Perceivable> getPerception(WorldObject ob) {
		LinkedList<Perceivable> perceivedObjects = new LinkedList<Perceivable>();

		TypeCell currentCell;
		try {
			currentCell = (TypeCell) ob.getCurrentCell(this.environment);
			
			// gets cells perceived by the body, if currentCell is out of borders, CellNotFound is thrown
			List<TypeCell> cells = this.getPerceivedCells(currentCell);
			// goes through the perceived cells
			Iterator<TypeCell> itCells = cells.iterator();
			while(itCells.hasNext())
			{
				TypeCell cell = itCells.next();
				
				Type type;
				Point cellPosition = cell.getPosition();
				
				// gets type of the cell
				if (cell.containsPortal())
				{
					type = Type.PORTAL;
				}
				else
				{
					type = cell.getType();
				}
				// if it is still an empty, let's check if it may be a floor
				if (type == Type.EMPTY)
				{
					try {
						TypeCell bottomCell = this.environment.getCellAt(cellPosition.x, cellPosition.y+1);
						if (!Type.isTraversable( bottomCell.getType() ) )
						{
							type = Type.FLOOR;
						}
					} catch (CellNotFoundException e) { // we are at the bottom of the world
						type = Type.FLOOR;
					}
				}
				Point portalPosition = ((LemmingEnvironment)this.environment).getPortal().getPosition();
				PerceivedType perceivedType = new PerceivedType(cellPosition.x,
																cellPosition.y, 
																cellPosition.x-currentCell.getPosition().x,
																cellPosition.y-currentCell.getPosition().y,
																portalPosition.x-cellPosition.x,
																portalPosition.y-cellPosition.y,
																type
																);
				perceivedObjects.add(perceivedType);
			}
		} catch (CellNotFoundException e1) {
			e1.printStackTrace();
		}
	
		return perceivedObjects;
	}
	
	private TypeCell getCellAt(int x, int y)
	{
		try {
			return this.environment.getCellAt(x, y);
		} catch (CellNotFoundException e) {
			return new TypeCell(x, y, Type.ROCK);
		}
	}

	public Environment<TypeCell> getEnvironment() {
		return environment;
	}

	public void setEnvironment(LemmingEnvironment environment) {
		this.environment = environment;
	}

}
