package model;

import java.util.ArrayList;

/**
 * This class represents an Actor and its scope represented by the tree of goals starting from the rootNode
 */
public class GmActor extends GmComponent{
	private ArrayList<GmGoal> rootNodes;
	private ArrayList<GmGoal> goals;
	
	public GmActor(String id, String description) {
		super(id, description);
		this.rootNodes = new ArrayList<GmGoal>();
		this.goals = new ArrayList<GmGoal>();
	}
	
	public ArrayList<GmGoal> getRootNodes() {
		return rootNodes;
	}

	public void setRootNode(GmGoal rootNode) {
		this.rootNodes.add(rootNode);
	}

	@Override
	public String toString() {
		//ANSI escape codes to color the output
		final String ANSI_RESET = "\u001B[0m";
//		final String ANSI_BLACK = "\u001B[30m";
//		final String ANSI_RED = "\u001B[31m";
//		final String ANSI_GREEN = "\u001B[32m";
//		final String ANSI_YELLOW = "\u001B[33m";
//		final String ANSI_BLUE = "\u001B[34m";
//		final String ANSI_PURPLE = "\u001B[35m";
		final String ANSI_CYAN = "\u001B[36m";
//		final String ANSI_WHITE = "\u001B[37m";
		
		return ANSI_CYAN + super.getDescription() + ANSI_RESET;
	}

	public ArrayList<GmGoal> getGoals() {
		return goals;
	}

	public void setGoals(ArrayList<GmGoal> goals) {
		this.goals = goals;
	}
	
	/**
	 * This method retrieves a specific goal from its goals
	 * @param id is the String id to match
	 * @return the goal we wants otherwise null
	 */
	public GmGoal getSpecificGoal(String id) {
		for (GmGoal goal : this.goals) {
			if ( goal.getId().equals(id) ) {
				return goal;
			}
		}
		return null;
	}
}
