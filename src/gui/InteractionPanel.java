package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import environment.lemming.Type;
import main.Mandator;

public class InteractionPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JButton run;
	private JButton learn;
	private JButton addLemming;
	private JButton getLab;
	private JButton getStage;
	private JButton reset;
	
	private JTextField setStage;
	private JTextField setLab;
	private JTextField setIteNumber;
	private JTextField setLabNumber;
	private JTextField setItPerLabNumber;
	private JTextField setLemmingsNumber;
	
	private Mandator mandator;
	
	private JRadioButton setBlock;
	private JRadioButton setClay;
	
	private GUI gui;
	
	public InteractionPanel(GUI gui)
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.setPreferredSize(new Dimension(300, 700));
	
		this.gui = gui;
		
		this.run = new JButton("Run");
		this.learn = new JButton("Learn");
		this.addLemming = new JButton("Add lemming");
		this.getLab = new JButton("Load lab");
		this.getStage = new JButton("Load stage");
		this.addLemming = new JButton("Add lemming");
		this.reset = new JButton("Reset");
		
		this.setStage = new JTextField(15);
		this.setLab = new JTextField(15);
		this.setIteNumber = new JTextField(15);
		this.setLabNumber = new JTextField(15);
		this.setItPerLabNumber = new JTextField(15);
		this.setLemmingsNumber = new JTextField(15);
		
		this.setBlock = new JRadioButton("Rock");
		this.setClay = new JRadioButton("Ground");
		
		this.getLab.addActionListener(this);
		this.getStage.addActionListener(this);
		this.learn.addActionListener(this);
		this.run.addActionListener(this);
		this.addLemming.addActionListener(this);
		this.reset.addActionListener(this);
		this.setBlock.addActionListener(this);
		this.setClay.addActionListener(this);
		
		this.setBlock.setSelected(true);
		this.run.setEnabled(false);
		this.reset.setEnabled(false);
		this.addLemming.setEnabled(false);
		
		//lab work
		JPanel labPanel = new JPanel();
		labPanel.setLayout(new SpringLayout());
		
		JLabel iterationNumber = new JLabel("# of iterations : ");
		JLabel labNumber = new JLabel("# of labs : ");
		JLabel itPerLabNumber = new JLabel("# of it. per lab : ");
		
		iterationNumber.setLabelFor(this.setIteNumber);
		labNumber.setLabelFor(this.setLabNumber);
		itPerLabNumber.setLabelFor(this.setItPerLabNumber);

		labPanel.add(this.getLab);
		labPanel.add(this.setLab);
		labPanel.add(iterationNumber);
		labPanel.add(this.setIteNumber);
		labPanel.add(labNumber);
		labPanel.add(this.setLabNumber);
		labPanel.add(itPerLabNumber);
		labPanel.add(this.setItPerLabNumber);
		labPanel.add(new JComponent(){});
		labPanel.add(this.learn);
		
		SpringUtilities.makeCompactGrid(labPanel,
                5, 2,
                6, 6,   
                6, 6);
		
		//simulation
		JPanel simPanel = new JPanel();
		simPanel.setLayout(new SpringLayout());
		simPanel.add(this.getStage);
		simPanel.add(this.setStage);
		simPanel.add(this.addLemming);
		simPanel.add(this.setLemmingsNumber);
		simPanel.add(new JComponent(){});
		simPanel.add(this.run);
		simPanel.add(new JComponent(){});
		simPanel.add(this.reset);
		
		SpringUtilities.makeCompactGrid(simPanel,
                4, 2, 
                6, 6,       
                6, 6);       
		
		//select sprite type
		ButtonGroup  group = new ButtonGroup ();
		group.add(this.setBlock);
		group.add(this.setClay);
		
		JPanel choicePanel = new JPanel();
		choicePanel.setLayout(new SpringLayout());
		choicePanel.add(this.setBlock);
		choicePanel.add(new JComponent(){});
		choicePanel.add(this.setClay);
		choicePanel.add(new JComponent(){});
		
		SpringUtilities.makeCompactGrid(choicePanel,
                2, 2, 
                6, 6,       
                6, 6);       
		
		this.add(Box.createVerticalStrut(5));
		this.add(new JLabel("Lab work"));
		this.add(labPanel);
		this.add(Box.createVerticalStrut(30));
		this.add(new JLabel("Simulation"));
		this.add(simPanel);
		this.add(Box.createVerticalStrut(30));
		this.add(new JLabel("Edit mode"));
		this.add(choicePanel);
		this.add(Box.createVerticalStrut(Short.MAX_VALUE));
		
	}
	
	public void setLabPath()
	{
		
	}
	
	public void setStagePath()
	{
		
	}
	public Dimension getPreferredSize()
	{
		Dimension dim = super.getPreferredSize();
		dim.width = 300;
		
		return dim;
	}
	public Dimension getMinimumSize()
	{
		Dimension dim = super.getMinimumSize();
		dim.width = 300;
		
		return dim;
	}
	
	public Dimension getMaximumSize()
	{
		Dimension dim = super.getMaximumSize();
		dim.width = 300;
		
		return dim;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() instanceof JButton)
		{
			JButton button = (JButton) e.getSource();
			final JFileChooser fileChooser = new JFileChooser();
			
			if(button == this.getLab)
			{
				int returnVal = fileChooser.showOpenDialog(this.getParent());
				
				if(returnVal == JFileChooser.APPROVE_OPTION)
					this.setLab.setText(fileChooser.getSelectedFile().getPath());
			}
			else if(button == this.getStage)
			{
				int returnVal = fileChooser.showOpenDialog(this.getParent());
				
				if(returnVal == JFileChooser.APPROVE_OPTION)
					this.setStage.setText(fileChooser.getSelectedFile().getPath());
			}
			else if(button == this.learn)
			{
				int setIteNumber = -1;
				int setLabNumber = -1;
				int setItPerLabNumber = -1; 
				
				try
				{
					setIteNumber = Integer.parseInt(this.setIteNumber.getText());
					this.mandator.setNbIterationPerLab(setIteNumber);
				}
				catch(Exception ex)
				{
				}
				
				try
				{
					setLabNumber = Integer.parseInt(this.setLabNumber.getText());
					this.mandator.setNbMaxLab(setLabNumber);
				}
				catch(Exception ex)
				{
				}
				
				try
				{
					setItPerLabNumber = Integer.parseInt(this.setItPerLabNumber.getText());
					this.mandator.setNbStepMaxInIteration(setItPerLabNumber);
				}
				catch(Exception ex)
				{
				}
				
				this.mandator.loadLabs(this.setLab.getText());
				this.mandator.runLab();
				
				this.run.setEnabled(true);
			}
			else if(button == this.run)
			{
				this.mandator.loadWorld(this.setStage.getText());
				
				this.mandator.getWorld().addListener(this.gui.WorldPanel());
				this.gui.setMandatorListenerData(this.mandator);
				this.mandator.getEngine().initialize();
				
				this.mandator.runInWorld();
				
				this.addLemming.setEnabled(true);
				this.reset.setEnabled(true);
				this.run.setEnabled(false);
			}
			else if(button == this.addLemming)
			{
				try
				{
					int value = Integer.parseInt(this.setLemmingsNumber.getText());
					
					this.mandator.addLemmingsInWorld(value);
				}
				catch(Exception ex)
				{
				}
			}
			else if(button == this.reset)
			{
				this.mandator.reset();
				this.reset.setEnabled(false);
				this.run.setEnabled(true);
				this.run.doClick();
			}
		}
		else if(e.getSource() instanceof JRadioButton)
		{
			JRadioButton button = (JRadioButton) e.getSource();
			
			if(button == this.setBlock)
			{
				this.gui.setBlockType(Type.ROCK);
			}
			else if(button == this.setClay)
			{
				this.gui.setBlockType(Type.CLAY);
			}
		}
	}

	public void setMandator(Mandator mandator) 
	{
		this.mandator = mandator;
	}
}
