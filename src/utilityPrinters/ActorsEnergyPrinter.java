package utilityPrinters;

import java.util.ArrayList;
import java.util.Collections;

import model.GmActor;
import model.GmGoal;
import model.GoalModel;

/**
 * This class is devoted to the actor energy consumption analysis
 */
public class ActorsEnergyPrinter {

	/**
	 * Prints actors ordered by energy consumption in descending order
	 * @param goalModel
	 */
	public void printActorsByEnergyDesc (GoalModel goalModel) {
				
		//Update energy consumption of actors
		for (GmActor actor : goalModel.getActorsArray()) {
			for (GmGoal goal : actor.getRootNodes()) {
				actor.updateEnergyConsumption(goal.getEnergyConsumption());
			}
		}
		
		//First I group all the actors in the model in a single array
		ArrayList<GmActor> totalActors = new ArrayList<GmActor>();
		for (GmActor actor : goalModel.getActorsArray()) {
			totalActors.add(actor);
		}
		
		//Ordering actors by energy descending
		Collections.sort(totalActors, (a1, a2) -> Float.compare(a2.getEnergyConsumption(), a1.getEnergyConsumption()));
		
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
		
		System.out.println("Actors ordered by energy consumption DESCENDING");
		System.out.println();
		
		float systemConsumption = 0;
		for (GmActor actor : goalModel.getActorsArray()) {
			systemConsumption += actor.getEnergyConsumption();
		}
		
		for (GmActor actor : totalActors) {
			System.out.println("Actor: " + ANSI_CYAN + actor.getDescription() + ANSI_RESET + " with energy consumption of " + ANSI_YELLOW + actor.getEnergyConsumption() + ANSI_RESET + " consisting in the " + (actor.getEnergyConsumption()*100)/systemConsumption + "% of the total system");
		}
		
		System.out.println();
	}
	
}
