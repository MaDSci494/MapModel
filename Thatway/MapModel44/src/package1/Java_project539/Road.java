package package1.Java_project539;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.21.0.4678 modeling language!*/


import java.util.*;

// line 53 "model.ump"
// line 226 "model.ump"
public class Road extends Path
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Road Associations
  private List<Lines> lines;
  private Map map;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Road(Tile aStart, Tile aEnd, Map aMap, Tile... allTiles)
  {
    super(aStart, aEnd, allTiles);
    lines = new ArrayList<Lines>();
    boolean didAddMap = setMap(aMap);
    if (!didAddMap)
    {
      throw new RuntimeException("Unable to create road due to map");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Lines getLine(int index)
  {
    Lines aLine = lines.get(index);
    return aLine;
  }

  public List<Lines> getLines()
  {
    List<Lines> newLines = Collections.unmodifiableList(lines);
    return newLines;
  }

  public int numberOfLines()
  {
    int number = lines.size();
    return number;
  }

  public boolean hasLines()
  {
    boolean has = lines.size() > 0;
    return has;
  }

  public int indexOfLine(Lines aLine)
  {
    int index = lines.indexOf(aLine);
    return index;
  }

  public Map getMap()
  {
    return map;
  }

  public static int minimumNumberOfLines()
  {
    return 0;
  }

  public Lines addLine()
  {
    return new Lines(this);
  }

  public boolean addLine(Lines aLine)
  {
    boolean wasAdded = false;
    if (lines.contains(aLine)) { return false; }
    Road existingRoad = aLine.getRoad();
    boolean isNewRoad = existingRoad != null && !this.equals(existingRoad);
    if (isNewRoad)
    {
      aLine.setRoad(this);
    }
    else
    {
      lines.add(aLine);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLine(Lines aLine)
  {
    boolean wasRemoved = false;
    //Unable to remove aLine, as it must always have a road
    if (!this.equals(aLine.getRoad()))
    {
      lines.remove(aLine);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addLineAt(Lines aLine, int index)
  {  
    boolean wasAdded = false;
    if(addLine(aLine))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLines()) { index = numberOfLines() - 1; }
      lines.remove(aLine);
      lines.add(index, aLine);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLineAt(Lines aLine, int index)
  {
    boolean wasAdded = false;
    if(lines.contains(aLine))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLines()) { index = numberOfLines() - 1; }
      lines.remove(aLine);
      lines.add(index, aLine);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLineAt(aLine, index);
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
      existingMap.removeRoad(this);
    }
    map.addRoad(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=lines.size(); i > 0; i--)
    {
      Lines aLine = lines.get(i - 1);
      aLine.delete();
    }
    Map placeholderMap = map;
    this.map = null;
    placeholderMap.removeRoad(this);
    super.delete();
  }

}