package client;

import java.util.List;
import java.util.function.Function;

import gmConverter.JsonParser;
import gmConverter.JsonRiskParser;
import gmConverter.XmlParser;
import model.GmActor;
import model.GmGoal;
import model.GoalModel;

/**
 * This is the main class of the program and the starting point
 */
public class Client {

	public static void main(String[] args) {
		
		GoalModel goalModel = new GoalModel();
		JsonParser jsonParser = new JsonParser();
		JsonRiskParser jsonRiskParser = new JsonRiskParser();
		XmlParser xmlParser = new XmlParser();
		
		jsonParser.start(goalModel);
		
		System.out.println("-------------------------RISK-----------------------------------");
		System.out.println();
		
		jsonRiskParser.start(goalModel);
		
		System.out.println("--------------------------XML-----------------------------------");
		System.out.println();
		
		xmlParser.start(goalModel);
		
		//DEBUG ZONE
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("--------------------------------GOAL MODEL ACTORS--------------------------------------");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println();	
		
		Function<GmGoal, List<GmGoal>> getChildrenFunc = node -> node.getChildren();
		for (GmActor actor : goalModel.getActorsArray()) {
			System.out.println(actor.toString());
			printTree("", actor.getRootNode(), getChildrenFunc, true);
			System.out.println();
		}
		
	}
	
	/**
	 * Print a tree structure in ASCII format.
	 * @param prefix is the current prefix. Use "" in initial call!
	 * @param node is the current node. Pass the root node of your tree in initial call.
	 * @param getChildrenFunc is a {@link Function} that returns the children of a given node.
	 * @param isTail if the node is the last of its siblings. Use true in initial call. (This is needed for pretty printing.)
	 */
	private static void printTree(String prefix, GmGoal node, Function<GmGoal, List<GmGoal>> getChildrenFunc, boolean isTail) {
		String nodeName = node.toString();
	    String nodeConnection = isTail ? "|__ " : "|-- ";
	    System.out.println(prefix + nodeConnection + nodeName);
	    List<GmGoal> children = getChildrenFunc.apply(node);
	    for (int i = 0; i < children.size(); i++) {
	        String newPrefix = prefix + (isTail ? "    " : "|   ");
	        printTree(newPrefix, children.get(i), getChildrenFunc, i == children.size()-1);
	    }
	}

}
