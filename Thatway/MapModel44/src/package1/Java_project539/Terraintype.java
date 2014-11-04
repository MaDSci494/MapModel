package package1.Java_project539;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.21.0.4678 modeling language!*/


import java.util.*;

// line 84 "model.ump"
// line 258 "model.ump"
public class Terraintype
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Terraintype Attributes
  private String name;

  //Terraintype Associations
  private Sprite sprite;
  private List<Tile> tiles;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Terraintype(String aName, Sprite aSprite)
  {
    name = aName;
    boolean didAddSprite = setSprite(aSprite);
    if (!didAddSprite)
    {
      throw new RuntimeException("Unable to create terraintype due to sprite");
    }
    tiles = new ArrayList<Tile>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public Sprite getSprite()
  {
    return sprite;
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

  public boolean setSprite(Sprite aSprite)
  {
    boolean wasSet = false;
    if (aSprite == null)
    {
      return wasSet;
    }

    Sprite existingSprite = sprite;
    sprite = aSprite;
    if (existingSprite != null && !existingSprite.equals(aSprite))
    {
      existingSprite.removeTerraintype(this);
    }
    sprite.addTerraintype(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfTiles()
  {
    return 0;
  }

  public Tile addTile(int aCoorX, int aCoorY, Height aHeight, Level aLevel)
  {
    return new Tile(aCoorX, aCoorY, this, aHeight, aLevel);
  }

  public boolean addTile(Tile aTile)
  {
    boolean wasAdded = false;
    if (tiles.contains(aTile)) { return false; }
    if (tiles.contains(aTile)) { return false; }
    if (tiles.contains(aTile)) { return false; }
    if (tiles.contains(aTile)) { return false; }
    Terraintype existingTerraintype = aTile.getTerraintype();
    boolean isNewTerraintype = existingTerraintype != null && !this.equals(existingTerraintype);
    if (isNewTerraintype)
    {
      aTile.setTerraintype(this);
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
    //Unable to remove aTile, as it must always have a terraintype
    if (!this.equals(aTile.getTerraintype()))
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
    Sprite placeholderSprite = sprite;
    this.sprite = null;
    placeholderSprite.removeTerraintype(this);
    for(int i=tiles.size(); i > 0; i--)
    {
      Tile aTile = tiles.get(i - 1);
      aTile.delete();
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "sprite = "+(getSprite()!=null?Integer.toHexString(System.identityHashCode(getSprite())):"null")
     + outputString;
  }
}