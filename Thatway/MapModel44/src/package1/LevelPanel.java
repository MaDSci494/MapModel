package package1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import package1.Java_project539.Level;
import package1.Java_project539.Path;
import package1.Java_project539.Tile;
import package1.Java_project539.Ramp;

import java.util.ArrayList; //RX
import java.util.List; //RX

public class LevelPanel extends JPanel implements MouseMotionListener
{
	private int tileX;// width of a tile in terms of pixels
	private int tileY;// height of a tile in terms of pixels

	private Level level;

	public static int levelWidth;
	public static int levelHeight;

	private Tile selectedBegin;
	private Tile selectedEnd;
	public ArrayList<Tile> selectedTiles = new ArrayList<Tile>();

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
			
		 MouseAdapter mouseHandler;
	     mouseHandler = new MouseAdapter() 
	     {
	    		public void mousePressed(MouseEvent e) 
	    		{
	    			//Ran: our actual tile start at (1,1)
	    			
	    			int column = e.getX()/tileX;
	    			int row = e.getY()/tileY;

	    			//Ran: get selected cell and this will be the beginning one
	    			try
	    			{
	    				selectedBegin = level.getTileByXY(column,row);
		    			//clear up selectedTile if we have one
		    			selectedTiles.clear();
		    			selectedTiles.add(selectedBegin);
	    			}
	    			catch(IndexOutOfBoundsException e2)
	    			{
	    				selectedTiles.clear();
	    			}
	    			
	    			repaint();
	    		}
	    		
	    		public void mouseDragged(MouseEvent e) 
				{
	    			int column = e.getX()/tileX;
	    			int row = e.getY()/tileY;
	    			
	    			selectedEnd = level.getTileByXY(column,row);
	    			//Ran:add all tiles between those to selectedlist 
	    			
	    			if(selectedEnd != null)
	    			{
	    				selectedTiles.clear();
	    				if(selectedEnd.getCoorX()>=selectedBegin.getCoorX()){
		    				if(selectedEnd.getCoorY()>=selectedBegin.getCoorY()){
		    					for(int i=selectedBegin.getCoorX();i<=selectedEnd.getCoorX();i++){
		    						for(int j=selectedBegin.getCoorY();j<=selectedEnd.getCoorY();j++){
		    							selectedTiles.add(level.getTileByXY(i,j));
		    						}
		    					}
		    				}else{
		    					for(int i=selectedBegin.getCoorX();i<=selectedEnd.getCoorX();i++){
		    						for(int j=selectedEnd.getCoorY();j<=selectedBegin.getCoorY();j++){
		    							selectedTiles.add(level.getTileByXY(i,j));
		    						}
		    					}
		    				}
		    			}else{
		    				if(selectedEnd.getCoorY()>=selectedBegin.getCoorY()){
		    					for(int i=selectedEnd.getCoorX();i<=selectedBegin.getCoorX();i++){
		    						for(int j=selectedBegin.getCoorY();j<=selectedEnd.getCoorY();j++){
		    							selectedTiles.add(level.getTileByXY(i,j));
		    						}
		    					}
		    				}else{
		    					for(int i=selectedEnd.getCoorX();i<=selectedBegin.getCoorX();i++){
		    						for(int j=selectedEnd.getCoorY();j<=selectedBegin.getCoorY();j++){
		    							selectedTiles.add(level.getTileByXY(i,j));
		    						}
		    					}
		    				}
		    			}
	    				
	    			}
	    			
					repaint();
		             
				}
				
	    		//Ran: generate selected tiles list
	    		public void mouseReleased(MouseEvent e)
	    		{
        		//Ran:get selected cell and this will be end
	    		
	    			int column = e.getX()/tileX;
	    			int row = e.getY()/tileY;
	    			MapModelFrame.getInstance().getOptionsPanel().setSelectionInformation(column, row);
	    			selectedEnd = level.getTileByXY(column,row);
	    			
	    			//Ran:add all tiles between those to selectedlist 
	    			
	    			if (selectedEnd != null) // If selected end is valid
	    			{
	    				selectedTiles.clear();
		    			if(selectedEnd.getCoorX()>=selectedBegin.getCoorX())
		    			{
		    				if(selectedEnd.getCoorY()>=selectedBegin.getCoorY())
		    				{
		    					for(int i=selectedBegin.getCoorX();i<=selectedEnd.getCoorX();i++){
		    						for(int j=selectedBegin.getCoorY();j<=selectedEnd.getCoorY();j++){
		    							selectedTiles.add(level.getTileByXY(i,j));
		    						}
		    					}
		    				}else{
		    					for(int i=selectedBegin.getCoorX();i<=selectedEnd.getCoorX();i++){
		    						for(int j=selectedEnd.getCoorY();j<=selectedBegin.getCoorY();j++){
		    							selectedTiles.add(level.getTileByXY(i,j));
		    						}
		    					}
		    				}
		    			}else{
		    				if(selectedEnd.getCoorY()>=selectedBegin.getCoorY()){
		    					for(int i=selectedEnd.getCoorX();i<=selectedBegin.getCoorX();i++){
		    						for(int j=selectedBegin.getCoorY();j<=selectedEnd.getCoorY();j++){
		    							selectedTiles.add(level.getTileByXY(i,j));
		    						}
		    					}
		    				}else{
		    					for(int i=selectedEnd.getCoorX();i<=selectedBegin.getCoorX();i++){
		    						for(int j=selectedEnd.getCoorY();j<=selectedBegin.getCoorY();j++){
		    							selectedTiles.add(level.getTileByXY(i,j));
		    						}
		    					}
		    				}
		    			}
	    				
	    				
	    			}
	    			repaint();
	    		}
	
		};// end MouseAdapter
			
