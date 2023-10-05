package gmPropagation;

import java.util.List;

/**
 * This class is in charge of calculating the max value
 */
public class MaxCalculator {
	
	/**
	 * Calculates maximum of an array of floats
	 * @param array
	 * @return
	 */
	public Float calculateMax(List<Float> array) {
		if (array.size() == 0) {
			return null; //empty array case
		}
		
		float max = array.get(0); //assume first element is maximum
		
		for (int i = 1; i < array.size(); i++) {
			if (array.get(i) > max) {
				max = array.get(i); //update max if we encounter a larger value
			}
		}
		
		return max;
	}
}
