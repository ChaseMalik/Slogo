package components;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import features.Feature;
import features.TextDisplayFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import sun.misc.Queue;


public class LeftPane extends VBox implements Feature {
	
	/**
	 * Initializes the display area for console history as well as error display
	 */
	protected GridPane myTextPane;
	private ScrollPane myScrollPane;
	protected int myTextIndex;
	private Map<String, Double> myVariables;
	public LeftPane(){
		super();
		//myVariables = variables;
		setStyle("-fx-background-color: #336666");
		setPrefHeight(500);
		setPrefWidth(200);
		Label dummy = new Label("Label for Text Display");
		setMargin(dummy, new Insets(5));
		//getChildren().add(dummy);
		//super.setFill(Color.DODGERBLUE);
		myTextIndex = 0;
		myTextPane = new GridPane();
		myScrollPane = new ScrollPane();
		myScrollPane.setContent(myTextPane);
		myScrollPane.setPrefHeight(450);
		myScrollPane.setPrefWidth(180);
		super.getChildren().addAll(dummy, myScrollPane);		
		setMargin(myScrollPane, new Insets(20));
		this.addClear();

	}
	public void clear(){
		myTextPane.getChildren().clear();
		myTextIndex = 0;
	}
	public void update(Node b){
		myTextPane.add(b, 0, myTextIndex);
		myTextIndex++;
	}
	
	public void updateVars(Map<String, Double> vars){
		myTextIndex = 0;
		for(String s:vars.keySet()){
			myTextPane.add(new Label(s), 0, myTextIndex);
			myTextPane.add(new Label(vars.get(s).toString()), 1, myTextIndex);
			myTextIndex++;
		}
	}
	private void addClear(){
		Button clear = new Button("Clear");
		clear.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				LeftPane.this.clear();
			}		
		});
		this.getChildren().add(clear);
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}

