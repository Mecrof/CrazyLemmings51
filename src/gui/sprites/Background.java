package gui.sprites;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Background extends Sprite {

	private BufferedImage image;
	
	public Background()
	{
		super(Type.VOID);
		
		try
		{
			this.image = ImageIO.read(this.getClass().getResource("/background.jpeg"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void paint(Graphics g, Point p) 
	{
		g.drawImage(this.image, 0, 0, p.x, p.y, null);
	}

	@Override
	public void updateSprite() 
	{
		//nothing to do
	}
}
