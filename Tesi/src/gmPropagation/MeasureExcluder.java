package gmPropagation;

import java.util.ArrayList;
import java.util.Scanner;

import model.GmSecurityMeasure;
import model.GmWfp;
import model.GoalModel;

/**
 * This class deals with the exclusion of measures from the propagation phase
 */
public class MeasureExcluder {
	
	/**
	 * Asks the user to input the measure he wants to exclude from the propagation
	 * @param goalModel
	 * @param scanner
	 * @return a list of strings containing the names of the measures to exclude
	 */
	public ArrayList<String> startExcluder(GoalModel goalModel, Scanner scanner){
		System.out.println("These are the measures present in the model");
		System.out.println();
		System.out.println("WFP:");
		for (GmWfp wfp : goalModel.getWfpArray()) {
			System.out.println(wfp.getDescription());
		}
		System.out.println();
		System.out.println("SM:");
		for (GmSecurityMeasure sm : goalModel.getSmArray()) {
			System.out.println(sm.getDescription());
		}
		System.out.println();
		System.out.println("Please enter the measures you want to exclude from the propagation (press Enter to finish)");
		
		ArrayList<String> toExclude = new ArrayList<String>();
		String line;
		
		while (true) {
			line = scanner.nextLine();
			if (line.isEmpty()) {
                // If an empty line is entered, exit the loop
                break;
            }
            toExclude.add(line);
		}
		
		return toExclude;
	}
	
}
