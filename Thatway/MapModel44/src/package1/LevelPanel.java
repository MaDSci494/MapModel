package package1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import package1.Java_project539.Level;
import package1.Java_project539.Tile;

import java.util.ArrayList; //RX
import java.util.List; //RX
import java.awt.event.*; //RX




public class LevelPanel extends JPanel implements MouseMotionListener
{
	private int tileX;// width of a tile in terms of pixels
	private int tileY;// height of a tile in terms of pixels


	private Level level;

	public static int levelWidth;
	public static int levelHeight;



	private List<Rectangle> cells;
	private Point selectedCell;
	private Point selectedCell2;
	private Point selectedStart;
	private int widthAbs;
	private int heightAbs;
	
	private Tile selectedBegin;
	private Tile selectedEnd;
	private ArrayList<Tile> selectedTile = new ArrayList<Tile>();

	private Point startPoint=null;
	private Point endPoint=null;
	

	private ArrayList<RampDraw> rampdraw = new ArrayList<RampDraw>();

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

				
//		class MyMouseListener extends MouseInputAdapter
//		{

			
		 MouseAdapter mouseHandler;
	     mouseHandler = new MouseAdapter() {
	    		private int xMin;
	    		private int xMax;
	    		private int yMin;
	    		private int yMax;
	    		
	            
	         public void mousePressed(MouseEvent e) {

	        	 selectedStart=null;
	        	 
	       		 int width = getWidth();
	             int height = getHeight();

	            int cellWidth = width / levelWidth;
	            int cellHeight = height / levelHeight;
	    
	    		int xOffset = (width - (levelWidth * cellWidth)) / 2;
	    		int yOffset = (height - (levelHeight * cellHeight)) / 2;
	            
	            //changed the way to calculate selected cells so it can go all directions
	        	 startPoint = e.getPoint();
	        	 endPoint =startPoint;
	        	xMin = startPoint.x;
	        	xMax = startPoint.x;
	        	yMin = startPoint.y;
	        	yMax = startPoint.y;
	        	
	         	
//<<<<<<< HEAD


	             int column = (startPoint.x-xOffset)/cellWidth;
	             int row = (startPoint.y-yOffset)/cellHeight;
	             
	             
             selectedCell = new Point(column, row);
             
             //Ran: get selected cell and this will be the beginning one
             selectedBegin = level.getTileByXY(column,row);
             System.out.println("click star->"+"start:"+selectedBegin.toString());
             //
             repaint();

         }
//				this.addMouseMotionListener(new MouseMotionAdapter()
//				{
				public void mouseDragged(MouseEvent e) 
				{


			  		int width = getWidth();
		            int height = getHeight();
		            

		            int cellWidth = width / levelWidth;
		            int cellHeight = height / levelHeight;
		            
		    		int xOffset = (width - (levelWidth * cellWidth)) / 2;
		    		int yOffset = (height - (levelHeight * cellHeight)) / 2;
		    		
					endPoint = e.getPoint();
					xMin = Math.min(xMin,endPoint.x);
					xMax = Math.max(xMax,endPoint.x);
					yMin = Math.min(yMin,endPoint.y);
					yMax = Math.min(yMax,endPoint.y);
					
					
			        int column = (endPoint.x-xOffset)/cellWidth;
		             int row = (endPoint.y-yOffset)/cellHeight;
					selectedCell2 = new Point(column,row);
					repaint();

					

		             
				}
				
//				});
				
//			
				
         //Ran: generate selected tiles list
         public void mouseReleased(MouseEvent e){
//        	//Ran:get selected cell and this will be end

        	 
        	 
        		int width = getWidth();
	            int height = getHeight();

	            int cellWidth = width / levelWidth;
	            int cellHeight = height / levelHeight;
	            
	    		int xOffset = (width - (levelWidth * cellWidth)) / 2;
	    		int yOffset = (height - (levelHeight * cellHeight)) / 2;
        	 
        	 int x=Math.min(startPoint.x, endPoint.x);
        	 int y=Math.min(startPoint.y, endPoint.y);
        
        	 widthAbs = Math.abs(startPoint.x-endPoint.x)/cellWidth;
        	 heightAbs = Math.abs(startPoint.y-endPoint.y)/cellHeight;
        	 
        	 int column = (x-xOffset)/cellWidth;
        	 int row= (y-yOffset)/cellHeight;
        	 
        	 selectedStart = new Point(column,row);
        	 startPoint =null;
        	 
        	 repaint();
        	 
        	 
        	selectedEnd = level.getTileByXY(column,row);
 			System.out.println("click end->"+"start:"+selectedBegin.toString()+"end:"+selectedEnd.toString());
 			//add all tiles between those to selectedlist
 			//Ran: we can only select from upper left to lower right right now!
 			//that's our painting function
 			//this will be use if we can select from any direction
 			//right now only triggering selectedTiles if selceted from upper left to lower right
 			if(selectedEnd.getCoorX()>=selectedBegin.getCoorX()){
 				if(selectedEnd.getCoorY()>=selectedBegin.getCoorY()){
 					for(int i =selectedBegin.getCoorX();i<selectedEnd.getCoorX();i++){
 		 				for(int j=selectedBegin.getCoorY();j<selectedEnd.getCoorY();j++){
 		 					selectedTile.add(level.getTileByXY(i,j));
 		 				}
 		 			}
 				}else{
 					
 				}
 			}else{
 				if(selectedEnd.getCoorY()>=selectedBegin.getCoorY()){
 					
 				}else{
 					
 				}
 			}
 			
 			
 			//print out selectedTile just to check
 			System.out.print("selectedList->");
 			for(Tile t : selectedTile){
 				System.out.print(t.toString()+" ");
 			}
 			System.out.println("");
         }

		
	
		};
			
