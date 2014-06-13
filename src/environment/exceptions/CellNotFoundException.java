package environment.exceptions;

public class CellNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CellNotFoundException(int x, int y) {
		super("Cell not found at ("+x+","+y+")");
	}

}
