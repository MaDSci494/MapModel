package package1.Java_project539;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.21.0.4678 modeling language!*/


import java.util.*;

// line 138 "model.ump"
// line 304 "model.ump"
public class Path
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Path Attributes
  private Tile start;
  private Tile end;

  //Path Associations
  private List<Tile> tiles;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Path(Tile aStart, Tile aEnd, Tile... allTiles)
  {
    start = aStart;
    end = aEnd;
    tiles = new ArrayList<Tile>();
    boolean didAddTiles = setTiles(allTiles);
    if (!didAddTiles)
    {
      throw new RuntimeException("Unable to create Path, must have at least 2 tiles");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStart(Tile aStart)
  {
    boolean wasSet = false;
    start = aStart;
    wasSet = true;
    return wasSet;
  }

  public boolean setEnd(Tile aEnd)
  {
    boolean wasSet = false;
    end = aEnd;
    wasSet = true;
    return wasSet;
  }

  public Tile getStart()
  {
    return start;
  }

  public Tile getEnd()
  {
    return end;
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

  public boolean isNumberOfTilesValid()
  {
    boolean isValid = numberOfTiles() >= minimumNumberOfTiles();
    return isValid;
  }

  public static int minimumNumberOfTiles()
  {
    return 2;
  }

  public boolean addTile(Tile aTile)
  {
    boolean wasAdded = false;
    if (tiles.contains(aTile)) { return false; }
    if (tiles.contains(aTile)) { return false; }
    tiles.add(aTile);
    if (aTile.indexOfPath(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aTile.addPath(this);
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
    if (aTile.indexOfPath(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aTile.removePath(this);
      if (!wasRemoved)
      {
        tiles.add(oldIndex,aTile);
      }
    }
    return wasRemoved;
  }

  public boolean setTiles(Tile... newTiles)
  {
    boolean wasSet = false;
    ArrayList<Tile> verifiedTiles = new ArrayList<Tile>();
    for (Tile aTile : newTiles)
    {
      if (verifiedTiles.contains(aTile))
      {
        continue;
      }
      verifiedTiles.add(aTile);
    }

    if (verifiedTiles.size() != newTiles.length || verifiedTiles.size() < minimumNumberOfTiles())
    {
      return wasSet;
    }

    ArrayList<Tile> oldTiles = new ArrayList<Tile>(tiles);
    tiles.clear();
    for (Tile aNewTile : verifiedTiles)
    {
      tiles.add(aNewTile);
      if (oldTiles.contains(aNewTile))
      {
        oldTiles.remove(aNewTile);
      }
      else
      {
        aNewTile.addPath(this);
      }
    }

    for (Tile anOldTile : oldTiles)
    {
      anOldTile.removePath(this);
    }
    wasSet = true;
    return wasSet;
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

  public void delete()
  {
    ArrayList<Tile> copyOfTiles = new ArrayList<Tile>(tiles);
    tiles.clear();
    for(Tile aTile : copyOfTiles)
    {
      aTile.removePath(this);
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "start" + "=" + (getStart() != null ? !getStart().equals(this)  ? getStart().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "end" + "=" + (getEnd() != null ? !getEnd().equals(this)  ? getEnd().toString().replaceAll("  ","    ") : "this" : "null")
     + outputString;
  }
}