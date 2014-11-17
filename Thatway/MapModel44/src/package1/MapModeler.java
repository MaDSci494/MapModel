package package1;

import java.io.FileOutputStream;
import java.io.FileReader;
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
import package1.Java_project539.Ramp;
import package1.Java_project539.Sprite;
import package1.Java_project539.Staircase;
import package1.Java_project539.Tile;
import package1.Java_project539.Object;

//Singleton. The Map Modeler serves as a medium between the UI and the map. 
public class MapModeler 
{
	private static MapModeler modelerInstance = new MapModeler();
	
	private Map map;
	public ArrayList<Object> objects;
	
	private ArrayList<Tile> currentSelection = new ArrayList<Tile>();
	// private constructor, since MapModeler should always have only one instance.
	private MapModeler() 
	{
		// TODO could have used enums
		
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
	
	public List<Tile> getCurrentSelection()
	{
		return currentSelection;
	}

	//Ran's helper functions
	//Ran: load & save function, map.java should only contain data and let modeler deal with saving
	public void saveMap(String path)
	{
		XMLOutputFactory xof = XMLOutputFactory.newInstance();
		try {
			XMLStreamWriter writer = xof.createXMLStreamWriter(new FileOutputStream(path),"utf-8");
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
					writer.writeCharacters(String.valueOf(t.getHeight().getHeightnum()));
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
			
			//start to save the ramp list
			writer.writeStartElement("Ramps");
			for(Ramp r : map.getRamps()){
				Tile t1 = r.getTile(0);
				Tile t2 = r.getTile(1);
				int levelnum = t1.getLevel().getLevelnum();
				writer.writeStartElement("Ramp");
				
				writer.writeStartElement("RampLevel");
				writer.writeCharacters(String.valueOf(levelnum));
				writer.writeEndElement();

				writer.writeStartElement("RampTile1x");
				writer.writeCharacters(String.valueOf(t1.getCoorX()));
				writer.writeEndElement();
				writer.writeStartElement("RampTile1y");
				writer.writeCharacters(String.valueOf(t1.getCoorY()));
				writer.writeEndElement();
				writer.writeStartElement("RampTile2x");
				writer.writeCharacters(String.valueOf(t2.getCoorX()));
				writer.writeEndElement();
				writer.writeStartElement("RampTile2y");
				writer.writeCharacters(String.valueOf(t2.getCoorY()));
				writer.writeEndElement();
				
				writer.writeEndElement();
			}
			
			//end ramp list
			writer.writeEndElement();
			
			//start staircase list
			writer.writeStartElement("Staircases");
			for(Level l : MapModeler.GetInstance().getMap().getLevels()){
				for(Tile t : l.getTiles()){
					if(t.hasStaircase()){
						Tile t1 = t.getStaircase().getTile(0);
						Tile t2 = t.getStaircase().getTile(1);
						//avoid confilcts
						if(t1.getCoorX()==t.getCoorX()&&t1.getCoorY()==t.getCoorY()&&t1.getLevel().getLevelnum()==t.getLevel().getLevelnum()){
							String temp = "("+t1.getCoorX()+","+t1.getCoorY()+","+t1.getLevel().getLevelnum()+")"+":"
								+"("+t2.getCoorX()+","+t2.getCoorY()+","+t2.getLevel().getLevelnum()+")";
							//(x,y,l)
							System.out.println(temp);
							writer.writeStartElement("Staircase");
							writer.writeCharacters(temp);
							writer.writeEndElement();
						}
						
					}	
				}
			}
			//end staircase list
			writer.writeEndElement();
			
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
							Height height;
							if(h==0) height = new Height(h,0,0,0);//Ran: how we going to new a height?
							else height = Height.generateHeight(h);
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
						else
						{
							if(reader.getLocalName().equals("Ramps")&&event==XMLStreamConstants.START_ELEMENT)
							{
								reader.next();
								while(reader.getLocalName().equals("Ramp")&&event==XMLStreamConstants.START_ELEMENT){
									reader.next();reader.next();
									int levelnum = Integer.parseInt(reader.getText());
									reader.next();reader.next();reader.next();
									int t1x = Integer.parseInt(reader.getText());
									reader.next();reader.next();reader.next();
									int t1y = Integer.parseInt(reader.getText()); 
									reader.next();reader.next();reader.next();
									int t2x = Integer.parseInt(reader.getText());
									reader.next();reader.next();reader.next();
									int t2y = Integer.parseInt(reader.getText()); 
									reader.next();reader.next();reader.next();
									//should stop at next Ramp now or </Ramps> but doesn't matter over here
									Tile t1 = loadMap.getLevel(levelnum).getTileByXY(t1x,t1y);
									Tile t2 = loadMap.getLevel(levelnum).getTileByXY(t2x,t2y);
									loadMap.addRamp(new Ramp(t1,t2,loadMap));
								}
							}else{
								if(reader.getLocalName().equals("Staircases")&&event==XMLStreamConstants.START_ELEMENT){
									reader.next();
									while(reader.getLocalName().equals("Staircase")&&event==XMLStreamConstants.START_ELEMENT){
										reader.next();
										String s = reader.getText();
										String[] ss = s.split(":");
										String ori = ss[0];
										String des = ss[1];
										String origin = ori.substring(1,ori.length()-1);
										String destination = des.substring(1,des.length()-1);
										String[] originP = origin.split(",");
										String[] destinationP = destination.split(",");
										int oriX = Integer.parseInt(originP[0]);
										int oriY = Integer.parseInt(originP[1]);
										int oriL = Integer.parseInt(originP[2]);
										int desX = Integer.parseInt(destinationP[0]);
										int desY = Integer.parseInt(destinationP[1]);
										int desL = Integer.parseInt(destinationP[2]);
										Tile t1 = loadMap.getLevel(oriL).getTileByXY(oriX,oriY);
										Tile t2 = loadMap.getLevel(desL).getTileByXY(desX,desY);
										Tile[] t = {t1,t2};
										Staircase stairs = new Staircase(objects_t.get(4).getSprite(), t);
										reader.next();reader.next();
									}
									reader.next();
								}
							}
						}// end else
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
	}
}
