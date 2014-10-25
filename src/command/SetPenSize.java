package command;

import java.util.List;

import actor.Turtle;
import parser.Node;
import workspace.Workspace;

public class SetPenSize extends Command {

	public SetPenSize(String s) {
		super(s,1);
	}

	@Override
	public double execute(List<Node> inputs, Workspace workspace)
			throws Exception {
		double size = inputs.get(0).evaluate(workspace);
		for(Turtle turtle :workspace.getActiveTurtles()){
			turtle.getPen().setSize(size);
		}
		return size;
	}

}
