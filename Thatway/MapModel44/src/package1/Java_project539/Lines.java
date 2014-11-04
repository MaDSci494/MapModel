package package1.Java_project539;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.21.0.4678 modeling language!*/



// line 150 "model.ump"
// line 329 "model.ump"
public class Lines
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Lines Associations
  private Road road;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Lines(Road aRoad)
  {
    boolean didAddRoad = setRoad(aRoad);
    if (!didAddRoad)
    {
      throw new RuntimeException("Unable to create line due to road");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Road getRoad()
  {
    return road;
  }

  public boolean setRoad(Road aRoad)
  {
    boolean wasSet = false;
    if (aRoad == null)
    {
      return wasSet;
    }

    Road existingRoad = road;
    road = aRoad;
    if (existingRoad != null && !existingRoad.equals(aRoad))
    {
      existingRoad.removeLine(this);
    }
    road.addLine(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Road placeholderRoad = road;
    this.road = null;
    placeholderRoad.removeLine(this);
  }

}