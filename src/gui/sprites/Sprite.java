package gui.sprites;

import gui.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class representing a sprite
 * 
 * 
 */
public abstract class Sprite {

	protected BufferedImage image;
	
	public Sprite(Type type)
	{
		try 
		{
			BufferedImage tmp = null;

			switch(type)
			{
				case EXIT:
					tmp = ImageIO.read(this.getClass().getResource("/exit.png"));
					this.image = new BufferedImage(30, 20, BufferedImage.TYPE_INT_RGB);
					this.image.getGraphics().drawImage(tmp, 0, 0, null);
					break;
				case GROUND:
					tmp = ImageIO.read(this.getClass().getResource("/ground.png"));
					this.image = new BufferedImage((int) GUI.RATIO_X, (int) GUI.RATIO_Y, BufferedImage.TYPE_INT_RGB);
					this.image.getGraphics().drawImage(tmp.getScaledInstance((int) GUI.RATIO_X, (int) GUI.RATIO_Y, BufferedImage.SCALE_SMOOTH), 0, 0, null);
					break;
				case GROUND_UP:
					tmp = ImageIO.read(this.getClass().getResource("/ground_up.png"));
					this.image = new BufferedImage((int) GUI.RATIO_X, (int) GUI.RATIO_Y, BufferedImage.TYPE_INT_RGB);
					this.image.getGraphics().drawImage(tmp.getScaledInstance((int) GUI.RATIO_X, (int) GUI.RATIO_Y, BufferedImage.SCALE_SMOOTH), 0, 0, null);
					break;
				case LEMMING:
					tmp = ImageIO.read(this.getClass().getResource("/lemming.png"));
					this.image = new BufferedImage(tmp.getWidth(), tmp.getHeight(), BufferedImage.TYPE_INT_RGB);
					this.image.getGraphics().drawImage(tmp, 0, 0, null);
					break;
				case ROCK:
					tmp = ImageIO.read(this.getClass().getResource("/rock.png"));
					this.image = new BufferedImage((int) GUI.RATIO_X, (int) GUI.RATIO_Y, BufferedImage.TYPE_INT_RGB);
					this.image.getGraphics().drawImage(tmp.getScaledInstance((int) GUI.RATIO_X, (int) GUI.RATIO_Y, BufferedImage.SCALE_SMOOTH), 0, 0, null);
					break;
				default:
					break;
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Paint the sprite
	 * 
	 * @param g the graphics used to paint the sprite
	 * @param p the position of the sprite
	 */
	public abstract void paint(Graphics g, Point p);
	
	/**
	 * Update the sprite
	 */
	public abstract void updateSprite();
	
	protected static class Transparency 
	{
		public static Image makeColorTransparent(BufferedImage image)
		{
			ImageFilter filter = new RGBImageFilter() {
		    	private int markerRGB = Color.BLACK.getRGB() | 0xFF000000;

		    	@Override
		    	public final int filterRGB(int x, int y, int rgb) 
		    	{
		    		if((rgb | 0xFF000000 ) == markerRGB ) 
		    			return 0x00FFFFFF & rgb;
		    		else 
		    			return rgb;
		    	}
		    }; 

		    ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
		    return Toolkit.getDefaultToolkit().createImage(ip);
		}
	}
}
