
public class Environment {
	
	private final int width;
	private final int height;
	
	private WorldObject[][] grid;
	
	public Environment(int width, int height) {
		// TODO Auto-generated constructor stub
		this.width = width;
		this.height = height;
		this.grid = new WorldObject[width][height];
		
		build(0,this.width,0,this.height);
	}
	
	private void build(int minX, int maxX, int minY, int maxY){
		for(int i = 0; i<this.width;i++){
			for(int y = 0; y<this.height;y++){
				this.grid[i][y] = new Earth();
			}
		}
		
		
		for(int i = 0; i<this.width;i++){
			this.grid[i][maxY-1] = new Stone();
			this.grid[i][minY] = new Stone();
			this.grid[i][maxY-2] = new Earth();
		}
		for(int i = 0; i<this.height;i++){
			this.grid[maxX-1][i] = new Stone();
			this.grid[minX][i] = new Stone();
		}
		this.grid[5][5] = new Portal();
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public synchronized boolean isAir(int x, int y){
		return (x>=0 && y>=0 && x<this.width && y<this.height && (this.grid[x][y] instanceof Air));
	}
	
	public synchronized boolean isEarth(int x, int y){
		return (x>=0 && y>=0 && x<this.width && y<this.height && (this.grid[x][y] instanceof Earth));
	}
	
	public synchronized boolean isStone(int x, int y){
		return (x>=0 && y>=0 && x<this.width && y<this.height && (this.grid[x][y] instanceof Stone));
	}
	
	public synchronized boolean isPortal(int x, int y){
		return (x>=0 && y>=0 && x<this.width && y<this.height && (this.grid[x][y] instanceof Portal));
	}
	
	public void setStone(int x, int y){
		this.grid[x][y] = new Stone();
	}
	
	public void setEarth(int x, int y){
		this.grid[x][y] = new Earth();
	}

	public void setAir(int x, int y){
		this.grid[x][y] = new Air();
	}
}