   addMouseListener(mouseHandler);
   addMouseMotionListener(mouseHandler);

//<<<<<<< HEAD
		}
		

	public void invalidate() {
		selectedCell = null;
		selectedCell2 = null;
			repaint();
			
		}
		
//		});
		
		
		
//		addRampIcon(MapModeler.GetInstance().rampsTrigger);
		
//>>>>>>> FETCH_HEAD
//	}




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

			if (selectedStart !=null)
		{

			g2.setColor(Color.RED);		
//			g2.fillRect(selectedCell.x*cellWidth+xOffset,selectedCell.y*cellHeight+yOffset,widthT,heightT);
			g2.fillRect(selectedStart.x*cellWidth+xOffset,selectedStart.y*cellHeight+yOffset,(widthAbs+1)*cellWidth,(heightAbs+1)*cellHeight);
			
		}


		
		g2.setColor(Color.GRAY);
		for (Rectangle cell : cells) {
			g2.draw(cell);
		}

		g2.dispose();
		
		// to draw ramp
		for (RampDraw rp : rampdraw)
		{
			Rectangle r=rp.getRectangle();
			g.fillRect(r.x,r.y,r.width,r.height);
		}
			
		
		boolean ghostRampTilesAreNotWater = true;//calculate this each time you change ghostRampTiles
		if(MapModeler.GetInstance().rampsTrigger && ghostRampTilesAreNotWater && selectedCell!=null && selectedCell2!=null)
		{


			g2.setColor(Color.BLACK);


			boolean horizontal = (selectedCell.y == selectedCell2.y && (selectedCell2.x - selectedCell.x)==1 ); 
			boolean vertical = (selectedCell.x==selectedCell2.x && (selectedCell2.y - selectedCell.y)==1);
			double topleftx;
			double toplefty;
			double bottomrightx;
			double bottomrighty;


			if (horizontal)
			{

				topleftx = (selectedCell.x+0.5)*cellWidth + xOffset;
				toplefty = (selectedCell.y+0.25)*cellHeight +yOffset;

				int TLX = (int) topleftx;
				int TLY = (int) toplefty;				
				g2.fillRect(TLX,TLY,cellWidth,cellHeight/2);
				Rectangle r=new Rectangle(TLX,TLY,cellWidth,cellHeight/2);
				RampDraw rp = new RampDraw(r);
				addRectangle(rp);

			}

			if (vertical)
			{
				topleftx = (selectedCell.x+0.25)*cellWidth + xOffset;
				toplefty = (selectedCell.y+0.5)*cellHeight +yOffset;

				int TLX = (int) topleftx;
				int TLY = (int) toplefty;
				g2.fillRect(TLX,TLY,cellWidth/2,cellHeight);
				Rectangle r=new Rectangle(TLX,TLY,cellWidth/2,cellHeight);
				RampDraw rp = new RampDraw(r);
				addRectangle(rp);

			}

			

		}
	}
	
			public void addRectangle(RampDraw rectangle)
			{
				rampdraw.add(rectangle);
				repaint();
				
			}


			class RampDraw
			{
				private Rectangle rectangle;

				public RampDraw( Rectangle rectangle)
				{
					this.rectangle = rectangle;
				}

				public Rectangle getRectangle()
				{
					return rectangle;
				}
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
//				ramplabel.setLocation(e.getX(),e.getY());


			}

		}
