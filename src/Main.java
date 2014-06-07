import environment.engine.LemmingEngine;
import environment.lemming.LemmingEnvironment;
import gui.GUI;

public class Main {

	public static void main(String[] args) {
		LemmingEnvironment e = new LemmingEnvironment(80, 60);
		System.out.println(e.toString());
		LemmingEngine engine = new LemmingEngine(e, 1000);
		GUI gui = new GUI();
		e.addListener(gui.WorldPanel());
		engine.initialize();
		gui.pack();
		gui.setVisible(true);
		engine.run();
	}

}
