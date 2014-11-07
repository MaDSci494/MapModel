package package1;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MapModelMenuBar extends JMenuBar // TODO maybe: implements EventListener
{
	
	public MapModelMenuBar()
	{
		super();
		
		// Create FILE menu item
		JMenu fileMenu = new JMenu("File");
		JMenu helpMenu = new JMenu("Help");
		
		JMenuItem load = new JMenuItem("Load Map");
		JMenuItem save = new JMenuItem("Save Map");
		
		fileMenu.add(load);
		fileMenu.add(save);
		
		// TODO ran add listeners that call  Map.save() (should be done through the MapModeler.GetInstance() ?) and Map.load()
		
		this.add(fileMenu);
		this.add(helpMenu);
		
	}
	
	

}
