package buttonInput;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import mainApplication.GUI;
import features.Feature;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;



public class Toolbar extends Node{
	public Menu myMenu;

public Toolbar(){
	MenuItem test = new MenuItem("test");
	MenuItem test2 = new MenuItem("test2");
	List<MenuItem> list = new ArrayList<MenuItem>();
	list.add(test);
	list.add(test2);
	Menu file = new Menu("test3");
	for(MenuItem i:list){
		setActions(i);
		file.getItems().add(i);

	}
	myMenu=file;
}

private void setActions(MenuItem i) {
	i.setOnAction(new EventHandler<ActionEvent>(){
		@Override
		public void handle(ActionEvent event) {
		}
	});
}

@Override
protected boolean impl_computeContains(double arg0, double arg1) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public BaseBounds impl_computeGeomBounds(BaseBounds arg0, BaseTransform arg1) {
	// TODO Auto-generated method stub
	return null;
}

@Override
protected NGNode impl_createPeer() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Object impl_processMXNode(MXNodeAlgorithm arg0,
		MXNodeAlgorithmContext arg1) {
	// TODO Auto-generated method stub
	return null;
}
}
