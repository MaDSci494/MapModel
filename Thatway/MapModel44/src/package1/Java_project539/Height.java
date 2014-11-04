package package1.Java_project539;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.21.0.4678 modeling language!*/


import java.util.*;

// line 59 "model.ump"
// line 232 "model.ump"
public class Height
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Height Attributes
  private int heightnum;

  //Height Associations
  private List<Tile> tiles;
  private Color color;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Height(int aHeightnum, Color aColor)
  {
    heightnum = aHeightnum;
    tiles = new ArrayList<Tile>();
    if (aColor == null || aColor.getHeight() != null)
    {
      throw new RuntimeException("Unable to create Height due to aColor");
    }
    color = aColor;
  }

  public Height(int aHeightnum, int aColorRForColor, int aColorGForColor, int aColorBForColor)
  {
    heightnum = aHeightnum;
    tiles = new ArrayList<Tile>();
    color = new Color(aColorRForColor, aColorGForColor, aColorBForColor, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setHeightnum(int aHeightnum)
  {
    boolean wasSet = false;
    heightnum = aHeightnum;
    wasSet = true;
    return wasSet;
  }

  public int getHeightnum()
  {
    return heightnum;
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

  public Color getColor()
  {
    return color;
  }

  public static int minimumNumberOfTiles()
  {
    return 0;
  }

  public Tile addTile(int aCoorX, int aCoorY, Terraintype aTerraintype, Level aLevel)
  {
    return new Tile(aCoorX, aCoorY, aTerraintype, this, aLevel);
  }

  public boolean addTile(Tile aTile)
  {
    boolean wasAdded = false;
    if (tiles.contains(aTile)) { return false; }
    Height existingHeight = aTile.getHeight();
    boolean isNewHeight = existingHeight != null && !this.equals(existingHeight);
    if (isNewHeight)
    {
      aTile.setHeight(this);
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
    //Unable to remove aTile, as it must always have a height
    if (!this.equals(aTile.getHeight()))
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

  public void delete()
  {
    for(int i=tiles.size(); i > 0; i--)
    {
      Tile aTile = tiles.get(i - 1);
      aTile.delete();
    }
    Color existingColor = color;
    color = null;
    if (existingColor != null)
    {
      existingColor.delete();
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "heightnum" + ":" + getHeightnum()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "color = "+(getColor()!=null?Integer.toHexString(System.identityHashCode(getColor())):"null")
     + outputString;
  }
}