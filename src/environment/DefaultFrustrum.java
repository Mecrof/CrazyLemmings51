package environment;

import java.util.List;

public class DefaultFrustrum implements Sensor {
	
	Environment<?> environment;
	
	public DefaultFrustrum(Environment<?> e) {
		this.environment = e;
	}

	@Override
	public List<?> getPerceivedCells(Cell currentCell) {
		// TODO Auto-generated method stub
		return null;
	}

	public Environment<?> getEnvironment() {
		return environment;
	}

}
