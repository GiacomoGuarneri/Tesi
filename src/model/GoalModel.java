package model;

import java.util.ArrayList;

/**
 * This class represents the entire goal model and the components in it
 */
public class GoalModel {
	private ArrayList<GmWfp> wfpArray;
	private ArrayList<GmSecurityMeasure> smArray;
	private ArrayList<GmAsset> assetArray;
	private ArrayList<GmRelationship> relationshipsArray;
	private ArrayList<GmActor> actorsArray;
	
	public GoalModel() {
		this.wfpArray = new ArrayList<GmWfp>();
		this.smArray = new ArrayList<GmSecurityMeasure>();
		this.assetArray = new ArrayList<GmAsset>();
		this.relationshipsArray = new ArrayList<GmRelationship>();
		this.actorsArray = new ArrayList<GmActor>();
	}
	
 	public ArrayList<GmWfp> getWfpArray() {
		return wfpArray;
	}
	
	public void setWfpArray(ArrayList<GmWfp> wfpArray) {
		this.wfpArray = wfpArray;
	}

	public ArrayList<GmSecurityMeasure> getSmArray() {
		return smArray;
	}

	public void setSmArray(ArrayList<GmSecurityMeasure> smArray) {
		this.smArray = smArray;
	}

	public ArrayList<GmAsset> getAssetArray() {
		return assetArray;
	}

	public void setAssetArray(ArrayList<GmAsset> assetArray) {
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
