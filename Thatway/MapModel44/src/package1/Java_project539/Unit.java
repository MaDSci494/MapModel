package package1.Java_project539;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.21.0.4678 modeling language!*/


import java.util.*;

// line 96 "model.ump"
// line 269 "model.ump"
public class Unit extends Object
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Unit Attributes
  private int size;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Unit(String aName, Sprite aSprite, int aSize)
  {
    super(aName, aSprite);
    size = aSize;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSize(int aSize)
  {
    boolean wasSet = false;
    size = aSize;
    wasSet = true;
    return wasSet;
  }

  public int getSize()
  {
    return size;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "size" + ":" + getSize()+ "]"
     + outputString;
  }
}