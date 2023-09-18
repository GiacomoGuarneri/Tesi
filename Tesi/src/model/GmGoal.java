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
	
	public GmGoal getParent() {
		return parent;
	}

	public void setParent(GmGoal parent) {
		this.parent = parent;
	}
	
	/**
	 * This method removes the parent setting it to null
	 */
	public void removeParent() {
		this.parent = null;
	}	
	
	/**
	 * This method adds an already created sub-goal as child
	 * @param child is the sub-goal to append
	 */
	public void addChild(GmGoal child) {
		this.children.add(child);
		child.setParent(this);
	}
	
	public List<GmGoal> getChildren() {
		return children;
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
	
	@Override
	public String toString() {
		return "GmGoal [id=" + this.getId() + ", description=" + this.getDescription() + ", energyConsumption=" + this.getEnergyConsumption()
				+ ", residualRisk=" + this.getResidualRisk() + "]";
	}
		
}
