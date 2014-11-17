package package1.Java_project539;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.21.0.4678 modeling language!*/


import java.util.*;

// line 79 "model.ump"
// line 252 "model.ump"
public class Staircase
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Staircase Associations
  private Sprite sprite;
  private List<Tile> tiles;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Staircase(Sprite aSprite, Tile... allTiles)
  {
    boolean didAddSprite = setSprite(aSprite);
    if (!didAddSprite)
    {
      throw new RuntimeException("Unable to create staircase due to sprite");
    }
    tiles = new ArrayList<Tile>();
    boolean didAddTiles = setTiles(allTiles);
    if (!didAddTiles)
    {
      throw new RuntimeException("Unable to create Staircase, must have 2 tiles");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

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
      existingSprite.removeStaircase(this);
    }
    sprite.addStaircase(this);
    wasSet = true;
    return wasSet;
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

  public boolean setTiles(Tile... newTiles)
  {
    boolean wasSet = false;
    ArrayList<Tile> checkNewTiles = new ArrayList<Tile>();
    for (Tile aTile : newTiles)
    {
      if (checkNewTiles.contains(aTile))
      {
        return wasSet;
      }
      else if (aTile.getStaircase() != null && !this.equals(aTile.getStaircase()))
      {
        return wasSet;
      }
      checkNewTiles.add(aTile);
    }

    if (checkNewTiles.size() != minimumNumberOfTiles())
    {
      return wasSet;
    }

    tiles.removeAll(checkNewTiles);
    
    for (Tile orphan : tiles)
    {
      setStaircase(orphan, null);
    }
    tiles.clear();
    for (Tile aTile : newTiles)
    {
      setStaircase(aTile, this);
      tiles.add(aTile);
    }
    wasSet = true;
    return wasSet;
  }

  private void setStaircase(Tile aTile, Staircase aStaircase)
  {
    try
    {
      java.lang.reflect.Field mentorField = aTile.getClass().getDeclaredField("staircase");
      mentorField.setAccessible(true);
      mentorField.set(aTile, aStaircase);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Issue internally setting aStaircase to aTile", e);
    }
  }

  public void delete()
  {
    Sprite placeholderSprite = sprite;
    this.sprite = null;
    placeholderSprite.removeStaircase(this);
    for(Tile aTile : tiles)
    {
      setStaircase(aTile,null);
    }
    tiles.clear();
  }
  
  // Developper code
  public Tile getDestinationTileFrom(Tile origin)
  {
	  if (tiles.get(0) == origin)
		  return tiles.get(1);
	  else if (tiles.get(1) == origin)
		  return tiles.get(0);
	  else
	  {
		  int a = 4;
		  int b = 0;
		  int c = a / b;//crash
		  return null;
	  }
  }

}