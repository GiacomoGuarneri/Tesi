package utilityPrinters;

import java.util.List;
import java.util.function.Function;

import model.GmActor;
import model.GmGoal;
import model.GoalModel;

/**
 * This class encapsulates all the functions related to printing the model
 */
public class ModelPrinter {
	
	/**
	 * Prints all the goal model actors in the model
	 * @param goalModel
	 */
	public void printModel(GoalModel goalModel) {
		Function<GmGoal, List<GmGoal>> getChildrenFunc = node -> node.getChildren();
		for (GmActor actor : goalModel.getActorsArray()) {
			System.out.println(actor.toString());
			//printTree("", actor.getRootNode(), getChildrenFunc, true);
			for (GmGoal root : actor.getRootNodes()) {
				printTree("", root, getChildrenFunc, true);
			}
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
