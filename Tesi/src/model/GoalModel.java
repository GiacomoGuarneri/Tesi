package model;

import java.util.ArrayList;

/**
 * This class represents the entire goal model and the components in it
 */
public class GoalModel {
	private ArrayList<GmComponent> wfpArray;
	private ArrayList<GmComponent> smArray;
	private ArrayList<GmComponent> assetArray;
	private ArrayList<GmRelationship> relationshipsArray;
	private ArrayList<GmActor> actorsArray;
	
	public GoalModel() {
		this.wfpArray = new ArrayList<GmComponent>();
		this.smArray = new ArrayList<GmComponent>();
		this.assetArray = new ArrayList<GmComponent>();
		this.relationshipsArray = new ArrayList<GmRelationship>();
		this.actorsArray = new ArrayList<GmActor>();
	}
	
 	public ArrayList<GmComponent> getWfpArray() {
		return wfpArray;
	}
	
	public void setWfpArray(ArrayList<GmComponent> wfpArray) {
		this.wfpArray = wfpArray;
	}

	public ArrayList<GmComponent> getSmArray() {
		return smArray;
	}

	public void setSmArray(ArrayList<GmComponent> smArray) {
		this.smArray = smArray;
	}

	public ArrayList<GmComponent> getAssetArray() {
		return assetArray;
	}

	public void setAssetArray(ArrayList<GmComponent> assetArray) {
		this.assetArray = assetArray;
	}

	public ArrayList<GmRelationship> getRelationshipsArray() {
		return relationshipsArray;
	}

	public void setRelationshipsArray(ArrayList<GmRelationship> relationshipsArray) {
		this.relationshipsArray = relationshipsArray;
	}

	public ArrayList<GmActor> getActorsArray() {
		return actorsArray;
	}

	public void setActorsArray(ArrayList<GmActor> actorsArray) {
		this.actorsArray = actorsArray;
	}

}
