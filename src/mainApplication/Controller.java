package mainApplication;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parser.Node;
import parser.Parser;
import javafx.scene.Scene;
import view.View;
import workspace.Workspace;

public class Controller {
	
	private List<Workspace> myWorkspaces;
	private Parser myParser;
	private int myActive;
	private Workspace myActiveWS;
	
	public Controller(){
		myWorkspaces = new ArrayList<>();
		myActiveWS = new Workspace(0);
		myWorkspaces.add(myActiveWS);
		myParser = new Parser();
	}
	
	public void parseAndEvaluate(String s){
		List<Node> list = myParser.parse(s);
		myActiveWS.evaluate(list);
	}
	
	public int createWorkspace(GUI gui){
		Workspace workspace=new Workspace(myWorkspaces.size());
		workspace.addObserver(gui);
		myWorkspaces.add(workspace);
		
		return myWorkspaces.size()-1;
	}
	
	public void setActive(int id){
		myActive = id;
		myActiveWS = myWorkspaces.get(myActive);
	}
	
	public Map<String,Double> getVariables(){
		return myActiveWS.getVariables();
	}
	
	public void clear(){
		myActiveWS.clear();
	}
	
	public void load(File f){
		myActiveWS = new Workspace(f);
	}
	
	public void save(String s){
		myActiveWS.save(s);
	}
	
	
}