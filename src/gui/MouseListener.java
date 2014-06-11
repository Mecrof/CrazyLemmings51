package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import environment.engine.LemmingEngine;
import environment.exceptions.CellNotFoundException;
import environment.lemming.LemmingEnvironment;
import environment.lemming.Type;
import environment.lemming.TypeCell;

public class MouseListener extends MouseAdapter {

	private LemmingEnvironment environment;
	private Point oldPosition;
	
	public MouseListener(LemmingEnvironment e)
	{
		this.environment = e;
		this.oldPosition = new Point();
	}
	
	@Override
	public void mouseMoved(MouseEvent e) 
	{
		if(!LemmingEngine.LOCK.isLocked())
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
			LemmingEngine.LOCK.setLocked(!LemmingEngine.LOCK.isLocked());
			if(!LemmingEngine.LOCK.isLocked())
				LemmingEngine.LOCK.notifyUnlocked();
			return;
		}
		else if((SwingUtilities.isLeftMouseButton(e) || SwingUtilities.isMiddleMouseButton(e)) && !LemmingEngine.LOCK.isLocked())
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
		
		if(cell.getType() == Type.EMTPY && !cell.getIterator().hasNext() && SwingUtilities.isLeftMouseButton(e))
		{
			cell.setType(Type.ROCK);
		}
		else if(cell.getType() != Type.EMTPY && SwingUtilities.isMiddleMouseButton(e))
		{
			cell.setType(Type.EMTPY);
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
}
