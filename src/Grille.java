import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;


public class Grille extends JFrame {
	
	public static final int Grid_width = 10;
	public static final int Grid_height = 10;
	
	private final Environment environment;
	
	public Grille(Environment env) {
		this.environment = env;
		
		GridPanel gp = new GridPanel();
		gp.setPreferredSize(new Dimension(
				Grid_width*this.environment.getWidth(),
				Grid_height*this.environment.getHeight()));
		
		JScrollPane sc = new JScrollPane(gp);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(BorderLayout.CENTER, sc);
		setPreferredSize(new Dimension(Grid_width*this.environment.getWidth(),
				Grid_height*this.environment.getHeight()));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
	
	private class GridPanel extends JPanel {
		
		public GridPanel() {
			setBackground(Color.WHITE);
		}
		
		public void paint(Graphics g){
			super.paint(g);
			
			int px, py;
			Graphics2D g2d = (Graphics2D)g;
			
			synchronized(Grille.this.environment){
				for(int x=0; x<Grille.this.environment.getWidth();x++){
					for(int y=0; y<Grille.this.environment.getHeight();y++){
						px = Grid_width * x;
						py = Grid_height * y;
						
						if(Grille.this.environment.isAir(x,y)){
							g2d.setColor(Color.WHITE);
							g2d.fillRect(px, py, Grid_width, Grid_height);
						}
						else if(Grille.this.environment.isEarth(x,y)){
								g2d.setColor(Color.ORANGE);
								g2d.fillRect(px, py, Grid_width, Grid_height);
						}
						else if(Grille.this.environment.isStone(x,y)){
							g2d.setColor(Color.GRAY);
							g2d.fillRect(px, py, Grid_width, Grid_height);
						}
						else if(Grille.this.environment.isPortal(x,y)){
							g2d.setColor(Color.PINK);
							g2d.fillOval(px, py, Grid_width, Grid_height);
						}
					}
				}
			}
		}
		
	}
	
}
