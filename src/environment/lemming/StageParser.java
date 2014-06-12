package environment.lemming;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import environment.Cell;

public class StageParser {

	private String stageName;
	private BufferedReader reader;
	
	public StageParser(String name)
	{
		this.stageName = name;
	}
	
	public void load()
	{
		try 
		{
			this.reader = new BufferedReader(new FileReader(this.getClass().getResource(this.stageName).getPath()));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
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
	
	public void setCellOjbect(TypeCell c, LemmingEnvironment env)
	{
		try 
		{
			int charRead = this.reader.read();
			
			switch((char) charRead)
			{
				case '\n':
					this.setCellOjbect(c,env);
					break;
				case '\r':
					this.setCellOjbect(c,env);
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
