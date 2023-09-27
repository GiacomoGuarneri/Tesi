package gmPropagation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.GmActor;
import model.GmAsset;
import model.GmEnforceRelationship;
import model.GmGoal;
import model.GmProtectRelationship;
import model.GmRelationship;
import model.GmSecurityMeasure;
import model.GmWfp;
import model.GoalModel;

/**
 * This class performs propagation of risk
 */
public class RiskPropagation implements PropagationPolicy {

	@Override
	public void startPropagation(GoalModel goalModel) {
		//First we need to select our propagation policy
		System.out.println("Select which policy to adopt for risk propagation:");
		System.out.println("1 - Median");
		System.out.println("2 - Max");
		System.out.println();
		Scanner scanner = new Scanner(System.in); //System.in is a standard input system
		System.out.println("Enter the number corresponding to the chosen policy: ");
		int choice = scanner.nextInt();
		while (choice != 1 && choice != 2) {
			System.out.println("Please enter a valid number: ");
			choice = scanner.nextInt();
		}
		switch (choice) {
			case 1:
				System.out.println("You have selected Median policy");
				medianPropagation(goalModel);
				break;
			case 2:
				System.out.println("You have selected Max policy");
				maxPropagation(goalModel);
				break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + choice);
		}
	}

	/**
	 * This method performs medRank propagation of risk
	 * @param goalModel
	 */
	public static void medianPropagation(GoalModel goalModel) {
		preliminaryPropagation(goalModel);
		
		//Now we need to perform goal propagation following medRank policy
		for (GmActor actor : goalModel.getActorsArray()) {
			
			//I need to traverse arrayList of goals from end to start to do one pass and propagate
			ArrayList<GmGoal> goalList = actor.getGoals();
			
			for (int i = goalList.size() - 1; i>=0; i--) {
				GmGoal currentGoal = goalList.get(i);
				
				//Leaf goals are ok, we need to only update the non-leaf goals
				if (! currentGoal.isLeaf()) {
					List<GmGoal> children = currentGoal.getChildren();
					
					//first I prepare an array of child values
					List<Integer> array = new ArrayList<>();
					for (GmGoal goal : children) {
						array.add(goal.getResidualRisk());
					}
					
					//now we have an array of all the child values and we can find median value
					MedianCalculator calculator = new MedianCalculator();
					int medianValue = calculator.calculateMedian(array);
					
					//we can update rr of curent goal
					currentGoal.setResidualRisk(medianValue);
				}
			}
		}
	}
	
	/**
	 * This method performs max propagation of risk
	 * @param goalModel
	 */
	public static void maxPropagation(GoalModel goalModel) {
		preliminaryPropagation(goalModel);
		
		//Now we need to perform goal propagation following max policy
		for (GmActor actor : goalModel.getActorsArray()) {
		
			//I need to traverse arrayList of goals from end to start to do one pass and propagate
			ArrayList<GmGoal> goalList = actor.getGoals();
					
			for (int i = goalList.size() - 1; i>=0; i--) {
				GmGoal currentGoal = goalList.get(i);
						
				//Leaf goals are ok, we need to only update the non-leaf goals
				if (! currentGoal.isLeaf()) {
					List<GmGoal> children = currentGoal.getChildren();
							
					//first I prepare an array of child values
					List<Integer> array = new ArrayList<>();
					for (GmGoal goal : children) {
						array.add(goal.getResidualRisk());
					}
							
					//now we have an array of all the child values and we can find max value
					MaxCalculator calculator = new MaxCalculator();
					int maxValue = calculator.calculateMax(array);
							
					//we can update rr of current goal
					currentGoal.setResidualRisk(maxValue);
				}
			}
		}
	}
	
	/**
	 * Performs preliminary propagation of risk: asset(IR) -> goal(RR), measures(RMI) -> goal(RR), goal(RR) -> asset(RR)
	 * @param goalModel
	 */
	public static void preliminaryPropagation(GoalModel goalModel) {
		protectPropagation(goalModel);
		enforcePropagation(goalModel);
		assetUpdate(goalModel);
	}
	
	/**
	 * Propagate inherent risk from asset participating in Protect relationships to respective goals
	 * @param goalModel
	 */
	public static void protectPropagation(GoalModel goalModel) {
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
				
				//Set goal residual risk to that of connected asset
				goalNode.setResidualRisk(assetNode.getInherentRisk());
			}
		}
	}

	/**
	 * Propagate risk mitigation impact from measures participating in Enforce relationships to respective goals
	 * @param goalModel
	 */
	public static void enforcePropagation(GoalModel goalModel) {
		for (GmRelationship relationship : goalModel.getRelationshipsArray()) {
			if (relationship instanceof GmEnforceRelationship) {
				String goal_id = relationship.getSource_id();
				GmGoal goalNode = null;
				String measure_id = relationship.getTarget_id();
				
				//Gets goal
				for (GmActor actor : goalModel.getActorsArray()) {
					for (GmGoal goal : actor.getGoals()) {
						if (goal.getId().equals(goal_id)) {
							goalNode = goal;
						}
					}
				}
				
				//Gets measure (sm or wfp)
				for (GmSecurityMeasure sm : goalModel.getSmArray()) {
					if (sm.getId().equals(measure_id)) {
						GmSecurityMeasure measure = sm;
						//Decrement goal RR by the amount indicated on RMI
						goalNode.updateResidualRisk(measure.getRiskMitigationImpact());
					}
				}
				for (GmWfp wfp : goalModel.getWfpArray()) {
					if (wfp.getId().equals(measure_id)) {
						GmWfp measure = wfp;
						//Decrement goal RR by the amount indicated on RMI
						goalNode.updateResidualRisk(measure.getRiskMitigationImpact());
					}
				}
			}				
		}
	}

	/**
	 * Updates asset residual risk to that of updated connected goal
	 * @param goalModel
	 */
	public static void assetUpdate(GoalModel goalModel) {
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
				
				//Set asset residual risk to that of updated connected goal
				assetNode.setResidualRisk(goalNode.getResidualRisk());
			}
		}
	}
}
