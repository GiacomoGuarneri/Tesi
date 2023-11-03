package utilityPrinters;

import java.util.ArrayList;
import java.util.Collections;

import model.GmWfp;
import model.GoalModel;

/**
 * This class is devoted to the workflow patterns energy consumption analysis
 */
public class WfpEnergyPrinter {

	/**
	 * Prints workflow patterns ordered by energy consumption in descending order
	 * @param goalModel
	 */
	public void printWfpByEnergyDesc (GoalModel goalModel) {
		//First I group all the wfp in the model in a single array
		ArrayList<GmWfp> totalWfps = new ArrayList<GmWfp>();
		for (GmWfp wfp : goalModel.getWfpArray()) {
			totalWfps.add(wfp);
		}
		
		//Ordering wfp by energy descending
		Collections.sort(totalWfps, (a1, a2) -> Float.compare(a2.getEnergyConsumption(), a1.getEnergyConsumption()));
		
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
		
		System.out.println("Workflow patterns ordered by energy consumption DESCENDING");
		System.out.println();
		
		for (GmWfp wfp : totalWfps) {
			System.out.println("Workflow pattern: " + ANSI_CYAN + wfp.getDescription() + ANSI_RESET + " with energy consumption of " + ANSI_YELLOW + wfp.getEnergyConsumption() + ANSI_RESET);
		}
		
		System.out.println();
	}
	
}
