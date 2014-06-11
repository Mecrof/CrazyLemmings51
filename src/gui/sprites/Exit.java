package gui.sprites;

import gui.GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Exit extends Sprite {

	private Point position;
	private Image scaledImage;
	
	public Exit() 
	{
		super(Type.EXIT);
		
		this.position = new Point();
	}

	@Override
	public void paint(Graphics g, Point p)
	{
		if(this.position.x == 0)
		{
			this.position.x = p.x - 1;
			this.position.y = p.y - 1;
		}
		
		g.drawImage(this.scaledImage, (int) (this.position.x*GUI.RATIO_X), (int) (this.position.y*GUI.RATIO_Y), null);
	}

	@Override
	public void updateSprite()
	{
		this.scaledImage = this.image.getScaledInstance((int) (GUI.RATIO_X + GUI.RATIO_X * 2), (int) (GUI.RATIO_Y + GUI.RATIO_Y), BufferedImage.SCALE_SMOOTH);
	}
	
}
