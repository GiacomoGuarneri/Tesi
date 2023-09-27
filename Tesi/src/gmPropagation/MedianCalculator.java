package gmPropagation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class is in charge of calculating the median value, if list size is even we take the highest among median values
 */
public class MedianCalculator {
	
	public Integer calculateMedian(List<Integer> array) {
		int size = array.size();
		if (size == 0) {
			return null; // empty array case
		} else {
			return quickselect(array, size/2); //use quickselect algo to find k-th smallest element (k = size/2 to find median)
		}
	}

	/**
	 * Implementation of quicksort algoritm to find k-th smallest integer in an unordered list
	 * @param array
	 * @param k
	 * @return
	 */
	private static Integer quickselect(List<Integer> array, int k) {
		int size = array.size();
		Random random = new Random();
		
		int pivotIndex = random.nextInt(size);
		int pivot = array.get(pivotIndex);
		
		List<Integer> lower = new ArrayList<>();
        List<Integer> higher = new ArrayList<>();
        List<Integer> equal = new ArrayList<>();
        
        for (int num : array) {
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
