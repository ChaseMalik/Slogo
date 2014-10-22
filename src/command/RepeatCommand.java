package command;

import java.util.List;
import parser.Node;
import workspace.Workspace;

public class RepeatCommand extends Command{

    public RepeatCommand(String s){
        super(s);
        myNumLists=1;
    }
    @Override
    protected int setNumInputs () {
        // TODO Auto-generated method stub
        return 2;
    }
    

    @Override
    public double execute (List<Node> inputs, Workspace workspace) {
        // TODO Auto-generated method stub
        for(int i=0; i<inputs.get(0).evaluate(workspace); i++){
            for(int j=1; j<inputs.size(); j++){
                inputs.get(j).evaluate(workspace);
            }
        }
        return 0;
    }

}
