package package1;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MapModelMenuBar extends JMenuBar 
{
	
	public MapModelMenuBar()
	{
		super();
		
		// Create FILE menu item
		JMenu fileMenu = new JMenu("File");
		JMenu helpMenu = new JMenu("Help");
		
		JMenuItem load = new JMenuItem("Load");
		JMenuItem save = new JMenuItem("Save");
		
		fileMenu.add(load);
		fileMenu.add(save);
		
		
		
		this.add(fileMenu);
		this.add(helpMenu);
		
	}

}
