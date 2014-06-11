package gui.sprites;

import gui.GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;


public class Lemmings extends Sprite {

	private BufferedImage left;
	private BufferedImage right;
	private BufferedImage digLeft;
	private BufferedImage digRight;
	private BufferedImage dead;
	
	private Image leftScaled;
	private Image rightScaled;
	private Image digLeftScaled;
	private Image digRightScaled;
	private Image deadScaled;
	
	public Lemmings() 
	{
		super(Type.LEMMING);
		
		this.left = new BufferedImage(Sprite.WIDTH, Sprite.HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.left.getGraphics().drawImage(this.image, 0, 0, 10, 10, 20, 10, 30, 20, null);
		
		this.right = new BufferedImage(Sprite.WIDTH, Sprite.HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.right.getGraphics().drawImage(this.image, 0, 0, 10, 10, 20, 0, 30, 10, null);
		
		this.digRight = new BufferedImage(Sprite.WIDTH, Sprite.HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.digRight.getGraphics().drawImage(this.image, 0, 0, 10, 10, 22, 327, 32, 338, null);
		
		this.digLeft = new BufferedImage(Sprite.WIDTH, Sprite.HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.digLeft.getGraphics().drawImage(this.image, 0, 0, 10, 10, 16, 301, 26, 313, null);
		
		this.dead = new BufferedImage(Sprite.WIDTH, Sprite.HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.dead.getGraphics().drawImage(this.image, 0, 0, 10, 10, 69, 172, 76, 178, null);
		
		this.updateSprite();
	}

	public void paintRight(Graphics g, Point p) 
	{
		g.drawImage(this.rightScaled, (int) (p.x*GUI.RATIO_X), (int) (p.y*GUI.RATIO_Y), null);
	}

	public void paintLeft(Graphics g, Point p) 
	{
		g.drawImage(this.leftScaled, (int) (p.x*GUI.RATIO_X), (int) (p.y*GUI.RATIO_Y), null);
	}

	public void paintDead(Graphics g, Point p) 
	{
		g.drawImage(this.deadScaled, (int) (p.x*GUI.RATIO_X), (int) (p.y*GUI.RATIO_Y), null);
	}

	public void paintDigRight(Graphics g, Point p) 
	{
		g.drawImage(this.digRightScaled, (int) (p.x*GUI.RATIO_X), (int) (p.y*GUI.RATIO_Y), null);
	}
	
	public void paintDigLeft(Graphics g, Point p) 
	{
		g.drawImage(this.digLeftScaled, (int) (p.x*GUI.RATIO_X), (int) (p.y*GUI.RATIO_Y), null);
	}
	
	@Override
	public void paint(Graphics g, Point p)
	{
	}

	@Override
	public void updateSprite() 
	{
		this.leftScaled = this.left.getScaledInstance((int) GUI.RATIO_X, (int) GUI.RATIO_Y, BufferedImage.SCALE_SMOOTH);
		this.rightScaled = this.right.getScaledInstance((int) GUI.RATIO_X, (int) GUI.RATIO_Y, BufferedImage.SCALE_SMOOTH);
		this.digLeftScaled = this.digLeft.getScaledInstance((int) GUI.RATIO_X, (int) GUI.RATIO_Y, BufferedImage.SCALE_SMOOTH);
		this.digRightScaled = this.digRight.getScaledInstance((int) GUI.RATIO_X, (int) GUI.RATIO_Y, BufferedImage.SCALE_SMOOTH);
		this.deadScaled = this.dead.getScaledInstance((int) GUI.RATIO_X, (int) GUI.RATIO_Y, BufferedImage.SCALE_SMOOTH);
	}

}
