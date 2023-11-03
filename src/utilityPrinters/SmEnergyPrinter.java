package utilityPrinters;

import java.util.ArrayList;
import java.util.Collections;

import model.GmSecurityMeasure;
import model.GoalModel;

/**
 * This class is devoted to the security measure energy consumption analysis
 */
public class SmEnergyPrinter {

	/**
	 * Prints security measures ordered by energy consumption in descending order
	 * @param goalModel
	 */
	public void printSmByEnergyDesc (GoalModel goalModel) {
		//First I group all the security measures in the model in a single array
		ArrayList<GmSecurityMeasure> totalMeasures = new ArrayList<GmSecurityMeasure>();
		for (GmSecurityMeasure sm : goalModel.getSmArray()) {
			totalMeasures.add(sm);
		}
		
		//Ordering sm by energy descending
		Collections.sort(totalMeasures, (a1, a2) -> Float.compare(a2.getEnergyConsumption(), a1.getEnergyConsumption()));
		
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
		
		System.out.println("Security measures ordered by energy consumption DESCENDING");
		System.out.println();
		
		for (GmSecurityMeasure sm : totalMeasures) {
			System.out.println("Security measure: " + ANSI_CYAN + sm.getDescription() + ANSI_RESET + " with energy consumption of " + ANSI_YELLOW + sm.getEnergyConsumption() + ANSI_RESET);
		}
		
		System.out.println();
	}
	
}
