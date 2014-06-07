package environment.engine;

import java.util.Iterator;
import java.util.LinkedList;

import qlearning.Agent;
import environment.Environment;
import environment.Influence;
import environment.lemming.Lemming;

public class LemmingEngine implements Engine {
	
	private int timePerFrame;
	private Environment environment;
	private LinkedList<Lemming> lemmings;
	private LinkedList<Agent<Lemming>> agents;
	private boolean ended;
	
	public LemmingEngine(Environment e, int tpf) {
		this.environment = e;
		this.timePerFrame = tpf;
		lemmings = new LinkedList<Lemming>();
		agents = new LinkedList<Agent<Lemming>>();
		
	}

	@Override
	public void initialize() {
		Iterator<Agent<Lemming>> it = agents.iterator();
		lemmings = new LinkedList<Lemming>();
		while(it.hasNext())
		{
			lemmings.add(it.next().createBody());
		}
		this.ended = false;
	}

	@Override
	public void run() {
		while(!ended)
		{
			try {
				Thread.sleep(timePerFrame);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Iterator<Agent<Lemming>> it = agents.iterator();
			while(it.hasNext())
			{
				it.next().live();
			}
			Iterator<Lemming> itLem = lemmings.iterator();
			while(itLem.hasNext())
			{
				Lemming lemming = itLem.next();
				Influence influence = lemming.getInfluence();
				// TODO APPLY INFLUENCE & SEND REWARD TO AGENT
			}
		}
	}
	
	public boolean enableAgent(Agent<Lemming> ag)
	{
		if (agents.add(ag))
		{
			lemmings.add(ag.createBody());
			return true;
		}
		return false;
	}

}
