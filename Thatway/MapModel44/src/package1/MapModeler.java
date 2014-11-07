package package1;

import java.util.List;
import java.util.ArrayList;

import package1.Java_project539.Level;
import package1.Java_project539.Map;
import package1.Java_project539.Tile;

//Singleton. The Map Modeler serves as a medium between the UI and the map. 
public class MapModeler 
{
	private static MapModeler modelerInstance = new MapModeler();
	
	private Map map;
	
	
	private boolean rampsTrigger = false;
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

}
