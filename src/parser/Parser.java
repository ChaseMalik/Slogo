package parser;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import exceptions.NotEnoughInputsException;
import exceptions.UnclosedListException;
import javafx.scene.input.KeyCode;

public class Parser {
    private List<Node> myTreeHeads;
    private int myIndex;
    private ResourceBundle myLanguage;
    private ResourceBundle myCommands;
    private static final String RESOURCE_BUNDLE="resources.languages/";
    private static final String COMMAND_BUNDLE="resources.languages/Command";
    private static final String DEFAULT_LANGUAGE="English";
    public Parser(){
        myTreeHeads=new ArrayList<Node>();
        changeLanguage(DEFAULT_LANGUAGE);
        myCommands=ResourceBundle.getBundle(COMMAND_BUNDLE);
    }
    /**
     * takes in a string and parses it by puts all commands into a graph 
     * of Nodes 
     * @param string
     * @return the string used if the commands were successful
     */
   public List<Node> parse(String string) throws Exception{
       myTreeHeads.clear();
       string=string.toLowerCase();
       string=string.replaceAll("[\\\t|\\\n|\\\r]"," ");
       String[] commands=string.split("\\s+");
       myIndex=0;
       while(myIndex<commands.length){
           Node n=makeTree(commands);
           myTreeHeads.add(n);

       }
       return myTreeHeads;
   }
   public void changeLanguage(String language){
       myLanguage=ResourceBundle.getBundle(RESOURCE_BUNDLE+language);
   }
   private Node makeTree(String[] s) throws Exception{
       Node node=makeNode(s[myIndex]);
       
       myIndex++;
       Node next=null;
       try{
       for(int i=0; i<node.getCommand().getNumInputs(next); i++){
              next=makeTree(s);
              node.addChild(next);          
       }
       }catch(Exception e){
           if(node.getCommand().isList())
               throw new UnclosedListException("");
           else
               throw new NotEnoughInputsException(node.getCommand().toString());
       }
       return node;
   }
   private Node makeNode(String command) throws Exception{
       commandFactory factory=new basicCommandCreator();
       Set<String> keys=myLanguage.keySet();
       String name="Error";
       for(String key : keys){
           String value = myLanguage.getString(key);
           if(command.matches(value)){
               factory=new basicCommandCreator();
               name=myCommands.getString(key);
               if(!key.equals("Command")){
                   break;
               }
           }
       }
       
       return new Node(factory.createCommand(name, command));
}
}   
