package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class extends that of GmComponent and represents a Goal that is part of the goal-tree of an Actor
 */
public class GmGoal extends GmComponent{
	private GmGoal parent = null;
	private List<GmGoal> children = new ArrayList<GmGoal>();
	
	public GmGoal(String id, String description) {
		super(id, description);
	}
	
	public GmGoal(String id, String description, GmGoal parent) {
		super(id, description);
		this.parent = parent;
	}

	public GmGoal getParent() {
		return parent;
	}

	public void setParent(GmGoal parent) {
		parent.addChild(this);
		this.parent = parent;
	}
	
	/**
	 * This method removes the parent setting it to null
	 */
	public void removeParent() {
		this.parent = null;
	}	
	
	public List<GmGoal> getChildren() {
		return children;
	}
	
	/**
	 * This method first creates a sub-goal and then adds it as child
	 * @param id is the id of the sub-goal
	 * @param description is the description of the sub-goal
	 */
	public void addChild(String id, String description) {
		GmGoal child = new GmGoal(id, description);
		child.setParent(this);
		this.children.add(child);
	}
	
	/**
	 * This method adds an already created sub-goal as child
	 * @param child is the sub-goal to append
	 */
	public void addChild(GmGoal child) {
		child.setParent(this);
		this.children.add(child);
	}
	
	/**
	 * This method says is the current goal is a root
	 * @return
	 */
	public boolean isRoot() {
		return (this.parent == null);
	}
	
	/**
	 * This method says is the current goal is a leaf
	 * @return
	 */
	public boolean isLeaf() {
		return this.children.size() == 0;
	}
		
}
