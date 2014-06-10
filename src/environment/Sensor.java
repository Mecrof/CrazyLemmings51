package environment;

import java.util.List;

public interface Sensor<C extends Cell> {
	
	public List<C> getPerceivedCells(C currentCell);
	
	public Environment<C> getEnvironment();

}
