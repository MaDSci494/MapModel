package package1.Java_project539;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.21.0.4678 modeling language!*/



// line 161 "model.ump"
// line 339 "model.ump"
public class Color
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Color Attributes
  private int colorR;
  private int colorG;
  private int colorB;

  //Color Associations
  private Height height;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Color(int aColorR, int aColorG, int aColorB, Height aHeight)
  {
    colorR = aColorR;
    colorG = aColorG;
    colorB = aColorB;
    if (aHeight == null || aHeight.getColor() != null)
    {
      throw new RuntimeException("Unable to create Color due to aHeight");
    }
    height = aHeight;
  }

  public Color(int aColorR, int aColorG, int aColorB, int aHeightnumForHeight)
  {
    colorR = aColorR;
    colorG = aColorG;
    colorB = aColorB;
    height = new Height(aHeightnumForHeight, this);
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

  public Height getHeight()
  {
    return height;
  }

  public void delete()
  {
    Height existingHeight = height;
    height = null;
    if (existingHeight != null)
    {
      existingHeight.delete();
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "colorR" + ":" + getColorR()+ "," +
            "colorG" + ":" + getColorG()+ "," +
            "colorB" + ":" + getColorB()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "height = "+(getHeight()!=null?Integer.toHexString(System.identityHashCode(getHeight())):"null")
     + outputString;
  }
}