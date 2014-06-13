package main;

import javax.swing.UIManager;

import gui.GUI;
import gui.MouseListener;

public class Main {

	public static void main(String[] args) {
		
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (Exception e)
	    {
	       //never reach
	    }
	
		//create the mandator
		Mandator mandator = new Mandator(10);

		//create the GUI
		GUI gui = new GUI();
		gui.setMandator(mandator);
		gui.setMouseListener(new MouseListener());

		gui.pack();
		gui.setVisible(true);
	}

}
