package gmConverter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.GmAsset;
import model.GmSecurityMeasure;
import model.GmWfp;
import model.GoalModel;

/**
 * This class is responsible of parsing the json file that contains info related to risk
 */
public class JsonRiskParser {

	/**
	 * This method starts the parsing procedure
	 * @param goalModel
	 */
	public void start(GoalModel goalModel) {
		
		//JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();

		String filePath = "Risk data/demo.json";
		
		try (FileReader reader = new FileReader(filePath)) {
			
			//Read JSON file
			Object obj = jsonParser.parse(reader);
			
			JSONObject jsonObj = (JSONObject) obj;
			
			//Gets wfp jsonArray
			JSONArray wfp = new JSONArray();
			wfp = (JSONArray) jsonObj.get("wfp");
			JsonRiskParser.wfpRoutine(wfp, goalModel);
			
			// Gets sm jsonArray
    		JSONArray sm = new JSONArray();
    		sm = (JSONArray) jsonObj.get("sm");
    		JsonRiskParser.smRoutine(sm, goalModel);
    		
    		// Gets asset jsonArray
    		JSONArray asset = new JSONArray();
    		asset = (JSONArray) jsonObj.get("asset");
    		JsonRiskParser.assetRoutine(asset, goalModel);
			
		} catch (FileNotFoundException e) {
			// Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This method is able to extract risk info about wfp
	 * @param array is the array of wfp
	 * @param goalModel is the goal model we need to update
	 */
	private static void wfpRoutine(JSONArray array, GoalModel goalModel) {
		for (int i=0; i< array.size(); i++) {
			
			JSONObject object = new JSONObject();
			object = (JSONObject) array.get(i);
			
			//Get wfp id
			String wfpId = (String) object.get("id");
			//System.out.println("wfpId: " + wfpId); 
			//Get wfp rmi
			String wfpRmi = (String) object.get("rmi");
			//System.out.println("wfpRmi: " + wfpRmi);
			//System.out.println();
			
			for(GmWfp element : goalModel.getWfpArray()) {
				if(element.getId() != null && element.getId().equals(wfpId)) {
					int rmi = Integer.valueOf(wfpRmi);
					element.setRiskMitigationImpact(rmi);
				}
			}
		}
	}
	
	/**
	 * This method is able to extract risk info about security measures
	 * @param array is the array of sm
	 * @param goalModel is the general goal model to update
	 */
	private static void smRoutine(JSONArray array, GoalModel goalModel) {
		for (int i=0; i< array.size(); i++) {
			
			JSONObject object = new JSONObject();
			object = (JSONObject) array.get(i);
			
			//Get sm id
			String smId = (String) object.get("id");
			//System.out.println("smId: " + smId); 
			//Get sm rmi
			String smRmi = (String) object.get("rmi");
			//System.out.println("smRmi: " + smRmi);
			//System.out.println();
			
			for(GmSecurityMeasure element : goalModel.getSmArray()) {
				if(element.getId() != null && element.getId().equals(smId)) {
					int rmi = Integer.valueOf(smRmi);
					element.setRiskMitigationImpact(rmi);
				}
			}
		}
	}
	
	/**
	 * This method is able to extract risk info about assets
	 * @param array is the array of assets
	 * @param goalModel is the general goal model to update
	 */
	private static void assetRoutine(JSONArray array, GoalModel goalModel) {
		for (int i=0; i< array.size(); i++) {
			
			JSONObject object = new JSONObject();
			object = (JSONObject) array.get(i);
			
			//Get asset id
			String assetId = (String) object.get("id");
			//System.out.println("assetId: " + assetId); 
			//Get asset ir
			String assetIr = (String) object.get("ir");
			//System.out.println("assetIr: " + assetIr);
			//System.out.println();
			
			for(GmAsset element : goalModel.getAssetArray()) {
				if(element.getId() != null && element.getId().equals(assetId)) {
					int ir = Integer.valueOf(assetIr);
					element.setInherentRisk(ir);
				}
			}
		}
	}

}
