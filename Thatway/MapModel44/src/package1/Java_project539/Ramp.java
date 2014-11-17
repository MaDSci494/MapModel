package package1.Java_project539;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.21.0.4678 modeling language!*/


import java.util.*;

// line 29 "model.ump"
// line 193 "model.ump"
public class Ramp
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ramp Associations
  private MyRectangle rectangle;
  private Map map;
  private List<Tile> tiles;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ramp(MyRectangle aRectangle, Map aMap)
  {
    if (aRectangle == null || aRectangle.getRamp() != null)
    {
      throw new RuntimeException("Unable to create Ramp due to aRectangle");
    }
    rectangle = aRectangle;
    boolean didAddMap = setMap(aMap);
    if (!didAddMap)
    {
      throw new RuntimeException("Unable to create ramp due to map");
    }
    tiles = new ArrayList<Tile>();
  }

  public Ramp(Map aMap)
  {
    rectangle = new MyRectangle(this);
    boolean didAddMap = setMap(aMap);
    if (!didAddMap)
    {
      throw new RuntimeException("Unable to create ramp due to map");
    }
    tiles = new ArrayList<Tile>();
  }
  
  //Ran:another constructor
  public Ramp(Tile t1,Tile t2, Map aMap){
	  tiles = new ArrayList<Tile>();
	  addTile(t1);
	  addTile(t2);
	  map = aMap;
	  rectangle = new MyRectangle(this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public MyRectangle getRectangle()
  {
    return rectangle;
  }

  public Map getMap()
  {
    return map;
  }

  public Tile getTile(int index)
  {
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
      existingMap.removeRamp(this);
    }
    map.addRamp(this);
    wasSet = true;
    return wasSet;
  }

  public boolean isNumberOfTilesValid()
  {
    boolean isValid = numberOfTiles() >= minimumNumberOfTiles() && numberOfTiles() <= maximumNumberOfTiles();
    return isValid;
  }

  public static int requiredNumberOfTiles()
  {
    return 2;
  }

  public static int minimumNumberOfTiles()
  {
    return 2;
  }

  public static int maximumNumberOfTiles()
  {
    return 2;
  }

  public boolean addTile(Tile aTile)
  {
    boolean wasAdded = false;
    if (tiles.contains(aTile)) { return false; }
    if (numberOfTiles() >= maximumNumberOfTiles())
    {
      return wasAdded;
    }

    tiles.add(aTile);
    if (aTile.indexOfRamp(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aTile.addRamp(this);
      if (!wasAdded)
      {
        tiles.remove(aTile);
      }
    }
    return wasAdded;
  }

  public boolean removeTile(Tile aTile)
  {
    boolean wasRemoved = false;
    if (!tiles.contains(aTile))
    {
      return wasRemoved;
    }

    if (numberOfTiles() <= minimumNumberOfTiles())
    {
      return wasRemoved;
    }

    int oldIndex = tiles.indexOf(aTile);
    tiles.remove(oldIndex);
    if (aTile.indexOfRamp(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aTile.removeRamp(this);
      if (!wasRemoved)
      {
        tiles.add(oldIndex,aTile);
      }
    }
    return wasRemoved;
  }

  public void delete()
  {
    MyRectangle existingRectangle = rectangle;
    rectangle = null;
    if (existingRectangle != null)
    {
      existingRectangle.delete();
    }
    Map placeholderMap = map;
    this.map = null;
    placeholderMap.removeRamp(this);
    ArrayList<Tile> copyOfTiles = new ArrayList<Tile>(tiles);
    tiles.clear();
    for(Tile aTile : copyOfTiles)
    {
      aTile.removeRamp(this);
    }
  }

}