package package1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
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
	}

	// getter for the Singleton.
	public static MapModeler GetInstance() 
	{
		return modelerInstance;
	}

	public void generateNewMap(int length, int height) 
	{
		map = new Map(length, height);
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
			writer.writeCharacters("Version1.0");
			
			//start map
			writer.writeStartElement("Map");
			//size X
			writer.writeStartElement("sizeX");
			writer.writeCharacters(String.valueOf(map.getSizeX()));
			writer.writeEndElement();
			//size Y
			writer.writeStartElement("sizeY");
			writer.writeCharacters(String.valueOf(map.getSizeY()));
			writer.writeEndElement();
			
			//Ran: Currently I will only save all levels
			for(int i=0;i<map.numberOfLevels();i++){
				//start new level
				writer.writeStartElement("Level");
				Level l = map.getLevel(i);
				writer.writeStartElement("levelnum");
				writer.writeCharacters(String.valueOf(l.getLevelnum()));
				writer.writeEndElement();
				//save each tile that belongs to this level
				for(int j=0;j<l.numberOfTiles();j++){
					//start new tile
					writer.writeStartElement("Tile");
					Tile t = l.getTile(j);
					
					writer.writeStartElement("coorX");
					writer.writeCharacters(String.valueOf(t.getCoorX()));
					writer.writeEndElement();
					
					writer.writeStartElement("coorY");
					writer.writeCharacters(String.valueOf(t.getCoorY()));
					writer.writeEndElement();
					
					//Ran: then I need to save tile's other attributes but I need progress from Rubing
					//save tile height & object if any
					writer.writeStartElement("height");
					writer.writeCharacters("0");
					writer.writeEndElement();
					
					writer.writeStartElement("object");
					if(t.getObject()!=null){
						writer.writeCharacters(t.getObject().getName());
					}else{
						writer.writeCharacters("null");
					}
					writer.writeEndElement();
					
					//end tile
					writer.writeEndElement();
				}
				//end level
				writer.writeEndElement();
			}
			//end map
			writer.writeEndElement();
			//end mapmodel44
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
	
	public void loadMap(String path)//Ran: warning:no error handling
	{
		Map loadMap = null;
		XMLInputFactory xof = XMLInputFactory.newInstance();
		
		int mapSizeX = 0;
		int mapSizeY = 0;
		int currentLevel = 0;
		
		//start a temp objects array in-case we messed up some where so that we don't interchange the original one
		//Ran:inistailize object array
		ArrayList<Object> objects_t = new ArrayList<Object>();
		//do not change order
		objects_t.add(new Object("Destructible",new Sprite("Destructible.png")));
		objects_t.add(new Object("Inaccessible",new Sprite("inaccessible.png")));
		objects_t.add(new Object("Movable",new Sprite("movable.png")));
		objects_t.add(new Object("Spawning",new Sprite("spawning.png")));
		objects_t.add(new Object("Staircase",new Sprite("staircase.png")));
		objects_t.add(new Object("Water",new Sprite("water.png")));
		objects_t.add(new Object("Ramp",new Sprite("ramp.png")));//<- do we need this?
		
		
		try{
			XMLStreamReader reader = xof.createXMLStreamReader(new FileReader(path));
			//check if it is mapmadel44 version1.0
			int event = reader.next();
			if(!reader.getLocalName().equals("MapModel44")){
				System.out.println("Invalid MapModel44 file.");
				return;
			}
			reader.next();
			if(reader.hasText()){
				if(!reader.getText().equals("Version1.0")){
					System.out.println("Not version 1.0.");
					return;
				}
			}
			reader.next();
			//System.out.println("Start parsing "+ reader.getLocalName());
			//start parsing the map
			while(reader.hasNext()){
				if(reader.getLocalName().equals("Map")&&event==XMLStreamConstants.START_ELEMENT){
				//initiate the map
				reader.next(); reader.next();
				mapSizeX = Integer.parseInt(reader.getText());
				reader.next(); reader.next(); reader.next();
				mapSizeY = Integer.parseInt(reader.getText());
				loadMap = new Map(mapSizeX,mapSizeY);
				//System.out.println("Start a new map with "+mapSizeX + "*" +mapSizeY);
				reader.next();
				}else{
					if(reader.getLocalName().equals("Level")&&event==XMLStreamConstants.START_ELEMENT){
						reader.next();reader.next();
						currentLevel = Integer.parseInt(reader.getText());
						loadMap.addLevel(new Level(currentLevel,currentLevel,loadMap));
						//System.out.println("Start a new level with "+currentLevel);
						reader.next();
					}
					else{
						if(reader.getLocalName().equals("Tile")&&event==XMLStreamConstants.START_ELEMENT){
							reader.next();reader.next();
							int x = Integer.parseInt(reader.getText());
							reader.next();reader.next();reader.next();
							int y = Integer.parseInt(reader.getText());
							reader.next();reader.next();reader.next();
							int h = Integer.parseInt(reader.getText());
							Height height = new Height(h,0,0,0);//Ran: how we going to new a height?
							reader.next();reader.next();reader.next();
							String o = reader.getText();
							reader.next();reader.next();
							Tile t = loadMap.getLevel(currentLevel).getTileByXY(x,y);
							t.setHeight(height);
							switch(o){
								case "Destructible":
									t.setObject(objects_t.get(0));
									break;
								case "Inaccessible":
									t.setObject(objects_t.get(1));
									break;
								case "Movable":
									t.setObject(objects_t.get(2));
									break;
								case "Spawning":
									t.setObject(objects_t.get(3));
									break;
								case "StairCase":
									t.setObject(objects_t.get(4));
									break;
								case "Water":
									t.setObject(objects_t.get(5));
									break;
								default://default we don't set anything
							}
							//System.out.println("Change tile " + x + "," + y + " with height:" + h + " object:"+o);
						}
					}
				}
						
				//reader should stop at an end_element
				//proceed
				event = reader.next();
			}
			//replace the original ones
			map = loadMap;
			objects = objects_t;
			MapModelFrame.getInstance().fillWithMapModeler();
		}catch (XMLStreamException e) {
			System.out.println("Error xml writer.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error writing file " + path);
			e.printStackTrace();
		}
		
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
