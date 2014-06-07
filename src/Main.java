import environment.Environment;
import environment.engine.LemmingEngine;

public class Main {

	public static void main(String[] args) {
		Environment e = new Environment(20,5);
		LemmingEngine engine = new LemmingEngine(e, 1000);
		engine.initialize();
		engine.run();
	}

}
