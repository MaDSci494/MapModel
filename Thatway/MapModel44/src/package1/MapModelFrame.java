package package1;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MapModelFrame extends JFrame {

	public MapModelFrame()
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1000, 800);
		
		
		// Create menu bar
		MapModelMenuBar menuBar = new MapModelMenuBar();
		this.setJMenuBar(menuBar);
		
		
		generateFirstFrame();
		
		
		pack();
		this.setVisible(true);
	}
	
	private void generateFirstFrame()
	{
		
		
		JTextField lengthField = new JTextField("Enter length");
		JTextField heightField = new JTextField("Enter height");
		JButton buttonGo = new JButton("Go");
		
		buttonGo.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				// TODO add frame over
			    
			}
		});
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.add(lengthField);
		mainPanel.add(heightField);
		mainPanel.add(buttonGo);
		
		this.add(mainPanel);
		
		
	}
	
	
	
	public static void main(String[] args)  {
		System.out.println("This project will be done in time");
		MapModelFrame mainFrame = new MapModelFrame();
		
		
	}

}
