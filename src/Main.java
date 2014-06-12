import qlearning.LearningLemmingAgent;
import qlearning.RandomLemmingAgent;
import environment.DefaultFrustrum;
import environment.engine.LemmingEngine;
import environment.lemming.LemmingEnvironment;
import environment.lemming.Portal;
import gui.GUI;
import gui.MouseListener;

public class Main {

	public static void main(String[] args) {
		
		LemmingEnvironment e = new LemmingEnvironment(80, 60);
		Portal portal = new Portal(77,58,true);
		e.setPortal(portal);
		
		LemmingEngine engine = new LemmingEngine(e, 1000);
		/*
		RandomLemmingAgent lemming2 = new RandomLemmingAgent("Lemming2",77, 52, new DefaultFrustrum(e));
		engine.enableAgent(lemming2);
		//*/
		LearningLemmingAgent lemming = new LearningLemmingAgent(10, 50, new DefaultFrustrum(e));
		engine.enableAgent(lemming);
		/*
		RandomLemmingAgent lemming;
		for (int i = 0; i < 10; i++)
		{
			int x = (int)(Math.random()*80.0);
			int y = (int)(Math.random()*60.0);
			lemming = new RandomLemmingAgent("Lemming"+i,x, y, new DefaultFrustrum(e));
			engine.enableAgent(lemming);
		}
		//*/
		GUI gui = new GUI();
		gui.setMouseListener(new MouseListener(e, engine.getLock()));
		e.addListener(gui.WorldPanel());
		engine.initialize();
		gui.pack();
		gui.setVisible(true);
		System.out.println(e.toString());
		engine.run();
	}

}
