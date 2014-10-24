package features;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import mainApplication.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;



public class Toolbar extends ToolBar implements Feature{
	//public Menu myMenu;

public Toolbar(){
	super(new Button("New"),
			new Separator(),
		     new Button("Open"),
		     new Separator(),
		     new Button("Save"));
/*	Label languageLabel = new Label("Language Pack:");
	ComboBox<String> languageCB = new ComboBox<String>();
	languageCB.getItems().addAll("English", "Spanish", "Chinese");
	super.getItems().addAll(languageLabel, languageCB);
	languageCB.setOnAction(new EventHandler<ActionEvent>(){

		@Override
		public void handle(ActionEvent arg0) {
			System.out.println(languageCB.getSelectionModel().getSelectedItem());	
		}
	
		
	});
	*/
	//super.getChildren().add(new Button("test"));
	//super.getItems().add(new Button("test"));
	//this.getChildren().add(myMenu);
}

//private void setActions(MenuItem i) {
//	i.setOnAction(new EventHandler<ActionEvent>(){
//		@Override
//		public void handle(ActionEvent event) {
//		}
//	});
//}

@Override
public void update() {
	// TODO Auto-generated method stub
	
}

}
