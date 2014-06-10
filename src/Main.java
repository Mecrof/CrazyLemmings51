import qlearning.RandomLemmingAgent;
import environment.DefaultFrustrum;
import environment.engine.LemmingEngine;
import environment.exceptions.CellNotFoundException;
import environment.lemming.LemmingEnvironment;
import environment.lemming.Portal;
import environment.lemming.TypeCell;
import gui.GUI;

public class Main {

	public static void main(String[] args) {
		
		LemmingEnvironment e = new LemmingEnvironment(80, 60);
		Portal portal = new Portal(70,52,true);
		e.setPortal(portal);
		
		LemmingEngine engine = new LemmingEngine(e, 1000);
		//*
		RandomLemmingAgent lemming2 = new RandomLemmingAgent(15, 50, new DefaultFrustrum<TypeCell>(e));
		engine.enableAgent(lemming2);
		//*/
		/*
		RandomLemmingAgent lemming = new RandomLemmingAgent(27, 50, new DefaultFrustrum(e));
		RandomLemmingAgent lemming2 = new RandomLemmingAgent(15, 50, new DefaultFrustrum(e));
		RandomLemmingAgent lemming3 = new RandomLemmingAgent(60, 45, new DefaultFrustrum(e));
		RandomLemmingAgent lemming4 = new RandomLemmingAgent(60, 30, new DefaultFrustrum(e));
		engine.enableAgent(lemming);
		engine.enableAgent(lemming2);
		engine.enableAgent(lemming3);
		engine.enableAgent(lemming4);
		//*/
		/*
		RandomLemmingAgent lemming;
		for (int i = 0; i < 20; i++)
		{
			int x = (int)(Math.random()*80.0);
			int y = (int)(Math.random()*60.0);
			lemming = new RandomLemmingAgent(x, y, new DefaultFrustrum(e));
			engine.enableAgent(lemming);
		}
		//*/
		GUI gui = new GUI();
		e.addListener(gui.WorldPanel());
		engine.initialize();
		gui.pack();
		gui.setVisible(true);
		System.out.println(e.toString());
		engine.run();
	}

}
