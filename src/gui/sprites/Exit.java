package gui.sprites;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Exit extends Sprite {

	private BufferedImage topLeft;
	private BufferedImage topMiddle;
	private BufferedImage topRight;
	private BufferedImage bottomLeft;
	private BufferedImage bottomMiddle;
	private BufferedImage bottomRight;
	
	private int count;
	
	public Exit() {
		super(Type.EXIT);
		
		this.topLeft = new BufferedImage(Sprite.WIDTH, Sprite.HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.topLeft.getGraphics().drawImage(this.image, 0, 0, 10, 10, 0, 0, 10, 10, null);
		
		this.topMiddle = new BufferedImage(Sprite.WIDTH, Sprite.HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.topMiddle.getGraphics().drawImage(this.image, 0, 0, 10, 10, 10, 0, 20, 10, null);
		
		this.topRight = new BufferedImage(Sprite.WIDTH, Sprite.HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.topRight.getGraphics().drawImage(this.image, 0, 0, 10, 10, 20, 0, 30, 10, null);
		
		this.bottomLeft = new BufferedImage(Sprite.WIDTH, Sprite.HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.bottomLeft.getGraphics().drawImage(this.image, 0, 0, 10, 10, 0, 10, 10, 20, null);
		
		this.bottomMiddle = new BufferedImage(Sprite.WIDTH, Sprite.HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.bottomMiddle.getGraphics().drawImage(this.image, 0, 0, 10, 10, 10, 10, 20, 20, null);
		
		this.bottomRight = new BufferedImage(Sprite.WIDTH, Sprite.HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.bottomRight.getGraphics().drawImage(this.image, 0, 0, 10, 10, 20, 10, 30, 20, null);
		
		this.count = 0;
	}

	@Override
	public void paint(Graphics g, Point p)
	{
		System.out.println(this.count);
		
		switch(this.count)
		{
			case 0:
				g.drawImage(this.topLeft, p.x, p.y, null);
				break;
			case 1:
				g.drawImage(this.bottomLeft, p.x, p.y, null);
				break;
			case 2:
				g.drawImage(this.topMiddle, p.x, p.y, null);
				break;
			case 3:
				g.drawImage(this.bottomMiddle, p.x, p.y, null);
				break;
			case 4:
				g.drawImage(this.topRight, p.x, p.y, null);
				break;
			case 5:
				g.drawImage(this.bottomRight, p.x, p.y, null);
				break;
		}
		
		count++;
		
		if(count == 6)
			count = 0;
	}
}
