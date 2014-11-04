package package1.Java_project539;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.21.0.4678 modeling language!*/



// line 172 "model.ump"
// line 349 "model.ump"
public class Tab
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Tab Attributes
  private int tabnum;

  //Tab Associations
  private Level level;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Tab(int aTabnum, Level aLevel)
  {
    tabnum = aTabnum;
    if (aLevel == null || aLevel.getTab() != null)
    {
      throw new RuntimeException("Unable to create Tab due to aLevel");
    }
    level = aLevel;
  }

  public Tab(int aTabnum, int aLevelnumForLevel, Map aMapForLevel)
  {
    tabnum = aTabnum;
    level = new Level(aLevelnumForLevel, this, aMapForLevel);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTabnum(int aTabnum)
  {
    boolean wasSet = false;
    tabnum = aTabnum;
    wasSet = true;
    return wasSet;
  }

  public int getTabnum()
  {
    return tabnum;
  }

  public Level getLevel()
  {
    return level;
  }

  public void delete()
  {
    Level existingLevel = level;
    level = null;
    if (existingLevel != null)
    {
      existingLevel.delete();
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "tabnum" + ":" + getTabnum()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "level = "+(getLevel()!=null?Integer.toHexString(System.identityHashCode(getLevel())):"null")
     + outputString;
  }
}