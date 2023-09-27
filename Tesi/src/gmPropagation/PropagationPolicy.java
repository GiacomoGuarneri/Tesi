package gmPropagation;

import model.GoalModel;

/**
 * This interface only impose to provide a propagation for the class that implements it
 */
public interface PropagationPolicy {
	/**
	 * This method perform the propagation given the goalModel
	 * @param goalModel
	 */
	public void startPropagation(GoalModel goalModel);
}