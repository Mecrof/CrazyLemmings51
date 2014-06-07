package environment.exceptions;

public class CellNotFoundException extends Exception {
	
	public CellNotFoundException(int x, int y) {
		super("Cell not found at ("+x+","+y+")");
	}

}
