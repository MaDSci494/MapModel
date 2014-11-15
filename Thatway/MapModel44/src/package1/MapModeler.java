package package1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import package1.Java_project539.Height;
import package1.Java_project539.Level;
import package1.Java_project539.Map;
import package1.Java_project539.Sprite;
import package1.Java_project539.Tile;
import package1.Java_project539.Object;

//Singleton. The Map Modeler serves as a medium between the UI and the map. 
public class MapModeler 
{
	private static MapModeler modelerInstance = new MapModeler();
	
	private Map map;
	public ArrayList<Object> objects;
	
	public boolean rampsTrigger = false;
	private ArrayList<Tile> currentSelection = new ArrayList<Tile>();
	
	
	
	// private constructor, since MapModeler should always have only one instance.
	private MapModeler() 
	{
	}

	// getter for the Singleton.
	public static MapModeler GetInstance() 
	{
		return modelerInstance;
	}

	public void generateNewMap(int length, int height) 
	{
		map = new Map(length, height);
		//Ran:inistailize object array
		objects = new ArrayList<Object>();
		//do not change order
		objects.add(new Object("Destructible",new Sprite("Destructible.png")));
		objects.add(new Object("Inaccessible",new Sprite("inaccessible.png")));
		objects.add(new Object("Movable",new Sprite("movable.png")));
		objects.add(new Object("Spawning",new Sprite("spawning.png")));
		objects.add(new Object("Staircase",new Sprite("staircase.png")));
		objects.add(new Object("Water",new Sprite("water.png")));
		objects.add(new Object("Ramp",new Sprite("ramp.png")));//<- do we need this?
		//Ran: new level 0 to map
		Level level0 = new Level(0,0,map);
	    map.addLevel(level0);
	    
	}

	public boolean hasMap() 
	{
		return map != null;
	}
	
	public int getMapWidth()
	{
		return map.getSizeX();
	}
	
	public int getMapHeight()
	{
		return map.getSizeY();
	}

	public void switchToRampsMode() 
	{
		int currentLevel = 0; // TODO get the real current level
		rampsTrigger = true;
		
	}
	
	private Level getCurrentLevel()
	{
		int currentLevel = 0; // TODO get the real current level
		
		return null;
	}
	
	public List<Tile> getCurrentSelection()
	{
		return currentSelection;
	}

	public void clearAllTriggers() 
	{
		rampsTrigger = false;
		// TODO remove others
		
	}
	//Ran's helper functions
	//TODO Ran: load & save function, map.java should only contain data and let modeler deal with saving
	public void saveMap(String path)
	{
		XMLOutputFactory xof = XMLOutputFactory.newInstance();
		try {
			XMLStreamWriter writer = xof.createXMLStreamWriter(new FileWriter(path));
			//start document
			writer.writeStartDocument("utf-8","1.0");
			//identification element
			writer.writeStartElement("MapModel44");
			writer.writeAttribute("Version","1.0");
			writer.writeEndElement();
			//start map
			writer.writeStartElement("Map");
			writer.writeAttribute("sizeX",String.valueOf(map.getSizeX()));
			writer.writeAttribute("sizeY",String.valueOf(map.getSizeY()));
			//Ran: Currently I will only save all levels
			for(int i=0;i<map.numberOfLevels();i++){
				//start new level
				writer.writeStartElement("Level");
				Level l = map.getLevel(i);
				writer.writeAttribute("levelnum",String.valueOf(l.getLevelnum()));
					for(int j=0;j<l.numberOfTiles();j++){
						//start new tile
						writer.writeStartElement("Tile");
						Tile t = l.getTile(j);
						writer.writeAttribute("coorX",String.valueOf(t.getCoorX()));
						writer.writeAttribute("coorY",String.valueOf(t.getCoorY()));
						//Ran: then I need to save tile's other attributes but I need progress from Rubing
						//end tile
						writer.writeEndElement();
					}
				//end level
				writer.writeEndElement();
			}
			//end map
			writer.writeEndElement();
			//close document
			writer.flush();
			writer.close();
			
		} catch (XMLStreamException e) {
			System.out.println("Error xml writer.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error writing file " + path);
			e.printStackTrace();
		}
	}
	
	public void loadMap(String path)
	{
		
	}
	
	public Map getMap(){
		return map;
	}
	
	public void insertLevel(){ //insert new level to map the model
		int levelnum = map.numberOfLevels();//since we are counting from level 0
		Level templevel = new Level(levelnum,levelnum,map);
	    map.addLevel(templevel);
	    /*
	    //fill leveltemp with tiles
	    for(int i=0;i<map.getSizeX();i++){
	    	for(int j=0;j<map.getSizeY();j++){
	    		Tile temp = new Tile(i,j,null,new Height(0,0,0,0),templevel); //height use (aHeight,R,G,B)
	    		templevel.addTile(temp);
	    	}
	    }
	   */
	}
}
