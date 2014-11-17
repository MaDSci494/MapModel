package package1;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.*;

import package1.Java_project539.Height;
import package1.Java_project539.Map;
import package1.Java_project539.Path;
import package1.Java_project539.Ramp;
import package1.Java_project539.Staircase;
import package1.Java_project539.Tile;


public class OptionsPanel extends JPanel
{
	private JButton addRampButton = new JButton("Add Ramp");
	private JTabbedPane leftpane;

	///////////////////////////////////////////////////////////////
	private JLabel originTileXLabel = new JLabel("Origin X: ");
	private JTextField originXField = new JTextField("0");
	private JLabel originTileYLabel = new JLabel("Origin Y: ");
	private JTextField originYField = new JTextField("0");
	private JLabel originLevelLabel = new JLabel("Origin Level: ");
	private JTextField originLevelField = new JTextField("0");
	
	private JLabel destinationTileXLabel = new JLabel("Destination X: ");
	private JTextField destinationXField = new JTextField("0");
	private JLabel destinationTileYLabel = new JLabel("Destination Y: ");
	private JTextField destinationYField = new JTextField("0");
	private JLabel destinationLevelLabel = new JLabel("Destination Level: ");
	private JTextField destinationLevelField = new JTextField("0");
	
	private JButton stairCaseButton = new JButton("Add staircase");
	private JButton staircaseDeleteButton = new JButton("Delete staircase at selection");
	
	private JLabel unitSizeLabel = new JLabel("Define Unit Size (0 for 1 tile, 1 for 5 tiles, 2 for 13, etc.) : ");
	private JTextField unitSizeField = new JTextField("0");
	private JButton pathfindingButton = new JButton("Calculate Path");
	private JLabel pathfindingLabel = new JLabel("length of path: ");
	private JLabel pathfindingLengthLabel = new JLabel("(length)");
	///////////////////////////////////////////////////////////////
	
	private JButton makeIntoWaterButton = new JButton("Make selection into water");
	

	
	String[] optionsPatterns = {"[type height between -100 and 100]", "1", "2", "3" };
	private JComboBox<String> heightToGiveBox = new JComboBox<String>(optionsPatterns); // TODO optional: add each new option to the list
	private JButton heightButton = new JButton("Give Selection this height ");
	
	private JButton addMovableRockButton = new JButton("Make selection into movable rocks");
	private JButton addDestructibleRockButton = new JButton("Make selection into destructible rocks"); // in honor of Dustin Browder 
	
	private JButton spawningPointButton = new JButton("Make a spawning point");
	private JButton unaccessibleTerrainButton = new JButton("Make selection into unaccessible terrain");
	private JButton accessibleTerrainButton = new JButton("Make selection empty");
	
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
		originLevelField.setPreferredSize(new Dimension(50, 30));
		destinationLevelField.setPreferredSize(new Dimension(50, 30));
		
		setupLayout();
		addListeners();
		
		originXField.setMaximumSize(new Dimension(50, 30));
		originYField.setMaximumSize(new Dimension(50, 30));
		destinationXField.setMaximumSize(new Dimension(50, 30));
		destinationYField.setMaximumSize(new Dimension(50, 30));
		unitSizeField.setMaximumSize(new Dimension(50, 30));
		originLevelField.setMaximumSize(new Dimension(50, 30));
		destinationLevelField.setMaximumSize(new Dimension(50, 30));
		
