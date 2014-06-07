import environment.Environment;
import environment.engine.LemmingEngine;
import gui.GUI;

public class Main {

	public static void main(String[] args) {
		Environment e = new Environment(80,60);
		LemmingEngine engine = new LemmingEngine(e, 1000);
		GUI gui = new GUI();
		e.addListener(gui.WorldPanel());
		engine.initialize();
		gui.pack();
		gui.setVisible(true);
		engine.run();
	}

}
