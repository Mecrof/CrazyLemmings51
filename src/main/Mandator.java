package main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

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
	private LinkedList<String> labsPathName;
	private int nbIterationPerLab = 100;

	private int nbMaxLab = 5;
	private int nbStepMaxInIteration = 70;
	private LearningLemmingAgent agentLab;
	private int frameRate;
	private Executor singleThreadExecutor = Executors.newSingleThreadExecutor();
	
	public Mandator(int fps) {
		this.frameRate = fps;
	}
		
	public void runLab()
	{
		DefaultFrustrum frustrum = new DefaultFrustrum(null);
		agentLab = new LearningLemmingAgent(0, 0, frustrum, true);
		int nbStep;
		int nbLab = (nbMaxLab > labsPathName.size()) ? labsPathName.size() : nbMaxLab;
		//agentLab.setLearning(true);
		for(int i = 0; i < nbLab; i++)
		{
			
			for (int j = 0; j < nbIterationPerLab; j++)
			{
				System.out.println("##"+j);
				LemmingEnvironment currentEnvironment = new LemmingEnvironment(LAB_STAGE_SIZE, LAB_STAGE_SIZE, labsPathName.get(i));
				frustrum.setEnvironment(currentEnvironment);
				engine = new LemmingEngine(currentEnvironment, frameRate);
				//System.out.println(currentEnvironment.toString());
				
				nbStep = 0;
				agentLab.createBody().setDead(false);
				agentLab.createBody().setPosition(currentEnvironment, currentEnvironment.getStartPosition());
				//currentEnvironment.addWorldObject(agentLab.createBody());
				engine.enableAgent(agentLab);
				agentLab.ressucite();
				while (nbStep < nbStepMaxInIteration && !agentLab.isKilled())
				{
					//System.out.println(currentEnvironment.toString());
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
		singleThreadExecutor.execute(new Runnable() {
			@Override
			public void run() 
			{
				engine.run();
			}
		});

		/*
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
	
	public synchronized void addLemmingsInWorld(int number)
	{
		synchronized (this.engine.getMutex()) {
			
		
		// TODO: clone memory of agentLab in the new agent
		try {
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
		} catch (CellNotFoundException e) {
			System.out.println("Error during adding new agent lemming in Mandator");
			e.printStackTrace();
		}
		}
	}

	public LemmingEnvironment getWorld() {
		return world;
	}

	public LemmingEngine getEngine() {
		return engine;
	}

	public boolean reset()
	{
		if(this.engine.getLock().isLocked())
			return false;
		
		LemmingEngine.exit();
		return true;
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
