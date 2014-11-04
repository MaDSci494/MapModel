package package1.Java_project539;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.21.0.4678 modeling language!*/



// line 154 "model.ump"
// line 334 "model.ump"
public class Contourcolor
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Contourcolor Attributes
  private int colorR;
  private int colorG;
  private int colorB;

  //Contourcolor Associations
  private Territory territory;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Contourcolor(int aColorR, int aColorG, int aColorB, Territory aTerritory)
  {
    colorR = aColorR;
    colorG = aColorG;
    colorB = aColorB;
    if (aTerritory == null || aTerritory.getContourcolor() != null)
    {
      throw new RuntimeException("Unable to create Contourcolor due to aTerritory");
    }
    territory = aTerritory;
  }

  public Contourcolor(int aColorR, int aColorG, int aColorB, Map aMapForTerritory)
  {
    colorR = aColorR;
    colorG = aColorG;
    colorB = aColorB;
    territory = new Territory(this, aMapForTerritory);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setColorR(int aColorR)
  {
    boolean wasSet = false;
    colorR = aColorR;
    wasSet = true;
    return wasSet;
  }

  public boolean setColorG(int aColorG)
  {
    boolean wasSet = false;
    colorG = aColorG;
    wasSet = true;
    return wasSet;
  }

  public boolean setColorB(int aColorB)
  {
    boolean wasSet = false;
    colorB = aColorB;
    wasSet = true;
    return wasSet;
  }

  public int getColorR()
  {
    return colorR;
  }

  public int getColorG()
  {
    return colorG;
  }

  public int getColorB()
  {
    return colorB;
  }

  public Territory getTerritory()
  {
    return territory;
  }

  public void delete()
  {
    Territory existingTerritory = territory;
    territory = null;
    if (existingTerritory != null)
    {
      existingTerritory.delete();
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "colorR" + ":" + getColorR()+ "," +
            "colorG" + ":" + getColorG()+ "," +
            "colorB" + ":" + getColorB()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "territory = "+(getTerritory()!=null?Integer.toHexString(System.identityHashCode(getTerritory())):"null")
     + outputString;
  }
}