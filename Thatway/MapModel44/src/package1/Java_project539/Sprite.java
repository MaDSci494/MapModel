package package1.Java_project539;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.21.0.4678 modeling language!*/


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import javax.imageio.ImageIO;

// line 144 "model.ump"
// line 309 "model.ump"
// line 314 "model.ump"
// line 319 "model.ump"
// line 324 "model.ump"
public class Sprite
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Sprite Attributes
  private String name;
  //Ran: buffered image to display
  private BufferedImage img;
 
  //Sprite Associations
  private List<Object> objects;
  private List<Staircase> staircases;
  private List<Terraintype> terraintypes;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Sprite(String aName) 
  {
    name = aName;
    try{
    	img = ImageIO.read(new File("src/images/"+name));
    }
    catch(IOException e){
    	//who cares
    	System.out.println("Error reading img");
    }
    objects = new ArrayList<Object>();
    staircases = new ArrayList<Staircase>();
    terraintypes = new ArrayList<Terraintype>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  //Ran: getter for img
  public BufferedImage getImg(){
	  return img;
  }
  
  
  
  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public Object getObject(int index)
  {
    Object aObject = objects.get(index);
    return aObject;
  }

  public List<Object> getObjects()
  {
    List<Object> newObjects = Collections.unmodifiableList(objects);
    return newObjects;
  }

  public int numberOfObjects()
  {
    int number = objects.size();
    return number;
  }

  public boolean hasObjects()
  {
    boolean has = objects.size() > 0;
    return has;
  }

  public int indexOfObject(Object aObject)
  {
    int index = objects.indexOf(aObject);
    return index;
  }

  public Staircase getStaircase(int index)
  {
    Staircase aStaircase = staircases.get(index);
    return aStaircase;
  }

  public List<Staircase> getStaircases()
  {
    List<Staircase> newStaircases = Collections.unmodifiableList(staircases);
    return newStaircases;
  }

  public int numberOfStaircases()
  {
    int number = staircases.size();
    return number;
  }

  public boolean hasStaircases()
  {
    boolean has = staircases.size() > 0;
    return has;
  }

  public int indexOfStaircase(Staircase aStaircase)
  {
    int index = staircases.indexOf(aStaircase);
    return index;
  }

  public Terraintype getTerraintype(int index)
  {
    Terraintype aTerraintype = terraintypes.get(index);
    return aTerraintype;
  }

  public List<Terraintype> getTerraintypes()
  {
    List<Terraintype> newTerraintypes = Collections.unmodifiableList(terraintypes);
    return newTerraintypes;
  }

  public int numberOfTerraintypes()
  {
    int number = terraintypes.size();
    return number;
  }

  public boolean hasTerraintypes()
  {
    boolean has = terraintypes.size() > 0;
    return has;
  }

  public int indexOfTerraintype(Terraintype aTerraintype)
  {
    int index = terraintypes.indexOf(aTerraintype);
    return index;
  }

  public static int minimumNumberOfObjects()
  {
    return 0;
  }

  public Object addObject(String aName)
  {
    return new Object(aName, this);
  }

  public boolean addObject(Object aObject)
  {
    boolean wasAdded = false;
    if (objects.contains(aObject)) { return false; }
    Sprite existingSprite = aObject.getSprite();
    boolean isNewSprite = existingSprite != null && !this.equals(existingSprite);
    if (isNewSprite)
    {
      aObject.setSprite(this);
    }
    else
    {
      objects.add(aObject);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeObject(Object aObject)
  {
    boolean wasRemoved = false;
    //Unable to remove aObject, as it must always have a sprite
    if (!this.equals(aObject.getSprite()))
    {
      objects.remove(aObject);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addObjectAt(Object aObject, int index)
  {  
    boolean wasAdded = false;
    if(addObject(aObject))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfObjects()) { index = numberOfObjects() - 1; }
      objects.remove(aObject);
      objects.add(index, aObject);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveObjectAt(Object aObject, int index)
  {
    boolean wasAdded = false;
    if(objects.contains(aObject))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfObjects()) { index = numberOfObjects() - 1; }
      objects.remove(aObject);
      objects.add(index, aObject);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addObjectAt(aObject, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfStaircases()
  {
    return 0;
  }

  public Staircase addStaircase(Tile... allTiles)
  {
    return new Staircase(this, allTiles);
  }

  public boolean addStaircase(Staircase aStaircase)
  {
    boolean wasAdded = false;
    if (staircases.contains(aStaircase)) { return false; }
    Sprite existingSprite = aStaircase.getSprite();
    boolean isNewSprite = existingSprite != null && !this.equals(existingSprite);
    if (isNewSprite)
    {
      aStaircase.setSprite(this);
    }
    else
    {
      staircases.add(aStaircase);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeStaircase(Staircase aStaircase)
  {
    boolean wasRemoved = false;
    //Unable to remove aStaircase, as it must always have a sprite
    if (!this.equals(aStaircase.getSprite()))
    {
      staircases.remove(aStaircase);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addStaircaseAt(Staircase aStaircase, int index)
  {  
    boolean wasAdded = false;
    if(addStaircase(aStaircase))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStaircases()) { index = numberOfStaircases() - 1; }
      staircases.remove(aStaircase);
      staircases.add(index, aStaircase);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveStaircaseAt(Staircase aStaircase, int index)
  {
    boolean wasAdded = false;
    if(staircases.contains(aStaircase))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStaircases()) { index = numberOfStaircases() - 1; }
      staircases.remove(aStaircase);
      staircases.add(index, aStaircase);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addStaircaseAt(aStaircase, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfTerraintypes()
  {
    return 0;
  }

  public Terraintype addTerraintype(String aName)
  {
    return new Terraintype(aName, this);
  }

  public boolean addTerraintype(Terraintype aTerraintype)
  {
    boolean wasAdded = false;
    if (terraintypes.contains(aTerraintype)) { return false; }
    Sprite existingSprite = aTerraintype.getSprite();
    boolean isNewSprite = existingSprite != null && !this.equals(existingSprite);
    if (isNewSprite)
    {
      aTerraintype.setSprite(this);
    }
    else
    {
      terraintypes.add(aTerraintype);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTerraintype(Terraintype aTerraintype)
  {
    boolean wasRemoved = false;
    //Unable to remove aTerraintype, as it must always have a sprite
    if (!this.equals(aTerraintype.getSprite()))
    {
      terraintypes.remove(aTerraintype);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addTerraintypeAt(Terraintype aTerraintype, int index)
  {  
    boolean wasAdded = false;
    if(addTerraintype(aTerraintype))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTerraintypes()) { index = numberOfTerraintypes() - 1; }
      terraintypes.remove(aTerraintype);
      terraintypes.add(index, aTerraintype);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTerraintypeAt(Terraintype aTerraintype, int index)
  {
    boolean wasAdded = false;
    if(terraintypes.contains(aTerraintype))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTerraintypes()) { index = numberOfTerraintypes() - 1; }
      terraintypes.remove(aTerraintype);
      terraintypes.add(index, aTerraintype);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTerraintypeAt(aTerraintype, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=objects.size(); i > 0; i--)
    {
      Object aObject = objects.get(i - 1);
      aObject.delete();
    }
    for(int i=staircases.size(); i > 0; i--)
    {
      Staircase aStaircase = staircases.get(i - 1);
      aStaircase.delete();
    }
    for(int i=terraintypes.size(); i > 0; i--)
    {
      Terraintype aTerraintype = terraintypes.get(i - 1);
      aTerraintype.delete();
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "]"
     + outputString;
  }
}