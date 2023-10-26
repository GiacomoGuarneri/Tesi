package gmPropagation;

import java.util.ArrayList;
import java.util.Scanner;

import model.GoalModel;

/**
 * This interface only impose to provide a propagation for the class that implements it
 */
public interface PropagationPolicy {
	/**
	 * This method perform the propagation
	 * @param goalModel is the model
	 * @param scanner is useful to take input from the user
	 * @param toExclude is a list of strings containing the measures to exclude from the propagation
	 */
	public void startPropagation(GoalModel goalModel, Scanner scanner, ArrayList<String> toExclude);
}