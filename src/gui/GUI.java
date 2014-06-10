package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	protected final static int WIDTH = 800;
	protected final static int HEIGHT = 600;
	
	private World mainPanel;

	public GUI()
	{
		this.setPreferredSize(new Dimension(WIDTH + 16, HEIGHT + 39));
		this.mainPanel = new World();
		this.add(this.mainPanel);
	}

	public World WorldPanel() 
	{
		return this.mainPanel;
	}
}
