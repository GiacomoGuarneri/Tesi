package gmPropagation;

import java.util.List;

/**
 * This class is in charge of calculating the average value, if list size is even we take the highest among median values
 */
public class AverageCalculator {

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
