package gui.sprites;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;


public class Lemmings extends Sprite {

	private BufferedImage left;
	private BufferedImage right;
	private BufferedImage digLeft;
	private BufferedImage digRight;
	private BufferedImage dead;
	
	public Lemmings() 
	{
		super(Type.LEMMING);
		
		this.left = new BufferedImage(Sprite.WIDTH, Sprite.HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.left.getGraphics().drawImage(this.image, 0, 0, 10, 10, 20, 10, 30, 20, null);
		
		this.right = new BufferedImage(Sprite.WIDTH, Sprite.HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.right.getGraphics().drawImage(this.image, 0, 0, 10, 10, 20, 0, 30, 10, null);
		
		this.digLeft = new BufferedImage(Sprite.WIDTH, Sprite.HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.digLeft.getGraphics().drawImage(this.image, 0, 0, 10, 10, 22, 327, 32, 338, null);
		
		this.digRight = new BufferedImage(Sprite.WIDTH, Sprite.HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.digRight.getGraphics().drawImage(this.image, 0, 0, 10, 10, 16, 301, 26, 313, null);
		
		this.dead = new BufferedImage(Sprite.WIDTH, Sprite.HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.dead.getGraphics().drawImage(this.image, 0, 0, 10, 10, 69, 173, 76, 180, null);
	}

	public void paintRight(Graphics g, Point p) 
	{
		g.drawImage(this.right, p.x*10, p.y*10, null);
	}

	public void paintLeft(Graphics g, Point p) 
	{
		g.drawImage(this.left, p.x*10, p.y*10, null);
	}

	public void paintDead(Graphics g, Point p) 
	{
		g.drawImage(this.dead, p.x*10, p.y*10, null);
	}

	public void paintDigRight(Graphics g, Point p) 
	{
		g.drawImage(this.digRight, p.x*10, p.y*10, null);
	}
	
	public void paintDigLeft(Graphics g, Point p) 
	{
		g.drawImage(this.digLeft, p.x*10, p.y*10, null);
	}

}
