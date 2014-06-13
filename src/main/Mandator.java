package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import qlearning.LearningLemmingAgent;
import environment.DefaultFrustrum;
import environment.engine.LemmingEngine;
import environment.exceptions.CellNotFoundException;
import environment.lemming.LemmingEnvironment;

/**
 * Object managing the lab work and the main engine
 * 
 */
public class Mandator {
	
	//current lemming environmnet
	private LemmingEnvironment world;
	//currnet lemming engine
	private LemmingEngine engine;
	
	//sizae of a lab world
	private final int LAB_STAGE_SIZE = 8;
	
	//path to the lab file
	private LinkedList<String> labsPathName;
	
	//number of iterations in a lab world
	private int nbIterationPerLab = 100;
	//number of labs to run
	private int nbMaxLab = 5;
	//number of step during an iteration of the lab world
	private int nbStepMaxInIteration = 70;
	
	//the agent learning
	private LearningLemmingAgent agentLab;
	//the frame rate of the engine
	private int frameRate;
	
	//the executor managing th engine thread
	private Executor singleThreadExecutor = Executors.newSingleThreadExecutor();
	
	public Mandator(int fps) 
	{
		this.frameRate = fps;
	}
		
	/**
	 * Run a lab learning simulation
	 */
	public void runLab()
	{
		DefaultFrustrum frustrum = new DefaultFrustrum(null);
		agentLab = new LearningLemmingAgent(0, 0, frustrum, true);
		int nbStep;
		int nbLab = (nbMaxLab > labsPathName.size()) ? labsPathName.size() : nbMaxLab;
		
		for(int i = 0; i < nbLab; i++)
		{
			for (int j = 0; j < nbIterationPerLab; j++)
			{
				System.out.println("##"+j);
				
				LemmingEnvironment currentEnvironment = new LemmingEnvironment(LAB_STAGE_SIZE, LAB_STAGE_SIZE, labsPathName.get(i));
				frustrum.setEnvironment(currentEnvironment);
				engine = new LemmingEngine(currentEnvironment, frameRate);
				
				nbStep = 0;
				agentLab.createBody().setDead(false);
				agentLab.createBody().setPosition(currentEnvironment, currentEnvironment.getStartPosition());
		
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
	
	/**
	 * Run the main world
	 */
	public void runInWorld()
	{
		singleThreadExecutor.execute(new Runnable() {
			@Override
			public void run() 
			{
				engine.run();
			}
		});
	}
	
	/**
	 * Load a world file
	 * 
	 * @param stageFile the path to the file
	 */
	public void loadWorld(String stageFile)
	{
		world = new LemmingEnvironment(80, 60, stageFile);
		engine = new LemmingEngine(world, frameRate);
	}
	
	/**
	 * Load the lab worlds
	 * 
	 * @param labStageFile the path to the file containing the name of the worlds
	 */
	public void loadLabs(String labStageFile)
	{
		labsPathName = new LinkedList<String>();
		BufferedReader buffer = null;
		try
		{
			String line;
			String path = labStageFile.substring(0, labStageFile.lastIndexOf(File.separatorChar) + 1);
			buffer = new BufferedReader(new FileReader(labStageFile));
			System.out.println(path);
			while ( (line = buffer.readLine()) != null)
			{
				labsPathName.add(path+line);
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
	
	/**
	 * Add a number of lemmings to the world
	 * 
	 * @param number the number of lemmings to add
	 */
	public synchronized void addLemmingsInWorld(int number)
	{
		synchronized(this.engine.getMutex()) 
		{	
			try 
			{
				LearningLemmingAgent ag;
				for (int i = 0; i < number; i++)
				{
					ag = new LearningLemmingAgent(world.getStartPosition().x, 
						world.getStartPosition().y, 
						new DefaultFrustrum(world),
						false
						);
				
					ag.setqProblem(agentLab.getqProblem());
					world.addWorldObject(ag.createBody());
					engine.enableAgent(ag);
				}
			} 
			catch (CellNotFoundException e) {
				System.out.println("Error during adding new agent lemming in Mandator");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Reset the engine if possible
	 * 
	 * @return true if the engine is reseted
	 */
	public boolean reset()
	{
		if(this.engine.getLock().isLocked())
			return false;
		
		LemmingEngine.exit();
		return true;
	}
	public LemmingEnvironment getWorld() 
	{
		return world;
	}

	public LemmingEngine getEngine() 
	{
		return engine;
	}
	
	public void setNbIterationPerLab(int nbIterationPerLab) 
	{
		this.nbIterationPerLab = nbIterationPerLab;
	}
	
	public void setNbMaxLab(int nbMaxLab)
	{
		this.nbMaxLab = nbMaxLab;
	}

	public void setNbStepMaxInIteration(int nbStepMaxInIteration)
	{
		this.nbStepMaxInIteration = nbStepMaxInIteration;
	}

}
