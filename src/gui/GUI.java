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

/**
 * Main class for the GUI 
 *
 *
 */
public class GUI extends JFrame implements ComponentListener, WindowListener {

	private static final long serialVersionUID = 1L;
	
	//ratios for scaling sprites
	public static float RATIO_X = 10;
	public static float RATIO_Y = 10;
	
	//panel showing the world
	private World mainPanel;
	//panel showing the buttons and fields
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

	/**
	 * 
	 * @return the main panel
	 */
	public World getWorldPanel() 
	{
		return this.mainPanel;
	}

	/**
	 * Set the mouse listener for the main panel
	 */
	public void setMouseListener(MouseListener mouseListener)
	{
		this.mainPanel.addMouseListener(mouseListener);
		this.mainPanel.addMouseMotionListener(mouseListener);
	}

	/**
	 * Adjust the scaling factors when resizing the window 
	 *
	 */
	@Override
	public void componentResized(ComponentEvent arg0) 
	{
		RATIO_X = (int) ((this.mainPanel.getWidth() - 16) / 80.0f);
		RATIO_Y = (int) ((this.mainPanel.getHeight() - 39) / 60.0f);
		
		this.mainPanel.updateSprites();
	}
	
	/**
	 * Set the mandator in the interaction panel
	 */
	public void setMandator(Mandator mandator) 
	{
		this.intPanel.setMandator(mandator);
	}

	/**
	 * 
	 * @return
	 */
	public void setMandatorListenerData(Mandator mandator) {
		MouseListener listener = (MouseListener) this.mainPanel.getMouseListeners()[0];
		
		listener.setWorld(mandator.getWorld());
		listener.setLock(mandator.getEngine().getLock());
	}

	/**
	 * Set the block type selected by the user
	 */
	public void setBlockType(environment.lemming.Type type) 
	{
		MouseListener listener = (MouseListener) this.mainPanel.getMouseListeners()[0];
		
		listener.setBlockType(type);
	}
	
	@Override
	public void windowClosing(WindowEvent arg0)
	{
		LemmingEngine.exit();
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
}
