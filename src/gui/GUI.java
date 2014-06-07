package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private final int WIDTH = 816;
	private final int HEIGHT = 639;
	
	private World mainPanel;

	public GUI()
	{
		this.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
		this.mainPanel = new World();
		this.add(this.mainPanel);
	}

	public World WorldPanel() 
	{
		return this.mainPanel;
	}
}
