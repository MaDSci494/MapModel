package package1.Java_project539;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.21.0.4678 modeling language!*/


import java.util.*;

// line 47 "model.ump"
// line 219 "model.ump"
public class Territory
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Territory Associations
  private List<Tile> tiles;
  private Contourcolor contourcolor;
  private Map map;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Territory(Contourcolor aContourcolor, Map aMap)
  {
    tiles = new ArrayList<Tile>();
    if (aContourcolor == null || aContourcolor.getTerritory() != null)
    {
      throw new RuntimeException("Unable to create Territory due to aContourcolor");
    }
    contourcolor = aContourcolor;
    boolean didAddMap = setMap(aMap);
    if (!didAddMap)
    {
      throw new RuntimeException("Unable to create territory due to map");
    }
  }

  public Territory(int aColorRForContourcolor, int aColorGForContourcolor, int aColorBForContourcolor, Map aMap)
  {
    tiles = new ArrayList<Tile>();
    contourcolor = new Contourcolor(aColorRForContourcolor, aColorGForContourcolor, aColorBForContourcolor, this);
    boolean didAddMap = setMap(aMap);
    if (!didAddMap)
    {
      throw new RuntimeException("Unable to create territory due to map");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public Contourcolor getContourcolor()
  {
    return contourcolor;
  }

  public Map getMap()
  {
    return map;
  }

  public static int minimumNumberOfTiles()
  {
    return 0;
  }

  public boolean addTile(Tile aTile)
  {
    boolean wasAdded = false;
    if (tiles.contains(aTile)) { return false; }
    Territory existingTerritory = aTile.getTerritory();
    if (existingTerritory == null)
    {
      aTile.setTerritory(this);
    }
    else if (!this.equals(existingTerritory))
    {
      existingTerritory.removeTile(aTile);
      addTile(aTile);
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
    if (tiles.contains(aTile))
    {
      tiles.remove(aTile);
      aTile.setTerritory(null);
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
      existingMap.removeTerritory(this);
    }
    map.addTerritory(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while( !tiles.isEmpty() )
    {
      tiles.get(0).setTerritory(null);
    }
    Contourcolor existingContourcolor = contourcolor;
    contourcolor = null;
    if (existingContourcolor != null)
    {
      existingContourcolor.delete();
    }
    Map placeholderMap = map;
    this.map = null;
    placeholderMap.removeTerritory(this);
  }

}