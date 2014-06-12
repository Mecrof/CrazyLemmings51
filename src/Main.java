import java.awt.Point;

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
		
		/*
		LemmingEnvironment e = new LemmingEnvironment(80, 60, "../../stage/stage_1");
		Point startPosition = e.getStartPosition();
		
		LemmingEngine engine = new LemmingEngine(e, 250);
		/*
		RandomLemmingAgent lemming2 = new RandomLemmingAgent("Lemming2",77, 52, new DefaultFrustrum(e));
		engine.enableAgent(lemming2);
		//*/
		/*
		LearningLemmingAgent lemming = new LearningLemmingAgent(10, 50, new DefaultFrustrum(e));
		engine.enableAgent(lemming);
		//*/
		/*
		RandomLemmingAgent lemming;
		for (int i = 0; i < 10; i++)
		{
			int x = (int)(Math.random()*80.0);
			int y = (int)(Math.random()*60.0);
			lemming = new RandomLemmingAgent("Lemming"+i,startPosition.x, startPosition.y, new DefaultFrustrum(e));
			engine.enableAgent(lemming);
		}
		//*/
		//*
		Mandator mandator = new Mandator(100);
		mandator.loadLabs("./stage/labStages");
		mandator.runLab();
		//*/
		//*
		//Mandator mandator = new Mandator(100);
		mandator.loadWorld("../../stage/stage_1");
		GUI gui = new GUI();
		gui.setMouseListener(new MouseListener(mandator.getWorld(), mandator.getEngine().getLock()));
		mandator.getWorld().addListener(gui.WorldPanel());
		mandator.getEngine().initialize();
		gui.pack();
		gui.setVisible(true);
		//System.out.println(e.toString());
		//engine.run();
		mandator.addLemmingsInWorld(20);
		mandator.runInWorld();
		//*/
	}

}
