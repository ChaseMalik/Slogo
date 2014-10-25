package command;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import actor.Turtle;
import parser.Node;
import workspace.Workspace;

public class TellCommand extends BasicListCommand {

	public TellCommand(String s) {
		super(s,1,1);

	}

	@Override
	public double execute(List<Node> inputs, Workspace workspace) throws Exception {
		checkListException(inputs);
	        Map<Integer,Turtle> map = workspace.getTurtles();
		Set<Integer> ids = new HashSet<>();
		int id = 0;
		for(int i = 1; i<getBracketIndex(inputs); i++){
			id = (int) inputs.get(i).evaluate(workspace);
			if(!map.containsKey(id)){
				workspace.createTurtle(id);
			}
			ids.add(id);
		}
		for(Integer i: map.keySet()){
			map.get(i).setActive(ids.contains(i));
		}

		return id;
	}

}
