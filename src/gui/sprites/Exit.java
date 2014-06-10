package gui.sprites;

import java.awt.Graphics;
import java.awt.Point;

public class Exit extends Sprite {

	private Point position;
	
	public Exit() 
	{
		super(Type.EXIT);
		
		this.position = new Point();
	}

	@Override
	public void paint(Graphics g, Point p)
	{
		if(this.position.x == 0)
		{
			this.position.x = p.x - 1;
			this.position.y = p.y - 1;
		}
		
		super.paint(g, this.position);
	}
	
}
