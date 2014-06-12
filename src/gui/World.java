package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JPanel;

import environment.EnvironmentState;
import environment.WorldObject;
import environment.lemming.Lemming;
import environment.lemming.Portal;
import environment.lemming.Type;
import environment.lemming.TypeCell;
import listener.EnvironmentEvent;
import listener.EnvironmentListener;
import gui.sprites.Background;
import gui.sprites.Exit;
import gui.sprites.Ground;
import gui.sprites.GroundUp;
import gui.sprites.Lemmings;
import gui.sprites.Rock;

public class World extends JPanel implements EnvironmentListener {

	private static final long serialVersionUID = 6772576164000906224L;
	
	private final Lemmings lemming;
	private final Rock rock;
	private final Ground ground;
	private final GroundUp groundUp;
	private final Exit exit;
	private final Background background;
	
	private EnvironmentState<TypeCell> state;
	private Point portalPosition;
	
	private BufferedImage world;

	private boolean editionMode;
	
	public World()
	{
		this.setPreferredSize(new Dimension(800 + 16, 600 + 39));
		
		this.lemming = new Lemmings();
		this.rock = new Rock();
		this.ground = new Ground();
		this.groundUp = new GroundUp();
		this.exit = new Exit();
		this.background = new Background();
		
		this.portalPosition = null;
		this.world = null;
	}
	
	@SuppressWarnings("incomplete-switch")
	public void paintWorld(Graphics g)
	{
		if(this.state == null)
			return;
		
		LinkedList<TypeCell> cells = this.state.getCurrentState();
		Iterator<WorldObject> iterator;
		WorldObject object;
		Point p;
		
		this.background.paint(g, new Point((int) (GUI.RATIO_X * 80), (int) (GUI.RATIO_Y * 60)));
		
		if(this.portalPosition == null)
		{
			for(TypeCell cell : cells)
			{
				switch (cell.getType()) 
				{
					case EMPTY:
						iterator = cell.getIterator();
						
						if(!iterator.hasNext())
							break;
	
						while(iterator.hasNext())
						{
							object = iterator.next();
							
							if(object instanceof Portal)
							{
								this.portalPosition = cell.getPosition();
								this.exit.paint(g, this.portalPosition);
								break;
							}
						}
						break;
				}
				
				if(this.portalPosition != null)
					break;
			}
		}
		else
			this.exit.paint(g, this.portalPosition);
		
		for(TypeCell cell : cells)
		{
			switch (cell.getType()) {
				case CLAY:
					p = cell.getPosition();
					try
					{
						if((p.x == this.portalPosition.x - 1 || p.x == this.portalPosition.x || p.x == this.portalPosition.x + 1 ) &&
								p.y == this.portalPosition.y - 1)
							continue;
						
						if(cells.get(GUI.WIDTH/10*(p.y-1)+p.x).getType() == Type.EMPTY)
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
					p = cell.getPosition();
					if((p.x == this.portalPosition.x - 1 || p.x == this.portalPosition.x || p.x == this.portalPosition.x + 1 ) &&
							p.y == this.portalPosition.y - 1)
						continue;
					
					this.rock.paint(g, p);
					break;
				case EMPTY:
					iterator = cell.getIterator();
					
					if(!iterator.hasNext())
						break;
					
					while(iterator.hasNext())
					{
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
							
							break;
						}
					}
					break;
				default:
					break;
			}
		}

		g.setColor(Color.BLACK);
	}

	public void paintComponent(Graphics g)
	{
		if(this.world != null && !this.editionMode)
		{
			g.drawImage(this.world, 0, 0, this.getWidth(), this.getHeight(), null);		
			
			g.setColor(Color.WHITE);
			g.drawString("Morts : " + this.state.getDeadNubmer(), 5, 15);
		}
		else
		{
			g.setColor(Color.BLACK);
			g.fillRect(0,0, this.getWidth(), this.getHeight());
			
			if(this.editionMode)
			{
				this.paintWorld(g);
				g.setColor(Color.WHITE);
				g.drawString("Morts : " + this.state.getDeadNubmer(), 5, 15);
			}
		}	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void onEnvironmentChanged(EnvironmentEvent event)
	{
		this.state = (EnvironmentState<TypeCell>) event.getEnvironmentState();

		if(!this.editionMode)
		{
			this.world = new BufferedImage((int) (GUI.RATIO_X * 80), (int) (GUI.RATIO_Y * 60), BufferedImage.TYPE_INT_RGB); 
			Graphics g2 = this.world.createGraphics();
			this.paintWorld(g2);
		}
		
		this.repaint();
	}

	public Graphics getWorldGraphics()
	{
		return this.world.getGraphics();
	}
	
	public void updateSprites() 
	{
		this.lemming.updateSprite();
		this.rock.updateSprite();
		this.ground.updateSprite();
		this.groundUp.updateSprite();
		this.exit.updateSprite();
	}

	public void setEditionMode(boolean b) 
	{
		this.editionMode = b;
	}
}
