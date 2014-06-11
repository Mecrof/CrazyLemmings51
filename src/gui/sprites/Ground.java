package gui.sprites;

import gui.GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Ground extends Sprite {

	private Image scaledImage;
		
	public Ground() 
	{
		super(Type.GROUND);
		
		this.updateSprite();
	}

	@Override
	public void paint(Graphics g, Point p)
	{
		g.drawImage(this.scaledImage, (int) (p.x*GUI.RATIO_X), (int) (p.y*GUI.RATIO_Y), null);
	}

	@Override
	public void updateSprite() 
	{
		this.scaledImage = this.image.getScaledInstance((int) GUI.RATIO_X, (int) GUI.RATIO_Y, BufferedImage.SCALE_SMOOTH);
	}

}
