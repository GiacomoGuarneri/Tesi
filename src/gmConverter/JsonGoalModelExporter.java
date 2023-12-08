package gmConverter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import model.*;

/**
 * This class exports the goal model enriched with the information on energy and risk of goals
 */
public class JsonGoalModelExporter {
	/**
	 * This method starts the json exporter of the goal model
	 * @param goalModel
	 */
	public void start(GoalModel goalModel){
		
		// filepath of where I want to write
		String filePath = "Goal models exports/demo.json";
		
		// filepath of original goal model
		String filePathOriginal = "Goal models/demo.json";
		
		try (FileWriter file = new FileWriter(filePath)) {
			
			//Creating a global JSONObject
		    JSONObject globalObject = new JSONObject();
		    
		    //wfp
			wfpRoutine(goalModel, file, globalObject);
			
			//security measures
			smRoutine(goalModel, file, globalObject);
			
			//assets
			assetsRoutine(goalModel, file, globalObject);
			
			//actors and goals
			actorsRoutine(goalModel, file, globalObject);
			
			//relationships
			relationshipRoutine(goalModel, file, globalObject);
			
			//AND links
			try {
				ANDRoutine(goalModel, file, globalObject, filePathOriginal);
			} catch (ParseException e) {
				//Auto-generated catch block
				e.printStackTrace();
			}
			
			// at the end we write and close the file
			file.write(prettyPrint(globalObject.toJSONString()));
			file.close();
			
			System.out.println("Goal model has been exported to location: " + filePath);
			System.out.println();
			
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This method writes all the wfp into the json global object
	 * @param goalModel
	 * @param file
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 * @throws IOException
	 */
	private static void wfpRoutine(GoalModel goalModel, FileWriter file, JSONObject globalObject){
		ArrayList<GmWfp> wfpArray = goalModel.getWfpArray();
		
		//creating jsonArray for wfp
		JSONArray jsonArray = new JSONArray();
		
		for(GmWfp wfp : wfpArray) {
			//Creating a JSONObject for single wfp
		    JSONObject jsonObject = new JSONObject();
		    
		    //Inserting key-value pairs into the json object
		    jsonObject.put("id", wfp.getId());
		    jsonObject.put("description", wfp.getDescription());
		    jsonObject.put("energy", wfp.getEnergyConsumption());
		    jsonObject.put("risk_mitigation_impact", wfp.getRiskMitigationImpact());
		    
		    //adding the object to the array
		    jsonArray.add(jsonObject);
		}
		
		//we add the array to the global object
		globalObject.put("wfp", jsonArray);
	}
	
	/**
	 * This method writes all the security measures into the json global object
	 * @param goalModel
	 * @param file
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 * @throws IOException
	 */
	private static void smRoutine(GoalModel goalModel, FileWriter file, JSONObject globalObject){
		ArrayList<GmSecurityMeasure> smArray = goalModel.getSmArray();
		
		//creating jsonArray for sm
		JSONArray jsonArray = new JSONArray();
		
		for(GmSecurityMeasure sm : smArray) {
			//Creating a JSONObject for single sm
		    JSONObject jsonObject = new JSONObject();
		    
		    //Inserting key-value pairs into the json object
		    jsonObject.put("id", sm.getId());
		    jsonObject.put("description", sm.getDescription());
		    jsonObject.put("energy", sm.getEnergyConsumption());
		    jsonObject.put("risk_mitigation_impact", sm.getRiskMitigationImpact());
		    
		    //adding the object to the array
		    jsonArray.add(jsonObject);
		}
		
		//we add the array to the global object
		globalObject.put("sm", jsonArray);
	}
	
	/**
	 * This method writes all the assets into the json global object
	 * @param goalModel
	 * @param file
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 * @throws IOException
	 */
	private static void assetsRoutine(GoalModel goalModel, FileWriter file, JSONObject globalObject){
		ArrayList<GmAsset> assetsArray = goalModel.getAssetArray();
		
		//creating jsonArray for assets
		JSONArray jsonArray = new JSONArray();
		
		for(GmAsset asset : assetsArray) {
			//Creating a JSONObject for single asset
		    JSONObject jsonObject = new JSONObject();
		    
		    //Inserting key-value pairs into the json object
		    jsonObject.put("id", asset.getId());
		    jsonObject.put("description", asset.getDescription());
		    jsonObject.put("energy", asset.getEnergyConsumption());
		    jsonObject.put("inherent_risk", asset.getInherentRisk());
		    jsonObject.put("residual_risk", asset.getResidualRisk());
		    
		    //adding the object to the array
		    jsonArray.add(jsonObject);
		}
		
		//we add the array to the global object
		globalObject.put("asset", jsonArray);
	}
	
	/**
	 * This method writes all the actors and goals into the json global object
	 * @param goalModel
	 * @param file
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 * @throws IOException
	 */
	private static void actorsRoutine(GoalModel goalModel, FileWriter file, JSONObject globalObject){
		ArrayList<GmActor> actorsArray = goalModel.getActorsArray();
		
		//creating jsonArray for actors
		JSONArray jsonActorsArray = new JSONArray();
		
		for(GmActor actor : actorsArray) {
			//Creating a JSONObject for single actor
		    JSONObject jsonActorObject = new JSONObject();
		    
		    //Inserting key-value pairs into the json object
		    jsonActorObject.put("id", actor.getId());
		    jsonActorObject.put("description", actor.getDescription());
		    jsonActorObject.put("energy", actor.getEnergyConsumption());
		    
		    //creating jsonArray for goals
			JSONArray jsonGoalsArray = new JSONArray();
		    
		    for (GmGoal goal : actor.getGoals()) {
		    	//Creating a JSONObject for single goal
			    JSONObject jsonGoal = new JSONObject();
			    
			    //Inserting key-value pairs into the json object
			    jsonGoal.put("id", goal.getId());
			    jsonGoal.put("description", goal.getDescription());
			    jsonGoal.put("energy", goal.getEnergyConsumption());
			    jsonGoal.put("residual_risk", goal.getResidualRisk());
			    
			    jsonGoalsArray.add(jsonGoal);
		    }
		    
		    //we add the goals array to the actor object
			jsonActorObject.put("goals", jsonGoalsArray);
		    
		    //adding the object to the array
		    jsonActorsArray.add(jsonActorObject);
		}
		
		//we add the array to the global object
		globalObject.put("actors", jsonActorsArray);
	}
	
	/**
	 * This method writes all the relationships into the json global object
	 * @param goalModel
	 * @param file
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 * @throws IOException
	 */
	private static void relationshipRoutine(GoalModel goalModel, FileWriter file, JSONObject globalObject){
		ArrayList<GmRelationship> relationshipArray = goalModel.getRelationshipsArray();
		
		//creating jsonArray for relationships
		JSONArray jsonArray = new JSONArray();
		
		for(GmRelationship rel : relationshipArray) {
			//Creating a JSONObject for single relationship
		    JSONObject jsonObject = new JSONObject();
		    
		    //Inserting key-value pairs into the json object
		    jsonObject.put("id", rel.getId());
		    jsonObject.put("source", rel.getSource_id());
		    jsonObject.put("target", rel.getTarget_id());
		    
		    if (rel instanceof GmPartOfRelationship)
		    	jsonObject.put("type", "part-of");
		    else if (rel instanceof GmProtectRelationship)
		    	jsonObject.put("type", "protect");
		    else if (rel instanceof GmEnforceRelationship)
		    	jsonObject.put("type", "enforce");
		    else if (rel instanceof GmIsARelationship)
		    	jsonObject.put("type", "is-a");
		    else if (rel instanceof GmDelegationRelationship)
		    	jsonObject.put("type", "delegation");
		    
		    //adding the object to the array
		    jsonArray.add(jsonObject);
		}
		
		//we add the array to the global object
		globalObject.put("relationships", jsonArray);
	}
	
	/**
	 * This method writes all the AND links into the json global object
	 * @param goalModel
	 * @param file
	 * @throws ParseException 
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 * @throws IOException
	 */
	private static void ANDRoutine(GoalModel goalModel, FileWriter file, JSONObject globalObject, String filePathOriginal) throws IOException, ParseException{

		//creating jsonArray for ANDLinks
		JSONArray jsonArray = new JSONArray();
		
		//JSON parser object to parse read file
    	JSONParser jsonParser = new JSONParser();
    	FileReader reader = new FileReader(filePathOriginal);
    	//Read JSON file
		Object obj = jsonParser.parse(reader);
		JSONObject jsonObj = (JSONObject) obj;
		// Gets links jsonArray: it contains ANDlinks among goals
		JSONArray links = new JSONArray();
		links = (JSONArray) jsonObj.get("links");
		// Gets info on each link
		for (int i=0; i < links.size(); i++) {
			//REMEMBER that in iStar when we specify an AND-link the target is the parent in the tree structure and the source is the child
			
			JSONObject andLink = new JSONObject();
			andLink = (JSONObject) links.get(i);
			//Get link id
	        String linkId = (String) andLink.get("id");
			//Get link source
	        String linkSource = (String) andLink.get("source");  
	        //Get link target
	        String linkTarget = (String) andLink.get("target");  
	        
	        //we copy those info into the new object
	        
	        //Creating a JSONObject for single link
		    JSONObject newLink = new JSONObject();
		    //Inserting key-value pairs into the new json object
		    newLink.put("id", linkId);
		    newLink.put("source", linkSource);
		    newLink.put("target", linkTarget);
		    //adding the object to the array
		    jsonArray.add(newLink);
		}
		
		//we add the array to the global object
		globalObject.put("and_links", jsonArray);
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