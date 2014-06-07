package environment;

import java.util.List;

public interface Sensor {
	
	public List<Cell> getPerceivedCells(Cell currentCell);

}
