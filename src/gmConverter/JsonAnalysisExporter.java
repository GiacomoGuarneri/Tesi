package gmConverter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import model.GmActor;
import model.GmAsset;
import model.GmGoal;
import model.GmSecurityMeasure;
import model.GmWfp;
import model.GoalModel;

/**
* This class exports the analysis proposed by the method
*/
public class JsonAnalysisExporter {

	/**
	 * This method starts the json exporter of the analysis
	 * @param goalModel
	 */
	public void start(GoalModel goalModel){
		
		// filepath of where I want to write
		String filePath = "Analysis exports/demo.json";
				
		// filepath of original goal model
		String filePathOriginal = "Goal models/demo.json";
		
		try (FileWriter file = new FileWriter(filePath)) {
			
			//Creating a global JSONObject
		    JSONObject globalObject = new JSONObject();
		    
		    //energy per goal analysis
		    goalEnergyAnalysis(goalModel, globalObject);
		    
		    //energy per actor analysis
		    actorsEnergyAnalysis(goalModel, globalObject);
		    
		    //energy per sm analysis
		    smEnergyAnalysis(goalModel, globalObject);
		    
		    //energy per wfp analysis
		    wfpEnergyAnalysis(goalModel, globalObject);
		    
		    //energy per asset analysis
		    assetEnergyAnalysis(goalModel, globalObject);
		    
		    //risk per asset analysis
		    assetRiskAnalysis(goalModel, globalObject);
			
			// at the end we write and close the file
			file.write(prettyPrint(globalObject.toJSONString()));
			file.close();
			
			System.out.println("Analysis has been exported to location: " + filePath);
			System.out.println();
			
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This method inserts the analysis of goals ordered per energy descending
	 * @param goalModel
	 * @param globalObject
	 */
	private static void goalEnergyAnalysis(GoalModel goalModel, JSONObject globalObject){
		//First I group all the goals in the model in a single array
		ArrayList<GmGoal> totalGoals = new ArrayList<GmGoal>();
			for (GmActor actor : goalModel.getActorsArray()) {
				for (GmGoal goal : actor.getGoals()) {
					totalGoals.add(goal);
				}
			}
		
		//Ordering goals by energy descending
		Collections.sort(totalGoals, (g1, g2) -> Float.compare(g2.getEnergyConsumption(), g1.getEnergyConsumption()));	
		
		//creating jsonArray for goals
		JSONArray jsonArray = new JSONArray();
		
		for(GmGoal goal : totalGoals) {
			//Creating a JSONObject for single goal
		    JSONObject jsonObject = new JSONObject();
		    
		    //Inserting key-value pairs into the json object
		    jsonObject.put("id", goal.getId());
		    jsonObject.put("description", goal.getDescription());
		    jsonObject.put("energy", goal.getEnergyConsumption());
		    
		    //adding the object to the array
		    jsonArray.add(jsonObject);
		}
		
		//we add the array to the global object
		globalObject.put("goalsEnergyDescending", jsonArray);
			
	}
	
	/**
	 * This method inserts the analysis of actors ordered per energy descending
	 * @param goalModel
	 * @param globalObject
	 */
	private static void actorsEnergyAnalysis(GoalModel goalModel, JSONObject globalObject){
		//First I group all the actors in the model in a single array
		ArrayList<GmActor> totalActors = new ArrayList<GmActor>();
			for (GmActor actor : goalModel.getActorsArray()) {
				totalActors.add(actor);
			}
				
		//Ordering actors by energy descending
		Collections.sort(totalActors, (a1, a2) -> Float.compare(a2.getEnergyConsumption(), a1.getEnergyConsumption()));
		
		//creating jsonArray for actors
		JSONArray jsonArray = new JSONArray();
		
		for(GmActor actor : totalActors) {
			//Creating a JSONObject for single actor
		    JSONObject jsonObject = new JSONObject();
		    
		    //Inserting key-value pairs into the json object
		    jsonObject.put("id", actor.getId());
		    jsonObject.put("description", actor.getDescription());
		    jsonObject.put("energy", actor.getEnergyConsumption());
		    
		    //adding the object to the array
		    jsonArray.add(jsonObject);
		}
		
		//we add the array to the global object
		globalObject.put("actorsEnergyDescending", jsonArray);
			
	}
	
	/**
	 * This method inserts the analysis of security measures ordered per energy descending
	 * @param goalModel
	 * @param globalObject
	 */
	private static void smEnergyAnalysis(GoalModel goalModel, JSONObject globalObject){
		//First I group all the security measures in the model in a single array
		ArrayList<GmSecurityMeasure> totalMeasures = new ArrayList<GmSecurityMeasure>();
		for (GmSecurityMeasure sm : goalModel.getSmArray()) {
			totalMeasures.add(sm);
		}
				
		//Ordering sm by energy descending
		Collections.sort(totalMeasures, (a1, a2) -> Float.compare(a2.getEnergyConsumption(), a1.getEnergyConsumption()));
		
		//creating jsonArray for sm
		JSONArray jsonArray = new JSONArray();
		
		for(GmSecurityMeasure sm : totalMeasures) {
			//Creating a JSONObject for single sm
		    JSONObject jsonObject = new JSONObject();
		    
		    //Inserting key-value pairs into the json object
		    jsonObject.put("id", sm.getId());
		    jsonObject.put("description", sm.getDescription());
		    jsonObject.put("energy", sm.getEnergyConsumption());
		    
		    //adding the object to the array
		    jsonArray.add(jsonObject);
		}
		
		//we add the array to the global object
		globalObject.put("smEnergyDescending", jsonArray);
			
	}
	
	/**
	 * This method inserts the analysis of wfp ordered per energy descending
	 * @param goalModel
	 * @param globalObject
	 */
	private static void wfpEnergyAnalysis(GoalModel goalModel, JSONObject globalObject){
		//First I group all the wfp in the model in a single array
		ArrayList<GmWfp> totalWfps = new ArrayList<GmWfp>();
		for (GmWfp wfp : goalModel.getWfpArray()) {
			totalWfps.add(wfp);
		}
				
		//Ordering wfp by energy descending
		Collections.sort(totalWfps, (a1, a2) -> Float.compare(a2.getEnergyConsumption(), a1.getEnergyConsumption()));
		
		//creating jsonArray for wfp
		JSONArray jsonArray = new JSONArray();
		
		for(GmWfp wfp : totalWfps) {
			//Creating a JSONObject for single wfp
		    JSONObject jsonObject = new JSONObject();
		    
		    //Inserting key-value pairs into the json object
		    jsonObject.put("id", wfp.getId());
		    jsonObject.put("description", wfp.getDescription());
		    jsonObject.put("energy", wfp.getEnergyConsumption());
		    
		    //adding the object to the array
		    jsonArray.add(jsonObject);
		}
		
		//we add the array to the global object
		globalObject.put("wfpEnergyDescending", jsonArray);
			
	}
	
	/**
	 * This method inserts the analysis of assets ordered per energy descending
	 * @param goalModel
	 * @param globalObject
	 */
	private static void assetEnergyAnalysis(GoalModel goalModel, JSONObject globalObject){
		//First I group all the assets in the model in a single array
		ArrayList<GmAsset> totalAssets = new ArrayList<GmAsset>();
		for (GmAsset asset : goalModel.getAssetArray()) {
			totalAssets.add(asset);
		}
				
		//Ordering assets by energy descending
		Collections.sort(totalAssets, (a1, a2) -> Float.compare(a2.getEnergyConsumption(), a1.getEnergyConsumption()));
		
		//creating jsonArray for asset
		JSONArray jsonArray = new JSONArray();
		
		for(GmAsset asset : totalAssets) {
			//Creating a JSONObject for single asset
		    JSONObject jsonObject = new JSONObject();
		    
		    //Inserting key-value pairs into the json object
		    jsonObject.put("id", asset.getId());
		    jsonObject.put("description", asset.getDescription());
		    jsonObject.put("energy", asset.getEnergyConsumption());
		    
		    //adding the object to the array
		    jsonArray.add(jsonObject);
		}
		
		//we add the array to the global object
		globalObject.put("assetEnergyDescending", jsonArray);
			
	}
	
	/**
	 * This method inserts the analysis of assets ordered per risk descending
	 * @param goalModel
	 * @param globalObject
	 */
	private static void assetRiskAnalysis(GoalModel goalModel, JSONObject globalObject){
		//First I group all the assets in the model in a single array
		ArrayList<GmAsset> totalAssets = new ArrayList<GmAsset>();
		for (GmAsset asset : goalModel.getAssetArray()) {
			totalAssets.add(asset);
		}
				
		//Ordering assets by residual risk descending
		Collections.sort(totalAssets, (a1, a2) -> Float.compare(a2.getResidualRisk(), a1.getResidualRisk()));
		
		//creating jsonArray for asset
		JSONArray jsonArray = new JSONArray();
		
		for(GmAsset asset : totalAssets) {
			//Creating a JSONObject for single asset
		    JSONObject jsonObject = new JSONObject();
		    
		    //Inserting key-value pairs into the json object
		    jsonObject.put("id", asset.getId());
		    jsonObject.put("description", asset.getDescription());
		    jsonObject.put("residual_risk", asset.getResidualRisk());
		    
		    //adding the object to the array
		    jsonArray.add(jsonObject);
		}
		
		//we add the array to the global object
		globalObject.put("assetRiskDescending", jsonArray);
			
	}
	
	/**
	 * This method pretty prints a json string into a well-formatted string that is more human-readable
	 * @param ugly
	 * @return
	 */
	private static String prettyPrint (String ugly) {
		//creates Gson instance with pretty print feature
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		return gson.toJson(JsonParser.parseString(ugly));
	}
	
}
