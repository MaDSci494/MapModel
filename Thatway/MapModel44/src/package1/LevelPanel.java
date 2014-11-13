package package1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.ArrayList; //RX
import java.util.List; //RX
import java.awt.event.*; //RX


public class LevelPanel extends JPanel implements MouseMotionListener
{
	private int tileX;// width of a tile in terms of pixels
	private int tileY;// height of a tile in terms of pixels

//	private int levelWidth;
//	private int levelHeight;

	public static int levelWidth;
	public static int levelHeight;
	
	public static JLabel ramplabel; //RX
	public static ImageIcon labelicon = new ImageIcon("src/images/ramp.png","ramp"); //RX
	
	private List<Rectangle> cells;
	private Point selectedCell;
	private Point selectedCell2;
	
	public LevelPanel(int width, int height) 
	{
		super();

		if (width != 0 && height != 0) 
		{
			tileX = Constants.LEVEL_PANE_WIDTH / width;
			tileY = Constants.LEVEL_PANE_HEIGHT / height;
			levelWidth = width;
			levelHeight = height;

			if (tileX == 0 || tileY == 0) 
			{
				System.out.println("Problem - given width or height was too big");
				System.err.println("Error - given width or height was too big");
			}
			
		} 
		else 
		{
			System.out.println("Problem - given width or height is 0");
			System.err.println("Error - given width or height is 0");
		}
		
//	addRampIcon();
//		ramplabel=new JLabel(labelicon);
//		ramplabel.setBounds(15,225,labelicon.getIconWidth(),labelicon.getIconHeight());
//		 this.add(ramplabel);
//		    this.addMouseMotionListener(this);
	
		
	//tile selection		
	cells=new ArrayList<>(levelWidth*levelHeight);

	
	this.addMouseListener(new MouseAdapter()
	{
	
		public void mousePressed(MouseEvent e) 
		{

			Point point = e.getPoint();
			
			int width = getWidth();			
			int height = getHeight();

			
			int cellWidth = width/levelWidth;
			int cellHeight = height/levelHeight;
			
			int column = e.getX()/cellWidth;
			int row =e.getY()/cellHeight;
			
			
			selectedCell = new Point(column,row);
			
			
		}
	});
	
		this.addMouseMotionListener(new MouseMotionAdapter()
		{
		public void mouseDragged(MouseEvent e) 
		{

			Point point2 = e.getPoint();
			
			int width = getWidth();			
			int height = getHeight();

			
			int cellWidth = width/levelWidth;
			int cellHeight = height/levelHeight;
			
			int column = e.getX()/cellWidth;
			int row =e.getY()/cellHeight;
			

			
			selectedCell2 = new Point(column,row);
			repaint();
		}
		
		});
		
	}
	
	
	  public void invalidate() {
//          cells.clear();
          selectedCell = null;
          super.invalidate();
      }
	  
	

	
	
@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();

//		g2.setColor(Color.WHITE);
//		g2.drawRect(0, 0, this.getWidth(), this.getHeight());
//		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
//
//		g2.setColor(Color.GRAY);
//
//		// Draw vertical grid lines
//		for (int i = 0; i < levelWidth; ++i) 
//		{
//			int column = i * tileX;
//			g2.drawLine(column, 0, column, levelHeight * tileY);
//		}
//
//		// Draw horizontal grid lines
//		for (int j = 0; j < levelHeight; ++j) 
//		{
//			int row = j * tileY;
//			g2.drawLine(0, row, levelWidth * tileX, row);
//		}
		
		//used new code to generate map 
		int width = getWidth();
        int height = getHeight();

        int cellWidth = width / levelWidth;
        int cellHeight = height / levelHeight;

        int xOffset = (width - (levelWidth * cellWidth)) / 2;
        int yOffset = (height - (levelHeight * cellHeight)) / 2;

        
        if (cells.isEmpty()) {
            for (int row = 0; row < levelHeight; row++) {
                for (int col = 0; col < levelWidth; col++) {
                    Rectangle cell = new Rectangle(
                            xOffset + (col * cellWidth),
                            yOffset + (row * cellHeight),
                            cellWidth,
                            cellHeight);
                    cells.add(cell);
                }
            }
        }

		if (selectedCell != null)
		{
		
			int widthT=(selectedCell2.x-selectedCell.x)*cellWidth;
			int heightT=(selectedCell2.y-selectedCell.y)*cellHeight;
			
			g2.setColor(Color.RED);
			g2.fillRect(selectedCell.x*cellWidth+xOffset,selectedCell.y*cellHeight+yOffset,widthT,heightT);
			
		}
		
		 g2.setColor(Color.GRAY);
         for (Rectangle cell : cells) {
             g2.draw(cell);
         }

         g2.dispose();

		
	}
	
	

		
public  void addRampIcon() 
{
	
//RX  add ramp icon to mouse tip
	ramplabel=new JLabel(labelicon);
	ramplabel.setBounds(15,225,labelicon.getIconWidth(),labelicon.getIconHeight());

	    this.add(ramplabel);
	    this.addMouseMotionListener(this);

	    
//	 System.out.println("abcdedf..."); //for test puropse only
}

	
	
	

	
	
	
	
		
		
		// TODO draw ramps

		// TODO draw paths

		// TODO draw height difference

		// TODO insert images  JB

		// System.out.println("Drawing loop");
	








	private void tileSelection() {
	// TODO Auto-generated method stub
	
}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		ramplabel.setLocation(e.getX(),e.getY());

		
	}

}
