package gmPropagation;

import java.util.List;

/**
 * This class is in charge of calculating the average value
 */
public class AverageCalculator {

	/**
	 * Calculates the average of an array of floats
	 * @param array
	 * @return
	 */
	public Float calculateAverage(List<Float> array) {
		if (array.size() == 0) {
			return null; //empty array case
		}

        float sum = 0;

        for (float number : array) {
            sum += number;
        }

        return sum / array.size();
	}
	
}
