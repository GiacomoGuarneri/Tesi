package model;

import java.util.ArrayList;

/**
 * This class represents an Actor and its scope represented by the tree of goals starting from the rootNode
 */
public class GmActor {
	private String id;
	private String description;
	private GmGoal rootNode;
	private ArrayList<GmGoal> goals;
	
	public GmActor(String id, String description) {
		this.id = id;
		this.description = description;
		this.goals = new ArrayList<GmGoal>();
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public GmGoal getRootNode() {
		return rootNode;
	}

	public void setRootNode(GmGoal rootNode) {
		this.rootNode = rootNode;
	}

	@Override
	public String toString() {
		return "GmActor [id=" + id + ", description=" + description + ", rootNode=" + rootNode + "]";
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