		heightToGiveBox.setEditable(true);
		heightToGiveBox.setMaximumSize(new Dimension(50, 30));

	}
	//Ran:allow modeler to update the tabbedpane after load
	public void setLeftPane(JTabbedPane t){
		leftpane = t;
	}
	
	public void addListeners() 
	{
		addRampButton.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				LevelPanel l = (LevelPanel)leftpane.getSelectedComponent();
				if(l.selectedTiles.size()==2)
				{
					Tile t1 = l.selectedTiles.get(0);
					Tile t2 = l.selectedTiles.get(1);
					Map map = MapModeler.GetInstance().getMap();
					map.addRamp(new Ramp(t1,t2,map));
				}
				l.selectedTiles.clear();
				l.repaint();
				
			}

			
		});
		
		pathfindingButton.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				Path path = MapModeler.GetInstance().getMap().doAStar(Integer.parseInt(originXField.getText()), Integer.parseInt(originYField.getText()),Integer.parseInt(originLevelField.getText()),
						 Integer.parseInt(destinationXField.getText()), Integer.parseInt(destinationYField.getText()), Integer.parseInt(destinationLevelField.getText()), Integer.parseInt(unitSizeField.getText()));
				
				if (path != null)
				{
					pathfindingLengthLabel.setText(String.format("%d", path.getTiles().size() - 1));
					
					MapModeler.GetInstance().getMap().setDisplayedPath(path);
					LevelPanel l = (LevelPanel) leftpane.getSelectedComponent();
					l.selectedTiles.clear();
					l.repaint();
				}
				else
				{
					pathfindingLengthLabel.setText("No Path Found from origin to destination");
				}
			}

			
		});
		
		
		stairCaseButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				Map map = MapModeler.GetInstance().getMap();
				Tile origin = map.getTileAt(Integer.parseInt(originLevelField.getText()), Integer.parseInt(originXField.getText()), Integer.parseInt(originYField.getText()));
				Tile destination = map.getTileAt(Integer.parseInt(destinationLevelField.getText()), Integer.parseInt(destinationXField.getText()), Integer.parseInt(destinationYField.getText()));
				
				if (origin != null && destination != null )
				{
					if (!origin.hasStaircase() && !destination.hasStaircase() && origin != destination)
					{
						Tile[] array = {origin, destination};
						Staircase stairs = new Staircase(MapModeler.GetInstance().objects.get(4).getSprite(), array);
						// effects of creating those stairs is adding them to the Tile
					}
					
					LevelPanel l = (LevelPanel) leftpane.getSelectedComponent();
					l.selectedTiles.clear();
					l.repaint();
				}
				
			}
		});
		
		staircaseDeleteButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				LevelPanel l = (LevelPanel) leftpane.getSelectedComponent();
				for(Tile t : l.selectedTiles)
				{
					if (t.hasStaircase())
						t.getStaircase().delete();
				}
				
				// clear selection
				l.selectedTiles.clear();
				l.repaint();
			}
		});
		
		//Ran: make selection to water
		makeIntoWaterButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				LevelPanel l = (LevelPanel) leftpane.getSelectedComponent();
				for(Tile t : l.selectedTiles){
					t.setObject(MapModeler.GetInstance().objects.get(5));
				}
				
				// clear selection
				l.selectedTiles.clear();
				l.repaint();
			}
		});
		
		heightButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				LevelPanel l = (LevelPanel) leftpane.getSelectedComponent();
				for(Tile t : l.selectedTiles)
				{
					String picked = (String) heightToGiveBox.getSelectedItem();
					t.setHeight(Height.generateHeight(Integer.parseInt(picked)));
				}
				
				// clear selection
				l.selectedTiles.clear();
				l.repaint();
			}
		});
		
		//Ran: add destructible rock
		//the only difference is which img to load
		//maybe we can create new mousadapter that takes care everything
		addDestructibleRockButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				LevelPanel levelPanel = (LevelPanel) leftpane.getSelectedComponent();
				for(Tile t : levelPanel.selectedTiles){
					t.setObject(MapModeler.GetInstance().objects.get(0));
				}
				//since we need to select only one otherwise just de-selecte all
				levelPanel.selectedTiles.clear();
				levelPanel.repaint();
			}
		});
		addMovableRockButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				LevelPanel levelPanel = (LevelPanel) leftpane.getSelectedComponent();
				for(Tile t : levelPanel.selectedTiles){
					t.setObject(MapModeler.GetInstance().objects.get(2));
				}
				//since we need to select only one otherwise just de-selecte all
				levelPanel.selectedTiles.clear();
				levelPanel.repaint();
			}
		});
		spawningPointButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				LevelPanel l = (LevelPanel) leftpane.getSelectedComponent();
				if(l.selectedTiles.size()==1){
					l.selectedTiles.get(0).setObject(MapModeler.GetInstance().objects.get(3));
				}
				//since we need to select only one otherwise just de-selecte all
				l.selectedTiles.clear();
				l.repaint();
			}
		});
		unaccessibleTerrainButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				LevelPanel l = (LevelPanel) leftpane.getSelectedComponent();
				for(Tile t : l.selectedTiles){
					t.setObject(MapModeler.GetInstance().objects.get(1));
				}
				//since we need to select only one otherwise just de-selecte all
				l.selectedTiles.clear();
				l.repaint();
			}
		});
		
		accessibleTerrainButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				LevelPanel l = (LevelPanel) leftpane.getSelectedComponent();
				for (Tile t : l.selectedTiles)
				{
					//remove unaccesible from tile
					if(t.getObject()!=null)
					{
						t.removeObject();
					}
				}
				//since we need to select only one otherwise just de-select all
				l.selectedTiles.clear();
				l.repaint();
			}
		});
		
	}

	private void setupLayout()
	{
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(originTileXLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGap(35)
						.addComponent(originXField)
						.addComponent(originTileYLabel)
						.addGap(40)
						.addComponent(originYField)
						.addComponent(originLevelLabel)
						.addGap(40)
						.addComponent(originLevelField)
						)
				.addGroup(layout.createSequentialGroup()
						.addComponent(destinationTileXLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(destinationXField)
						.addComponent(destinationTileYLabel)
						.addComponent(destinationYField)
						.addComponent(destinationLevelLabel)
						.addComponent(destinationLevelField)
						)
				.addGroup(layout.createSequentialGroup()
						.addComponent(unitSizeLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(unitSizeField)
						)
				.addComponent(stairCaseButton)
				.addComponent(staircaseDeleteButton)
				.addComponent(pathfindingButton)
				//.addGap(30)
				.addGroup(layout.createSequentialGroup()
						.addComponent(pathfindingLabel)
						.addComponent(pathfindingLengthLabel)
						)
				.addGroup(layout.createSequentialGroup()
						.addComponent(heightToGiveBox)
						.addComponent(heightButton)
						)
				.addComponent(accessibleTerrainButton)
				.addComponent(makeIntoWaterButton)
				//.addComponent(addWaterButton)
				.addComponent(unaccessibleTerrainButton)
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
						.addComponent(stairCaseButton)
						.addGap(20)
						.addComponent(staircaseDeleteButton)
						.addGap(20)
						.addComponent(pathfindingButton)
						.addGap(30)
						.addComponent(pathfindingLabel)
						.addComponent(heightToGiveBox)
						.addComponent(accessibleTerrainButton)
						.addComponent(makeIntoWaterButton)
						//.addComponent(addWaterButton)
						.addComponent(unaccessibleTerrainButton)
						.addComponent(spawningPointButton)
						.addComponent(addMovableRockButton)
						.addComponent(addDestructibleRockButton)
						.addComponent(addRampButton)
						)
				.addGroup(layout.createSequentialGroup()
						.addComponent(originXField)
						.addComponent(destinationXField)
						.addComponent(unitSizeField)
						.addGap(155)
						.addComponent(pathfindingLengthLabel)
						.addComponent(heightButton)
						)
				.addGroup(layout.createSequentialGroup()
						.addComponent(originTileYLabel)
						.addGap(20)
						.addComponent(destinationTileYLabel)
						)
				.addGroup(layout.createSequentialGroup()
						.addComponent(originYField)
						.addGap(10)
						.addComponent(destinationYField)
						)
				.addGroup(layout.createSequentialGroup()
						.addComponent(originLevelLabel)
						.addGap(20)
						.addComponent(destinationLevelLabel)
						)
				.addGroup(layout.createSequentialGroup()
						.addComponent(originLevelField)
						.addComponent(destinationLevelField)
						)
		);
		
	
	}
	public void setSelectionInformation(int column, int row) 
	{
		originXField.setText(String.format("%d", column));
		originYField.setText(String.format("%d", row));
	}
}
