package gui;

import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import environment.EnvironmentState;
import environment.WorldObject;
import environment.lemming.TypeCell;
import listener.EnvironmentEvent;
import listener.EnvironmentListener;
import gui.sprites.Exit;
import gui.sprites.Ground;
import gui.sprites.GroundUp;
import gui.sprites.Lemming;
import gui.sprites.Rock;
import gui.sprites.Start;

public class World extends JPanel implements EnvironmentListener {

	private static final long serialVersionUID = 6772576164000906224L;
	
	private final Lemming lemming;
	private final Rock rock;
	private final Ground ground;
	private final GroundUp groundUp;
	private final Start start;
	private final Exit exit;
	
	private EnvironmentState<TypeCell> state;
	
	public World()
	{
		this.lemming = new Lemming();
		this.rock = new Rock();
		this.ground = new Ground();
		this.groundUp = new GroundUp();
		this.start = new Start();
		this.exit = new Exit();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		g.clearRect(0, 0, this.getWidth(), this.getHeight());

		if(this.state == null)
			return;
		
		for(TypeCell cell : this.state.getCurrentState())
		{
		
			switch (cell.getType()) {
				case CLAY:
					this.ground.paint(g, cell.getPosition());
					break;
				case EMTPY:
					break;
				case FLOOR:
					this.groundUp.paint(g, cell.getPosition());
					break;
				case ROCK:
					this.rock.paint(g, cell.getPosition());
					break;
				default:
					break;
			}
		}
	}

	@Override
	public void onEnvironmentChanged(EnvironmentEvent event)
	{
		this.state = event.getEnvironmentState();
		this.repaint();
	}
}
