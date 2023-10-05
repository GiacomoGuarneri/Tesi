package gmPropagation;

import java.util.ArrayList;
import java.util.Scanner;

import model.GmActor;
import model.GmAsset;
import model.GmComponent;
import model.GmEnforceRelationship;
import model.GmGoal;
import model.GmProtectRelationship;
import model.GmRelationship;
import model.GmSecurityMeasure;
import model.GmWfp;
import model.GoalModel;

/**
 * This class performs propagation of energy consumption
 */
public class EnergyPropagation implements PropagationPolicy {

	/**
	 * This method starts the energy propagation
	 */
	@Override
	public void startPropagation(GoalModel goalModel, Scanner scanner, ArrayList<String> toExclude) {
		
		//First we propagate EN of sm/wfp to connected goals (enforce relationships)
		enforcePropagation(goalModel, toExclude);
		
		//After having updated all leaf nodes we have to update assets EN by down propagating updated goal values via protect relationships
		protectPropagation(goalModel);
		
		//After having updated the leaf goals we need to propagate EN among goal tree
		goalPropagation(goalModel);
		
	}
	
	/**
	 * Propagate EN from measures participating in Enforce relationships to respective goals
	 * @param goalModel
	 * @param toExclude is a list of measure to exclude from propagation
	 */
	public static void enforcePropagation(GoalModel goalModel, ArrayList<String> toExclude) {
		for (GmRelationship relationship : goalModel.getRelationshipsArray()) {
			if (relationship instanceof GmEnforceRelationship && !toExclude.contains(relationship.getTarget_id())) {
				String measure_id = relationship.getTarget_id();
				GmComponent measure = null;
				
				//Gets measure (sm or wfp)
				for (GmSecurityMeasure sm : goalModel.getSmArray()) {
					if (sm.getId().equals(measure_id)) {
						measure = sm;
					}
				}
				for (GmWfp wfp : goalModel.getWfpArray()) {
					if (wfp.getId().equals(measure_id)) {
						measure = wfp;
					}
				}
				
				//First I count to how many goals the measure is connected to
				int occurrences = 0;
				for (GmRelationship rel : goalModel.getRelationshipsArray()) {
					if (rel instanceof GmEnforceRelationship && measure_id.equals(rel.getTarget_id())) {
						occurrences++;
					}
				}

				//Second cycle to update energy of goals
				for (GmRelationship rel : goalModel.getRelationshipsArray()) {
					
					if (rel instanceof GmEnforceRelationship && measure.getId().equals(rel.getTarget_id())) {
						String goal_id = rel.getSource_id();
						GmGoal goalNode = null;
						
						//Gets goal
						for (GmActor actor : goalModel.getActorsArray()) {
							for (GmGoal goal : actor.getGoals()) {
								if (goal.getId().equals(goal_id)) {
									goalNode = goal;
								}
							}
						}
						
						if (occurrences == 1) { //measure is connected to only one goal, so all his consumption is propagated to the goal
							goalNode.updateEnergyConsumption(measure.getEnergyConsumption());
						}
						else { //measure is connected to multiple goals, so the average energy value is spread among the linked goals
							float average_en = measure.getEnergyConsumption() / occurrences;
							goalNode.updateEnergyConsumption(average_en);
						}
					}
				}
				
			}				
		}
	}
	
	/**
	 * Propagate EN from goal participating in Protect relationships to respective assets
	 * @param goalModel
	 */
	private static void protectPropagation(GoalModel goalModel) {
		for (GmRelationship relationship : goalModel.getRelationshipsArray()) {
			if (relationship instanceof GmProtectRelationship) {
				String goal_id = relationship.getSource_id();
				GmGoal goalNode = null;
				String asset_id = relationship.getTarget_id();
				GmAsset assetNode = null;
				
				//Gets goal
				for (GmActor actor : goalModel.getActorsArray()) {
					for (GmGoal goal : actor.getGoals()) {
						if (goal.getId().equals(goal_id)) {
							goalNode = goal;
						}
					}
				}
				
				//Gets asset
				for (GmAsset asset : goalModel.getAssetArray()) {
					if (asset.getId().equals(asset_id)) {
						assetNode = asset;
					}
				}
				
				//Set asset EN with that of connected leaf goal
				assetNode.setEnergyConsumption(goalNode.getEnergyConsumption());
			}
		}
	}
	
	/**
	 * Propagates energy consumption in goal trees: AND branches mean that goal consumption need to be summed up
	 * @param goalModel
	 */
	private static void goalPropagation(GoalModel goalModel) {
		//For each actor I need to perform propagation
		for (GmActor actor : goalModel.getActorsArray()) {
			//I need to traverse arrayList of goals from end to start to do one pass and propagate
			ArrayList<GmGoal> goalList = actor.getGoals();
			for (int i = goalList.size() - 1; i>0; i--) {
				GmGoal currentGoal = goalList.get(i);
				currentGoal.getParent().updateEnergyConsumption(currentGoal.getEnergyConsumption());
			}
		}
	}	

}
