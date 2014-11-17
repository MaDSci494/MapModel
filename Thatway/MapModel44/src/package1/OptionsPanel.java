package package1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.*;

import package1.Java_project539.Map;
import package1.Java_project539.Ramp;
import package1.Java_project539.Tile;

import java.awt.*;
import java.awt.event.*;
import java.io.File;


public class OptionsPanel extends JPanel
{
	private JButton addRampButton = new JButton("Add Ramp");
	private JTabbedPane leftpane;

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
	

	
	String[] optionsPatterns = {"[type height between -100 and 100]" };
	private JComboBox heightToGiveBox = new JComboBox(optionsPatterns); // TODO add to the panel
	private JButton makeIntoThisHeightButton = new JButton("Give Selection this height: ");
	
	private JButton addMovableRockButton = new JButton("Make a movable rock");
	private JButton addDestructibleRockButton = new JButton("Make a destructible rock"); // in honor of Dustin Browder 
	
	private JButton spawningPointButton = new JButton("Make a spawning point");
	private JButton unaccessibleTerrainButton = new JButton("Make a tile unaccessible");
	private JButton accessibleTerrainButton = new JButton("Make a tile accessible");
	
	public static JLabel ramplabel; //RX
	public static ImageIcon labelicon = new ImageIcon("src/images/ramp.png","ramp"); //RX
	
	public OptionsPanel(JTabbedPane p)
	{
		super();
		
		leftpane = p;
		
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
	//Ran:allow modeler to update the tabbedpane after laod
	public void setLeftPane(JTabbedPane t){
		leftpane = t;
	}
	
	public void addListeners() 
	{
		addRampButton.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				//addRampButton.setSelected(true);// to make it appear selected // TODO remove it after it's used or cancelled
				//MapModeler.GetInstance().switchToRampsMode();  // should make it able to de-select the button,
				LevelPanel l = (LevelPanel)leftpane.getSelectedComponent();
				if(l.selectedTile.size()==2){
					Tile t1 = l.selectedTile.get(0);
					Tile t2 = l.selectedTile.get(1);
					Map map = MapModeler.GetInstance().getMap();
					map.addRamp(new Ramp(t1,t2,map));
				}
				l.selectedTile.clear();
				l.repaint();
			}

			
		});
		//Ran: make selection to water
		makeIntoWaterButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				LevelPanel l = (LevelPanel) leftpane.getSelectedComponent();
				for(Tile t : l.selectedTile){
					t.setObject(MapModeler.GetInstance().objects.get(5));
				}
				l.selectedTile.clear();
				l.repaint();
			}
		});
		//Ran: add destructible rock
		//the only difference is which img to load
		//maybe we can create new mousadapter that takes care everything
		addDestructibleRockButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				LevelPanel l = (LevelPanel) leftpane.getSelectedComponent();
				if(l.selectedTile.size()==1){
					l.selectedTile.get(0).setObject(MapModeler.GetInstance().objects.get(0));
				}
				//since we need to select only one otherwise just de-selecte all
				l.selectedTile.clear();
				l.repaint();
			}
		});
		addMovableRockButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				LevelPanel l = (LevelPanel) leftpane.getSelectedComponent();
				if(l.selectedTile.size()==1){
					l.selectedTile.get(0).setObject(MapModeler.GetInstance().objects.get(2));
				}
				//since we need to select only one otherwise just de-selecte all
				l.selectedTile.clear();
				l.repaint();
			}
		});
		spawningPointButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				LevelPanel l = (LevelPanel) leftpane.getSelectedComponent();
				if(l.selectedTile.size()==1){
					l.selectedTile.get(0).setObject(MapModeler.GetInstance().objects.get(3));
				}
				//since we need to select only one otherwise just de-selecte all
				l.selectedTile.clear();
				l.repaint();
			}
		});
		addWaterButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				LevelPanel l = (LevelPanel) leftpane.getSelectedComponent();
				if(l.selectedTile.size()==1){
					l.selectedTile.get(0).setObject(MapModeler.GetInstance().objects.get(5));
				}
				//since we need to select only one otherwise just de-selecte all
				l.selectedTile.clear();
				l.repaint();
			}
		});
		unaccessibleTerrainButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				LevelPanel l = (LevelPanel) leftpane.getSelectedComponent();
				if(l.selectedTile.size()==1){
					l.selectedTile.get(0).setObject(MapModeler.GetInstance().objects.get(1));
				}
				//since we need to select only one otherwise just de-selecte all
				l.selectedTile.clear();
				l.repaint();
			}
		});
		
		accessibleTerrainButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				LevelPanel l = (LevelPanel) leftpane.getSelectedComponent();
				if(l.selectedTile.size()==1){
					//remeve unaccesible from tile
					Tile t = l.selectedTile.get(0);
					if(t.getObject()!=null){
						if(t.getObject().getName().equals("Inaccessible")){
							t.removeObject();
						}
					}
				}
				//since we need to select only one otherwise just de-selecte all
				l.selectedTile.clear();
				l.repaint();
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
