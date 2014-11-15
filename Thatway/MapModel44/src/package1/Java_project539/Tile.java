package package1.Java_project539;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.21.0.4678 modeling language!*/


import java.util.*;

// line 33 "model.ump"
// line 199 "model.ump"
public class Tile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Tile Attributes
  private int coorX;
  private int coorY;

  //Tile Associations
  private List<Ramp> ramps;
  private Object object;
  private Staircase staircase;
  private Terraintype terraintype;
  private List<Path> paths;
  private List<Tile> neighour;
  private Tile tile;
  private Territory territory;
  private Height height;
  private Level level;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Tile(int aCoorX, int aCoorY, Terraintype aTerraintype, Height aHeight, Level aLevel)
  {
    coorX = aCoorX;
    coorY = aCoorY;
    ramps = new ArrayList<Ramp>();
    /* Ran: I comment these cause don't know what to deal with Terrian now
    boolean didAddTerraintype = setTerraintype(aTerraintype);
    if (!didAddTerraintype)
    {
      throw new RuntimeException("Unable to create tile due to terraintype");
    }
    */
    paths = new ArrayList<Path>();
    neighour = new ArrayList<Tile>();
    
    boolean didAddHeight = setHeight(aHeight);
    if (!didAddHeight)
    {
      throw new RuntimeException("Unable to create tile due to height");
    }
    boolean didAddLevel = setLevel(aLevel);
    if (!didAddLevel)
    {
      throw new RuntimeException("Unable to create tile due to level");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCoorX(int aCoorX)
  {
    boolean wasSet = false;
    coorX = aCoorX;
    wasSet = true;
    return wasSet;
  }

  public boolean setCoorY(int aCoorY)
  {
    boolean wasSet = false;
    coorY = aCoorY;
    wasSet = true;
    return wasSet;
  }

  public int getCoorX()
  {
    return coorX;
  }

  public int getCoorY()
  {
    return coorY;
  }

  public Ramp getRamp(int index)
  {
    Ramp aRamp = ramps.get(index);
    return aRamp;
  }

  public List<Ramp> getRamps()
  {
    List<Ramp> newRamps = Collections.unmodifiableList(ramps);
    return newRamps;
  }

  public int numberOfRamps()
  {
    int number = ramps.size();
    return number;
  }

  public boolean hasRamps()
  {
    boolean has = ramps.size() > 0;
    return has;
  }

  public int indexOfRamp(Ramp aRamp)
  {
    int index = ramps.indexOf(aRamp);
    return index;
  }

  public Object getObject()
  {
    return object;
  }

  public boolean hasObject()
  {
    boolean has = object != null;
    return has;
  }

  public Staircase getStaircase()
  {
    return staircase;
  }

  public boolean hasStaircase()
  {
    boolean has = staircase != null;
    return has;
  }

  public Terraintype getTerraintype()
  {
    return terraintype;
  }

  public Path getPath(int index)
  {
    Path aPath = paths.get(index);
    return aPath;
  }

  public List<Path> getPaths()
  {
    List<Path> newPaths = Collections.unmodifiableList(paths);
    return newPaths;
  }

  public int numberOfPaths()
  {
    int number = paths.size();
    return number;
  }

  public boolean hasPaths()
  {
    boolean has = paths.size() > 0;
    return has;
  }

  public int indexOfPath(Path aPath)
  {
    int index = paths.indexOf(aPath);
    return index;
  }

  public Tile getNeighour(int index)
  {
    Tile aNeighour = neighour.get(index);
    return aNeighour;
  }

  public List<Tile> getNeighour()
  {
    List<Tile> newNeighour = Collections.unmodifiableList(neighour);
    return newNeighour;
  }

  public int numberOfNeighour()
  {
    int number = neighour.size();
    return number;
  }

  public boolean hasNeighour()
  {
    boolean has = neighour.size() > 0;
    return has;
  }

  public int indexOfNeighour(Tile aNeighour)
  {
    int index = neighour.indexOf(aNeighour);
    return index;
  }

  public Tile getTile()
  {
    return tile;
  }

  public boolean hasTile()
  {
    boolean has = tile != null;
    return has;
  }

  public Territory getTerritory()
  {
    return territory;
  }

  public boolean hasTerritory()
  {
    boolean has = territory != null;
    return has;
  }

  public Height getHeight()
  {
    return height;
  }

  public Level getLevel()
  {
    return level;
  }

  public static int minimumNumberOfRamps()
  {
    return 0;
  }

  public static int maximumNumberOfRamps()
  {
    return 4;
  }

  public boolean addRamp(Ramp aRamp)
  {
    boolean wasAdded = false;
    if (ramps.contains(aRamp)) { return false; }
    if (numberOfRamps() >= maximumNumberOfRamps())
    {
      return wasAdded;
    }

    ramps.add(aRamp);
    if (aRamp.indexOfTile(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aRamp.addTile(this);
      if (!wasAdded)
      {
        ramps.remove(aRamp);
      }
    }
    return wasAdded;
  }

  public boolean removeRamp(Ramp aRamp)
  {
    boolean wasRemoved = false;
    if (!ramps.contains(aRamp))
    {
      return wasRemoved;
    }

    int oldIndex = ramps.indexOf(aRamp);
    ramps.remove(oldIndex);
    if (aRamp.indexOfTile(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aRamp.removeTile(this);
      if (!wasRemoved)
      {
        ramps.add(oldIndex,aRamp);
      }
    }
    return wasRemoved;
  }

  public boolean addRampAt(Ramp aRamp, int index)
  {  
    boolean wasAdded = false;
    if(addRamp(aRamp))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRamps()) { index = numberOfRamps() - 1; }
      ramps.remove(aRamp);
      ramps.add(index, aRamp);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRampAt(Ramp aRamp, int index)
  {
    boolean wasAdded = false;
    if(ramps.contains(aRamp))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRamps()) { index = numberOfRamps() - 1; }
      ramps.remove(aRamp);
      ramps.add(index, aRamp);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRampAt(aRamp, index);
    }
    return wasAdded;
  }

  public boolean setObject(Object aObject)
  {
    boolean wasSet = false;
    Object existingObject = object;
    object = aObject;
    if (existingObject != null && !existingObject.equals(aObject))
    {
      existingObject.removeTile(this);
    }
    if (aObject != null)
    {
      aObject.addTile(this);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean setTerraintype(Terraintype aTerraintype)
  {
    boolean wasSet = false;
    if (aTerraintype == null)
    {
      return wasSet;
    }

    Terraintype existingTerraintype = terraintype;
    terraintype = aTerraintype;
    if (existingTerraintype != null && !existingTerraintype.equals(aTerraintype))
    {
      existingTerraintype.removeTile(this);
    }
    terraintype.addTile(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfPaths()
  {
    return 0;
  }

  public boolean addPath(Path aPath)
  {
    boolean wasAdded = false;
    if (paths.contains(aPath)) { return false; }
    paths.add(aPath);
    if (aPath.indexOfTile(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPath.addTile(this);
      if (!wasAdded)
      {
        paths.remove(aPath);
      }
    }
    return wasAdded;
  }

  public boolean removePath(Path aPath)
  {
    boolean wasRemoved = false;
    if (!paths.contains(aPath))
    {
      return wasRemoved;
    }

    int oldIndex = paths.indexOf(aPath);
    paths.remove(oldIndex);
    if (aPath.indexOfTile(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPath.removeTile(this);
      if (!wasRemoved)
      {
        paths.add(oldIndex,aPath);
      }
    }
    return wasRemoved;
  }

  public boolean addPathAt(Path aPath, int index)
  {  
    boolean wasAdded = false;
    if(addPath(aPath))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPaths()) { index = numberOfPaths() - 1; }
      paths.remove(aPath);
      paths.add(index, aPath);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePathAt(Path aPath, int index)
  {
    boolean wasAdded = false;
    if(paths.contains(aPath))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPaths()) { index = numberOfPaths() - 1; }
      paths.remove(aPath);
      paths.add(index, aPath);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPathAt(aPath, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfNeighour()
  {
    return 0;
  }

  public static int maximumNumberOfNeighour()
  {
    return 5;
  }

  public boolean addNeighour(Tile aNeighour)
  {
    boolean wasAdded = false;
    if (neighour.contains(aNeighour)) { return false; }
    if (numberOfNeighour() >= maximumNumberOfNeighour())
    {
      return wasAdded;
    }

    Tile existingTile = aNeighour.getTile();
    if (existingTile == null)
    {
      aNeighour.setTile(this);
    }
    else if (!this.equals(existingTile))
    {
      existingTile.removeNeighour(aNeighour);
      addNeighour(aNeighour);
    }
    else
    {
      neighour.add(aNeighour);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeNeighour(Tile aNeighour)
  {
    boolean wasRemoved = false;
    if (neighour.contains(aNeighour))
    {
      neighour.remove(aNeighour);
      aNeighour.setTile(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addNeighourAt(Tile aNeighour, int index)
  {  
    boolean wasAdded = false;
    if(addNeighour(aNeighour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfNeighour()) { index = numberOfNeighour() - 1; }
      neighour.remove(aNeighour);
      neighour.add(index, aNeighour);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveNeighourAt(Tile aNeighour, int index)
  {
    boolean wasAdded = false;
    if(neighour.contains(aNeighour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfNeighour()) { index = numberOfNeighour() - 1; }
      neighour.remove(aNeighour);
      neighour.add(index, aNeighour);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addNeighourAt(aNeighour, index);
    }
    return wasAdded;
  }

  public boolean setTile(Tile aTile)
  {
    boolean wasSet = false;
    if (aTile != null && aTile.numberOfNeighour() >= Tile.maximumNumberOfNeighour())
    {
      return wasSet;
    }

    Tile existingTile = tile;
    tile = aTile;
    if (existingTile != null && !existingTile.equals(aTile))
    {
      existingTile.removeNeighour(this);
    }
    if (aTile != null)
    {
      aTile.addNeighour(this);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean setTerritory(Territory aTerritory)
  {
    boolean wasSet = false;
    Territory existingTerritory = territory;
    territory = aTerritory;
    if (existingTerritory != null && !existingTerritory.equals(aTerritory))
    {
      existingTerritory.removeTile(this);
    }
    if (aTerritory != null)
    {
      aTerritory.addTile(this);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean setHeight(Height aHeight)
  {
    boolean wasSet = false;
    if (aHeight == null)
    {
      return wasSet;
    }

    Height existingHeight = height;
    height = aHeight;
    if (existingHeight != null && !existingHeight.equals(aHeight))
    {
      existingHeight.removeTile(this);
    }
    height.addTile(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setLevel(Level aLevel)
  {
    boolean wasSet = false;
    if (aLevel == null)
    {
      return wasSet;
    }

    Level existingLevel = level;
    level = aLevel;
    if (existingLevel != null && !existingLevel.equals(aLevel))
    {
      existingLevel.removeTile(this);
    }
    level.addTile(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Ramp> copyOfRamps = new ArrayList<Ramp>(ramps);
    ramps.clear();
    for(Ramp aRamp : copyOfRamps)
    {
      if (aRamp.numberOfTiles() <= Ramp.minimumNumberOfTiles())
      {
        aRamp.delete();
      }
      else
      {
        aRamp.removeTile(this);
      }
    }
    if (object != null)
    {
      Object placeholderObject = object;
      this.object = null;
      placeholderObject.removeTile(this);
    }
    if (staircase != null)
    {
      staircase.delete();
    }
    Terraintype placeholderTerraintype = terraintype;
    this.terraintype = null;
    placeholderTerraintype.removeTile(this);
    ArrayList<Path> copyOfPaths = new ArrayList<Path>(paths);
    paths.clear();
    for(Path aPath : copyOfPaths)
    {
      if (aPath.numberOfTiles() <= Path.minimumNumberOfTiles())
      {
        aPath.delete();
      }
      else
      {
        aPath.removeTile(this);
      }
    }
    while( !neighour.isEmpty() )
    {
      neighour.get(0).setTile(null);
    }
    if (tile != null)
    {
      Tile placeholderTile = tile;
      this.tile = null;
      placeholderTile.removeNeighour(this);
    }
    if (territory != null)
    {
      Territory placeholderTerritory = territory;
      this.territory = null;
      placeholderTerritory.removeTile(this);
    }
    Height placeholderHeight = height;
    this.height = null;
    placeholderHeight.removeTile(this);
    Level placeholderLevel = level;
    this.level = null;
    placeholderLevel.removeTile(this);
  }

  
  //Ran: removeObject
  public void removeObject(){
	   Object existingObject = object;
	   if (existingObject != null)
	    {
	      existingObject.removeTile(this);
	    }
	    object = null;
  }

  public String toString()
  {
	  /*
	  String outputString = "";
    return super.toString() + "["+
            "coorX" + ":" + getCoorX()+ "," +
            "coorY" + ":" + getCoorY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "object = "+(getObject()!=null?Integer.toHexString(System.identityHashCode(getObject())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "staircase = "+(getStaircase()!=null?Integer.toHexString(System.identityHashCode(getStaircase())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "terraintype = "+(getTerraintype()!=null?Integer.toHexString(System.identityHashCode(getTerraintype())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "territory = "+(getTerritory()!=null?Integer.toHexString(System.identityHashCode(getTerritory())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "height = "+(getHeight()!=null?Integer.toHexString(System.identityHashCode(getHeight())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "level = "+(getLevel()!=null?Integer.toHexString(System.identityHashCode(getLevel())):"null")
     + outputString;
     */
	  //for test
	  String r = "coorX" + ":" + getCoorX()+ "," +"coorY" + ":" + getCoorY();
	  return r;
  }
}