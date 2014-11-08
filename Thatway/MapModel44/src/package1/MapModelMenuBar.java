package package1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MapModelMenuBar extends JMenuBar // TODO maybe: implements EventListener
{
	private JFileChooser chooser = new JFileChooser(){
		@Override
	    public void approveSelection(){//prompt window to confirm overwrite a file
	        File f = getSelectedFile();
	        if(f.exists() && getDialogType() == SAVE_DIALOG){
	            int result = JOptionPane.showConfirmDialog(this,"The file exists, overwrite?","Existing file",JOptionPane.YES_NO_CANCEL_OPTION);
	            switch(result){
	                case JOptionPane.YES_OPTION:
	                    super.approveSelection();
	                    return;
	                case JOptionPane.NO_OPTION:
	                    return;
	                case JOptionPane.CLOSED_OPTION:
	                    return;
	                case JOptionPane.CANCEL_OPTION:
	                    cancelSelection();
	                    return;
	            }
	        }
	        super.approveSelection();
	    }
	};
	
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
		
		chooser.setFileFilter(new FileNameExtensionFilter("Map Model 44 File","xml"));
		load.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				int returnVal = chooser.showOpenDialog(getParent());
				if(returnVal == JFileChooser.APPROVE_OPTION){
					System.out.println("You chose to open this file: "+ chooser.getSelectedFile().getAbsolutePath());	
				//TODO update the map
				}
			}
		});
		save.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//if no map then nothing to save //currently for testing disable this feature
				//if(MapModeler.GetInstance().hasMap()){
					int returnVal = chooser.showSaveDialog(getParent());
					if(returnVal == JFileChooser.APPROVE_OPTION){
						System.out.println("You chose to save this file: "+chooser.getSelectedFile().getAbsolutePath());
						//TODO save the map
					}
				//}
			}
		});
		
		
		this.add(fileMenu);
		this.add(helpMenu);
		
	}
	
	

}
