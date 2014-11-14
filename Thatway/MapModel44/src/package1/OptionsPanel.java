package package1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;


public class OptionsPanel extends JPanel
{
	private JButton addRampButton = new JButton("Add Ramp");


	///////////////////////////////////////////////////////////////
	private JLabel originTileXLabel = new JLabel("Origin X: ");
	private JTextField originXField = new JTextField("0");
	private JLabel originTileYLabel = new JLabel("Origin Y: ");
	private JTextField originYField = new JTextField("0");
	
	private JLabel destinationTileXLabel = new JLabel("destination X: ");
	private JTextField destinationXField = new JTextField("0");
	private JLabel destinationTileYLabel = new JLabel("destination Y: ");
	private JTextField destinationYField = new JTextField("0");
	
	private JButton stairCaseButton = new JButton("Add staircase");
	
	private JLabel unitSizeLabel = new JLabel("Define Unit Size (0 for 1 tile, 1 for 5 tiles, 2 for 13, etc.) : ");
	private JTextField unitSizeField = new JTextField("0");
	private JButton pathfindingButton = new JButton("Calculate Path");
	private JLabel pathfindingLabel = new JLabel("length of path: ");
	private JLabel pathfindingLengthLabel = new JLabel("(length)");
	///////////////////////////////////////////////////////////////
	
	private JButton makeIntoWaterButton = new JButton("Make selection into water");
	private JButton addWaterButton = new JButton("Add water");
	
	//Ran: I put new level button with tabs
	private JButton newLevelButton = new JButton("Add new level");
	
	String[] optionsPatterns = {"[type height between -100 and 100]" };
	private JComboBox heightToGiveBox = new JComboBox(optionsPatterns); // TODO add to the panel
	private JButton makeIntoThisHeightButton = new JButton("Give Selection this height: ");
	
	private JButton addMovableRockButton = new JButton("Add movable rock");
	private JButton addDestructibleRockButton = new JButton("Add a destructible rock"); // in honor of Dustin Browder 
	
	private JButton spawningPointButton = new JButton("Add a spawning point");
	private JButton unaccessibleTerrainButton = new JButton("Make a tile unaccessible");
	private JButton accessibleTerrainButton = new JButton("Make a tile accessible");
	
	public static JLabel ramplabel; //RX
	public static ImageIcon labelicon = new ImageIcon("src/images/ramp.png","ramp"); //RX
	
	public OptionsPanel()
	{
		super();
		
		
		
		originXField.setPreferredSize(new Dimension(50, 30));
		originYField.setPreferredSize(new Dimension(50, 30));
		destinationXField.setPreferredSize(new Dimension(50, 30));
		destinationYField.setPreferredSize(new Dimension(50, 30));
		unitSizeField.setPreferredSize(new Dimension(50, 30));
		
		setupLayout();
		addListeners();
		
		//originXField.setColumns(20);
		originXField.setMaximumSize(new Dimension(50, 30));
		originYField.setMaximumSize(new Dimension(50, 30));
		destinationXField.setMaximumSize(new Dimension(50, 30));
		destinationYField.setMaximumSize(new Dimension(50, 30));
		unitSizeField.setMaximumSize(new Dimension(50, 30));

	}
	
	public void addListeners() 
	{
		addRampButton.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				addRampButton.setSelected(true);// to make it appear selected // TODO remove it after it's used or cancelled
				MapModeler.GetInstance().switchToRampsMode();
				
				//JOptionPane.showMessageDialog(null,"adding ramps to the map"); //RX

				
		
				
				//??how to call add rampIcon function in LevelPanel?? the code below doesn't not work as planned.
				//addRampIcon(true);  // not working..
					
			
			
				
			}

			
			
		});
		
				
	}
	
	// TODO 

	 

	private void setupLayout()
	{
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		
		// TODO not sure if need
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(originTileXLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(originXField)
						.addComponent(originTileYLabel)
						.addComponent(originYField)
						)
				.addGroup(layout.createSequentialGroup()
						.addComponent(destinationTileXLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(destinationXField)
						.addComponent(destinationTileYLabel)
						.addComponent(destinationYField)
						)
				.addGroup(layout.createSequentialGroup()
						.addComponent(unitSizeLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(unitSizeField)
						)
				.addComponent(pathfindingButton)
				//.addGap(30)
				.addGroup(layout.createSequentialGroup()
						.addComponent(pathfindingLabel)
						//.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pathfindingLengthLabel)
						)
				.addComponent(makeIntoWaterButton)
				.addComponent(addWaterButton)
				.addComponent(unaccessibleTerrainButton)
				.addComponent(accessibleTerrainButton)
				.addComponent(spawningPointButton)
				.addComponent(addMovableRockButton)
				.addComponent(addDestructibleRockButton)
				.addComponent(addRampButton)
		);
		
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addGroup(layout.createSequentialGroup()
						.addComponent(originTileXLabel)
						.addGap(20)
						.addComponent(destinationTileXLabel)
						.addGap(20)
						.addComponent(unitSizeLabel)
						.addGap(20)
						.addComponent(pathfindingButton)
						.addGap(30)
						.addComponent(pathfindingLabel)
						.addComponent(makeIntoWaterButton)
						.addComponent(addWaterButton)
						.addComponent(unaccessibleTerrainButton)
						.addComponent(accessibleTerrainButton)
						.addComponent(spawningPointButton)
						.addComponent(addMovableRockButton)
						.addComponent(addDestructibleRockButton)
						.addComponent(addRampButton)
						)
				.addGroup(layout.createSequentialGroup()
						.addComponent(originXField)
						.addComponent(destinationXField)
						.addComponent(unitSizeField)
						.addGap(60)
						.addComponent(pathfindingLengthLabel)
						)
				.addGroup(layout.createSequentialGroup()
						.addComponent(originTileYLabel)
						.addComponent(destinationTileYLabel)
						)
				.addGroup(layout.createSequentialGroup()
						.addComponent(originYField)
						.addComponent(destinationYField)
						)
		);
		
	
	}



//	@Override
//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		Graphics2D g2 = (Graphics2D) g;
//		
//		// No need for a paint in this panel, we only display swing components...
//	}
}
