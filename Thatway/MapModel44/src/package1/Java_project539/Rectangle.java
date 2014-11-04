package package1.Java_project539;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.21.0.4678 modeling language!*/



// line 177 "model.ump"
// line 354 "model.ump"
public class Rectangle
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Rectangle Associations
  private Ramp ramp;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Rectangle(Ramp aRamp)
  {
    if (aRamp == null || aRamp.getRectangle() != null)
    {
      throw new RuntimeException("Unable to create Rectangle due to aRamp");
    }
    ramp = aRamp;
  }

  public Rectangle(Map aMapForRamp)
  {
    ramp = new Ramp(this, aMapForRamp);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Ramp getRamp()
  {
    return ramp;
  }

  public void delete()
  {
    Ramp existingRamp = ramp;
    ramp = null;
    if (existingRamp != null)
    {
      existingRamp.delete();
    }
  }

}