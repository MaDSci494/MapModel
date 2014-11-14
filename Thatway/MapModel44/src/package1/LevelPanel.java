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

import package1.Java_project539.Level;

import java.util.ArrayList; //RX
import java.util.List; //RX
import java.awt.event.*; //RX
import java.awt.image.BufferedImage;



public class LevelPanel extends JPanel implements MouseMotionListener
{
	private int tileX;// width of a tile in terms of pixels
	private int tileY;// height of a tile in terms of pixels


	private Level level;
	
	public static int levelWidth;
	public static int levelHeight;

	
	public static JLabel ramplabel; //RX
	public static ImageIcon labelicon = new ImageIcon("src/images/ramp.png","ramp"); //RX
	
	private List<Rectangle> cells;
	private Point selectedCell;
	private Point selectedCell2;

//	private ArrayList<Ramp> ramps = new ArrayList<Ramp>();
	
	public LevelPanel(int width, int height, int levelNum) 
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
		
		level = MapModeler.GetInstance().getMap().getLevel(levelNum);
		
	
		
	//tile selection		
	cells=new ArrayList<>(levelWidth*levelHeight);

	 MouseAdapter mouseHandler;
     mouseHandler = new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent e) {
        	  selectedCell2=null;
             Point point = e.getPoint();

             int width = getWidth();
             int height = getHeight();

             int cellWidth = width / levelWidth;
             int cellHeight = height / levelHeight;

             int column = e.getX() / cellWidth;
             int row = e.getY() / cellHeight;

             selectedCell = new Point(column, row);
             repaint();

         }
     };
     addMouseListener(mouseHandler);
     addMouseMotionListener(mouseHandler);
     
	
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
		
//		addRampIcon(MapModeler.GetInstance().rampsTrigger);
		
	}
	
	
	  public void invalidate() {
//          cells.clear();
          selectedCell = null;
          selectedCell2 = null;
          super.invalidate();
      }
	  
	

	

	  @Override
	  public void paintComponent(Graphics g) 
	  {
		  super.paintComponent(g);
		  Graphics2D g2 = (Graphics2D) g.create();
		

		
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

		if (selectedCell != null && selectedCell2 != null)
		{
		
			int widthT=(selectedCell2.x-selectedCell.x)*cellWidth;
			int heightT=(selectedCell2.y-selectedCell.y)*cellHeight;
			
			g2.setColor(Color.RED);		
			g2.fillRect(selectedCell.x*cellWidth+xOffset,selectedCell.y*cellHeight+yOffset,widthT,heightT);
			
		}
		
		if (selectedCell !=null)
		{
            g2.setColor(Color.RED);
            g2.fillRect(selectedCell.x*cellWidth+xOffset,selectedCell.y*cellHeight+yOffset,cellWidth,cellHeight);

		}
		
		boolean ghostRampTilesAreNotWater = true;//calculate this each time you change ghostRampTiles
		if(MapModeler.GetInstance().rampsTrigger && ghostRampTilesAreNotWater && selectedCell!=null && selectedCell2!=null)
		{
			g2.setColor(Color.BLACK);
			
			
			boolean horizontal = selectedCell.y == selectedCell2.y; 
			boolean vertical = selectedCell.x==selectedCell2.x;
			double topleftx;
			double toplefty;
			double bottomrightx;
			double bottomrighty;
			
//			System.out.println(horizontal);
			
			if (horizontal)
			{

			 topleftx = (selectedCell.x+0.5)*cellWidth + xOffset;
			 toplefty = (selectedCell.y+0.25)*cellHeight +yOffset;
	
			int TLX = (int) topleftx;
				int TLY = (int) toplefty;				
					g2.fillRect(TLX,TLY,cellWidth,cellHeight/2);
			}
          	
		 if (vertical)
				{
			 topleftx = (selectedCell.x+0.25)*cellWidth + xOffset;
			 toplefty = (selectedCell.y+0.5)*cellHeight +yOffset;
	
			int TLX = (int) topleftx;
				int TLY = (int) toplefty;
					g2.fillRect(TLX,TLY,cellWidth/2,cellHeight);
				}
			

			
		}
		
		
		 g2.setColor(Color.GRAY);
         for (Rectangle cell : cells) {
             g2.draw(cell);
         }

         g2.dispose();

         
		
	}
	


//public  void addRampIcon(boolean iconpressed) 
//{
//
//	//RX  add ramp icon to mouse tip
//	ramplabel=new JLabel(labelicon);
//	ramplabel.setBounds(15,225,labelicon.getIconWidth(),labelicon.getIconHeight());
//
//	if (iconpressed)
//	{
//	    this.add(ramplabel);
//	    this.addMouseMotionListener(this);
//	    revalidate();
//	    repaint();
//	   
//	}
//	    
//	    
////	 System.out.println("abcdedf..."); //for test puropse only
//}

	
	
	

	
	
	
	
		
		
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
