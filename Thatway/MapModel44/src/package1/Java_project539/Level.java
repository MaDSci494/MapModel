package package1.Java_project539;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.21.0.4678 modeling language!*/


import java.util.*;

import javax.swing.JComponent;

// line 66 "model.ump"
// line 239 "model.ump"
public class Level extends JComponent
//public class Level
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Level Attributes
  private int levelnum;

  //Level Associations
  private List<Tile> tiles;
  private Tab tab;
  private Map map;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Level(int aLevelnum, Tab aTab, Map aMap)
  {
    levelnum = aLevelnum;
    tiles = new ArrayList<Tile>();
    if (aTab == null || aTab.getLevel() != null)
    {
      throw new RuntimeException("Unable to create Level due to aTab");
    }
    tab = aTab;
    boolean didAddMap = setMap(aMap);
    if (!didAddMap)
    {
      throw new RuntimeException("Unable to create level due to map");
    }
  }

  public Level(int aLevelnum, int aTabnumForTab, Map aMap)
  {
    levelnum = aLevelnum;
    tiles = new ArrayList<Tile>();
    tab = new Tab(aTabnumForTab, this);
    boolean didAddMap = setMap(aMap);
    if (!didAddMap)
    {
      throw new RuntimeException("Unable to create level due to map");
    }
    
    //Developper code
    //Ran:fill tiles
    for(int i=0;i<aMap.getSizeX();i++)
    {
    	for(int j=0;j<aMap.getSizeY();j++)
    	{
    		addTile(new Tile(i,j,null,new Height(0,0,0,0),this));
    	}
    }
    
    // Give them their neighbors
    for(int i=0;i<aMap.getSizeX();i++)
    {
    	for(int j=0;j<aMap.getSizeY();j++)
    	{
    		ArrayList<Tile> list = new ArrayList<Tile>();
    		list.add(getTileByXY(i, j - 1));
    		list.add(getTileByXY(i, j+1));
    		list.add(getTileByXY(i+1, j));
    		list.add(getTileByXY(i - 1, j));
    		
    		for (Tile t : list)
    		{
    			if (t != null)
    			{
    				getTileByXY(i, j).addNeighour(t);
    			}
    		}
    	}
    }
    
    
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLevelnum(int aLevelnum)
  {
    boolean wasSet = false;
    levelnum = aLevelnum;
    wasSet = true;
    return wasSet;
  }

  public int getLevelnum()
  {
    return levelnum;
  }

  public Tile getTile(int index)
  {
	  if(tiles.size() <= index)
		  return null;
	  
      Tile aTile = tiles.get(index);
      return aTile;
  }

  public List<Tile> getTiles()
  {
    List<Tile> newTiles = Collections.unmodifiableList(tiles);
    return newTiles;
  }

  public int numberOfTiles()
  {
    int number = tiles.size();
    return number;
  }

  public boolean hasTiles()
  {
    boolean has = tiles.size() > 0;
    return has;
  }

  public int indexOfTile(Tile aTile)
  {
    int index = tiles.indexOf(aTile);
    return index;
  }

  public Tab getTab()
  {
    return tab;
  }

  public Map getMap()
  {
    return map;
  }

  public static int minimumNumberOfTiles()
  {
    return 0;
  }

  public Tile addTile(int aCoorX, int aCoorY, Terraintype aTerraintype, Height aHeight)
  {
    return new Tile(aCoorX, aCoorY, aTerraintype, aHeight, this);
  }

  public boolean addTile(Tile aTile)
  {
    boolean wasAdded = false;
    if (tiles.contains(aTile)) { return false; }
    Level existingLevel = aTile.getLevel();
    boolean isNewLevel = existingLevel != null && !this.equals(existingLevel);
    if (isNewLevel)
    {
      aTile.setLevel(this);
    }
    else
    {
      tiles.add(aTile);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTile(Tile aTile)
  {
    boolean wasRemoved = false;
    //Unable to remove aTile, as it must always have a level
    if (!this.equals(aTile.getLevel()))
    {
      tiles.remove(aTile);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addTileAt(Tile aTile, int index)
  {  
    boolean wasAdded = false;
    if(addTile(aTile))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTiles()) { index = numberOfTiles() - 1; }
      tiles.remove(aTile);
      tiles.add(index, aTile);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTileAt(Tile aTile, int index)
  {
    boolean wasAdded = false;
    if(tiles.contains(aTile))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTiles()) { index = numberOfTiles() - 1; }
      tiles.remove(aTile);
      tiles.add(index, aTile);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTileAt(aTile, index);
    }
    return wasAdded;
  }

  public boolean setMap(Map aMap)
  {
    boolean wasSet = false;
    if (aMap == null)
    {
      return wasSet;
    }

    Map existingMap = map;
    map = aMap;
    if (existingMap != null && !existingMap.equals(aMap))
    {
      existingMap.removeLevel(this);
    }
    map.addLevel(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=tiles.size(); i > 0; i--)
    {
      Tile aTile = tiles.get(i - 1);
      aTile.delete();
    }
    Tab existingTab = tab;
    tab = null;
    if (existingTab != null)
    {
      existingTab.delete();
    }
    Map placeholderMap = map;
    this.map = null;
    placeholderMap.removeLevel(this);
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "levelnum" + ":" + getLevelnum()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "tab = "+(getTab()!=null?Integer.toHexString(System.identityHashCode(getTab())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "map = "+(getMap()!=null?Integer.toHexString(System.identityHashCode(getMap())):"null")
     + outputString;
  }
  
  
  // Developper code
  
  //Ran: get tile by (x,y) cord
  public Tile getTileByXY(int x,int y)
  {
	  if(x>=0 && x<map.getSizeX() && y<map.getSizeY() && y>=0)
		  return getTile(x*map.getSizeY()+y);
	  else 
		  return null;
  }
}
	  	
	  	
	  
