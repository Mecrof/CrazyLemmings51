package environment.lemming;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Parser that loads the stages
 *
 */
public class StageParser {

	private String stageName;
	private BufferedReader reader;
	
	public StageParser(String name)
	{
		this.stageName = name;
	}
	
	/**
	 * Load the file
	 */
	public void load()
	{
		try 
		{
			this.reader = new BufferedReader(new FileReader(this.stageName));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Close the file
	 */
	public void close()
	{
		try 
		{
			this.reader.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Set the object of the cell
	 * 
	 * @param c   the cell
	 * @param env the current environment
	 */
	public void setCellOjbect(TypeCell c, LemmingEnvironment env)
	{
		try 
		{
			int charRead = this.reader.read();
			
			switch((char) charRead)
			{
				case '\n':
					this.setCellOjbect(c, env);
					break;
				case '\r':
					this.setCellOjbect(c, env);
					break;
				case 'V':
					//nothing to do
					break;
				case 'E':
					env.setStartPosition(c.getPosition());
					break;
				case '$':
					Portal portal = new Portal(c.getPosition(), true);
					c.addWorldObject(portal);
					env.setPortal(portal);
					break;
				case 'G':
					c.setType(Type.CLAY);
					break;
				case 'R':
					c.setType(Type.ROCK);
					break;
				case 'F':
					c.setType(Type.CLAY);
					break;
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
}
