package model;

/**
 * This class represents an Actor and its scope represented by the tree of goals starting from the rootNode
 */
public class GmActor {
	private String id;
	private String description;
	private GmGoal rootNode;
	
	public GmActor(String id, String description) {
		this.id = id;
		this.description = description;
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
}
