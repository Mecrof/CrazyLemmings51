package environment;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import environment.exceptions.CellNotFoundException;
import environment.lemming.Type;
import environment.lemming.TypeCell;

public class DefaultFrustrum<C extends Cell> implements Sensor<C> {

	/*------------------------
	    DEFAULT FRUSTRUM
	         SHAPE
	--------------------------
	      ___ ___ ___
         |   | x |   |
         |___|___|___|
             |   |
             |___|
             |   |
             |___|
             	
	x = currentPosition
	
	--------------------------*/
	Environment<C> environment;
	
	public DefaultFrustrum(Environment<C> e) {
		this.environment = e;
	}

	@Override
	public List<C> getPerceivedCells(Cell currentCell) {
		LinkedList<C> cells = new LinkedList<C>();
		Point currentPosition = currentCell.getPosition();
		
		// adding cell at the left
		cells.add(this.getCellAt(currentPosition.x-1, currentPosition.y));
		// adding currentCell
		cells.add((C) currentCell);
		// adding cell at the right
		cells.add(this.getCellAt(currentPosition.x+1, currentPosition.y));
		// adding cell at the bottom
		cells.add(this.getCellAt(currentPosition.x, currentPosition.y+1));
		// adding cell at the bottom of the bottom cell
		cells.add(this.getCellAt(currentPosition.x, currentPosition.y+2));
		
		return cells;
	}
	
	private C getCellAt(int x, int y)
	{
		try {
			return this.environment.getCellAt(x, y);
		} catch (CellNotFoundException e) {
			return (C) new TypeCell(x, y, Type.ROCK);
		}
	}

	public Environment<C> getEnvironment() {
		return environment;
	}

}
