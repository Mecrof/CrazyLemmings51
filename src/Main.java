import javax.swing.JFrame;


public class Main {
	
	private final Environment environment;
	
	public Main(int width, int height){
		this.environment = new Environment(width, height);
	}
	
	protected JFrame createGUI() {
		return new Grille(this.environment);
	}
	
	public void run(){
		boolean continuer = true;
		JFrame window = createGUI();
		if(window!=null){
			window.setVisible(true);
		}
		
		while(continuer){
			if(window!=null){
				window.repaint();
			}
		}
	}
	
	public static void main(String[] args){
		Main test = new Main(100,50);
		test.run();
		System.exit(0);
	}
	
}
