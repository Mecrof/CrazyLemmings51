package gui.sprites;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public abstract class Sprite {

	public static final int WIDTH = 10;
	public static final int HEIGHT = 10;
	
	protected BufferedImage image;
	
	public Sprite(Type type)
	{
		try 
		{
			BufferedImage tmp = null;

			switch(type)
			{
				case EXIT:
					tmp = ImageIO.read(this.getClass().getResource("../../img/exit.png"));
					this.image = new BufferedImage(30, 20, BufferedImage.TYPE_INT_RGB);
					this.image.getGraphics().drawImage(tmp, 0, 0, null);
					break;
				case GROUND:
					tmp = ImageIO.read(this.getClass().getResource("../../img/ground.png"));
					this.image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
					this.image.getGraphics().drawImage(tmp.getScaledInstance(WIDTH, HEIGHT, BufferedImage.SCALE_SMOOTH), 0, 0, null);
					break;
				case GROUND_UP:
					tmp = ImageIO.read(this.getClass().getResource("../../img/ground_up.png"));
					this.image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
					this.image.getGraphics().drawImage(tmp.getScaledInstance(WIDTH, HEIGHT, BufferedImage.SCALE_SMOOTH), 0, 0, null);
					break;
				case LEMMING:
					tmp = ImageIO.read(this.getClass().getResource("../../img/lemming.png"));
					this.image = new BufferedImage(tmp.getWidth(), tmp.getHeight(), BufferedImage.TYPE_INT_RGB);
					this.image.getGraphics().drawImage(tmp, 0, 0, null);
					break;
				case ROCK:
					tmp = ImageIO.read(this.getClass().getResource("../../img/rock.png"));
					this.image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
					this.image.getGraphics().drawImage(tmp.getScaledInstance(WIDTH, HEIGHT, BufferedImage.SCALE_SMOOTH), 0, 0, null);
					break;
				case START:
					tmp = ImageIO.read(this.getClass().getResource("../../img/ground.png"));
					this.image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
					this.image.getGraphics().drawImage(tmp.getScaledInstance(WIDTH, HEIGHT, BufferedImage.SCALE_SMOOTH), 0, 0, null);
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
	
	public abstract void paint(Graphics g, Point p);
	
	public abstract void updateSprite();
}
