package package1.Java_project539;

import java.awt.Rectangle;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.21.0.4678 modeling language!*/



// line 177 "model.ump"
// line 354 "model.ump"
public class MyRectangle 
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Rectangle Associations
  private Ramp ramp;
  
  

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MyRectangle(Ramp aRamp)
  {
    if (aRamp == null || aRamp.getRectangle() != null)
    {
      throw new RuntimeException("Unable to create Rectangle due to aRamp");
    }
    ramp = aRamp;
  }

  public MyRectangle(Map aMapForRamp)
  {
	super();
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
  //Ran: generate a rectangle based on input width & length
  public Rectangle generateRec(int tileX,int tileY){
	  Tile selectedBegin = ramp.getTile(0);
	  Tile selectedEnd = ramp.getTile(1);
	  boolean horizontal = (selectedBegin.getCoorY() == selectedEnd.getCoorY() && Math.abs(selectedEnd.getCoorX() - selectedBegin.getCoorX())==1 ); 
	  boolean vertical = (selectedBegin.getCoorX()==selectedEnd.getCoorX() && Math.abs(selectedEnd.getCoorY() - selectedBegin.getCoorY())==1);
		if (horizontal)
		{
			//in order to select from differenct directions
			if(selectedEnd.getCoorX()<selectedBegin.getCoorX()){
				Tile temp = selectedEnd;
				selectedEnd = selectedBegin;
				selectedBegin = temp;
			}
			int TLX = selectedBegin.getCoorX()*tileX+tileX/2;
			int TLY = selectedBegin.getCoorY()*tileY+tileY/4;			
			return new Rectangle(TLX,TLY,tileX,tileY/2);
		}

		if (vertical)
		{
			//in order to select from differenct directions
			if(selectedEnd.getCoorY()<selectedBegin.getCoorY()){
				Tile temp = selectedEnd;
				selectedEnd = selectedBegin;
				selectedBegin = temp;
			}
			int TLX = selectedBegin.getCoorX()*tileX+tileX/4;
			int TLY = selectedBegin.getCoorY()*tileY+tileY/2;
			return new Rectangle(TLX,TLY,tileX/2,tileY);
		}
	  
	  return null;
  }

}
