package gui.sprites;

import gui.GUI;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
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
					tmp = ImageIO.read(this.getClass().getResource("./img/exit.png"));
					this.image = new BufferedImage(30, 20, BufferedImage.TYPE_INT_RGB);
					this.image.getGraphics().drawImage(tmp, 0, 0, null);
					break;
				case GROUND:
					tmp = ImageIO.read(this.getClass().getResource("./img/ground.png"));
					this.image = new BufferedImage((int) GUI.RATIO_X, (int) GUI.RATIO_Y, BufferedImage.TYPE_INT_RGB);
					this.image.getGraphics().drawImage(tmp.getScaledInstance((int) GUI.RATIO_X, (int) GUI.RATIO_Y, BufferedImage.SCALE_SMOOTH), 0, 0, null);
					break;
				case GROUND_UP:
					tmp = ImageIO.read(this.getClass().getResource("./img/ground_up.png"));
					this.image = new BufferedImage((int) GUI.RATIO_X, (int) GUI.RATIO_Y, BufferedImage.TYPE_INT_RGB);
					this.image.getGraphics().drawImage(tmp.getScaledInstance((int) GUI.RATIO_X, (int) GUI.RATIO_Y, BufferedImage.SCALE_SMOOTH), 0, 0, null);
					break;
				case LEMMING:
					tmp = ImageIO.read(this.getClass().getResource("./img/lemming.png"));
					this.image = new BufferedImage(tmp.getWidth(), tmp.getHeight(), BufferedImage.TYPE_INT_RGB);
					this.image.getGraphics().drawImage(tmp, 0, 0, null);
					break;
				case ROCK:
					tmp = ImageIO.read(this.getClass().getResource("./img/rock.png"));
					this.image = new BufferedImage((int) GUI.RATIO_X, (int) GUI.RATIO_Y, BufferedImage.TYPE_INT_RGB);
					this.image.getGraphics().drawImage(tmp.getScaledInstance((int) GUI.RATIO_X, (int) GUI.RATIO_Y, BufferedImage.SCALE_SMOOTH), 0, 0, null);
					break;
				case START:
					tmp = ImageIO.read(this.getClass().getResource("./img/ground.png"));
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
}
