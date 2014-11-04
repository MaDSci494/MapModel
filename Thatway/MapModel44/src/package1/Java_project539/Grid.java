package package1.Java_project539;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.21.0.4678 modeling language!*/



// line 168 "model.ump"
// line 344 "model.ump"
public class Grid
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Grid Associations
  private Map map;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Grid(Map aMap)
  {
    if (aMap == null || aMap.getGrid() != null)
    {
      throw new RuntimeException("Unable to create Grid due to aMap");
    }
    map = aMap;
  }

  public Grid(int aSizeXForMap, int aSizeYForMap)
  {
    map = new Map(aSizeXForMap, aSizeYForMap, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Map getMap()
  {
    return map;
  }

  public void delete()
  {
    Map existingMap = map;
    map = null;
    if (existingMap != null)
    {
      existingMap.delete();
    }
  }

}