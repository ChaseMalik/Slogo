package command;

import java.util.List;
import actor.Turtle;
import javafx.geometry.Point2D;
import parser.Node;
import workspace.Workspace;

public class SetPositionCommand extends Command {

	public SetPositionCommand(String s){
		super(s);
	}
	
	
	@Override
	protected int setNumInputs() {
		return 2;
	}

	@Override
	public double execute(List<Node> inputs, Workspace workspace) {
		double newX = inputs.get(0).evaluate(workspace);
		double newY = inputs.get(1).evaluate(workspace);
		Turtle turtle=workspace.getTurtles().get(0);
		Point2D oldP = turtle.getPoint();
		turtle.updatePosition(newX,newY,turtle.getAngle());
		return oldP.distance(newX,newY);
	}

}
