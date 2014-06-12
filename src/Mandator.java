import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import qlearning.LearningLemmingAgent;
import environment.DefaultFrustrum;
import environment.engine.LemmingEngine;
import environment.exceptions.CellNotFoundException;
import environment.lemming.LemmingEnvironment;



public class Mandator {
	
	private LemmingEnvironment world;
	private LemmingEngine engine;
	
	private final int LAB_STAGE_SIZE = 8;
	
	
	// lab variable
	private LinkedList<LemmingEnvironment> labs;
	private int nbIterationPerLab = 100;
	private int nbMaxLab = 4;
	private int nbStepMaxInIteration = 30;
	private LearningLemmingAgent agentLab;
	private int frameRate;
	
	public Mandator(int fps) {
		this.frameRate = fps;
	}
		
	public void runLab()
	{
		DefaultFrustrum frustrum = new DefaultFrustrum(null);
		agentLab = new LearningLemmingAgent(0, 0, frustrum, true);
		int nbStep;
		int nbLab = (nbMaxLab > labs.size()) ? labs.size() : nbMaxLab;
		//agentLab.setLearning(true);
		for(int i = 0; i < nbLab; i++)
		{
			LemmingEnvironment currentEnvironment = labs.get(i);
			frustrum.setEnvironment(currentEnvironment);
			engine = new LemmingEngine(currentEnvironment, frameRate);
			System.out.println(currentEnvironment.toString());
			for (int j = 0; j < nbIterationPerLab; j++)
			{
				System.out.println("it="+j);
				nbStep = 0;
				agentLab.createBody().setPosition(currentEnvironment, currentEnvironment.getStartPosition());
				//currentEnvironment.addWorldObject(agentLab.createBody());
				engine.enableAgent(agentLab);
				agentLab.ressucite();
				while (nbStep < nbStepMaxInIteration && !agentLab.isKilled())
				{
					System.out.print(".");
					nbStep++;
					engine.runAgents();
				}
				System.out.println();
			}
		}
	}
	
	public void runInWorld()
	{
		engine.run();/*
		if (addingLemming)
		{
			this.addLemmingInWorld();
		}
		engine.runAgents();
		this.world.fireChange();*/
	}
	
	public void loadWorld(String stageFile)
	{
		world = new LemmingEnvironment(80, 60, stageFile);
		engine = new LemmingEngine(world, frameRate);
	}
	
	public void loadLabs(String labStageFile)
	{
		labs = new LinkedList<LemmingEnvironment>();
		BufferedReader buffer = null;
		try
		{
			String line;
			buffer = new BufferedReader(new FileReader(this.getClass().getResource(labStageFile).getPath()));
			while ( (line = buffer.readLine()) != null)
			{
				labs.add(new LemmingEnvironment(LAB_STAGE_SIZE, LAB_STAGE_SIZE, "../../stage/"+line));
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				if (buffer != null)buffer.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void addLemmingsInWorld(int number)
	{
		// TODO: clone memory of agentLab in the new agent
		try {
			for (int i = 0; i < number; i++)
			{
				LearningLemmingAgent ag = new LearningLemmingAgent(world.getStartPosition().x, 
					world.getStartPosition().y, 
					new DefaultFrustrum(world),
					false
					);
				ag.setqProblem(agentLab.getqProblem());
				world.addWorldObject(ag.createBody());
				engine.enableAgent(ag);
			}
		} catch (CellNotFoundException e) {
			System.out.println("Error during adding new agent lemming in Mandator");
			e.printStackTrace();
		}
	}

	public LemmingEnvironment getWorld() {
		return world;
	}

	public LemmingEngine getEngine() {
		return engine;
	}

}
