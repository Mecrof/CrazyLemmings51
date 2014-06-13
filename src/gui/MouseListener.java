package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import environment.engine.Lock;
import environment.exceptions.CellNotFoundException;
import environment.lemming.LemmingEnvironment;
import environment.lemming.Type;
import environment.lemming.TypeCell;

/**
 * Mouse listener for the main panel allowing the user
 * to add/remove blocks in real time
 * 
 *
 */
public class MouseListener extends MouseAdapter {

	private LemmingEnvironment environment;
	private Point oldPosition;
	private Lock lock;
	private Type typeBlock;
	
	public MouseListener()
	{
		this.oldPosition = new Point();
		this.typeBlock = Type.ROCK;
	}
	
	/**
	 * Set the environment
	 * 
	 * @param e the environment
	 */
	public void setWorld(LemmingEnvironment e)
	{
		this.environment = e;
	}
	
	/**
	 * Set the lock from the engine
	 * 
	 * @param lock
	 */
	public void setLock(Lock lock)
	{
		this.lock = lock;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) 
	{
		if(this.lock == null || !this.lock.isLocked() || this.environment == null)
			return;
		
		World world = (World) e.getSource();
		
		final Graphics g = world.getGraphics();
		final Point position = e.getPoint();
		
		position.x /= GUI.RATIO_X;
		position.y /= GUI.RATIO_Y;
		
		g.setColor(Color.RED);
		g.drawRect((int) (position.x*GUI.RATIO_X), (int) (position.y*GUI.RATIO_Y), (int) GUI.RATIO_X, (int) GUI.RATIO_Y);
		
		if(oldPosition.x != position.x || oldPosition.y != position.y)
		{
			world.repaint();
			oldPosition = position;
			
			SwingUtilities.invokeLater(new Runnable()
			{
				@Override
				public void run()
				{
					g.drawRect((int) (position.x*GUI.RATIO_X), (int) (position.y*GUI.RATIO_Y), (int) GUI.RATIO_X, (int) GUI.RATIO_Y);
				}
				
			});
			
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if(SwingUtilities.isRightMouseButton(e))
		{
			this.lock.setLocked(!this.lock.isLocked());
			
			if(!this.lock.isLocked())
			{
				((World) e.getSource()).setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				this.lock.notifyUnlocked();
				((World) e.getSource()).setEditionMode(false);
			}
			else
			{
				((World) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
				((World) e.getSource()).setEditionMode(true);
			}
			
			return;
		}
		else if((SwingUtilities.isLeftMouseButton(e) || SwingUtilities.isMiddleMouseButton(e)) && !this.lock.isLocked())
			return;
		
		World world = (World) e.getSource();
		final Point position = e.getPoint();
		final Graphics g = world.getGraphics();
		
		position.x /= GUI.RATIO_X;
		position.y /= GUI.RATIO_Y;
		
		TypeCell cell = null;
		try 
		{
			cell = this.environment.getCellAt(position);
		}
		catch (CellNotFoundException e1)
		{
			return;
		}
		
		if(cell.getType() == Type.EMPTY && !cell.getIterator().hasNext() && SwingUtilities.isLeftMouseButton(e))
		{
			switch(this.typeBlock)
			{
				case ROCK:
					cell.setType(Type.ROCK);
					break;
				case CLAY:
					cell.setType(Type.CLAY);
					break;
				default:
					break;
			}
			
		}
		else if(cell.getType() != Type.EMPTY && SwingUtilities.isMiddleMouseButton(e))
		{
			cell.setType(Type.EMPTY);
		}
		
		world.repaint();
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				g.setColor(Color.RED);
				g.drawRect((int) (position.x*GUI.RATIO_X), (int) (position.y*GUI.RATIO_Y), (int) GUI.RATIO_X, (int) GUI.RATIO_Y);
			}
		});
	}

	/**
	 * Set the block type selected by the user
	 * 
	 * @param type the type selected
	 */
	public void setBlockType(Type type) 
	{
		this.typeBlock = type;
	}
}
