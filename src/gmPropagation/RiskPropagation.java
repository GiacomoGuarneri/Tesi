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

	/**
	 * Starts the risk propagation 
	 */
	@Override
	public void startPropagation(GoalModel goalModel, Scanner scanner, ArrayList<String> toExclude) {
		//First we need to select our propagation policy
		System.out.println("Select which policy to adopt for risk propagation:");
		System.out.println("1 - Median");
		System.out.println("2 - Max");
		System.out.println("3 - Average");
		System.out.println();System.out.println("Enter the number corresponding to the chosen policy: ");
		int choice = scanner.nextInt();
		while (choice != 1 && choice != 2 && choice != 3) {
			System.out.println("Please enter a valid number: ");
			choice = scanner.nextInt();
		}
		switch (choice) {
			case 1:
				System.out.println("You have selected Median policy");
				System.out.println();
				scanner.close();
				medianPropagation(goalModel, toExclude);
				break;
			case 2:
				System.out.println("You have selected Max policy");
				System.out.println();
				scanner.close();
				maxPropagation(goalModel, toExclude);
				break;
			case 3:
				System.out.println("You have selected Average policy");
				System.out.println();
				scanner.close();
				averagePropagation(goalModel, toExclude);
				break;
		default:
			scanner.close();
			throw new IllegalArgumentException("Unexpected value: " + choice);
		}
	}

	/**
	 * This method performs medRank propagation of risk
	 * @param goalModel
	 * @param toExclude is the list of measure to exclude from propagation
	 */
	public static void medianPropagation(GoalModel goalModel, ArrayList<String> toExclude) {
		preliminaryPropagation(goalModel, toExclude);
		
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
					List<Float> array = new ArrayList<>();
					for (GmGoal goal : children) {
						array.add(goal.getResidualRisk());
					}
					
					//now we have an array of all the child values and we can find median value
					MedianCalculator calculator = new MedianCalculator();
					float medianValue = calculator.calculateMedian(array);
					
					//we can update rr of curent goal
					currentGoal.setResidualRisk(medianValue);
				}
			}
		}
	}
	
	/**
	 * This method performs max propagation of risk
	 * @param goalModel
	 * @param toExclude is the list of measure to exclude from propagation
	 */
	public static void maxPropagation(GoalModel goalModel, ArrayList<String> toExclude) {
		preliminaryPropagation(goalModel, toExclude);
		
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
					List<Float> array = new ArrayList<>();
					for (GmGoal goal : children) {
						array.add(goal.getResidualRisk());
					}
							
					//now we have an array of all the child values and we can find max value
					MaxCalculator calculator = new MaxCalculator();
					float maxValue = calculator.calculateMax(array);
							
					//we can update rr of current goal
					currentGoal.setResidualRisk(maxValue);
				}
			}
		}
	}
	
	/**
	 * This method performs average propagation of risk
	 * @param goalModel
	 * @param toExclude is the list of measure to exclude from propagation
	 */
	public static void averagePropagation(GoalModel goalModel, ArrayList<String> toExclude) {
		preliminaryPropagation(goalModel, toExclude);
		
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
					List<Float> array = new ArrayList<>();
					for (GmGoal goal : children) {
						array.add(goal.getResidualRisk());
					}
									
					//now we have an array of all the child values and we can find average value
					AverageCalculator calculator = new AverageCalculator();
					float averageValue = calculator.calculateAverage(array);
									
					//we can update rr of current goal
					currentGoal.setResidualRisk(averageValue);
				}
			}
		}
	}
	
	/**
	 * Performs preliminary propagation of risk: asset(IR) -> goal(RR), measures(RMI) -> goal(RR), goal(RR) -> asset(RR)
	 * @param goalModel
	 * @param toExclude is the list of measure to exclude from propagation
	 */
	public static void preliminaryPropagation(GoalModel goalModel, ArrayList<String> toExclude) {
		protectPropagation(goalModel);
		enforcePropagation(goalModel, toExclude);
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
	 * @param toExclude is the list of measure to exclude from propagation
	 */
	public static void enforcePropagation(GoalModel goalModel, ArrayList<String> toExclude) {
		for (GmRelationship relationship : goalModel.getRelationshipsArray()) {
			if (relationship instanceof GmEnforceRelationship && !toExclude.contains(relationship.getTarget_id())) {
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
