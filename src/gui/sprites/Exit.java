package gui.sprites;

import gui.GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Exit extends Sprite {

	private Point position;
	private Image scaledImage;
	
	public Exit() 
	{
		super(Type.EXIT);
		
		this.position = new Point();
		
		try 
		{
			this.image = ImageIO.read(this.getClass().getResource("/exit.png"));
			Image tmp = Transparency.makeColorTransparent(this.image.getSubimage(0, 0, this.image.getWidth(), this.image.getHeight()));
			this.image = new BufferedImage(tmp.getWidth(null), tmp.getHeight(null), BufferedImage.TYPE_INT_ARGB);
			this.image.getGraphics().drawImage(tmp, 0, 0, null);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
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
