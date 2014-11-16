package package1;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import package1.Java_project539.Level;
import package1.Java_project539.Map;

public class MapModelFrame extends JFrame 
{
	
	private static MapModelFrame mainFrame;
	private JLayeredPane layeredPane;
	
	private OptionsPanel rightPanel;
	private JTabbedPane leftPanel;
	private MapModeler model;
	
	JTextField lengthField;
	JTextField heightField;
	JLabel descLabel;
	

	
	
	public static MapModelFrame getInstance()
	{
		return mainFrame;
	}
	
	public MapModelFrame()
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); // If you press x, exit the program.
		
		// Create menu bar
		MapModelMenuBar menuBar = new MapModelMenuBar();
		this.setJMenuBar(menuBar);
		
		
		generateFirstPage();
		
		this.setVisible(true); // yeah, that might help
		this.setLocation(200, 100);
		this.setSize(1300, 800);
	}

	private void generateFirstPage()
	{
		descLabel = new JLabel("Provide values for creating a new map.");
		lengthField = new JTextField("26"); // suggested values.
		heightField = new JTextField("32");
		lengthField.setPreferredSize(new Dimension(60, 30));
		heightField.setPreferredSize(new Dimension(60, 30));
		
	
		
		JButton buttonGo = new JButton("Go (values recommended between 1 and 300)");
		
		buttonGo.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				int length = Integer.parseInt(lengthField.getText());
				int height = Integer.parseInt(heightField.getText());
				
				if (length == 0 || height == 0 || length >= 500 || height >= 500)
				{
					descLabel.setText("INPUT DENIED. The values must be bigger than 0, and smaller than 500.");
				}
				else
				{
					MapModeler.GetInstance().generateNewMap(length, height);
					mainFrame.fillWithMapModeler();
				}
			}
		});
		
		// Add directly in line the visual elements to ask for map dimensions.
		JPanel mainPanel = new JPanel();
		mainPanel.add(lengthField);
		mainPanel.add(heightField);
		mainPanel.add(buttonGo);
		
		
		this.getContentPane().add(mainPanel);
	}
	
	// this function should be called when loading a map, AFTER assigning it to the MapModeler.
	// If the MapModeler has no map, this will do nothing.
	public void fillWithMapModeler()   //RX change from private to public
	{
		model = MapModeler.GetInstance();
	    
		if (!model.hasMap())
			return;
		
		this.getContentPane().remove(0);
		//remove old leftPanel if any
		if(leftPanel!=null) this.remove(leftPanel);
		
		// TODO Ran could add a leftmost panel for Level tabs?
		//TODO Ran add a leftmost panel for Level tabs
				//LevelPanel leftPanel = new LevelPanel(model.getMapWidth(), model.getMapHeight());
				leftPanel = new JTabbedPane(JTabbedPane.LEFT,JTabbedPane.WRAP_TAB_LAYOUT);
				final Map map = model.getMap();
				
				//the levels of current map
				for(int i=0;i<map.numberOfLevels();i++){
					LevelPanel levelPane = new LevelPanel(model.getMapWidth(), model.getMapHeight(),i);
					leftPanel.addTab((String)("Level "+i),levelPane);
				}
				//new level button
				leftPanel.addTab("+",null);	
						
				//change listener for tabbedpane
				leftPanel.addChangeListener(new ChangeListener(){
					@Override
					public void stateChanged(ChangeEvent e) {
						int index = leftPanel.getSelectedIndex();
						if(index == leftPanel.indexOfTab("+")){
							int levelnum = leftPanel.getTabCount()-1;
							//map.addLevel(new Level(levelnum,levelnum,map));
							LevelPanel levelPane = new LevelPanel(model.getMapWidth(), model.getMapHeight(),levelnum);
							//Ran:	when you add a level, it will trigger the stateChange again
							//		will get into infinite loop and stackoverflow if you do nothing
							
							//call mapModeler to insert a new level
							MapModeler.GetInstance().insertLevel();
							
							leftPanel.remove(index);
							leftPanel.addTab("Level "+levelnum,levelPane);
							leftPanel.addTab("+",null);
							leftPanel.setSelectedIndex(index);//focus on the new level
						}
					}
					
				
					

				});
				//Ran: add optionpanel a reference to leftpanel
				//remove old rightPanel from Frame
				if(rightPanel!=null)this.remove(rightPanel);
				rightPanel = new OptionsPanel(leftPanel);
				
		
		leftPanel.setBounds(0, 0, Constants.LEVEL_PANE_WIDTH, Constants.LEVEL_PANE_HEIGHT);
		
		
		
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addComponent(leftPanel)
				.addComponent(rightPanel)
		);
		
		layout.setVerticalGroup(layout.createParallelGroup()
				.addComponent(leftPanel)
				.addComponent(rightPanel)
		);
	}

		
	
	
	public static void main(String[] args)  
	{
		System.out.println("This project will be done in time and it will be marvelous!");
		mainFrame = new MapModelFrame();
	}

}
