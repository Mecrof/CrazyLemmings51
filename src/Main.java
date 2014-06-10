import qlearning.RandomLemmingAgent;
import environment.DefaultFrustrum;
import environment.engine.LemmingEngine;
import environment.exceptions.CellNotFoundException;
import environment.lemming.LemmingEnvironment;
import environment.lemming.Portal;
import gui.GUI;

public class Main {

	public static void main(String[] args) {
		
		LemmingEnvironment e = new LemmingEnvironment(80, 60);
		Portal portal = new Portal(70,52,true);
		e.setPortal(portal);
		//System.out.println(e.toString());
		LemmingEngine engine = new LemmingEngine(e, 1000);
		RandomLemmingAgent lemming = new RandomLemmingAgent(27, 45, new DefaultFrustrum(e));
		engine.enableAgent(lemming);
		GUI gui = new GUI();
		e.addListener(gui.WorldPanel());
		engine.initialize();
		gui.pack();
		//gui.setVisible(true);
		engine.run();
	}

}
