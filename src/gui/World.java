package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JPanel;

import environment.Cell;
import environment.EnvironmentState;
import environment.WorldObject;
import environment.exceptions.CellNotFoundException;
import environment.lemming.Lemming;
import environment.lemming.Portal;
import environment.lemming.Type;
import environment.lemming.TypeCell;
import listener.EnvironmentEvent;
import listener.EnvironmentListener;
import gui.sprites.Exit;
import gui.sprites.Ground;
import gui.sprites.GroundUp;
import gui.sprites.Lemmings;
import gui.sprites.Rock;
import gui.sprites.Start;

public class World extends JPanel implements EnvironmentListener {

	private static final long serialVersionUID = 6772576164000906224L;
	
	private final Lemmings lemming;
	private final Rock rock;
	private final Ground ground;
	private final GroundUp groundUp;
	private final Exit exit;
	
	private EnvironmentState<TypeCell> state;
	
	public World()
	{
		this.lemming = new Lemmings();
		this.rock = new Rock();
		this.ground = new Ground();
		this.groundUp = new GroundUp();
		this.exit = new Exit();
		this.setBackground(Color.BLACK);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		if(this.state == null)
			return;
		
		LinkedList<TypeCell> cells = this.state.getCurrentState();
		Iterator<WorldObject> iterator;
		WorldObject object;
		Point p;
		Point portalPosition = null;
		
		for(TypeCell cell : cells)
		{
			switch (cell.getType()) {
				case CLAY:
					p = cell.getPosition();
					try
					{
						if(cells.get(GUI.WIDTH/10*(p.y-1)+p.x).getType() == Type.EMTPY)
							this.groundUp.paint(g, cell.getPosition());
						else
							this.ground.paint(g, cell.getPosition());
					}
					catch(Exception e)
					{
						//out of bounds
					}
					break;
				case ROCK:
					this.rock.paint(g, cell.getPosition());
					break;
				case EMTPY:
					iterator = cell.getIterator();
					
					if(!iterator.hasNext())
						break;
					
					object = iterator.next();
					
					if(object instanceof Lemming)
					{
						if(((Lemming) object).isDead())
							this.lemming.paintDead(g, cell.getPosition());
						else if(((Lemming) object).getDirection() == Lemming.RIGHT)
						{
							 if(((Lemming) object).isDigging())
								this.lemming.paintDigRight(g, cell.getPosition());
							 else
								this.lemming.paintRight(g, cell.getPosition());
						}
						else if(((Lemming) object).getDirection() == Lemming.LEFT)
						{
							if(((Lemming) object).isDigging())
								this.lemming.paintDigLeft(g, cell.getPosition());
							else
								this.lemming.paintLeft(g, cell.getPosition());
						}
					}
					else if(object instanceof Portal)
					{
						portalPosition = cell.getPosition();
					}
					break;
				default:
					break;
			}
		}
		
		if(portalPosition != null)
		{
			this.exit.paint(g, portalPosition);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onEnvironmentChanged(EnvironmentEvent event)
	{
		this.state = event.getEnvironmentState();
		this.repaint();
	}
}
