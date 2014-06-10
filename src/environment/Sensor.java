package environment;

import java.util.List;

public interface Sensor {
	
	public List<?> getPerceivedCells(Cell currentCell);
	
	public Environment<?> getEnvironment();

}
