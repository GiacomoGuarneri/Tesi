package gmPropagation;

import java.util.List;

/**
 * This class is in charge of calculating the max value
 */
public class MaxCalculator {
	
	public Integer calculateMax(List<Integer> array) {
		if (array.size() == 0) {
			return null; //empty array case
		}
		
		int max = array.get(0); //assume first element is maximum
		
		for (int i = 1; i < array.size(); i++) {
			if (array.get(i) > max) {
				max = array.get(i); //update max if we encounter a larger value
			}
		}
		
		return max;
	}
}
