package gmPropagation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class is in charge of calculating the median value, if list size is even we take the highest among median values
 */
public class MedianCalculator {
	
	/**
	 * Actually calculates the median value of an array via quicksort algorithm
	 * @param array
	 * @return
	 */
	public Float calculateMedian(List<Float> array) {
		int size = array.size();
		if (size == 0) {
			return null; // empty array case
		} else {
			return quickselect(array, size/2); //use quickselect algo to find k-th smallest element (k = size/2 to find median)
		}
	}

	/**
	 * Implementation of quicksort algorithm to find k-th smallest float in an unordered list
	 * @param array
	 * @param k
	 * @return
	 */
	private static Float quickselect(List<Float> array, int k) {
		int size = array.size();
		Random random = new Random();
		
		int pivotIndex = random.nextInt(size);
		Float pivot = array.get(pivotIndex);
		
		List<Float> lower = new ArrayList<>();
        List<Float> higher = new ArrayList<>();
        List<Float> equal = new ArrayList<>();
        
        for (Float num : array) {
            if (num < pivot) {
                lower.add(num);
            } else if (num > pivot) {
                higher.add(num);
            } else {
                equal.add(num);
            }
        }
        
        if (k < lower.size()) {
            return quickselect(lower, k);
        } else if (k < lower.size() + equal.size()) {
            return equal.get(0);
        } else {
            return quickselect(higher, k - lower.size() - equal.size());
        }
	}
	
}
