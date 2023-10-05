package utilityPrinters;

import java.util.ArrayList;
import java.util.Collections;
import model.GmActor;
import model.GmGoal;
import model.GoalModel;

/**
 * This class is devoted to the goal energy consumption analysis
 */
public class GoalEnergyPrinter {
	
	/**
	 * Prints goals ordered by energy consumption in descending order
	 * @param goalModel
	 */
	public void printGoalsByEnergyDesc (GoalModel goalModel) {
		//First I group all the goals in the model in a single array
		ArrayList<GmGoal> totalGoals = new ArrayList<GmGoal>();
		for (GmActor actor : goalModel.getActorsArray()) {
			for (GmGoal goal : actor.getGoals()) {
				totalGoals.add(goal);
			}
		}
		
		//Ordering goals by energy descending
		Collections.sort(totalGoals, (g1, g2) -> Float.compare(g2.getEnergyConsumption(), g1.getEnergyConsumption()));
		
		//ANSI escape codes to color the output
		final String ANSI_RESET = "\u001B[0m";
//		final String ANSI_BLACK = "\u001B[30m";
//		final String ANSI_RED = "\u001B[31m";
//		final String ANSI_GREEN = "\u001B[32m";
		final String ANSI_YELLOW = "\u001B[33m";
//		final String ANSI_BLUE = "\u001B[34m";
//		final String ANSI_PURPLE = "\u001B[35m";
		final String ANSI_CYAN = "\u001B[36m";
//		final String ANSI_WHITE = "\u001B[37m";
		
		System.out.println("Goals ordered by energy consumption DESCENDING");
		System.out.println();
		
		for (GmGoal goal : totalGoals) {
			System.out.println("Goal: " + ANSI_CYAN + goal.getDescription() + ANSI_RESET + " with energy consumption of " + ANSI_YELLOW + goal.getEnergyConsumption() + ANSI_RESET);
		}
		
		System.out.println();
	}
	
}
