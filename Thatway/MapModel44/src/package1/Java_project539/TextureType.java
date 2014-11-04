package package1.Java_project539;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.21.0.4678 modeling language!*/



// line 129 "model.ump"
// line 299 "model.ump"
public class TextureType
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TextureType Attributes
  private int forest;
  private int jungle;
  private int desert;
  private int resource;
  private int wetland;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TextureType(int aForest, int aJungle, int aDesert, int aResource, int aWetland)
  {
    forest = aForest;
    jungle = aJungle;
    desert = aDesert;
    resource = aResource;
    wetland = aWetland;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setForest(int aForest)
  {
    boolean wasSet = false;
    forest = aForest;
    wasSet = true;
    return wasSet;
  }

  public boolean setJungle(int aJungle)
  {
    boolean wasSet = false;
    jungle = aJungle;
    wasSet = true;
    return wasSet;
  }

  public boolean setDesert(int aDesert)
  {
    boolean wasSet = false;
    desert = aDesert;
    wasSet = true;
    return wasSet;
  }

  public boolean setResource(int aResource)
  {
    boolean wasSet = false;
    resource = aResource;
    wasSet = true;
    return wasSet;
  }

  public boolean setWetland(int aWetland)
  {
    boolean wasSet = false;
    wetland = aWetland;
    wasSet = true;
    return wasSet;
  }

  public int getForest()
  {
    return forest;
  }

  public int getJungle()
  {
    return jungle;
  }

  public int getDesert()
  {
    return desert;
  }

  public int getResource()
  {
    return resource;
  }

  public int getWetland()
  {
    return wetland;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "forest" + ":" + getForest()+ "," +
            "jungle" + ":" + getJungle()+ "," +
            "desert" + ":" + getDesert()+ "," +
            "resource" + ":" + getResource()+ "," +
            "wetland" + ":" + getWetland()+ "]"
     + outputString;
  }
}