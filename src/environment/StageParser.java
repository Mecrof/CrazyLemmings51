package environment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import environment.lemming.GroundObject;
import environment.lemming.Type;

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
	
	public void setCellOjbect(Cell cell)
	{
		try 
		{
			int charRead = this.reader.read();
			
			switch((char) charRead)
			{
				case '\n':
					this.setCellOjbect(cell);
					break;
				case '\r':
					this.setCellOjbect(cell);
					break;
				case 'V':
					//nothing to do
					break;
				case 'G':
					cell.addWorldObject(new GroundObject(cell.getPosition(), Type.CLAY));
					break;
				case 'R':
					cell.addWorldObject(new GroundObject(cell.getPosition(), Type.ROCK));
					break;
				case 'F':
					cell.addWorldObject(new GroundObject(cell.getPosition(), Type.CLAY));
					break;
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		
	}
}
