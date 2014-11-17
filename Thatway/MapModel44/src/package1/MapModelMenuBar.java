package package1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	            int result = JOptionPane.showConfirmDialog(this,
	            										"The file exists, overwrite?",
	            										"Existing file",
	            										JOptionPane.YES_NO_CANCEL_OPTION,
	            										JOptionPane.WARNING_MESSAGE);
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
				//if we currently have a map then ask user to be sure that current work is going to be overwritten
				int result = JOptionPane.YES_OPTION;
				if(MapModeler.GetInstance().hasMap()){
					 result = JOptionPane.showConfirmDialog(null,
															"Any unsaved change will be discarded.",
															"Warning",
															JOptionPane.YES_NO_OPTION,
															JOptionPane.WARNING_MESSAGE);
				}
				switch(result){
					case JOptionPane.YES_OPTION:
						int returnVal = chooser.showOpenDialog(getParent());
						if(returnVal == JFileChooser.APPROVE_OPTION){
							String path = chooser.getSelectedFile().getAbsolutePath();
							System.out.println("You chose to open this file: "+ path);	
							//load up map
							MapModeler.GetInstance().loadMap(path);
							
						}else{
							if(returnVal == JFileChooser.CANCEL_OPTION){
								System.out.println("You canceled");
							}
							else{
								JOptionPane.showMessageDialog(null,"Error loading file.");
							}
						}
						break;
					default: return;
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
						String path = chooser.getSelectedFile().getAbsolutePath();
						System.out.println("You chose to save this file: "+path);
						//save the map
						MapModeler.GetInstance().saveMap(path);
					}else{
						if(returnVal == JFileChooser.CANCEL_OPTION){
							System.out.println("You canceled");
						}else{
							JOptionPane.showMessageDialog(null,"Error saving file.");
						}
					}
			}
		});
		
		this.add(fileMenu);
		this.add(helpMenu);
	}

}
