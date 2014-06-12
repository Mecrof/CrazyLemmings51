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
		
		Image image = Transparency.makeColorTransparent(this.image);
		
		this.left = new BufferedImage((int) GUI.RATIO_X, (int) GUI.RATIO_Y, BufferedImage.TYPE_INT_ARGB);
		this.left.getGraphics().drawImage(image, 0, 0, 10, 10, 20, 10, 30, 20, null);
		
		this.right = new BufferedImage((int) GUI.RATIO_X, (int) GUI.RATIO_Y, BufferedImage.TYPE_INT_ARGB);
		this.right.getGraphics().drawImage(image, 0, 0, 10, 10, 20, 0, 30, 10, null);
		
		this.digRight = new BufferedImage((int) GUI.RATIO_X, (int) GUI.RATIO_Y, BufferedImage.TYPE_INT_ARGB);
		this.digRight.getGraphics().drawImage(image, 0, 0, 10, 10, 22, 327, 32, 338, null);
		
		this.digLeft = new BufferedImage((int) GUI.RATIO_X, (int) GUI.RATIO_Y, BufferedImage.TYPE_INT_ARGB);
		this.digLeft.getGraphics().drawImage(image, 0, 0, 10, 10, 16, 301, 26, 313, null);
		
		this.dead = new BufferedImage((int) GUI.RATIO_X, (int) GUI.RATIO_Y, BufferedImage.TYPE_INT_ARGB);
		this.dead.getGraphics().drawImage(image, 0, 0, 10, 10, 69, 172, 76, 178, null);
		
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
	
	private static class Transparency 
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
