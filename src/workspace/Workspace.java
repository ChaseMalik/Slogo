package workspace;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import actor.Pen;
import actor.Turtle;
import parser.CommandObject;
import parser.Node;
import properties.Position;

public class Workspace extends Observable implements Observer{
	private static final Position DEFAULT_POSITION = new Position(0,0,Math.PI/2);
	private int myID;
	private List<Turtle> myTurtles;
	private String myLanguage;
	private Properties myProperties;
	private Map<String,Double> myVariables;
	private Map<String,CommandObject> myCommands; 
	
	
	public Workspace(int id){
		myTurtles = new ArrayList<>();
		myTurtles.add(new Turtle(DEFAULT_POSITION,new Pen(),0));
		myTurtles.get(0).addObserver(this);
		myID = id;
		myVariables=new HashMap<String,Double>();
	}
	public Workspace(File f) {
		// TODO Auto-generated constructor stub
	}
	
	//TODO this won't work because it will reassign variables incorrectly
	public void evaluate(List<Node> list) {
		for(int i = 0; i<list.size();i++){
					list.get(i).evaluate(this);
				
			}
		}
	
	
	public Map<String, Double> getVariables() {
		// TODO Auto-generated method stub
		return myVariables;
	}
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	public void save(String s) {
		// TODO Auto-generated method stub
		
	}
    public List<Turtle> getTurtles () {
        // TODO Auto-generated method stub
        return myTurtles;
    }
    @Override
    public void update (Observable arg0, Object arg1) {
        // TODO Auto-generated method stub
        notifyObservers(arg1);
    }
}
