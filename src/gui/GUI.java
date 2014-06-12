package gui;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import main.Mandator;
import environment.engine.LemmingEngine;

public class GUI extends JFrame implements ComponentListener, WindowListener {

	private static final long serialVersionUID = 1L;
	
	protected final static int WIDTH = 800;
	protected final static int HEIGHT = 600;
	public static float RATIO_X = 10;
	public static float RATIO_Y = 10;
	
	private World mainPanel;
	private InteractionPanel intPanel;
	
	public GUI()
	{
		this.setPreferredSize(new Dimension(1024, 768));
		this.setMinimumSize(new Dimension(640, 480));
		
		this.mainPanel = new World();
		this.intPanel = new InteractionPanel(this);
		
		this.addComponentListener(this);
		this.addWindowListener(this);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
		
		this.add(this.mainPanel);
		this.add(this.intPanel);
	}

	public World WorldPanel() 
	{
		return this.mainPanel;
	}

	public void setMouseListener(MouseListener mouseListener)
	{
		this.mainPanel.addMouseListener(mouseListener);
		this.mainPanel.addMouseMotionListener(mouseListener);
	}

	@Override
	public void componentResized(ComponentEvent arg0) 
	{
		RATIO_X = (int) ((this.mainPanel.getWidth() - 16) / 80.0f);
		RATIO_Y = (int) ((this.mainPanel.getHeight() - 39) / 60.0f);
		
		this.mainPanel.updateSprites();
	}
	
	@Override
	public void componentHidden(ComponentEvent arg0) 
	{
	}

	@Override
	public void componentMoved(ComponentEvent arg0) 
	{
	}

	@Override
	public void componentShown(ComponentEvent arg0) 
	{		
	}

	@Override
	public void windowActivated(WindowEvent arg0) 
	{
	}

	@Override
	public void windowClosed(WindowEvent arg0) 
	{
		
	}

	@Override
	public void windowClosing(WindowEvent arg0)
	{
		LemmingEngine.exit();
	}

	@Override
	public void windowDeactivated(WindowEvent arg0)
	{
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) 
	{
	}

	@Override
	public void windowIconified(WindowEvent arg0) 
	{
	}

	@Override
	public void windowOpened(WindowEvent arg0) 
	{
	}

	public void setMandator(Mandator mandator) 
	{
		this.intPanel.setMandator(mandator);
	}

	public void setMandatorListenerData(Mandator mandator) {
		MouseListener listener = (MouseListener) this.mainPanel.getMouseListeners()[0];
		
		listener.setWorld(mandator.getWorld());
		listener.setLock(mandator.getEngine().getLock());
	}

	public void setBlockType(environment.lemming.Type type) 
	{
		MouseListener listener = (MouseListener) this.mainPanel.getMouseListeners()[0];
		
		listener.setBlockType(type);
	}
}
