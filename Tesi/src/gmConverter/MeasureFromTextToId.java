package gmConverter;

import java.util.ArrayList;

import model.GmSecurityMeasure;
import model.GmWfp;
import model.GoalModel;

public class MeasureFromTextToId {

	public ArrayList<String> startConversion(GoalModel goalModel, ArrayList<String> toConvert) {
		
		ArrayList<String> converted = new ArrayList<String>();
		
		for (String string : toConvert) {
			
			for (GmWfp wfp : goalModel.getWfpArray()) {
				if (string.equals(wfp.getDescription())) {
					converted.add(wfp.getId());
				}
			}
			
			for (GmSecurityMeasure sm : goalModel.getSmArray()) {
				if (string.equals(sm.getDescription())) {
					converted.add(sm.getId());
				}
			}
			
		}
		
		return converted;
		
	}
	
}
