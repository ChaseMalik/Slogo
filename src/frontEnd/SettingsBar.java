package frontEnd;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import parser.Node;
import parser.Parser;
import actor.Turtle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


public class SettingsBar extends HBox implements Feature{
	
	private int mySceneHeight;
	private static int mySceneWidth;
	private RightPane myRightPane;
	private LeftPane myLeftPane;
	private ResourceBundle myNumberResources;
	private Turtle myTurtle;
	private static ComboBox<String> languageCB;
	private static ComboBox<String> colourCB;
	private ColorPicker displayColourPicker;
	
	
public SettingsBar(){
	
	super();
	setPrefWidth(mySceneWidth);
	setSpacing(10);
	setStyle("-fx-background-color: cornsilk;");
	
	Button startButton = new Button("Start");
	startButton.setPrefSize(100, 20);
	startButton.setOnAction(new EventHandler<ActionEvent>(){
		@Override
		public void handle(ActionEvent event) {
			System.out.println("start button clicked");
			System.out.println(languageCB.getValue());
			System.out.println(displayColourPicker.getValue());
		}
		
	});
	
	
	Label colourLabel = new Label("Pen Colour:");
	Label languageLabel = new Label("Language Pack:");
	
	//displayColourPicker = new ColorPicker();
	
	
	languageCB = new ComboBox<String>();
	languageCB.getItems().addAll("English", "Spanish", "Chinese");
	

	this.getChildren().addAll(languageLabel, languageCB, colourLabel);
	this.setPrefHeight(34);
}
	
public void updateColorPicker(ColorPicker cp){
	this.getChildren().add(cp);
}


@Override
public void update() {
	// TODO Auto-generated method stub
	
}
	

}