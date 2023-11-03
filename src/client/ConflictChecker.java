package client;

import java.util.ArrayList;

import com.google.common.collect.Table.Cell;

import model.GmActor;
import model.GmEnforceRelationship;
import model.GmGoal;
import model.GmKnowledgeBase;
import model.GmRelationship;
import model.GmSecurityMeasure;
import model.GmWfp;
import model.GoalModel;

/**
 * This class prints if there are kb conflicts present in the model
 */
public class ConflictChecker {
	/**
	 * This method starts the conflict checker procedure
	 * @param goalModel
	 * @param kb
	 */
	public void start(GoalModel goalModel, GmKnowledgeBase kb) {
		
		//ANSI escape codes to color the output
		final String ANSI_RESET = "\u001B[0m";
//		final String ANSI_BLACK = "\u001B[30m";
		final String ANSI_RED = "\u001B[31m";
//		final String ANSI_GREEN = "\u001B[32m";
		final String ANSI_YELLOW = "\u001B[33m";
//		final String ANSI_BLUE = "\u001B[34m";
//		final String ANSI_PURPLE = "\u001B[35m";
		final String ANSI_CYAN = "\u001B[36m";
//		final String ANSI_WHITE = "\u001B[37m";

		for (Cell<String, String, String> cell : kb.getKnowledgeBase().cellSet()) { // for each entry in kb
			
			String smName = cell.getRowKey(); //here we have the name of sm
			String smId = null;
			String wfpName = cell.getColumnKey(); //here we have the name of conflicting wfp
			String wfpId = null;
			ArrayList<String> goalsConnectedToSm = new ArrayList<String>();
			
			//gets id of the sm
			for (GmSecurityMeasure sm : goalModel.getSmArray()) {
				if (sm.getDescription().equals(smName)) {
					smId = sm.getId();
				}
			}
			
			//gets id of the wfp
			for (GmWfp wfp : goalModel.getWfpArray()) {
				if (wfp.getDescription().equals(wfpName)) {
					wfpId = wfp.getId();
				}
			}
			
			//I perform the check only if the sm and wfp actually exist in my model
			if (smId != null && wfpId != null) {
				
				//gets all goalIds of goals connected to our sm by an enforceRelationship
				for (GmRelationship relationship : goalModel.getRelationshipsArray()) {
					if (relationship instanceof GmEnforceRelationship && relationship.getTarget_id().equals(smId)) {
						String goalId = relationship.getSource_id();
						goalsConnectedToSm.add(goalId);
					}
				}
				
				//print a conflict if we find that conflicting wfpId is connected to a goal present in goalsConnectedToSm
				for (String goalId : goalsConnectedToSm) {
					String conflictingGoalActor = null;
					String conflictingGoalId = null;
					String conflictingGoalName = null;
					
					for (GmRelationship relationship : goalModel.getRelationshipsArray()) {
						if ( relationship instanceof GmEnforceRelationship && goalId.equals(relationship.getSource_id()) && wfpId.equals(relationship.getTarget_id()) ) {
							conflictingGoalId = goalId; //we found a conflicting goal
						}
					}
					
					//if conflict has been found we print
					if (conflictingGoalId != null) {
						//gets goal Name
						for (GmActor actor : goalModel.getActorsArray()) {
							for (GmGoal goal : actor.getGoals()) {
								if (goal.getId().equals(conflictingGoalId)) {
									conflictingGoalName = goal.getDescription();
									conflictingGoalActor = actor.getDescription();
								}
							}
						}
						System.out.println(ANSI_RED + "Conflict noted!" + ANSI_RESET + " (" + smName + " and " + wfpName + " are connected to the same goal " + ANSI_YELLOW + conflictingGoalName + ANSI_RESET + " in actor " + ANSI_CYAN + conflictingGoalActor + ANSI_RESET + ")");
						System.out.println();
					}
				}
			}	
		}
	}
}