		addMouseListener(mouseHandler);
		addMouseMotionListener(mouseHandler);

	}

	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		
		// Jb: Draw Height Colors
		for (Tile t : level.getTiles())
		{
			package1.Java_project539.Color myColor = t.getHeight().getColor();
			g2.setColor(new Color(myColor.getColorR(), myColor.getColorG(), myColor.getColorB()));
			if (myColor.getHeight().getHeightnum() != 0)
				g2.fillRect(t.getCoorX() * tileX, t.getCoorY()*tileY, tileX, tileY);
		    //g2.setColor(Color.WHITE);
			
		}
		
		//Ran:fill every thing from selectedTiles with red
		g2.setColor(Color.RED);
		for(Tile t: selectedTiles)
		{
			if (t != null)
				g2.fillRect(t.getCoorX()*tileX,t.getCoorY()*tileY,tileX,tileY);
		}

		//Ran:Draw Grid
		g2.setColor(Color.GRAY);
		/*
		for (Rectangle cell : cells) {
			g2.draw(cell);
		}
		*/
		//Ran:I am adding a (1,1) offset to the origin
		//draw vertical lines
		for(int i=0;i<=levelWidth;i++){
			g2.drawLine(1+i*tileX,1,1+i*tileX,tileY*levelHeight);
		}
		//draw horizontal lines
		for(int i=0;i<=levelHeight;i++){
			g2.drawLine(1,1+i*tileY,tileX*levelWidth,1+i*tileY);
		}
		
		Path highlightedPath = level.getMap().getDisplayedPath();
		if (highlightedPath != null && highlightedPath.getTiles().size() > 1)
		{
			g2.setColor(Color.BLUE);
			for (int i = 0; i < highlightedPath.getTiles().size() - 1; i++) // will need i
			{
				Tile t = highlightedPath.getTiles().get(i);
				Tile t2 = highlightedPath.getTiles().get(i+1);
				if (t.getLevel() == level && t2.getLevel() == level)
					drawPathBetween(g2, t, t2);
			}
		}
		
		//Ran: draw ramps
		g2.setColor(Color.BLACK);
		List<Ramp> ramps = MapModeler.GetInstance().getMap().getRamps();
		for(Ramp r : ramps){
			if(r.getTile(0).getLevel()==level){
				Rectangle rec = r.getRectangle().generateRec(tileX,tileY);
				//rampdraw.add(rec);
				g2.fill(rec);
			}
 		}

		//Ran: draw sprite
		for(Tile t : level.getTiles())
		{
			if(t.getObject()!=null){
				Image img = t.getObject().getSprite().getImg();
				g2.drawImage(img,t.getCoorX()*tileX+tileX/2,t.getCoorY()*tileY+tileY/2,tileX/2,tileY/2,null);
				//System.out.println(t.getObject().toString()+" "+t.getObject().getSprite());
			}
			
			// Jb: draw staircases
			if (t.hasStaircase())
			{
				Image img = t.getStaircase().getSprite().getImg();
				g2.drawImage(img,t.getCoorX()*tileX,t.getCoorY()*tileY,tileX/2,tileY/2,null);
			}
		}
		
		
		
		g2.dispose();
	}
	
	private void drawPathBetween(Graphics2D g2, Tile t1, Tile t2)
	{
		boolean horizontal = (t1.getCoorY() == t2.getCoorY() && Math.abs(t2.getCoorX() - t1.getCoorX())==1 ); 
		boolean vertical = (t1.getCoorX()==t2.getCoorX() && Math.abs(t2.getCoorY() - t1.getCoorY())==1);

		if (horizontal)
		{
			//in order to select from different directions
			if(t2.getCoorX()<t1.getCoorX()){
				Tile temp = t2;
				t2 = t1;
				t1 = temp;
			}

			int TLX = t1.getCoorX()*tileX+tileX/3;
			int TLY = t1.getCoorY()*tileY+tileY/3;
			g2.fillRect(TLX,TLY,4*tileX/3,tileY/3);
		}

		if (vertical)
		{
			//in order to select from different directions
			if(t2.getCoorY()<t1.getCoorY())
			{
				Tile temp = t2;
				t2 = t1;
				t1 = temp;
			}

			int TLX = t1.getCoorX()*tileX+tileX/3;
			int TLY = t1.getCoorY()*tileY+tileY/3;
			g2.fillRect(TLX,TLY,tileX/3,4*tileY/3);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub


	}

}
