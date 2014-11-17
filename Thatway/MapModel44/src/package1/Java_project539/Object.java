package package1.Java_project539;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.21.0.4678 modeling language!*/


import java.util.*;

import package1.MapModeler;

// line 73 "model.ump"
// line 246 "model.ump"
public class Object
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Object Attributes
  private String name;

  //Object Associations
  private Sprite sprite;
  private List<Tile> tiles;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Object(String aName, Sprite aSprite)
  {
    name = aName;
    boolean didAddSprite = setSprite(aSprite);
    if (!didAddSprite)
    {
      throw new RuntimeException("Unable to create object due to sprite");
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
      existingSprite.removeObject(this);
    }
    sprite.addObject(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfTiles()
  {
    return 0;
  }

  public boolean addTile(Tile aTile)
  {
    boolean wasAdded = false;
    if (tiles.contains(aTile)) { return false; }
    if (tiles.contains(aTile)) { return false; }
    if (tiles.contains(aTile)) { return false; }
    Object existingObject = aTile.getObject();
    if (existingObject == null)
    {
      aTile.setObject(this);
    }
    else if (!this.equals(existingObject))
    {
      existingObject.removeTile(aTile);
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
      aTile.setObject(null);
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
    placeholderSprite.removeObject(this);
    while( !tiles.isEmpty() )
    {
      tiles.get(0).setObject(null);
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
  
  
  // Developper code
  
  public boolean isWalkable()
  {
	  return this != MapModeler.GetInstance().objects.get(0) &&
			  this != MapModeler.GetInstance().objects.get(1) && 
			  this != MapModeler.GetInstance().objects.get(2) && 
			  this != MapModeler.GetInstance().objects.get(5);
  }
}